package model;

import util.DatoInvalidoException;

/**
 * Representa una experiencia turística ofrecida
 * por la agencia Llanquihue Tour.
 */
public class Tour implements Registrable {

    private String codigo;
    private String nombre;
    private String tipo;
    private int duracionHoras;
    private double precio;
    private boolean activo;

    public Tour(String codigo,
                String nombre,
                String tipo,
                int duracionHoras,
                double precio,
                boolean activo) {

        setCodigo(codigo);
        setNombre(nombre);
        setTipo(tipo);
        setDuracionHoras(duracionHoras);
        setPrecio(precio);
        setActivo(activo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El código del tour no puede estar vacío."
            );
        }

        this.codigo = codigo.trim().toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El nombre del tour no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El tipo de tour no puede estar vacío."
            );
        }

        this.tipo = tipo.trim();
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        if (duracionHoras <= 0) {
            throw new DatoInvalidoException(
                    "La duración del tour debe ser mayor que cero."
            );
        }

        this.duracionHoras = duracionHoras;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new DatoInvalidoException(
                    "El precio del tour debe ser mayor que cero."
            );
        }

        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String mostrarResumen() {
        return "Tour | Código: " + codigo
                + " | Nombre: " + nombre
                + " | Tipo: " + tipo
                + " | Duración: " + duracionHoras + " horas"
                + " | Precio: $" + String.format("%.0f", precio)
                + " | Activo: " + (activo ? "Sí" : "No");
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\n"
                + "Nombre: " + nombre + "\n"
                + "Tipo: " + tipo + "\n"
                + "Duración: " + duracionHoras + " horas\n"
                + "Precio: $" + String.format("%.0f", precio) + "\n"
                + "Activo: " + (activo ? "Sí" : "No");
    }
}