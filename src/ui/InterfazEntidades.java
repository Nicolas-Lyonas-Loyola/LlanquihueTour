package ui;

import data.GestorEntidades;
import model.Direccion;
import model.GuiaTuristico;
import model.Vehiculo;

import javax.swing.JOptionPane;

public class InterfazEntidades {

    private final GestorEntidades gestorEntidades;

    public InterfazEntidades() {
        gestorEntidades = new GestorEntidades();
    }

    public void iniciar() {

        int opcion = -1;

        while (opcion != 0) {

            String menu =
                    "LLANQUIHUE TOUR\n\n"
                            + "1. Registrar guía turístico\n"
                            + "2. Registrar vehículo\n"
                            + "3. Mostrar entidades registradas\n"
                            + "0. Salir\n\n"
                            + "Seleccione una opción:";

            String entrada = JOptionPane.showInputDialog(
                    null,
                    menu,
                    "Gestión de entidades",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (entrada == null) {
                opcion = 0;
                continue;
            }

            try {
                opcion = Integer.parseInt(entrada.trim());

                switch (opcion) {

                    case 1:
                        registrarGuiaTuristico();
                        break;

                    case 2:
                        registrarVehiculo();
                        break;

                    case 3:
                        mostrarEntidades();
                        break;

                    case 0:
                        JOptionPane.showMessageDialog(
                                null,
                                "Volviendo al menú principal.",
                                "Llanquihue Tour",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "La opción ingresada no es válida.",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE
                        );
                }

            } catch (NumberFormatException error) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debe ingresar una opción numérica.",
                        "Dato incorrecto",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void registrarGuiaTuristico() {

        try {
            String nombre = solicitarTexto("Nombre del guía:");
            if (nombre == null) {
                return;
            }

            String rut = solicitarTexto("RUT del guía:");
            if (rut == null) {
                return;
            }

            String correo = solicitarTexto("Correo electrónico:");
            if (correo == null) {
                return;
            }

            String telefono = solicitarTexto("Teléfono:");
            if (telefono == null) {
                return;
            }

            String calle = solicitarTexto("Calle:");
            if (calle == null) {
                return;
            }

            Integer numero = solicitarEntero("Número de la dirección:");
            if (numero == null) {
                return;
            }

            String comuna = solicitarTexto("Comuna:");
            if (comuna == null) {
                return;
            }

            String region = solicitarTexto("Región:");
            if (region == null) {
                return;
            }

            String especialidad = solicitarTexto(
                    "Especialidad del guía:"
            );

            if (especialidad == null) {
                return;
            }

            String idioma = solicitarTexto(
                    "Idioma que domina:"
            );

            if (idioma == null) {
                return;
            }

            Direccion direccion = new Direccion(
                    calle,
                    numero,
                    comuna,
                    region
            );

            GuiaTuristico guia = new GuiaTuristico(
                    nombre,
                    rut,
                    correo,
                    telefono,
                    direccion,
                    especialidad,
                    true,
                    idioma
            );

            gestorEntidades.agregarEntidad(guia);

            JOptionPane.showMessageDialog(
                    null,
                    "Guía registrado correctamente.\n\n"
                            + guia.mostrarResumen(),
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException error) {

            JOptionPane.showMessageDialog(
                    null,
                    error.getMessage(),
                    "No fue posible registrar el guía",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void registrarVehiculo() {

        try {
            String codigo = solicitarTexto(
                    "Código del vehículo:"
            );

            if (codigo == null) {
                return;
            }

            String nombre = solicitarTexto(
                    "Nombre o descripción del vehículo:"
            );

            if (nombre == null) {
                return;
            }

            String patente = solicitarTexto(
                    "Patente:"
            );

            if (patente == null) {
                return;
            }

            Integer capacidad = solicitarEntero(
                    "Capacidad de pasajeros:"
            );

            if (capacidad == null) {
                return;
            }

            String tipoVehiculo = solicitarTexto(
                    "Tipo de vehículo:"
            );

            if (tipoVehiculo == null) {
                return;
            }

            Vehiculo vehiculo = new Vehiculo(
                    codigo,
                    nombre,
                    patente,
                    capacidad,
                    tipoVehiculo
            );

            gestorEntidades.agregarEntidad(vehiculo);

            JOptionPane.showMessageDialog(
                    null,
                    "Vehículo registrado correctamente.\n\n"
                            + vehiculo.mostrarResumen(),
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException error) {

            JOptionPane.showMessageDialog(
                    null,
                    error.getMessage(),
                    "No fue posible registrar el vehículo",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarEntidades() {

        JOptionPane.showMessageDialog(
                null,
                gestorEntidades.mostrarTodosLosResumenes(),
                "Entidades registradas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private String solicitarTexto(String mensaje) {

        String valor = JOptionPane.showInputDialog(
                null,
                mensaje,
                "Ingreso de datos",
                JOptionPane.QUESTION_MESSAGE
        );

        if (valor == null) {
            return null;
        }

        return valor.trim();
    }

    private Integer solicitarEntero(String mensaje) {

        String valor = solicitarTexto(mensaje);

        if (valor == null) {
            return null;
        }

        return Integer.parseInt(valor);
    }

    public static void main(String[] args) {

        InterfazEntidades interfaz = new InterfazEntidades();
        interfaz.iniciar();
    }
}