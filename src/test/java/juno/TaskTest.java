package juno;

import org.junit.jupiter.api.Test;

import juno.task.Deadline;
import juno.task.Event;
import juno.task.ToDo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class TaskTest {

     @Test
    public void testToDoToString() {
        ToDo ToDo = new ToDo("Test Todo");
        String expected = "[T][ ] Test Todo";
        assertEquals(expected, ToDo.toString());
    }

    @Test
    public void testDeadlineTaskToString() {
        LocalDate deadline = LocalDate.of(2025, 5, 25);
        Deadline deadlineTask = new Deadline("Test Deadline", deadline);
        String expected = "[D][ ] Test Deadline (by: May 25 2025)";
        assertEquals(expected, deadlineTask.toString());
    }

    @Test
    public void testEventTaskToString() {
        LocalDate fromDate = LocalDate.of(2025, 5, 25);
        LocalDate toDate = LocalDate.of(2025, 5, 26);
        Event eventTask = new Event("Test Event", fromDate, toDate);
        String expected = "[E][ ] Test Event (from: May 25 2025 to: May 26 2025)";
        assertEquals(expected, eventTask.toString());
    }
}