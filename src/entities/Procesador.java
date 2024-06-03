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
    private int tareas_criticas;
    private final int TAREAS_CRIT_MAX = 2;

    public Procesador(String id, String codigo, boolean refrigeracion, int año_funcionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.refrigeracion = refrigeracion;
        this.año_funcionamiento = año_funcionamiento;
        this.tareas = new ArrayList<>();
        this.tiempo_ejecucion = 0;
        this.tareas_criticas = 0;
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(this.tareas);
    }

    public boolean addTarea(Tarea tarea, int tiempoMaximoNoRefrigerados) {
        int tiempoAux = this.tiempo_ejecucion + tarea.getTiempo_ejecucion();

        if (this.tareas_criticas < TAREAS_CRIT_MAX) {
            if (!this.isRefrigeracion() && tiempoAux < tiempoMaximoNoRefrigerados) {
                this.tareas.add(tarea);
                return true;
            } else {

            }
        }

    }

    public void deleteTarea(Tarea tarea) {
        this.tareas.remove(tarea);
    }

    public int getTiempoEjecucion() {
        return tiempo_ejecucion;
    }

    public void addTiempoEjecucion(int tiempoEjecucion) {
        this.tiempo_ejecucion += tiempoEjecucion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(boolean refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public int getAño_funcionamiento() {
        return año_funcionamiento;
    }

    public void setAño_funcionamiento(int año_funcionamiento) {
        this.año_funcionamiento = año_funcionamiento;
    }
}
