package model;

/**
 * Clase base que representa los datos comunes de una persona vinculada con la agencia.
 */
public class Persona {
    private String nombre;
    private String rut;
    private String correo;
    private String telefono;
    private Direccion direccion;

    public Persona(String nombre, String rut, String correo, String telefono, Direccion direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Direccion getDireccion(){
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "RUT: " + rut + "\n" +
                "Correo: " + correo + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Dirección:\n" + direccion;
    }
}