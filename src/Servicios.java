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

    private ArrayList<Tarea> tareasCriticas;
    private ArrayList<Tarea> tareasNoCriticas;
    private HashMap<String, Tarea> tareas;
    private HashMap<String, Procesador> procesadores;
    private Tarea arrayTareas[];

    //Expresar la complejidad temporal del constructor.
    public Servicios(String pathProcesadores, String pathTareas)
    {
        this.tareas = new HashMap<>();
        this.procesadores = new HashMap<>();
        this.tareasCriticas = new ArrayList<Tarea>();
        this.tareasNoCriticas = new ArrayList<Tarea>();

        CSVReader reader = new CSVReader(tareas, procesadores, pathTareas, pathProcesadores);
        reader.readProcessors();
        reader.readTasks();
        arrayTareas = new Tarea[tareas.size()];
        insertarOrdenadoArray();
        this.generarListadosSegunCriticicidad();
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

    private void generarListadosSegunCriticicidad(){
        for (Tarea t: tareas.values()) {
            if (t.isCritica()) {
                this.tareasCriticas.add(t);
            } else this.tareasNoCriticas.add(t);
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
    //Estructura utilizada: estructura HashMap o estructura de hashing o dispersión. Nos permite
    //llevar los tiempos de busquedas a O(1) o tiempo constante mediante el acceso asociativo por un ID,
    //y es valioso frente a otras estructuras como listas o arreglos donde el tiempo de busqueda seria
    //variable
    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    //Expresar la complejidad temporal del servicio 2.
    //O(1)
    //Estructura utilizada: doble lista (ArrayList), por un lado una lista de tareas criticas y otra de no criticas
    //Al cargar las tareas en el constructor nos permitimos instanciar dos listas para reducir ulteriormente
    //las complejidades de devolver las tareas criticas o no criticas a tiempo constante
    public List<Tarea> servicio2(boolean esCritica) {
        return esCritica ? new ArrayList<>(this.tareasCriticas) : new ArrayList<>(this.tareasNoCriticas);
    }

    //Expresar la complejidad temporal del servicio 3.
    //O(n)
    //Estructura utilizada: arreglo de tareas (Tarea[])
    //Con un arreglo ya previamente ordenado de tareas podemos implementar el algoritmo de busqueda binaria para
    //retornar la posicion del elemento correspondiente a la prioridad inferior (o el inmediatamente mas cercano de
    //entre los mayores a prioridad inferior), con la idea de achicar el tamaño del recorrido a iterar para devolver
    //las tareas entre los dos niveles de prioridad indicados.
    //Si bien en el peor de los casos tendria que hacer todo el recorrido (si se pidiera con prioridad inferior == 0),
    //se mejora el costo computacional para el caso promedio.
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        List<Tarea> res = new ArrayList<>();

        int inicio = busquedaBinaria(arrayTareas, prioridadInferior, 0, arrayTareas.length - 1);

        if (inicio >= arrayTareas.length) {
            return res;
        }

        //System.out.println("valor de prioridad en la pos de inicio: " + arrayTareas[inicio].getPrioridad());

        for (int i = inicio; i < arrayTareas.length; i++) {
            if (arrayTareas[i].getPrioridad() <= prioridadSuperior) {
                res.add(arrayTareas[i]);
            }
        }
        return res;
    }

    //Busqueda binaria en este caso retornara la posicion del elemento (si lo encuentra),
    // de lo contrario retornará la posicion > mas proxima al elem x buscado
    //la idea detrás de la utilizacion del algoritmo es mejorar el caso promedio aunque no reduzca la complejidad en el big(O) notation
    private int busquedaBinaria(Tarea[] array, int x, int inicio, int fin) {
        int medio;

        while (inicio <= fin) {
            medio = (inicio + fin) / 2;
            //System.out.println("Medio es ahora: " + medio);
            if (array[medio].getPrioridad() < x) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return inicio;
    }
}