package model;

/**
 * Representa una dirección física asociada a una persona vinculada a Llanquihue Tour.
 */

public class Direccion {

    private String calle;
    private int numero;
    private String comuna;
    private String region;

    public Direccion(String calle, int numero, String comuna, String region){
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.region = region;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String toString() {
        return "  Calle: " + calle + "\n" +
                "  Número: " + numero + "\n" +
                "  Comuna: " + comuna + "\n" +
                "  Región: " + region;
    }
}
