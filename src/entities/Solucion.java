package entities;

import java.util.ArrayList;

public class Solucion {
    private ArrayList<Procesador> procesadores;
//    private Procesador procMayorTiempoEjecucion;
    private int tiempo_ejecucion;

    public Solucion(ArrayList<Procesador> p) {
        procesadores = new ArrayList<>(p);
        tiempo_ejecucion = 0;
//        this.procMayorTiempoEjecucion = procesadores.getFirst();
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

    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    //    public void addTarea(Procesador p, Tarea t){
//        if(p.getTiempoEjecucion()+t.getTiempo_ejecucion() < tiempo_ejecucion){
//            p.addTarea(t);
//            tiempo_ejecucion += t.getTiempo_ejecucion();
//        }


//
//
//        if(procMayorTiempoEjecucion==null){
//            p.addTarea(t);
//            procMayorTiempoEjecucion = p;
//            if(procMayorTiempoEjecucion.getTiempoEjecucion() < p.getTiempoEjecucion()+t.getTiempo_ejecucion()){
//                tiempo_ejecucion =+ t.getTiempo_ejecucion();
//                procMayorTiempoEjecucion = p;
//            }
//        }
//    }


//    public void deleteTarea(Procesador p, Tarea t){
//        p.deleteTarea(t);
//        //todo restar procMayorTiempoEjecucion
//    }

    @Override
    public String toString() {
        return "Solucion minima: " +
                "procesadores=" + procesadores +
                ", tiempo_ejecucion=" + tiempo_ejecucion;
    }
}
