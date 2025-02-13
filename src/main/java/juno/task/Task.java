package juno.task;

/**
 * Represents a generic task with a description, completion status, and type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with a description and type.
     *
     * @param description The task description.
     * @param taskType The type of task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Converts the task to a file-friendly format.
     *
     * @return A formatted string representation of the task.
     */
    public abstract String toFileFormat();

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

     /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" if done, " " if not done
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the task type.
     *
     * @return The type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Formatted string with status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}