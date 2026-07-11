package model;

public class GuiaTuristico extends ColaboradorTuristico
        implements Registrable {

    private String idioma;

    public GuiaTuristico(
            String nombre,
            String rut,
            String correo,
            String telefono,
            Direccion direccion,
            String especialidad,
            boolean activo,
            String idioma
    ) {
        super(
                nombre,
                rut,
                correo,
                telefono,
                direccion,
                "Guía turístico",
                especialidad,
                activo
        );

        setIdioma(idioma);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El idioma no puede estar vacío."
            );
        }

        this.idioma = idioma.trim();
    }

    @Override
    public String mostrarResumen() {
        return "Guía turístico: "
                + getNombre()
                + " | Especialidad: "
                + getEspecialidad()
                + " | Idioma: "
                + idioma;
    }
}