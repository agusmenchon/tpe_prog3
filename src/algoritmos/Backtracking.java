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

        _backtracking(0, asignacionActual, tiempoMaximoNoRefrigerados);

        return asignacionMinima;
    }

    private void _backtracking(int tareaIndex, Solucion asignacionActual, int tiempoMaximoNoRefrigerados) {
        //aumento this.iteraciones para contabilizar la cantidad de recursiones.
        this.iteraciones++;

        //si ya distribuimos todas las tareas:
        if (tareaIndex == this.tareas.length) {
            //busco el procesador con mayor tiempo de ejecución de la asignación actual:
            int tiempoMAX = 0;
            for (Procesador p: asignacionActual.getProcesadores()) {
                 if (p.getTiempoEjecucion() > tiempoMAX){
                     tiempoMAX = p.getTiempoEjecucion();
                 }
            }

            //establezco como tiempo de ejecución de la asignación actual al mayor tiempo de ejecución
            //de todos los procesadores:
            asignacionActual.setTiempoEjecucion(tiempoMAX);

            if (asignacionActual.getTiempoEjecucion() < this.asignacionMinima.getTiempoEjecucion()) {
                this.asignacionMinima.clear();
                for (Procesador p: asignacionActual.getProcesadores()) {
                    Procesador tmp = new Procesador(p.getId(), p.getCodigo(), p.isRefrigeracion(), p.getAñoFuncionamiento(), p.getTiempoEjecucion(), p.getCantTareasCriticas());
                    tmp.getTareas().addAll(p.getTareas());

                    this.asignacionMinima.getProcesadores().add(tmp);
                }
                this.asignacionMinima.setTiempoEjecucion(asignacionActual.getTiempoEjecucion());
            }
        } else {
            for (Procesador p: asignacionActual.getProcesadores()) {
                //intento agregar la tarea actual al procesador:
                boolean isAgregada = p.addTarea(this.tareas[tareaIndex], tiempoMaximoNoRefrigerados);

                //recursión.
                if (isAgregada) {
                    _backtracking(tareaIndex+1, asignacionActual, tiempoMaximoNoRefrigerados);
                    p.deleteTarea(this.tareas[tareaIndex]);
                }
            }
        }
    }
}
