package model;

/**
 * Representa a un empleado administrativo u operativo de Llanquihue Tour.
 */
public class Empleado extends Persona {

    private String cargo;
    private String areaTrabajo;

    public Empleado(String nombre, String rut, String correo, String telefono, Direccion direccion,
                    String cargo, String areaTrabajo) {
        super(nombre, rut, correo, telefono, direccion);
        this.cargo = cargo;
        this.areaTrabajo = areaTrabajo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public String toString() {
        return "========== EMPLEADO ==========\n" +
                super.toString() + "\n" +
                "Cargo: " + cargo + "\n" +
                "Área de trabajo: " + areaTrabajo;
    }
}