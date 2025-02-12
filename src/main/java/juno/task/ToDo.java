package juno.task;

public class ToDo extends Task {
    public ToDo(String description) {
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
