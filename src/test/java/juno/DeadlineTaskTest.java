package juno;

import org.junit.jupiter.api.Test;

import juno.task.Deadline;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class DeadlineTaskTest {

    @Test
    void testGetDeadline() {
        LocalDate deadline = LocalDate.of(2025, 1, 31);
        Deadline deadlineTask = new Deadline("Finish project", deadline);

        assertEquals(deadline, deadlineTask.getDeadline(), "The deadline should be correctly set.");
    }

    @Test
    void testToStringWithoutDate() {
        LocalDate deadline = LocalDate.of(2025, 1, 31);
        Deadline deadlineTask = new Deadline("Finish project", deadline);
        
        String expected = "[D][ ] Finish project";
        assertEquals(expected, deadlineTask.toStringWithoutDate(), "The toStringWithoutDate method should return the expected string.");
    }
}