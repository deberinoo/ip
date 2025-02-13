package juno.task;

/**
 * Represents the different types of tasks available.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

     /**
     * Returns the task type as a lowercase string.
     *
     * @return The task type in lowercase (e.g., "todo", "deadline", "event").
     */
    @Override
    public String toString() {
        return this.name().toLowerCase(); // Formats as "todo", "deadline", "event"
    }
}
