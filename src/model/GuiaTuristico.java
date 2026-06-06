package model;

/**
 * Representa a un guía turístico que trabaja con Llanquihue Tour.
 */
public class GuiaTuristico extends Persona {

    private String especialidad;
    private int aniosExperiencia;

    public GuiaTuristico(String nombre, String rut, String correo, String telefono, Direccion direccion,
                         String especialidad, int aniosExperiencia) {
        super(nombre, rut, correo, telefono, direccion);
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String toString() {
        return "========== GUÍA TURÍSTICO ==========\n" +
                super.toString() + "\n" +
                "Especialidad: " + especialidad + "\n" +
                "Años de experiencia: " + aniosExperiencia;
    }
}