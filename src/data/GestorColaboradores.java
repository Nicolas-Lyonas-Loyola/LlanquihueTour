package data;

import model.ColaboradorTuristico;
import model.Direccion;
import util.ValidadorDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;
import java.util.Locale;


/**
 * Administra la colección de colaboradores turísticos.
 * Se encarga de leer el archivo, crear objetos, almacenarlos,
 * recorrerlos, buscarlos y filtrarlos.
 */
public class GestorColaboradores {

    private final List<ColaboradorTuristico> colaboradores;

    public GestorColaboradores() {
        colaboradores = new ArrayList<>();
    }

    /**
     * Lee los colaboradores desde un archivo CSV.
     *
     * @param rutaArchivo ubicación del archivo colaboradores.txt
     */
    public void cargarDesdeArchivo(String rutaArchivo) {

        colaboradores.clear();

        try (BufferedReader lector =
                     new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {

                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    continue;
                }

                // El parámetro -1 permite conservar posibles campos vacíos.
                String[] campos = linea.split(";", -1);

                if (!ValidadorDatos.validarCampos(campos)) {
                    System.out.println(
                            "Línea " + numeroLinea + " omitida por datos inválidos."
                    );
                    System.out.println(
                            "Contenido de la línea: " + linea
                    );
                    System.out.println("----------------------------------------");
                    continue;
                }

                try {
                    String nombre = campos[0].trim();
                    String rut = campos[1].trim();
                    String correo = campos[2].trim();
                    String telefono = campos[3].trim();
                    String calle = campos[4].trim();
                    int numero = Integer.parseInt(campos[5].trim());
                    String comuna = campos[6].trim();
                    String region = campos[7].trim();
                    String tipoColaborador = campos[8].trim();
                    String especialidad = campos[9].trim();
                    boolean activo = Boolean.parseBoolean(campos[10].trim());

                    Direccion direccion = new Direccion(
                            calle,
                            numero,
                            comuna,
                            region
                    );

                    ColaboradorTuristico colaborador =
                            new ColaboradorTuristico(
                                    nombre,
                                    rut,
                                    correo,
                                    telefono,
                                    direccion,
                                    tipoColaborador,
                                    especialidad,
                                    activo
                            );

                    colaboradores.add(colaborador);

                } catch (IllegalArgumentException error) {
                    System.out.println(
                            "Línea " + numeroLinea
                                    + " omitida: " + error.getMessage()
                    );
                    System.out.println(
                            "Contenido de la línea: " + linea
                    );
                    System.out.println("----------------------------------------");
                }
            }

            System.out.println(
                    "Carga finalizada. Colaboradores registrados: "
                            + colaboradores.size()
            );

        } catch (IOException error) {
            System.out.println(
                    "Error al leer el archivo: " + error.getMessage()
            );

        } catch (NumberFormatException error) {
            System.out.println(
                    "Error al convertir un dato numérico: "
                            + error.getMessage()
            );
        }
    }

    /**
     * Muestra todos los colaboradores almacenados.
     */
    public void mostrarTodos() {

        if (colaboradores.isEmpty()) {
            System.out.println("No hay colaboradores cargados.");
            return;
        }

        for (ColaboradorTuristico colaborador : colaboradores) {
            System.out.println(colaborador);
            System.out.println();
        }
    }

    /**
     * Muestra solamente los colaboradores activos.
     */
    public void mostrarActivos() {

        boolean encontrado = false;

        for (ColaboradorTuristico colaborador : colaboradores) {

            if (colaborador.isActivo()) {
                System.out.println(colaborador);
                System.out.println();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron colaboradores activos.");
        }
    }

    /**
     * Busca colaboradores según su tipo:
     * Guía, Operador o Proveedor.
     *
     * @param tipoBuscado tipo que se desea buscar
     */
    public void buscarPorTipo(String tipoBuscado) {

        boolean encontrado = false;

        for (ColaboradorTuristico colaborador : colaboradores) {

            if (normalizarTexto(colaborador.getTipoColaborador())
                    .contains(normalizarTexto(tipoBuscado))) {

                System.out.println(colaborador);
                System.out.println();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(
                    "No se encontraron colaboradores del tipo: "
                            + tipoBuscado
            );
        }
    }

    /**
     * Filtra colaboradores según su comuna.
     *
     * @param comunaBuscada comuna que se desea consultar
     */
    public void filtrarPorComuna(String comunaBuscada) {

        boolean encontrado = false;

        for (ColaboradorTuristico colaborador : colaboradores) {

            if (normalizarTexto(colaborador.getDireccion().getComuna())
                    .contains(normalizarTexto(comunaBuscada))) {

                System.out.println(colaborador);
                System.out.println();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(
                    "No se encontraron colaboradores en la comuna: "
                            + comunaBuscada
            );
        }
    }

    public int obtenerCantidadColaboradores() {
        return colaboradores.size();
    }
    /**
     * Convierte un texto a minúsculas y elimina tildes.
     * Ejemplo: "Guía" se transforma en "guia".
     */
    private String normalizarTexto(String texto) {

        String textoNormalizado = Normalizer.normalize(
                texto,
                Normalizer.Form.NFD
        );

        return textoNormalizado
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .trim();
    }

}