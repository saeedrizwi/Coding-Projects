import java.util.*;
import java.io.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
    
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Task Manager ===");
                System.out.println("1. Add Task");
                System.out.println("2. List Tasks");
                System.out.println("3. Mark Task as Done");
                System.out.println("4. Delete Task");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
    
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline
    
                switch (choice) {
                    case 1:
                        System.out.print("Enter task description: ");
                        String desc = scanner.nextLine();
                        tasks.add(new Task(desc));
                        break;
    
                    case 2:
                        listTasks();
                        break;
    
                    case 3:
                        listTasks();
                        System.out.print("Enter task number to mark as done: ");
                        int doneIndex = scanner.nextInt() - 1;
                        if (doneIndex >= 0 && doneIndex < tasks.size()) {
                            tasks.get(doneIndex).markDone();
                            System.out.println("Marked as done.");
                        } else {
                            System.out.println("Invalid task number.");
                        }
                        break;
    
                    case 4:
                        listTasks();
                        System.out.print("Enter task number to delete: ");
                        int delIndex = scanner.nextInt() - 1;
                        if (delIndex >= 0 && delIndex < tasks.size()) {
                            tasks.remove(delIndex);
                            System.out.println("Task deleted.");
                        } else {
                            System.out.println("Invalid task number.");
                        }
                        break;
    
                    case 5:
                        saveTasks();
                        System.out.println("Goodbye!");
                        return;
    
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
    
    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = Task.fromFileString(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
