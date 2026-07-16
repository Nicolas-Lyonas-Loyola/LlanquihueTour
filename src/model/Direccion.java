package model;

import util.DatoInvalidoException;

/**
 * Representa una dirección física asociada a una persona vinculada a Llanquihue Tour.
 */

public class Direccion {

    private String calle;
    private int numero;
    private String comuna;
    private String region;

    public Direccion(String calle, int numero, String comuna, String region) {
        setCalle(calle);
        setNumero(numero);
        setComuna(comuna);
        setRegion(region);
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        if (calle == null || calle.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "La calle no puede estar vacía."
            );
        }

        this.calle = calle.trim();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new DatoInvalidoException(
                    "El número de dirección debe ser mayor que cero."
            );
        }

        this.numero = numero;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        if (comuna == null || comuna.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "La comuna no puede estar vacía."
            );
        }

        this.comuna = comuna.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region == null || region.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "La región no puede estar vacía."
            );
        }

        this.region = region.trim();
    }

    public String toString() {
        return "  Calle: " + calle + "\n" +
                "  Número: " + numero + "\n" +
                "  Comuna: " + comuna + "\n" +
                "  Región: " + region;
    }
}
