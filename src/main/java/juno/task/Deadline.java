package juno.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task.
     *
     * @param description The task description.
     * @param by The due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        this.isDone = false;
    }

    /**
     * Returns the deadline date.
     *
     * @return The due date of the task.
     */
    public LocalDate getDeadline() {
        return this.by;
    }

     /**
     * Formats the task for file storage.
     *
     * @return A string representation in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
    
     /**
     * Returns a string representation of the deadline task.
     *
     * @return Formatted string with deadline information.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a string representation without the deadline date.
     *
     * @return Formatted string without deadline information.
     */
    public String toStringWithoutDate() {
        return "[D]" + super.toString();
    }
}
