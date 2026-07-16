package util;

/**
 * Excepción personalizada para informar datos inválidos
 * durante la creación o modificación de objetos.
 */
public class DatoInvalidoException extends IllegalArgumentException {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}