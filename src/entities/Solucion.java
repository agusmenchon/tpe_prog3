package entities;

import java.util.ArrayList;

public class Solucion {
    private ArrayList<Procesador> procesadores;
    private int tiempo_ejecucion;

    public Solucion() {
        procesadores = new ArrayList<>();
        tiempo_ejecucion = Integer.MAX_VALUE;
    }

    public Solucion(ArrayList<Procesador> p) {
        procesadores = new ArrayList<>(p);
        tiempo_ejecucion = Integer.MAX_VALUE;
    }

    public Solucion(ArrayList<Procesador> p, int t) {
        procesadores = new ArrayList<>(p);
        tiempo_ejecucion = t;
    }

    public void clear() {
        this.procesadores.clear();
    }

    public int getTiempoEjecucion() {
        return tiempo_ejecucion;
    }

    public ArrayList<Procesador> getProcesadores() {
        return this.procesadores;
    }

    public void setTiempoEjecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public void defineTiempoEjecucion() {
        int tiempoMAX = 0;
        for (Procesador p: this.procesadores) {
            if (p.getTiempoEjecucion() > tiempoMAX){
                tiempoMAX = p.getTiempoEjecucion();
            }
        }

        this.setTiempoEjecucion(tiempoMAX);
    }
}
