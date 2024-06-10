package utils;

import entities.Procesador;
import entities.Tarea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CSVReader {
    private HashMap<String, Tarea> tareas;
    private String taskPath;

    private HashMap<String, Procesador> procesadores;
    private String processorPath;

    public CSVReader(HashMap<String, Tarea> tareas,HashMap<String, Procesador> procesadores, String taskPath, String processorPath) {
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.taskPath = taskPath;
        this.processorPath = processorPath;
    }

    public void readTasks() {

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(taskPath);

        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String id = line[0].trim();
            String nombre = line[1].trim();
            Integer tiempo = Integer.parseInt(line[2].trim());
            Boolean critica = Boolean.parseBoolean(line[3].trim());
            Integer prioridad = Integer.parseInt(line[4].trim());

            // Aca instanciar lo que necesiten en base a los datos leidos
            Tarea t = new Tarea(id, nombre, tiempo, critica, prioridad);
            tareas.put(t.getId(), t);
            //System.out.println(tareas.get(t.getId()));
        }
    }

    public void readProcessors() {

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(processorPath);

        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String id = line[0].trim();
            String codigo = line[1].trim();
            Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
            Integer anio = Integer.parseInt(line[3].trim());
            // Aca instanciar lo que necesiten en base a los datos leidos

            Procesador p = new Procesador(id, codigo, refrigerado, anio);
            procesadores.put(p.getId(), p);
        }
    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }

}
