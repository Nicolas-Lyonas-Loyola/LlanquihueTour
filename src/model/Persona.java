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

    public Persona(String nombre, String rut, String correo,
                   String telefono, Direccion direccion) {

        setNombre(nombre);
        setRut(rut);
        setCorreo(correo);
        setTelefono(telefono);
        setDireccion(direccion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if (rut == null || rut.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El RUT no puede estar vacío."
            );
        }

        this.rut = rut.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null
                || correo.trim().isEmpty()
                || !correo.contains("@")) {

            throw new IllegalArgumentException(
                    "El correo electrónico no es válido."
            );
        }

        this.correo = correo.trim();
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El teléfono no puede estar vacío."
            );
        }

        this.telefono = telefono.trim();
    }
    public Direccion getDireccion(){
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException(
                    "La dirección no puede ser nula."
            );
        }

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