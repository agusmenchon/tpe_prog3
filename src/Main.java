import algoritmos.Backtracking;
import algoritmos.Greedy;
import entities.Procesador;
import entities.Solucion;
import entities.Tarea;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ///////// PRIMERA PARTE TPE /////////
        Servicios servicios = new Servicios("src/datasets/Procesadores.csv", "src/datasets/Tareas.csv");

        System.out.println("HASHMAP DE TAREAS");
        servicios.printHashMap();

        System.out.println("SERVICIO 1 con tarea 3");
        System.out.println(servicios.servicio1("T3"));

        System.out.println("SERVICIO 2 con tareas críticas");
        List<Tarea> listTareasCriticas = servicios.servicio2(true);
        for (Tarea t: listTareasCriticas) {
            System.out.println(t);
        }

        System.out.println("ARRAY ORDENADO SEGÚN PRIORIDAD DE TAREAS");
        servicios.printArray();

        System.out.println("SERVICIO 3 de 50 a 75");
        List<Tarea> listTareasPrioridad = servicios.servicio3(50, 75);

        for (Tarea t: listTareasPrioridad) {
            System.out.println(t);
        }

        ///////// SEGUNDA PARTE TPE /////////
        Backtracking();

        Greedy();

    }

    public static void Backtracking(){
        System.out.println("--- BACKTRACKING ---");

        HashMap<String, Procesador> procesadoresHash = new HashMap<>();
        HashMap<String, Tarea> tareasHash = new HashMap<>();

        String tareasPath = "src/datasets/Tareas.csv";
        String procesadoresPath = "src/datasets/Procesadores.csv";

        CSVReader reader = new CSVReader(tareasHash, procesadoresHash, tareasPath, procesadoresPath);
        reader.readProcessors();
        reader.readTasks();

        ArrayList<Procesador> procesadores = new ArrayList<>(procesadoresHash.values());
        Tarea[] tareas = new Tarea[tareasHash.size()];

        int iT = 0;
        for (Tarea tarea: tareasHash.values()) {
            tareas[iT] = tarea;
            iT++;
        }

        Backtracking backtracking = new Backtracking(procesadores, tareas);

        Solucion s = backtracking.backtracking(500);

        if (s == null) {
            System.out.println("Backtracking no encontró una solución.");
        } else {
            System.out.println("La solución con tiempo de ejecución " + s.getTiempoEjecucion() + " es:");

            for (Procesador p: s.getProcesadores()) {
                System.out.println(p);
                for (Tarea t: p.getTareas()) {
                    System.out.println(t);
                }
            }

            System.out.println("Iteraciones totales: " + backtracking.getIteraciones());
        }
    }

    public static void Greedy(){
        System.out.println("--- GREEDY ---");

        HashMap<String, Procesador> procesadoresHash = new HashMap<>();
        HashMap<String, Tarea> tareasHash = new HashMap<>();

        String tareasPath = "src/datasets/Tareas.csv";
        String procesadoresPath = "src/datasets/Procesadores.csv";

        CSVReader reader = new CSVReader(tareasHash, procesadoresHash, tareasPath, procesadoresPath);
        reader.readProcessors();
        reader.readTasks();

        ArrayList<Procesador> procesadores = new ArrayList<>(procesadoresHash.values());
        LinkedList<Tarea> tareas = new LinkedList<>(tareasHash.values());

        Greedy greedy = new Greedy(procesadores, tareas);

        Solucion s = greedy.greedy(500);

        if (s == null) {
            System.out.println("Greedy no encontró una solución.");
        } else {
            System.out.println("La solución con tiempo de ejecución " + s.getTiempoEjecucion() + " es:");

            for (Procesador p: s.getProcesadores()) {
                System.out.println(p);
                for (Tarea t: p.getTareas()) {
                    System.out.println(t);
                }
            }

            System.out.println("Candidatos considerados totales: " + greedy.getCantidadCandidatos());
        }
    }

//    public static void lectorCSV() {
//        HashMap<String, Procesador> procesadoresHash = new HashMap<>();
//        HashMap<String, Tarea> tareasHash = new HashMap<>();
//
//        String tareasPath = "src/datasets/Tareas.csv";
//        String procesadoresPath = "src/datasets/Procesadores.csv";
//
//        CSVReader reader = new CSVReader(tareasHash, procesadoresHash, tareasPath, procesadoresPath);
//        reader.readProcessors();
//        reader.readTasks();
//    }
}
