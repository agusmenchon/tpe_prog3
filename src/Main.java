import entities.Tarea;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Servicios servicios = new Servicios("src/datasets/Procesadores.csv", "src/datasets/Tareas.csv");

        System.out.println("HASHMAP DE TAREAS");
        servicios.printHashMap();

        System.out.println("SERVICIO 1 con tarea 3");
        System.out.println(servicios.servicio1("T3"));

        System.out.println("SERVICIO 2 con tareas críticas");
        List<Tarea> list = servicios.servicio2(true);
        for (Tarea t: list) {
            System.out.println(t);
        }

        System.out.println("ARRAY ORDENADO SEGÚN PRIORIDAD DE TAREAS");
        servicios.printArray();

        System.out.println("SERVICIO 3");

        }
    }
