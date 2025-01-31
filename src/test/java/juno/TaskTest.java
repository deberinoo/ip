package juno;

import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class TaskTest {

     @Test
    public void testTodoTaskToString() {
        TodoTask todoTask = new TodoTask("Test Todo");
        String expected = "[T][ ] Test Todo";
        assertEquals(expected, todoTask.toString());
    }

    @Test
    public void testDeadlineTaskToString() {
        LocalDate deadline = LocalDate.of(2025, 5, 25);
        DeadlineTask deadlineTask = new DeadlineTask("Test Deadline", deadline);
        String expected = "[D][ ] Test Deadline (by: May 25 2025)";
        assertEquals(expected, deadlineTask.toString());
    }

    @Test
    public void testEventTaskToString() {
        LocalDate fromDate = LocalDate.of(2025, 5, 25);
        LocalDate toDate = LocalDate.of(2025, 5, 26);
        EventTask eventTask = new EventTask("Test Event", fromDate, toDate);
        String expected = "[E][ ] Test Event (from: May 25 2025 to: May 26 2025)";
        assertEquals(expected, eventTask.toString());
    }
}