package model;

import util.DatoInvalidoException;

/**
 * Representa una reserva realizada por un cliente
 * para participar en un tour de Llanquihue Tour.
 *
 * Aplica composición porque contiene objetos
 * de tipo Cliente y Tour.
 */
public class Reserva implements Registrable {

    private String codigo;
    private Cliente cliente;
    private Tour tour;
    private int cantidadPersonas;
    private String estado;

    public Reserva(String codigo,
                   Cliente cliente,
                   Tour tour,
                   int cantidadPersonas,
                   String estado) {

        setCodigo(codigo);
        setCliente(cliente);
        setTour(tour);
        setCantidadPersonas(cantidadPersonas);
        setEstado(estado);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El código de la reserva no puede estar vacío."
            );
        }

        this.codigo = codigo.trim().toUpperCase();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new DatoInvalidoException(
                    "El cliente de la reserva no puede ser nulo."
            );
        }

        this.cliente = cliente;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        if (tour == null) {
            throw new DatoInvalidoException(
                    "El tour de la reserva no puede ser nulo."
            );
        }

        this.tour = tour;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        if (cantidadPersonas <= 0) {
            throw new DatoInvalidoException(
                    "La cantidad de personas debe ser mayor que cero."
            );
        }

        this.cantidadPersonas = cantidadPersonas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El estado de la reserva no puede estar vacío."
            );
        }

        this.estado = estado.trim();
    }

    /**
     * Calcula el valor total multiplicando el precio del tour
     * por la cantidad de personas de la reserva.
     */
    public double calcularTotal() {
        return tour.getPrecio() * cantidadPersonas;
    }

    @Override
    public String mostrarResumen() {
        return "Reserva | Código: " + codigo
                + " | Cliente: " + cliente.getNombre()
                + " | Tour: " + tour.getNombre()
                + " | Personas: " + cantidadPersonas
                + " | Total: $" + String.format("%.0f", calcularTotal())
                + " | Estado: " + estado;
    }

    @Override
    public String toString() {
        return "Código de reserva: " + codigo + "\n"
                + "Cliente: " + cliente.getNombre()
                + " | RUT: " + cliente.getRut() + "\n"
                + "Tour: " + tour.getNombre()
                + " | Código: " + tour.getCodigo() + "\n"
                + "Cantidad de personas: " + cantidadPersonas + "\n"
                + "Precio por persona: $"
                + String.format("%.0f", tour.getPrecio()) + "\n"
                + "Total: $"
                + String.format("%.0f", calcularTotal()) + "\n"
                + "Estado: " + estado;
    }
}