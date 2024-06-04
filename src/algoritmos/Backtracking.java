package algoritmos;

import entities.Procesador;
import entities.Solucion;
import entities.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Backtracking {
    private ArrayList<Procesador> procesadores;
    private Tarea[] tareas;
    //private int tiempoMax;
    private int iteraciones;
    //private int tiempoProcNoRefrigerados;
    //private HashMap<Procesador, ArrayList<Tarea>> asignacionMinima;
    private Solucion asignacionMinima;
//    private int tiempoFinalEjecucion;

//    P4;COD_P4;false;2015
//    ID, nombre, refrigerado, anio_func

//    T5;Tarea5;5;true;22
//    id, nombre, tiempo , critica, prioridad
    public Backtracking(ArrayList<Procesador> procesadores, Tarea[] tareas) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        //this.tiempoMax = tiempoMax;
        this.iteraciones = 0;
        //this.tiempoProcNoRefrigerados = tiempoProcNoRefrigerados;
        this.asignacionMinima = new Solucion(procesadores);
//        this.tiempoFinalEjecucion = Integer.MAX_VALUE;
    }


    public int getIteraciones() {
        return iteraciones;
    }

    public Solucion backtracking(int tiempoMaximoNoRefrigerados) {
        this.asignacionMinima.setTiempo_ejecucion(Integer.MAX_VALUE);
        this.asignacionMinima.clear();
//        this.tiempoFinalEjecucion = Integer.MAX_VALUE;

        Solucion asignacionActual = new Solucion(this.procesadores);

        /*Set<String> ids = procesadores.keySet();

        for (String id: ids) {
            Procesador temp = procesadores.get(id);
            asignacionActual.put(temp.getId(), temp);
        }*/

        //for(Procesador p : procesadores){
        //    asignacionActual.put(p, new ArrayList<>());
        //}

        _backtracking(0, asignacionActual, tiempoMaximoNoRefrigerados);

        return asignacionMinima;
    }

    private void _backtracking(int tareaIndex, Solucion asignacionActual, int tiempoMaximoNoRefrigerados) {
        //aumento this.iteraciones para contabilizar la cantidad de recursiones.
        this.iteraciones++;

        //si ya distribuimos todas las tareas:
        if (tareaIndex == this.tareas.length) {
            int tiempoMAX = 0;

            for (Procesador p: asignacionActual.getProcesadores()) {
                 if(p.getTiempoEjecucion() > tiempoMAX){
                     tiempoMAX = p.getTiempoEjecucion();
                 }

            }
            asignacionActual.setTiempo_ejecucion(tiempoMAX);


            if (asignacionActual.getTiempoEjecucion() < this.asignacionMinima.getTiempoEjecucion()) {
                this.asignacionMinima = asignacionActual;
//                this.tiempoFinalEjecucion = asignacionMinima.getTiempoEjecucion();
            }

        } else {
            for (Procesador p: asignacionActual.getProcesadores()) {
                //agregar solucion
                p.canAdd(this.tareas[tareaIndex], tiempoMaximoNoRefrigerados);

//                    asignacionActual.addTarea(p, this.tareas[tareaIndex]);
//                    p.addTarea(this.tareas[tareaIndex]);
//                }

                //recursión.

                _backtracking(tareaIndex+1, asignacionActual, tiempoMaximoNoRefrigerados );

                //eliminar de la solucion actual
//                asignacionActual.deleteTarea(p, this.tareas[tareaIndex]);
                p.deleteTarea(this.tareas[tareaIndex]);
            }

        }



        /*iteraciones++;
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

        }*/
    }


}
