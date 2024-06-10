import entities.Procesador;
import entities.Tarea;
import org.w3c.dom.ranges.RangeException;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "entities.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

    private HashMap<String, Tarea> tareas;
    private HashMap<String, Procesador> procesadores;
    private Tarea arrayTareas[];

    //Expresar la complejidad temporal del constructor.
    public Servicios(String pathProcesadores, String pathTareas)
    {
        this.tareas = new HashMap<>();
        //REVISAR
        this.procesadores = new HashMap<>();

        CSVReader reader = new CSVReader(tareas, procesadores, pathTareas, pathProcesadores);
        reader.readProcessors();
        reader.readTasks();
        arrayTareas = new Tarea[tareas.size()];
        insertarOrdenadoArray();
    }

    public Collection<Tarea> getTareas() {
        return tareas.values();
    }

    public Collection<Procesador> getProcesadores() {
        return procesadores.values();
    }

    private void insertarOrdenadoArray() {
        int i = 0;
        for (Tarea t: tareas.values()) {
            arrayTareas[i] = t;
            i++;
        }
        bubbleSort(arrayTareas);
    }

    private void bubbleSort(Tarea[] arr){
        Tarea aux;
        for(int i = 0; i<arr.length-1; i++){
            for(int j = 0; j<arr.length-i-1; j++){
                if(arr[j].getPrioridad() > arr[j+1].getPrioridad()){
                    aux = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = aux;
                }
            }
        }
    }

    public void printArray() {
        for (Tarea t: arrayTareas) {
            System.out.println(t);
        }
    }

    public void printHashMap() {
        for (Tarea t: tareas.values()) {
            System.out.println(t);
        }
    }

    //Expresar la complejidad temporal del servicio 1.
    //O(1)
    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    //Expresar la complejidad temporal del servicio 2.
    //O(n)
    public List<Tarea> servicio2(boolean esCritica) {
        List<Tarea> res = new ArrayList<>();
        for (Tarea t: tareas.values()) {
            if (t.isCritica() == esCritica) {
                res.add(t);
            }
        }
        return res;
    }

    //Expresar la complejidad temporal del servicio 3.
    //O(Log2n)
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        List<Tarea> res = new ArrayList<>();

        int inicio = busquedaBinaria(arrayTareas, prioridadInferior, 0, arrayTareas.length, 0);

        for (int i = inicio; i < arrayTareas.length; i++) {
            if (arrayTareas[i].getPrioridad() <= prioridadSuperior) {
                res.add(arrayTareas[i]);
            }
        }
        return res;
    }

    public int busquedaBinaria(Tarea[] array, int x, int inicio, int fin, int indicePrevio) {
        //System.out.println("Ingresa a la búsqueda binaria");
        int medio;

        if (inicio > fin) {
            //System.out.println("Ingresa al if de inicio > fin");
            //System.out.println(indicePrevio);
            return indicePrevio;
        } //sucederá si no se encuentra el elemento

        else {
            medio = (inicio + fin) / 2; //al ser medio un int, se realiza un truncado (pierde la parte decimal)
            //System.out.println("Medio: " +medio);
            if (x > array[medio].getPrioridad())
                return busquedaBinaria(array, x, medio+1, fin, medio);
            else
            if (x < array[medio].getPrioridad())
                return busquedaBinaria(array, x, inicio, medio-1, medio);
            else
                return medio;
        }
    }
}