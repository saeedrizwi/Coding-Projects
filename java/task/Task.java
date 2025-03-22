public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String toFileString() {
        return isDone + ";" + description;
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split(";", 2);
        Task task = new Task(parts[1]);
        if (parts[0].equals("true")) {
            task.markDone();
        }
        return task;
    }
}
