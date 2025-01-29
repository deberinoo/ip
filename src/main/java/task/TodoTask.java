package task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
