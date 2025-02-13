package juno.task;

/**
 * Represents a simple to-do task without a specific deadline or duration.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
        this.isDone = false;
    }

     /**
     * Converts the ToDo task to a file-friendly format.
     *
     * @return A formatted string representation for storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return Formatted string with task type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
