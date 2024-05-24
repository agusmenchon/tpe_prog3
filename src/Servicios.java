import entities.Tarea;
import utils.CSVReader;

import java.util.HashMap;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "entities.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

    private HashMap<String, Tarea> tareas;
    private Tarea array[];

    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores);
        reader.readTasks(pathTareas);
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
//    public entities.Tarea servicio1(String ID) {	}
//
//    /*
//     * Expresar la complejidad temporal del servicio 2.
//     */
//    public List<entities.Tarea> servicio2(boolean esCritica) {}
//
//    /*
//     * Expresar la complejidad temporal del servicio 3.
//     */
//    public List<entities.Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {}

}