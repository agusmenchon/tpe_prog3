package entities;

public class  Tarea {
    private String id;
    private String nombre;
    private int tiempo_ejecucion;
    private boolean critica;
    private int prioridad;

    public Tarea(String id, String nombre, int tiempo_ejecucion, boolean critica, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoEjecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public boolean isCritica() {
        return critica;
    }

    public void setCritica(boolean critica) {
        this.critica = critica;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String toString() {
        return "La tarea " + this.id + " con prioridad: " + this.prioridad + " y tiempo " + this.tiempo_ejecucion + " y si es crítica: " + this.critica;
    }
}