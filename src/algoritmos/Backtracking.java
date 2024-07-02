package algoritmos;

import entities.Procesador;
import entities.Solucion;
import entities.Tarea;

import java.util.*;

public class Backtracking {
    private ArrayList<Procesador> procesadores;
    private Tarea[] tareas;
    private int recursiones;
    private Solucion asignacionMinima;

    public Backtracking(ArrayList<Procesador> procesadores, Tarea[] tareas) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.recursiones = 0;
        this.asignacionMinima = new Solucion();
    }

    public int getRecursiones() {
        return this.recursiones;
    }

    public Solucion backtracking(int tiempoMaximoNoRefrigerados) {
        this.asignacionMinima.setTiempoEjecucion(Integer.MAX_VALUE);
        this.asignacionMinima.clear();

        Solucion asignacionActual = new Solucion(this.procesadores);
        asignacionActual.setTiempoEjecucion(0);

        _backtracking(0, asignacionActual, tiempoMaximoNoRefrigerados);

        if (asignacionMinima.getTiempoEjecucion() == Integer.MAX_VALUE) {
            return null;
        } else {
            return asignacionMinima;
        }
    }

    /* Este método backtracking va construyendo todas las combinaciones posibles de asignacion de una cantidad N de tareas
    a una cantidad M de procesadores.
    Empezara asignando tareas al primer procesador, obtendrá el tiempo parcial de ejecucion del procesador y verificara que sea menor
    al de la asignacion minima hasta el momento (condicion de PODA), luego verificara que la tarea sea agregable, donde se le delega
    la responsabilidad a la clase Procesador el comprobar que la misma sea agregable en funcion de la cant de tareas criticas y su
    limitante en caso de ser no refrigerado.
    Si pasa estas estructuras de control, entonces agrega la tarea al procesador, y llama recursivamente.
    Continuara agregando todas las tareas al procesador asignado hasta alcanzar el limite de tareas.
    Una vez alcanzado el limite de tareas distribuidas, la asignacion actual definira su tiempo de ejecucion con el de
    su procesador con max tiempo de ejecucion. Luego comparara la asignacion actual con la mejor-minima hasta el momento.
    De cumplirse esta condicion, genera una copia de los procesadores y guarda la asignacion actual reemplazando la asignacion mejor-minima vigente.
    La recursion desacoplará, quitará del procesador la ultima tarea, y pasará al siguiente procesador para agregar la tarea y probar
    con una nueva asignacion. Este proceso continuará hasta que se hayan explorado todas las combinaciones posibles, exceptuando aquellas
    en la cual la PODA corte el proceso de exploración del espacio de busqueda del arbol recursivo de backtracking.*/
    private void _backtracking(int tareaIndex, Solucion asignacionActual, int tiempoMaximoNoRefrigerados) {
        //aumento this.recursiones para contabilizar la cantidad de recursiones.
        this.recursiones++;

        //si ya distribuimos todas las tareas:
        if (tareaIndex == this.tareas.length) {
            //la asignación actual define su tiempo de ejecución cotejando sus procesadores:
            asignacionActual.defineTiempoEjecucion();

            //si la asignación actual es mejor que la asignación guardada, la reemplazo:
            if (asignacionActual.getTiempoEjecucion() < this.asignacionMinima.getTiempoEjecucion()) {
                //realiza una copia de los procesadores para almacenarla en la asignaciónMínima, ya que de otra forma
                //las unidades de memoria son las mismas.
                this.reemplazarAsignacionMinima(asignacionActual);
            }
        } else {
            for (Procesador p: asignacionActual.getProcesadores()) {
                int tiempoParcial = p.getTiempoEjecucion() + this.tareas[tareaIndex].getTiempoEjecucion();

                //PODA: si el procesador actual excediera el tiempo guardado al agregar la tarea,
                //no ejecuta y pasa al próximo procesador:
                if (tiempoParcial < asignacionMinima.getTiempoEjecucion()) {
                    //chequeo que se pueda agregar la tarea actual al procesador:
                    boolean isAgregable = p.checkAddTarea(this.tareas[tareaIndex], tiempoMaximoNoRefrigerados);

                    //si la tarea se puede agregar, la agrego y luego ingresa a la recursión:
                    if (isAgregable) {
                        p.addTarea(this.tareas[tareaIndex]);
                        _backtracking(tareaIndex+1, asignacionActual, tiempoMaximoNoRefrigerados);
                        p.deleteTarea(this.tareas[tareaIndex]);
                    }
                }
            }
        }
    }

    /* Este método se encarga de hacer la copia de los procesadores de la asignacion actual para setear la asignacion minima-mejor que llevaremos
    * para explorar el espacio de busqueda de backtracking. La copia es necesaria para mantener la consistencia en el funcionamiento
    * del backtracking, ya que los procesadores iran modificando sus tiempos de ejecucion constantemente al explorar el espacio
    * de busqueda del arbol recursivo, por lo cual necesitamos guardar en un nuevo espacio de memoria la mejor asignacion que tenemos hasta el momento
    * , ya que esta asignacion será alterada mediante los siguientes llamados recursivos en el proceso de exploración del arbol de busqueda */
    private void reemplazarAsignacionMinima(Solucion asignacionActual) {
        this.asignacionMinima.clear();
        for (Procesador p: asignacionActual.getProcesadores()) {
            Procesador tmp = new Procesador(p.getId(), p.getCodigo(), p.isRefrigeracion(), p.getAñoFuncionamiento(), p.getTiempoEjecucion(), p.getCantTareasCriticas());
            tmp.getTareas().addAll(p.getTareas());

            this.asignacionMinima.getProcesadores().add(tmp);
        }
        this.asignacionMinima.setTiempoEjecucion(asignacionActual.getTiempoEjecucion());
    }
}
