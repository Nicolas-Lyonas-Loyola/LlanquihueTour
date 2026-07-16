package model;

import util.DatoInvalidoException;

/**
 * Representa a un cliente de la agencia Llanquihue Tour.
 * Hereda los datos generales de Persona e implementa
 * el comportamiento definido por Registrable.
 */
public class Cliente extends Persona implements Registrable {

    private String tipoCliente;
    private String preferenciaTuristica;

    public Cliente(String nombre,
                   String rut,
                   String correo,
                   String telefono,
                   Direccion direccion,
                   String tipoCliente,
                   String preferenciaTuristica) {

        super(nombre, rut, correo, telefono, direccion);

        setTipoCliente(tipoCliente);
        setPreferenciaTuristica(preferenciaTuristica);
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        if (tipoCliente == null || tipoCliente.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El tipo de cliente no puede estar vacío."
            );
        }

        this.tipoCliente = tipoCliente.trim();
    }

    public String getPreferenciaTuristica() {
        return preferenciaTuristica;
    }

    public void setPreferenciaTuristica(String preferenciaTuristica) {
        if (preferenciaTuristica == null
                || preferenciaTuristica.trim().isEmpty()) {

            throw new DatoInvalidoException(
                    "La preferencia turística no puede estar vacía."
            );
        }

        this.preferenciaTuristica = preferenciaTuristica.trim();
    }

    @Override
    public String mostrarResumen() {
        return "Cliente | Nombre: " + getNombre()
                + " | RUT: " + getRut()
                + " | Tipo: " + tipoCliente
                + " | Preferencia: " + preferenciaTuristica;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Tipo de cliente: " + tipoCliente + "\n" +
                "Preferencia turística: " + preferenciaTuristica;
    }
}