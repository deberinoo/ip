package task;

public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
