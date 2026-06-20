package model;

/**
 * Representa a una persona que colabora con Llanquihue Tour.
 * Puede corresponder a un guía, operador o proveedor.
 */
public class ColaboradorTuristico extends Persona {

    private String tipoColaborador;
    private String especialidad;
    private boolean activo;

    public ColaboradorTuristico(
            String nombre,
            String rut,
            String correo,
            String telefono,
            Direccion direccion,
            String tipoColaborador,
            String especialidad,
            boolean activo
    ) {
        super(nombre, rut, correo, telefono, direccion);
        this.tipoColaborador = tipoColaborador;
        this.especialidad = especialidad;
        this.activo = activo;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "========== COLABORADOR TURÍSTICO ==========\n"
                + super.toString() + "\n"
                + "Tipo de colaborador: " + tipoColaborador + "\n"
                + "Especialidad: " + especialidad + "\n"
                + "Estado: " + (activo ? "Activo" : "Inactivo");
    }
}