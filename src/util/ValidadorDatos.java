package util;

/**
 * Clase de apoyo para validar los datos provenientes
 * del archivo colaboradores.csv.
 */
public class ValidadorDatos {

    /**
     * Constructor privado para evitar crear objetos de esta clase.
     * Sus métodos se utilizarán de forma estática.
     */
    private ValidadorDatos() {
    }

    /**
     * Valida que una línea separada del archivo tenga:
     * - exactamente 11 campos;
     * - ningún campo vacío;
     * - un número de dirección válido;
     * - un estado booleano válido.
     *
     * @param campos datos separados mediante split(";")
     * @return true si todos los datos son válidos
     */
    public static boolean validarCampos(String[] campos) {

        if (campos.length != 11) {
            System.out.println(
                    "Registro ignorado: se esperaban 11 campos, pero se encontraron "
                            + campos.length + "."
            );
            return false;
        }

        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                System.out.println(
                        "Registro ignorado: contiene uno o más campos vacíos."
                );
                return false;
            }
        }

        try {
            int numeroDireccion = Integer.parseInt(campos[5].trim());

            if (numeroDireccion <= 0) {
                System.out.println(
                        "Registro ignorado: el número de dirección debe ser mayor que cero."
                );
                return false;
            }

        } catch (NumberFormatException error) {
            System.out.println(
                    "Registro ignorado: el número de dirección no es válido."
            );
            return false;
        }

        String estado = campos[10].trim();

        if (!estado.equalsIgnoreCase("true")
                && !estado.equalsIgnoreCase("false")) {

            System.out.println(
                    "Registro ignorado: el campo activo debe contener true o false."
            );
            return false;
        }

        return true;
    }
}