package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate by;

    public DeadlineTask(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String toStringWithoutDate() {
        return "[D]" + super.toString();
    }
}
