package entities;

import java.util.ArrayList;

public class Solucion {
    private ArrayList<Procesador> procesadores;
    private int tiempo_ejecucion;

    public Solucion(ArrayList<Procesador> p) {
        procesadores = new ArrayList<>(p);
        tiempo_ejecucion = 0;
    }

    public void clear() {
        procesadores.clear();
    }

    public int getTiempoEjecucion() {
        return tiempo_ejecucion;
    }

    public ArrayList<Procesador> getProcesadores() {
        return new ArrayList<>(this.procesadores);
    }
}
