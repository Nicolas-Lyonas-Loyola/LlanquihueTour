package model;

/**
 * Representa a un cliente de la agencia Llanquihue Tour.
 */
public class Cliente extends Persona {

    private String preferenciaTour;
    private int cantidadReservas;

    public Cliente(String nombre, String rut, String correo, String telefono, Direccion direccion,
                   String preferenciaTour, int cantidadReservas) {
        super(nombre, rut, correo, telefono, direccion);
        this.preferenciaTour = preferenciaTour;
        this.cantidadReservas = cantidadReservas;
    }

    public String getPreferenciaTour() {
        return preferenciaTour;
    }

    public void setPreferenciaTour(String preferenciaTour) {
        this.preferenciaTour = preferenciaTour;
    }

    public int getCantidadReservas() {
        return cantidadReservas;
    }

    public void setCantidadReservas(int cantidadReservas) {
        this.cantidadReservas = cantidadReservas;
    }

    public String toString() {
        return "========== CLIENTE ==========\n" +
                super.toString() + "\n" +
                "Preferencia de tour: " + preferenciaTour + "\n" +
                "Cantidad de reservas: " + cantidadReservas;
    }
}
