package entities;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigeracion;
    private int año_funcionamiento;
//    private List<Tarea> tareas;
    private int tiempoEjecucion;

    public Procesador(String id, String codigo, boolean refrigeracion, int año_funcionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.refrigeracion = refrigeracion;
        this.año_funcionamiento = año_funcionamiento;
//        this.tareas = new ArrayList<>();
        this.tiempoEjecucion = 0;
    }

//    public List<Tarea> getTareas() {
//        return tareas;
//    }
//
//    public void addTareas(Tarea tarea) {
//        this.tareas.add(tarea);
//    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void addTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion += tiempoEjecucion;
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
