package model;

public class ColaboradorExterno extends ColaboradorTuristico
        implements Registrable {

    private String servicioPrestado;

    public ColaboradorExterno(
            String nombre,
            String rut,
            String correo,
            String telefono,
            Direccion direccion,
            String especialidad,
            boolean activo,
            String servicioPrestado
    ) {
        super(
                nombre,
                rut,
                correo,
                telefono,
                direccion,
                "Colaborador externo",
                especialidad,
                activo
        );

        setServicioPrestado(servicioPrestado);
    }

    public String getServicioPrestado() {
        return servicioPrestado;
    }

    public void setServicioPrestado(String servicioPrestado) {
        if (servicioPrestado == null
                || servicioPrestado.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El servicio prestado no puede estar vacío."
            );
        }

        this.servicioPrestado = servicioPrestado.trim();
    }

    @Override
    public String mostrarResumen() {
        return "Colaborador externo: "
                + getNombre()
                + " | Especialidad: "
                + getEspecialidad()
                + " | Servicio prestado: "
                + servicioPrestado
                + " | Teléfono: "
                + getTelefono();
    }
}