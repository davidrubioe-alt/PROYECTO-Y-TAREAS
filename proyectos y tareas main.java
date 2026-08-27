import java.util.ArrayList;
import java.util.List;

class Task {
    private String description;
    private String status;

    Task(String description) {
        this.description = description;
        this.status = "Pendiente";
    }

    @SuppressWarnings("unused")
    String getDescription() {
        return description;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "- " + description + " [" + status + "]";
    }
}

class Project {
    private String name;
    private String description;
    private final List<Task> tasks;
    private int maxTasks;

    Project(String name, String description, int maxTasks) {
        this.name = name != null ? name : "Sin nombre";
        this.description = description != null ? description : "Sin descripción";
        this.maxTasks = maxTasks;
        this.tasks = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    Project(List<Task> tasks) {
        this("Sin nombre", "Sin descripción", tasks != null ? tasks.size() : 0);
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    @SuppressWarnings("unused")
    Project(String description, int maxTasks, String name, List<Task> tasks) {
        this(name, description, maxTasks);
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    @SuppressWarnings("unused")
    Project(String name, List<Task> tasks) {
        this(name, "Sin descripción", tasks != null ? tasks.size() : 0);
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    boolean addTask(String taskDescription) {
        if (tasks.size() >= maxTasks) {
            System.out.println("Error: Se alcanzó el límite máximo de tareas (" + maxTasks + ").");
            return false;
        }
        tasks.add(new Task(taskDescription));
        return true;
    }

    void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setStatus("Completa");
        } else {
            System.out.println("Índice de tarea no válido.");
        }
    }

    void showTasks() {
        System.out.println("\n--- PROYECTO: " + name + " ---");
        System.out.println("Descripción: " + description);
        System.out.println("Tareas (" + tasks.size() + "/" + maxTasks + "):");
        
        if (tasks.isEmpty()) {
            System.out.println("(No hay tareas registradas)");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  [" + i + "] " + tasks.get(i));
        }
    }

    void showTasksByStatus(String status) {
        System.out.println("\n--- TAREAS FILTRADAS (" + status.toUpperCase() + ") ---");
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getStatus().equalsIgnoreCase(status)) {
                System.out.println("  [" + i + "] " + task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("(No se encontraron tareas con el estado: " + status + ")");
        }
    }

    @SuppressWarnings("unused")
    List<Task> getTasksByStatus(String status) {
        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase(status)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

    @SuppressWarnings("unused")
    List<Task> getTasks() {
        return tasks;
    }
}

@SuppressWarnings("unused")
class Main {
    public static void main(String[] args) {
        Project proyecto = new Project("Sistema Universitario", "Módulo de tareas", 5);
        
        proyecto.addTask("Diseñar base de datos");
        proyecto.addTask("Crear clases Java");
        proyecto.addTask("Escribir documentación");
        
        proyecto.markTaskAsCompleted(0);
        
        proyecto.showTasks();
        proyecto.showTasksByStatus("Pendiente");
        proyecto.showTasksByStatus("Completa");
    }
}