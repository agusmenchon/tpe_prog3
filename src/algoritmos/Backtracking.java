package algoritmos;

import entities.Procesador;
import entities.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Backtracking {
    private ArrayList<Procesador> procesadores;
    private ArrayList<Tarea> tareas;
    private int tiempoMax;
    private int iteraciones;
    private int tiempoProcNoRefrigerados;
    private HashMap<Procesador, ArrayList<Tarea>> asignacionMinima;
    private int tiempoFinalEjecucion;

//    P4;COD_P4;false;2015
//    ID, nombre, refrigerado, anio_func

//    T5;Tarea5;5;true;22
//    id, nombre, tiempo , critica, prioridad
    public Backtracking(ArrayList<Procesador> procesadores, ArrayList<Tarea> tareas, int tiempoMax, int tiempoProcNoRefrigerados) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoMax = tiempoMax;
        this.iteraciones = 0;
        this.tiempoProcNoRefrigerados = tiempoProcNoRefrigerados;
        this.asignacionMinima = new HashMap<>();
        this.tiempoFinalEjecucion = Integer.MAX_VALUE;
    }

    public HashMap<Procesador, ArrayList<Tarea>> backtracking() {
        HashMap<Procesador, ArrayList<Tarea>> asignacionActual = new HashMap<>();

        for(Procesador p : procesadores){
            asignacionActual.put(p, new ArrayList<>());
        }

        _backtracking(procesadores.getFirst(), asignacionActual, 0);

        return asignacionMinima;
    }

    private void _backtracking(Procesador proc, HashMap<Procesador, ArrayList<Tarea>> asignacionActual, int tiempoActual) {
        iteraciones++;
        //es solucion
        if((tiempoActual < tiempoMax && tiempoActual<tiempoFinalEjecucion) && tareas.isEmpty()){
            //todo otro condicional mas fuerte
            asignacionMinima.clear();
            asignacionMinima.putAll(asignacionActual);
            this.tiempoFinalEjecucion = tiempoActual;
        } else{
            if(!tareas.isEmpty()){
                for(Tarea t : tareas){
                    //restricciones
                    if(tiempoActual+t.getTiempo_ejecucion() < tiempoMax){
                        if((t.isCritica() || asignacionActual.get(proc).size()<2)){
                            if(proc.isRefrigeracion()) {
                                if (proc.getTiempoEjecucion() < tiempoProcNoRefrigerados) {
                                    //agregar a solucion
                                    asignacionActual.get(proc).add(t);
                                    tiempoActual += t.getTiempo_ejecucion();
                                    proc.addTiempoEjecucion(t.getTiempo_ejecucion());
                                    tareas.remove(t);

                                    _backtracking(proc, asignacionActual, tiempoActual);
                                }
                            } else{
                                //agregar a solucion
                                asignacionActual.get(proc).add(t);
                                tiempoActual += t.getTiempo_ejecucion();
                                proc.addTiempoEjecucion(t.getTiempo_ejecucion());
                                tareas.remove(t);

        //                        llamado recursivo

                                _backtracking(proc, asignacionActual, tiempoActual);


                            }
                            //deshacer cambios
                            asignacionActual.get(proc).remove(t);
                            tiempoActual -= t.getTiempo_ejecucion();
                            tareas.add(t);
                        }
                        if(asignacionActual.keySet().iterator().hasNext()) {
                            Procesador p = asignacionActual.keySet().iterator().next();
                            _backtracking(p, asignacionActual, tiempoActual);
                        }
                    }
                }
//            } else {
            }

        }
    }


}
