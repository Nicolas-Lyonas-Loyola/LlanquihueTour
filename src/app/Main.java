package app;

import model.Cliente;
import model.Direccion;
import model.Empleado;
import model.GuiaTuristico;

public class Main {

    public static void main(String[] args) {

        Direccion direccionCliente = new Direccion("Costanera", 120, "Llanquihue", "Los Lagos");
        Direccion direccionGuia = new Direccion("Los Volcanes", 455, "Puerto Varas", "Los Lagos");
        Direccion direccionEmpleado = new Direccion("Vicente Pérez Rosales", 890, "Frutillar", "Los Lagos");

        Cliente cliente1 = new Cliente(
                "Camila Rojas",
                "12.345.678-9",
                "camila.rojas@email.com",
                "+56 9 1234 5678",
                direccionCliente,
                "Tour gastronómico",
                3
        );

        GuiaTuristico guia1 = new GuiaTuristico(
                "Felipe Soto",
                "15.987.654-3",
                "felipe.soto@llanquihuetour.cl",
                "+56 9 8765 4321",
                direccionGuia,
                "Excursiones culturales",
                6
        );

        Empleado empleado1 = new Empleado(
                "María Torres",
                "18.456.789-1",
                "maria.torres@llanquihuetour.cl",
                "+56 9 5555 4444",
                direccionEmpleado,
                "Coordinadora de reservas",
                "Operaciones"
        );

        System.out.println("=======================================");
        System.out.println("       SISTEMA LLANQUIHUE TOUR");
        System.out.println("=======================================");
        System.out.println();

        System.out.println(cliente1);
        System.out.println();

        System.out.println("---------------------------------------");
        System.out.println();

        System.out.println(guia1);
        System.out.println();

        System.out.println("---------------------------------------");
        System.out.println();

        System.out.println(empleado1);
    }
}
