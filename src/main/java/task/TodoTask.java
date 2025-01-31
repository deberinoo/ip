package task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description, TaskType.TODO);
        this.isDone = false;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
