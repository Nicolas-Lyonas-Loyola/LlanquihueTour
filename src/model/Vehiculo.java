package model;

public class Vehiculo implements Registrable {

    private String codigo;
    private String nombre;
    private String patente;
    private int capacidadPasajeros;
    private String tipoVehiculo;

    public Vehiculo(
            String codigo,
            String nombre,
            String patente,
            int capacidadPasajeros,
            String tipoVehiculo
    ) {
        setCodigo(codigo);
        setNombre(nombre);
        setPatente(patente);
        setCapacidadPasajeros(capacidadPasajeros);
        setTipoVehiculo(tipoVehiculo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El código del vehículo no puede estar vacío."
            );
        }

        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del vehículo no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente == null || patente.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La patente no puede estar vacía."
            );
        }

        this.patente = patente.trim().toUpperCase();
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        if (capacidadPasajeros <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero."
            );
        }

        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        if (tipoVehiculo == null || tipoVehiculo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo de vehículo no puede estar vacío."
            );
        }

        this.tipoVehiculo = tipoVehiculo.trim();
    }

    @Override
    public String mostrarResumen() {
        return "Vehículo: "
                + nombre
                + " | Código: "
                + codigo
                + " | Patente: "
                + patente
                + " | Capacidad: "
                + capacidadPasajeros
                + " pasajeros"
                + " | Tipo: "
                + tipoVehiculo;
    }
}