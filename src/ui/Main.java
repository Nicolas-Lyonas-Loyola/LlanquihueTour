package ui;

import data.GestorColaboradores;

import java.util.Scanner;

/**
 * Clase principal de Llanquihue Tour.
 * Coordina la carga, visualización, búsqueda y filtrado
 * de los colaboradores turísticos.
 */
public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        GestorColaboradores gestor = new GestorColaboradores();

        // Carga inicial de colaboradores desde el archivo externo.
        gestor.cargarDesdeArchivo("resources/colaboradores.csv");

        int opcion = -1;

        do {
            mostrarMenu();

            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(teclado.nextLine().trim());

                switch (opcion) {

                    case 1:
                        System.out.println(
                                "\n========== TODOS LOS COLABORADORES =========="
                        );
                        gestor.mostrarTodos();
                        break;

                    case 2:
                        System.out.println(
                                "\n========== COLABORADORES ACTIVOS =========="
                        );
                        gestor.mostrarActivos();
                        break;

                    case 3:
                        System.out.print(
                                "Ingrese el tipo de colaborador "
                                        + "(Guía, Operador o Proveedor): "
                        );

                        String tipo = teclado.nextLine().trim();

                        System.out.println(
                                "\n========== BÚSQUEDA POR TIPO =========="
                        );
                        gestor.buscarPorTipo(tipo);
                        break;

                    case 4:
                        System.out.print(
                                "Ingrese la comuna que desea consultar: "
                        );

                        String comuna = teclado.nextLine().trim();

                        System.out.println(
                                "\n========== FILTRO POR COMUNA =========="
                        );
                        gestor.filtrarPorComuna(comuna);
                        break;

                    case 5:
                        System.out.println(
                                "\nCantidad total de colaboradores: "
                                        + gestor.obtenerCantidadColaboradores()
                        );
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
            }

        } while (opcion != 0);

        teclado.close();
    }

    /**
     * Muestra las opciones disponibles en la aplicación.
     */
    private static void mostrarMenu() {

        System.out.println("\n==========================================");
        System.out.println("         LLANQUIHUE TOUR APP");
        System.out.println("==========================================");
        System.out.println("1. Mostrar todos los colaboradores");
        System.out.println("2. Mostrar colaboradores activos");
        System.out.println("3. Buscar colaboradores por tipo");
        System.out.println("4. Filtrar colaboradores por comuna");
        System.out.println("5. Mostrar cantidad de colaboradores");
        System.out.println("0. Salir");
        System.out.println("==========================================");
    }
}