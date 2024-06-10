package algoritmos;

import entities.Solucion;
import entities.Tarea;
import entities.Procesador;

import java.util.ArrayList;
import java.util.LinkedList;

public class Greedy {
    private ArrayList<Procesador> procesadores;
    private LinkedList<Tarea> tareas;
    private int cantidadCandidatos;

    public Greedy(ArrayList<Procesador> p, LinkedList<Tarea> t) {
        this.procesadores = new ArrayList<>(p);
        this.tareas = new LinkedList<>(t);
        this.cantidadCandidatos = 0;
    }

    /*El método greedy utiliza la estrategia de ir asignando las tareas al procesador que menos tiempo de ejecución
    tenga en ese momento. Para ello, cada vez que selecciono una tarea recorro la lista de procesadores y retorno aquel
    que cumpla con el criterio ya mencionado y, a su vez, también cumpla con los requisitos propios de la consigna. Una vez
    fueron asignadas todas las tareas, genera una Solución con el array de procesadores y el tiempo de ejecución máximo.*/
    public Solucion greedy(int tiempoMaximoNoRefrigerados) {
        //construcción de la mejor solución, itera en tanto haya tareas para repartir:
        while (!this.tareas.isEmpty()) {
            Tarea t = this.tareas.getFirst();
            Procesador p = this.getProcesadorMinimo(this.procesadores, t, tiempoMaximoNoRefrigerados);

            if (p == null) return null;

            boolean isAgregada = p.addTarea(t, tiempoMaximoNoRefrigerados);

            //remuevo la tarea ya asignada de la lista de tareas y sigo iterando.
            this.tareas.removeFirst();

            //si la tarea no puede agregarse, intenta agregarla en otro procesador:
//            if (!isAgregada) {
//                ArrayList<Procesador> procTmp = new ArrayList<>(this.procesadores);
//
//                while (!isAgregada) {
//                    //remueve del array temporal el procesador que ya probó:
//                    procTmp.remove(p);
//
//                    //si el array temporal queda vacío significa que no hay solución:
//                    if (procTmp.isEmpty()) {
//                        return null;
//                    } else {
//                        p = this.getProcesadorMinimo(procTmp); //traemos el proximo procesador de minimo tiempo de ejecucion de los que quedaron
//                        isAgregada = p.addTarea(t, tiempoMaximoNoRefrigerados);
//                    }
//                }
//            }

        }

        int tiempo = this.getMayorTiempoEjecucion(this.procesadores);

        return new Solucion(this.procesadores, tiempo);
    }

    public Procesador getProcesadorMinimo(ArrayList<Procesador> procesadores, Tarea t, int tiempoMaxNoRefrig) {
        int tiempoEjecucionMinimo = Integer.MAX_VALUE;
        Procesador procesadorSolucion = null;

        for (Procesador p: procesadores) {
            //aumento la cantidad de candidatos considerados:
            this.cantidadCandidatos++;
            if ((p.getTiempoEjecucion() < tiempoEjecucionMinimo) && p.checkAddTarea(t, tiempoMaxNoRefrig)) {
                tiempoEjecucionMinimo = p.getTiempoEjecucion();
                procesadorSolucion = p;
            }
        }
        return procesadorSolucion;
    }

    public int getMayorTiempoEjecucion(ArrayList<Procesador> procesadores) {
        int tiempoMayor = 0;

        for (Procesador p: procesadores) {
            if (p.getTiempoEjecucion() > tiempoMayor) {
                tiempoMayor = p.getTiempoEjecucion();
            }
        }

        return tiempoMayor;
    }

    public int getCantidadCandidatos() {
        return this.cantidadCandidatos;
    }
}
