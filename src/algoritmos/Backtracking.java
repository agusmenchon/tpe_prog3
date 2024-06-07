package algoritmos;

import entities.Procesador;
import entities.Solucion;
import entities.Tarea;

import java.util.*;

public class Backtracking {
    private ArrayList<Procesador> procesadores;
    private Tarea[] tareas;
    private int iteraciones;
    private Solucion asignacionMinima;

    public Backtracking(ArrayList<Procesador> procesadores, Tarea[] tareas) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.iteraciones = 0;
        this.asignacionMinima = new Solucion();
    }

    public int getIteraciones() {
        return iteraciones;
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

    private void _backtracking(int tareaIndex, Solucion asignacionActual, int tiempoMaximoNoRefrigerados) {
        //aumento this.iteraciones para contabilizar la cantidad de recursiones.
        this.iteraciones++;

        //si ya distribuimos todas las tareas:
        if (tareaIndex == this.tareas.length) {
            //la asignación actual define su tiempo de ejecución cotejando sus procesadores:
            asignacionActual.defineTiempoEjecucion();

            //si la asignación actual es mejor que la asignación guardada, la reemplazo:
            if (asignacionActual.getTiempoEjecucion() < this.asignacionMinima.getTiempoEjecucion()) {
                this.reemplazarAsignacionMinima(asignacionActual);
            }
        } else {
            for (Procesador p: asignacionActual.getProcesadores()) {
                int tiempoParcial = p.getTiempoEjecucion() + this.tareas[tareaIndex].getTiempoEjecucion();

                //PODA: si el procesador actual excediera el tiempo guardado al agregar la tarea,
                //no ejecuta y pasa al próximo procesador:
                if (tiempoParcial < asignacionMinima.getTiempoEjecucion()) {
                    //intento agregar la tarea actual al procesador:
                    boolean isAgregada = p.addTarea(this.tareas[tareaIndex], tiempoMaximoNoRefrigerados);

                    //si la tarea es agregada, ingresa a la recursión:
                    if (isAgregada) {
                        _backtracking(tareaIndex+1, asignacionActual, tiempoMaximoNoRefrigerados);
                        p.deleteTarea(this.tareas[tareaIndex]);
                    }
                }
            }
        }
    }

    public void reemplazarAsignacionMinima(Solucion asignacionActual) { //hacemos una copia de la asignacion actual que tenemos en los procesadores y lo guardamos en la mejor asignacion encontrada hasta el momento en una nueva unidad de memoria
        this.asignacionMinima.clear();
        for (Procesador p: asignacionActual.getProcesadores()) {
            Procesador tmp = new Procesador(p.getId(), p.getCodigo(), p.isRefrigeracion(), p.getAñoFuncionamiento(), p.getTiempoEjecucion(), p.getCantTareasCriticas());
            tmp.getTareas().addAll(p.getTareas());

            this.asignacionMinima.getProcesadores().add(tmp);
        }
        this.asignacionMinima.setTiempoEjecucion(asignacionActual.getTiempoEjecucion());
    }
}
