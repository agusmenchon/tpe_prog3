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

    public Solucion greedy(int tiempoMaximoNoRefrigerados) {
        //construcción de la mejor solución, itera en tanto haya tareas para repartir:
        while (!this.tareas.isEmpty()) {
            Tarea t = this.tareas.getFirst();
            Procesador p = this.getProcesadorMinimo(this.procesadores);

            boolean isAgregada = p.addTarea(t, tiempoMaximoNoRefrigerados);

            //si la tarea no puede agregarse, intenta agregarla en otro procesador:
            if (!isAgregada) {
                ArrayList<Procesador> procTmp = new ArrayList<>(this.procesadores);

                while (!isAgregada) {
                    //remueve del array temporal el procesador que ya probó:
                    procTmp.remove(p);

                    //si el array temporal queda vacío significa que no hay solución:
                    if (procTmp.isEmpty()) {
                        return null;
                    } else {
                        p = this.getProcesadorMinimo(procTmp); //traemos el proximo procesador de minimo tiempo de ejecucion de los que quedaron
                        isAgregada = p.addTarea(t, tiempoMaximoNoRefrigerados);
                    }
                }
            }
            //remuevo la tarea ya asignada de la lista de tareas y sigo iterando.
            this.tareas.removeFirst(); //como estamos en greedy, la decision que tomamos con la tarea es definitiva, asi que la sacamos de la lista vinculada y seguimos
        }

        int tiempo = this.getMayorTiempoEjecucion(this.procesadores);

        return new Solucion(this.procesadores, tiempo);
    }

    public Procesador getProcesadorMinimo(ArrayList<Procesador> procesadores) {
        int tiempoEjecucionMinimo = Integer.MAX_VALUE;
        Procesador procesadorSolucion = null;

        for (Procesador p: procesadores) {
            if (p.getTiempoEjecucion() < tiempoEjecucionMinimo) {
                tiempoEjecucionMinimo = p.getTiempoEjecucion();
                procesadorSolucion = p;
            }
        }

        //aumento la cantidad de candidatos considerados:
        this.cantidadCandidatos++;
        return procesadorSolucion;
    }

    public int getMayorTiempoEjecucion(ArrayList<Procesador> procesadores) {
        int solucion = 0;

        for (Procesador p: procesadores) {
            if (p.getTiempoEjecucion() > solucion) {
                solucion = p.getTiempoEjecucion();
            }
        }

        return solucion;
    }

    public int getCantidadCandidatos() {
        return this.cantidadCandidatos;
    }
}
