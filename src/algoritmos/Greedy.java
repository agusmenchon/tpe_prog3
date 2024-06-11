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

    /*El método greedy utiliza la estrategia de ir asignando las tareas al procesador factible (un procesador al que podamos agregar pasando las restricciones)
    que menos tiempo de ejecución tenga en ese momento. Para ello, cada vez que selecciono una tarea recorro la lista de procesadores y retorno aquel
    que cumpla con el criterio ya mencionado y, a su vez, también cumpla con los requisitos propios de la consigna. Una vez
    fueron asignadas todas las tareas, genera una Solución con el array de procesadores y el tiempo de ejecución máximo.*/
    public Solucion greedy(int tiempoMaximoNoRefrigerados) {
        //lo primero que hacemos es agregar una tarea por procesador, ya que al principio no tendran tareas asignadas y
        // siempre habra procesadores minimos con 0 tiempoEjecucion hasta que todos tengan una tarea asignada
        // (si pasan las restricciones del checkAddTarea), esto optimizará la estrategia siempre y cuando el checkAddTarea nos retorne true
        for (Procesador p : this.procesadores){
            this.cantidadCandidatos++; //aumento la cantidad de candidatos considerados
            Tarea t = this.tareas.getFirst();
            if (p.checkAddTarea(t, tiempoMaximoNoRefrigerados)) {
                p.addTarea(t);
                this.tareas.removeFirst();
            }
        }

        //construcción de la mejor solución, itera en tanto haya tareas para repartir:
        while (!this.tareas.isEmpty()) {
            Tarea t = this.tareas.getFirst();
            Procesador p = this.getProcesadorMinimoFactible(this.procesadores, t, tiempoMaximoNoRefrigerados);

            if (p == null) return null;

            p.addTarea(t);

            this.tareas.removeFirst();
        }

        int tiempo = this.getMayorTiempoEjecucion(this.procesadores);
        return new Solucion(this.procesadores, tiempo);
    }

    //en este metodo obtendremos el procesador de menor tiempo de ejecucion que ademas cumpla con los criterios de la consigna
    //que estan derivados por cuestiones de responsabilidad al metodo checkAddTarea
    private Procesador getProcesadorMinimoFactible(ArrayList<Procesador> procesadores, Tarea t, int tiempoMaxNoRefrig) {
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
