import algoritmos.Backtracking;
import entities.Procesador;
import entities.Solucion;
import entities.Tarea;
import utils.CSVReader;
import utils.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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

        // TODO
        Backtracking(servicios);

        //TODO
        // Greedy();

    }

    public static void Backtracking(Servicios servicios){
        System.out.println("--- BACKTRACKING ---");

        ArrayList<Procesador> procesadores = new ArrayList<>();
        procesadores.addAll(servicios.getProcesadores());

        Tarea[] tareas = new Tarea[servicios.getTareas().size()];

        int iT = 0;
        for (Tarea t: servicios.getTareas()) {
            tareas[iT] = t;
            iT++;
        }

        //todo pedir por consola parametros.
        Backtracking back = new Backtracking(procesadores, tareas);

        Solucion s = back.backtracking(500);

        System.out.println("La solución con tiempo de ejecución " + s.getTiempoEjecucion() + " es:");

        for (Procesador p: s.getProcesadores()) {
            System.out.println(p);
            for (Tarea t: p.getTareas()) {
                System.out.println(t);
            }
        }

        //System.out.println(s.toString());
        System.out.println("Iteraciones totales: " + back.getIteraciones());
    }

    public static void Greedy(){
        System.out.println("--- Greedy ---");

        String pathTareas = "src/datasets/Tareas.csv";
        String pathProcesadores = "src/datasets/Procesadores.csv";

        HashMap<String, Tarea> tareas = new HashMap<>();
        HashMap<String, Procesador> procesadores = new HashMap<>();

        CSVReader reader = new CSVReader(tareas, procesadores, pathTareas, pathProcesadores);
        reader.readProcessors();
        reader.readTasks();

        System.out.println(procesadores.toString());
    }

}
