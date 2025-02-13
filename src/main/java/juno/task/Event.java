package juno.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task.
     *
     * @param description The event description.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate  to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
        this.isDone = false;
    }
    
    /**
     * Returns the event's start date.
     *
     * @return The start date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the event's end date.
     *
     * @return The end date.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Formats the event task for file storage.
     *
     * @return A string representation in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

     /**
     * Returns a string representation of the event task.
     *
     * @return Formatted string with event duration.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation without the event dates.
     *
     * @return Formatted string without date information.
     */
    public String toStringWithoutDate() {
        return "[E]" + super.toString();
    }
}
