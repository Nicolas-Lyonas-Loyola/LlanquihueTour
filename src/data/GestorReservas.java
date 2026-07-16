package data;

import model.Cliente;
import model.Direccion;
import model.Reserva;
import model.Tour;
import util.DatoInvalidoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

/**
 * Gestiona los clientes, tours y reservas de Llanquihue Tour.
 *
 * Utiliza:
 * - ArrayList para almacenar objetos.
 * - HashMap para realizar búsquedas rápidas.
 * - Stack para registrar el historial de acciones.
 * - Archivos TXT como fuente externa de datos.
 */
public class GestorReservas {

    private final List<Cliente> clientes;
    private final List<Tour> tours;
    private final List<Reserva> reservas;

    private final Map<String, Cliente> clientesPorRut;
    private final Map<String, Tour> toursPorCodigo;

    private final Stack<String> historialAcciones;

    public GestorReservas() {
        clientes = new ArrayList<>();
        tours = new ArrayList<>();
        reservas = new ArrayList<>();

        clientesPorRut = new HashMap<>();
        toursPorCodigo = new HashMap<>();

        historialAcciones = new Stack<>();
    }

    /**
     * Lee clientes desde un archivo TXT y los convierte
     * en objetos Cliente.
     *
     * @param rutaArchivo ruta del archivo clientes.txt
     * @return cantidad de clientes cargados correctamente
     */
    public int cargarClientesDesdeArchivo(String rutaArchivo) {
        clientes.clear();
        clientesPorRut.clear();

        int cargados = 0;
        int numeroLinea = 0;

        Path ruta = Path.of(rutaArchivo);

        try (BufferedReader lector = Files.newBufferedReader(
                ruta,
                StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                numeroLinea++;

                if (linea.isBlank()) {
                    continue;
                }

                try {
                    String[] datos = linea.split(";", -1);

                    if (datos.length != 10) {
                        throw new DatoInvalidoException(
                                "Se esperaban 10 datos y se encontraron "
                                        + datos.length + "."
                        );
                    }

                    String nombre = datos[0].trim();
                    String rut = datos[1].trim();
                    String correo = datos[2].trim();
                    String telefono = datos[3].trim();
                    String calle = datos[4].trim();
                    int numero = Integer.parseInt(datos[5].trim());
                    String comuna = datos[6].trim();
                    String region = datos[7].trim();
                    String tipoCliente = datos[8].trim();
                    String preferencia = datos[9].trim();

                    Direccion direccion = new Direccion(
                            calle,
                            numero,
                            comuna,
                            region
                    );

                    Cliente cliente = new Cliente(
                            nombre,
                            rut,
                            correo,
                            telefono,
                            direccion,
                            tipoCliente,
                            preferencia
                    );

                    clientes.add(cliente);

                    clientesPorRut.put(
                            normalizarClave(cliente.getRut()),
                            cliente
                    );

                    cargados++;

                } catch (IllegalArgumentException e) {
                    System.err.println(
                            "Cliente inválido en la línea "
                                    + numeroLinea + ": " + linea
                    );

                    System.err.println(
                            "Motivo: " + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo leer el archivo de clientes: "
                            + rutaArchivo,
                    e
            );
        }

        registrarAccion(
                "Se cargaron " + cargados
                        + " clientes desde " + rutaArchivo
        );

        return cargados;
    }

    /**
     * Lee tours desde un archivo TXT y los convierte
     * en objetos Tour.
     *
     * @param rutaArchivo ruta del archivo tours.txt
     * @return cantidad de tours cargados correctamente
     */
    public int cargarToursDesdeArchivo(String rutaArchivo) {
        tours.clear();
        toursPorCodigo.clear();

        int cargados = 0;
        int numeroLinea = 0;

        Path ruta = Path.of(rutaArchivo);

        try (BufferedReader lector = Files.newBufferedReader(
                ruta,
                StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                numeroLinea++;

                if (linea.isBlank()) {
                    continue;
                }

                try {
                    String[] datos = linea.split(";", -1);

                    if (datos.length != 6) {
                        throw new DatoInvalidoException(
                                "Se esperaban 6 datos y se encontraron "
                                        + datos.length + "."
                        );
                    }

                    String codigo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String tipo = datos[2].trim();

                    int duracionHoras =
                            Integer.parseInt(datos[3].trim());

                    double precio =
                            Double.parseDouble(datos[4].trim());

                    boolean activo =
                            convertirBooleano(datos[5].trim());

                    Tour tour = new Tour(
                            codigo,
                            nombre,
                            tipo,
                            duracionHoras,
                            precio,
                            activo
                    );

                    tours.add(tour);

                    toursPorCodigo.put(
                            normalizarClave(tour.getCodigo()),
                            tour
                    );

                    cargados++;

                } catch (IllegalArgumentException e) {
                    System.err.println(
                            "Tour inválido en la línea "
                                    + numeroLinea + ": " + linea
                    );

                    System.err.println(
                            "Motivo: " + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo leer el archivo de tours: "
                            + rutaArchivo,
                    e
            );
        }

        registrarAccion(
                "Se cargaron " + cargados
                        + " tours desde " + rutaArchivo
        );

        return cargados;
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Tour> getTours() {
        return new ArrayList<>(tours);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    public Stack<String> getHistorialAcciones() {
        Stack<String> copia = new Stack<>();
        copia.addAll(historialAcciones);
        return copia;
    }

    public Cliente buscarClientePorRut(String rut) {
        Cliente cliente = clientesPorRut.get(
                normalizarClave(rut)
        );

        if (cliente != null) {
            registrarAccion(
                    "Se buscó al cliente con RUT "
                            + cliente.getRut()
            );
        }

        return cliente;
    }

    public Tour buscarTourPorCodigo(String codigo) {
        Tour tour = toursPorCodigo.get(
                normalizarClave(codigo)
        );

        if (tour != null) {
            registrarAccion(
                    "Se buscó el tour con código "
                            + tour.getCodigo()
            );
        }

        return tour;
    }

    public List<Tour> filtrarToursPorTipo(String tipoBuscado) {
        List<Tour> resultados = new ArrayList<>();

        String criterio = normalizarTexto(tipoBuscado);

        if (criterio.isEmpty()) {
            return resultados;
        }

        for (Tour tour : tours) {
            String tipoTour = normalizarTexto(
                    tour.getTipo()
            );

            if (tipoTour.contains(criterio)) {
                resultados.add(tour);
            }
        }

        registrarAccion(
                "Se filtraron tours por tipo: "
                        + tipoBuscado
        );

        return resultados;
    }

    public Reserva registrarReserva(String codigoReserva,
                                    String rutCliente,
                                    String codigoTour,
                                    int cantidadPersonas) {

        Cliente cliente = buscarClientePorRut(rutCliente);

        if (cliente == null) {
            throw new DatoInvalidoException(
                    "No existe un cliente con el RUT indicado."
            );
        }

        Tour tour = buscarTourPorCodigo(codigoTour);

        if (tour == null) {
            throw new DatoInvalidoException(
                    "No existe un tour con el código indicado."
            );
        }

        if (!tour.isActivo()) {
            throw new DatoInvalidoException(
                    "El tour seleccionado no se encuentra activo."
            );
        }

        for (Reserva reserva : reservas) {
            if (normalizarClave(reserva.getCodigo())
                    .equals(normalizarClave(codigoReserva))) {

                throw new DatoInvalidoException(
                        "Ya existe una reserva con ese código."
                );
            }
        }

        Reserva nuevaReserva = new Reserva(
                codigoReserva,
                cliente,
                tour,
                cantidadPersonas,
                "Confirmada"
        );

        reservas.add(nuevaReserva);

        registrarAccion(
                "Se registró la reserva "
                        + nuevaReserva.getCodigo()
                        + " para el cliente "
                        + cliente.getNombre()
        );

        return nuevaReserva;
    }

    /**
     * Convierte únicamente los valores true o false.
     */
    private boolean convertirBooleano(String valor) {
        if ("true".equalsIgnoreCase(valor)) {
            return true;
        }

        if ("false".equalsIgnoreCase(valor)) {
            return false;
        }

        throw new DatoInvalidoException(
                "El estado activo debe ser true o false."
        );
    }

    /**
     * Normaliza RUT y códigos para usarlos como claves
     * dentro de los HashMap.
     */
    private String normalizarClave(String texto) {
        if (texto == null) {
            return "";
        }

        return texto
                .trim()
                .replace(".", "")
                .replace(" ", "")
                .toUpperCase(Locale.ROOT);
    }

    /**
     * Normaliza textos ignorando mayúsculas, minúsculas
     * y tildes.
     */
    private String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }

        String normalizado = Normalizer.normalize(
                texto,
                Normalizer.Form.NFD
        );

        return normalizado
                .replaceAll("\\p{M}", "")
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    private void registrarAccion(String accion) {
        historialAcciones.push(accion);
    }
}