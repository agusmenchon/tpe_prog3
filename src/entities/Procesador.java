package entities;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigeracion;
    private int año_funcionamiento;
    private List<Tarea> tareas;
    private int tiempo_ejecucion;
    private int cant_tareas_criticas;
    private final int TAREAS_CRIT_MAX = 2;

    public Procesador(String id, String codigo, boolean refrigeracion, int año_funcionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.refrigeracion = refrigeracion;
        this.año_funcionamiento = año_funcionamiento;
        this.tareas = new ArrayList<>();
        this.tiempo_ejecucion = 0;
        this.cant_tareas_criticas = 0;
    }

    public Procesador(String id, String codigo, boolean refrigeracion, int año_funcionamiento, int tiempo_ejecucion, int tareas_criticas) {
        this.id = id;
        this.codigo = codigo;
        this.refrigeracion = refrigeracion;
        this.año_funcionamiento = año_funcionamiento;
        this.tareas = new ArrayList<>();
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.cant_tareas_criticas = tareas_criticas;
    }

    public List<Tarea> getTareas() {
        return this.tareas;
    }

    public boolean checkAddTarea(Tarea tarea, int tiempoMaximoNoRefrigerados){
        int tiempoEstimadoProcesador = this.tiempo_ejecucion + tarea.getTiempoEjecucion();

        //no puedo agregar mas tareas si:
        // supero el tiempo max establecido para proc NO refrigerados OR la tarea es critica y ya estamos igual o superando el lim de criticas permitidas
        if ((!this.isRefrigeracion() && tiempoEstimadoProcesador > tiempoMaximoNoRefrigerados) || (tarea.isCritica() && this.cant_tareas_criticas >= TAREAS_CRIT_MAX)) {
            return false;
        }
        return true;
    }

    public void addTarea(Tarea tarea) {
        if (tarea.isCritica()) this.cant_tareas_criticas++;

        this.tareas.add(tarea);
        this.tiempo_ejecucion += tarea.getTiempoEjecucion();
    }

    public void deleteTarea(Tarea tarea) {
        if (tarea.isCritica()) {
            this.cant_tareas_criticas--;
        }

        this.tareas.remove(tarea);
        this.setTiempoEjecucion(this.getTiempoEjecucion() - tarea.getTiempoEjecucion());
    }

    public int getTiempoEjecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempoEjecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isRefrigeracion() {
        return refrigeracion;
    }

    public int getAñoFuncionamiento() {
        return año_funcionamiento;
    }

    public int getCantTareasCriticas() {
        return this.cant_tareas_criticas;
    }

    @Override
    public String toString() {
        return "Procesador " + this.getId() + " refrigerado (" + this.isRefrigeracion() + ") con tiempo de ejecución " + this.tiempo_ejecucion + " y las tareas:";
    }
}
