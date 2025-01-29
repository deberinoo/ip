package task;

public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
