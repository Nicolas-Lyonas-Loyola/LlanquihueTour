package data;

import model.ColaboradorExterno;
import model.GuiaTuristico;
import model.Registrable;
import model.Vehiculo;

import java.util.ArrayList;

public class GestorEntidades {

    private final ArrayList<Registrable> entidades;

    public GestorEntidades() {
        entidades = new ArrayList<>();
    }

    public void agregarEntidad(Registrable entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException(
                    "La entidad no puede ser nula."
            );
        }

        entidades.add(entidad);
    }

    public ArrayList<Registrable> getEntidades() {
        return entidades;
    }

    public String mostrarTodosLosResumenes() {

        if (entidades.isEmpty()) {
            return "No existen entidades registradas.";
        }

        StringBuilder texto = new StringBuilder();

        for (Registrable entidad : entidades) {

            texto.append(entidad.mostrarResumen())
                    .append("\n");

            if (entidad instanceof GuiaTuristico) {
                texto.append(
                        "Tipo detectado: Guía turístico\n"
                );

            } else if (entidad instanceof Vehiculo) {
                texto.append(
                        "Tipo detectado: Vehículo\n"
                );

            } else if (entidad instanceof ColaboradorExterno) {
                texto.append(
                        "Tipo detectado: Colaborador externo\n"
                );
            }

            texto.append(
                    "----------------------------------------\n"
            );
        }

        return texto.toString();
    }
}