package juno;

import task.DeadlineTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class DeadlineTaskTest {

    @Test
    void testGetDeadline() {
        LocalDate deadline = LocalDate.of(2025, 1, 31);
        DeadlineTask deadlineTask = new DeadlineTask("Finish project", deadline);

        assertEquals(deadline, deadlineTask.getDeadline(), "The deadline should be correctly set.");
    }

    @Test
    void testToStringWithoutDate() {
        LocalDate deadline = LocalDate.of(2025, 1, 31);
        DeadlineTask deadlineTask = new DeadlineTask("Finish project", deadline);
        
        String expected = "[D][ ] Finish project";
        assertEquals(expected, deadlineTask.toStringWithoutDate(), "The toStringWithoutDate method should return the expected string.");
    }
}