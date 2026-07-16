package ui;

import data.GestorColaboradores;
import data.GestorReservas;
import model.Cliente;
import model.Registrable;
import model.Reserva;
import model.Tour;
import util.DatoInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Clase principal de Llanquihue Tour.
 *
 * Coordina la gestión de colaboradores, clientes,
 * tours y reservas del sistema.
 */
public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        GestorColaboradores gestorColaboradores =
                new GestorColaboradores();

        GestorReservas gestorReservas =
                new GestorReservas();

        InterfazEntidades interfazEntidades =
                new InterfazEntidades();

        /*
         * Carga inicial de datos desde archivos TXT.
         */
        try {
            gestorColaboradores.cargarDesdeArchivo(
                    "resources/colaboradores.txt"
            );

            int clientesCargados =
                    gestorReservas.cargarClientesDesdeArchivo(
                            "resources/clientes.txt"
                    );

            int toursCargados =
                    gestorReservas.cargarToursDesdeArchivo(
                            "resources/tours.txt"
                    );

            System.out.println(
                    "\nCarga inicial realizada correctamente."
            );

            System.out.println(
                    "Clientes cargados: " + clientesCargados
            );

            System.out.println(
                    "Tours cargados: " + toursCargados
            );

        } catch (IllegalStateException error) {
            System.out.println(
                    "\nNo fue posible realizar la carga inicial."
            );

            System.out.println(
                    "Motivo: " + error.getMessage()
            );

            teclado.close();
            return;
        }

        int opcion = -1;

        do {
            mostrarMenu();

            try {
                System.out.print("Seleccione una opción: ");

                opcion = Integer.parseInt(
                        teclado.nextLine().trim()
                );

                switch (opcion) {

                    case 1:
                        System.out.println(
                                "\n========== TODOS LOS COLABORADORES =========="
                        );

                        gestorColaboradores.mostrarTodos();
                        break;

                    case 2:
                        System.out.println(
                                "\n========== COLABORADORES ACTIVOS =========="
                        );

                        gestorColaboradores.mostrarActivos();
                        break;

                    case 3:
                        buscarColaboradoresPorTipo(
                                teclado,
                                gestorColaboradores
                        );
                        break;

                    case 4:
                        filtrarColaboradoresPorComuna(
                                teclado,
                                gestorColaboradores
                        );
                        break;

                    case 5:
                        System.out.println(
                                "\nCantidad total de colaboradores: "
                                        + gestorColaboradores
                                        .obtenerCantidadColaboradores()
                        );
                        break;

                    case 6:
                        System.out.println(
                                "\nAbriendo gestión gráfica de entidades..."
                        );

                        interfazEntidades.iniciar();
                        break;

                    case 7:
                        mostrarClientes(gestorReservas);
                        break;

                    case 8:
                        mostrarTours(gestorReservas);
                        break;

                    case 9:
                        buscarClientePorRut(
                                teclado,
                                gestorReservas
                        );
                        break;

                    case 10:
                        filtrarToursPorTipo(
                                teclado,
                                gestorReservas
                        );
                        break;

                    case 11:
                        registrarReserva(
                                teclado,
                                gestorReservas
                        );
                        break;

                    case 12:
                        mostrarReservas(gestorReservas);
                        break;

                    case 13:
                        mostrarRegistrosPolimorficos(
                                gestorReservas
                        );
                        break;

                    case 14:
                        mostrarHistorial(gestorReservas);
                        break;

                    case 0:
                        System.out.println(
                                "\nPrograma finalizado correctamente."
                        );
                        break;

                    default:
                        System.out.println(
                                "\nOpción no válida. Intente nuevamente."
                        );
                }

            } catch (NumberFormatException error) {
                System.out.println(
                        "\nEntrada no válida. Debe ingresar un número."
                );

                opcion = -1;

            } catch (DatoInvalidoException error) {
                System.out.println(
                        "\nNo fue posible completar la operación."
                );

                System.out.println(
                        "Motivo: " + error.getMessage()
                );
            }

        } while (opcion != 0);

        teclado.close();
    }

    private static void buscarColaboradoresPorTipo(
            Scanner teclado,
            GestorColaboradores gestor) {

        System.out.print(
                "Ingrese el tipo de colaborador "
                        + "(Guía, Operador o Proveedor): "
        );

        String tipo = teclado.nextLine().trim();

        System.out.println(
                "\n========== BÚSQUEDA POR TIPO =========="
        );

        gestor.buscarPorTipo(tipo);
    }

    private static void filtrarColaboradoresPorComuna(
            Scanner teclado,
            GestorColaboradores gestor) {

        System.out.print(
                "Ingrese la comuna que desea consultar: "
        );

        String comuna = teclado.nextLine().trim();

        System.out.println(
                "\n========== FILTRO POR COMUNA =========="
        );

        gestor.filtrarPorComuna(comuna);
    }

    private static void mostrarClientes(
            GestorReservas gestorReservas) {

        List<Cliente> clientes =
                gestorReservas.getClientes();

        System.out.println(
                "\n========== CLIENTES =========="
        );

        if (clientes.isEmpty()) {
            System.out.println(
                    "No existen clientes cargados."
            );
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente.mostrarResumen());
        }

        System.out.println(
                "\nCantidad de clientes: " + clientes.size()
        );
    }

    private static void mostrarTours(
            GestorReservas gestorReservas) {

        List<Tour> tours =
                gestorReservas.getTours();

        System.out.println(
                "\n========== TOURS =========="
        );

        if (tours.isEmpty()) {
            System.out.println(
                    "No existen tours cargados."
            );
            return;
        }

        for (Tour tour : tours) {
            System.out.println(tour.mostrarResumen());
        }

        System.out.println(
                "\nCantidad de tours: " + tours.size()
        );
    }

    private static void buscarClientePorRut(
            Scanner teclado,
            GestorReservas gestorReservas) {

        System.out.print(
                "Ingrese el RUT del cliente: "
        );

        String rut = teclado.nextLine().trim();

        Cliente cliente =
                gestorReservas.buscarClientePorRut(rut);

        System.out.println(
                "\n========== RESULTADO DE BÚSQUEDA =========="
        );

        if (cliente == null) {
            System.out.println(
                    "No se encontró un cliente con ese RUT."
            );
            return;
        }

        System.out.println(cliente);
    }

    private static void filtrarToursPorTipo(
            Scanner teclado,
            GestorReservas gestorReservas) {

        System.out.print(
                "Ingrese el tipo de tour: "
        );

        String tipo = teclado.nextLine().trim();

        List<Tour> resultados =
                gestorReservas.filtrarToursPorTipo(tipo);

        System.out.println(
                "\n========== TOURS FILTRADOS =========="
        );

        if (resultados.isEmpty()) {
            System.out.println(
                    "No se encontraron tours de ese tipo."
            );
            return;
        }

        for (Tour tour : resultados) {
            System.out.println(tour.mostrarResumen());
        }
    }

    private static void registrarReserva(
            Scanner teclado,
            GestorReservas gestorReservas) {

        System.out.println(
                "\n========== REGISTRAR RESERVA =========="
        );

        System.out.print(
                "Ingrese el código de la reserva: "
        );

        String codigoReserva =
                teclado.nextLine().trim();

        System.out.print(
                "Ingrese el RUT del cliente: "
        );

        String rutCliente =
                teclado.nextLine().trim();

        System.out.print(
                "Ingrese el código del tour: "
        );

        String codigoTour =
                teclado.nextLine().trim();

        System.out.print(
                "Ingrese la cantidad de personas: "
        );

        int cantidadPersonas =
                Integer.parseInt(teclado.nextLine().trim());

        Reserva reserva =
                gestorReservas.registrarReserva(
                        codigoReserva,
                        rutCliente,
                        codigoTour,
                        cantidadPersonas
                );

        System.out.println(
                "\nReserva registrada correctamente."
        );

        System.out.println(reserva);
    }

    private static void mostrarReservas(
            GestorReservas gestorReservas) {

        List<Reserva> reservas =
                gestorReservas.getReservas();

        System.out.println(
                "\n========== RESERVAS =========="
        );

        if (reservas.isEmpty()) {
            System.out.println(
                    "No existen reservas registradas."
            );
            return;
        }

        for (Reserva reserva : reservas) {
            System.out.println(reserva.mostrarResumen());
        }

        System.out.println(
                "\nCantidad de reservas: " + reservas.size()
        );
    }

    /**
     * Demuestra polimorfismo almacenando objetos de
     * distintas clases en una colección común Registrable.
     *
     * También utiliza instanceof para identificar
     * el tipo real de cada objeto.
     */
    private static void mostrarRegistrosPolimorficos(
            GestorReservas gestorReservas) {

        List<Registrable> registros =
                new ArrayList<>();

        registros.addAll(
                gestorReservas.getClientes()
        );

        registros.addAll(
                gestorReservas.getTours()
        );

        registros.addAll(
                gestorReservas.getReservas()
        );

        System.out.println(
                "\n========== REGISTROS POLIMÓRFICOS =========="
        );

        if (registros.isEmpty()) {
            System.out.println(
                    "No existen registros para mostrar."
            );
            return;
        }

        for (Registrable registro : registros) {

            /*
             * Polimorfismo: cada objeto ejecuta su propia
             * implementación de mostrarResumen().
             */
            System.out.println(registro.mostrarResumen());

            /*
             * instanceof identifica el tipo real del objeto.
             */
            if (registro instanceof Cliente) {
                System.out.println(
                        "Tipo detectado: Cliente"
                );

            } else if (registro instanceof Tour) {
                System.out.println(
                        "Tipo detectado: Tour"
                );

            } else if (registro instanceof Reserva) {
                System.out.println(
                        "Tipo detectado: Reserva"
                );
            }

            System.out.println("----------------------------------");
        }
    }

    private static void mostrarHistorial(
            GestorReservas gestorReservas) {

        Stack<String> historial =
                gestorReservas.getHistorialAcciones();

        System.out.println(
                "\n========== HISTORIAL DE ACCIONES =========="
        );

        if (historial.isEmpty()) {
            System.out.println(
                    "No existen acciones registradas."
            );
            return;
        }

        /*
         * Se muestra desde la acción más reciente
         * hasta la más antigua, respetando la lógica LIFO.
         */
        for (int posicion = historial.size() - 1;
             posicion >= 0;
             posicion--) {

            System.out.println(
                    "- " + historial.get(posicion)
            );
        }
    }

    /**
     * Muestra las opciones disponibles en la aplicación.
     */
    private static void mostrarMenu() {

        System.out.println(
                "\n=========================================="
        );

        System.out.println(
                "         LLANQUIHUE TOUR APP"
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "--- GESTIÓN DE COLABORADORES ---"
        );

        System.out.println(
                "1. Mostrar todos los colaboradores"
        );

        System.out.println(
                "2. Mostrar colaboradores activos"
        );

        System.out.println(
                "3. Buscar colaboradores por tipo"
        );

        System.out.println(
                "4. Filtrar colaboradores por comuna"
        );

        System.out.println(
                "5. Mostrar cantidad de colaboradores"
        );

        System.out.println(
                "6. Abrir gestión gráfica de entidades"
        );

        System.out.println(
                "\n--- GESTIÓN TURÍSTICA ---"
        );

        System.out.println(
                "7. Mostrar clientes"
        );

        System.out.println(
                "8. Mostrar tours"
        );

        System.out.println(
                "9. Buscar cliente por RUT"
        );

        System.out.println(
                "10. Filtrar tours por tipo"
        );

        System.out.println(
                "11. Registrar una reserva"
        );

        System.out.println(
                "12. Mostrar reservas"
        );

        System.out.println(
                "13. Mostrar registros polimórficos"
        );

        System.out.println(
                "14. Mostrar historial de acciones"
        );

        System.out.println(
                "0. Salir"
        );

        System.out.println(
                "=========================================="
        );
    }
}