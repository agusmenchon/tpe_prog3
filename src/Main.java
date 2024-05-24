import entities.Tarea;

public class Main {
    public static void main(String[] args) {

            Servicios servicios = new Servicios("src/datasets/Procesadores.csv", "src/datasets/Tareas.csv");
            Tarea t = new Tarea("A", "1", 1, true, 5);
            System.out.print(t);
        }
    }
