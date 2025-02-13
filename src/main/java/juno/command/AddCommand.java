package juno.command;

import java.time.LocalDate;
import java.util.HashMap;

import juno.error.JunoException;
import juno.task.Deadline;
import juno.task.Event;
import juno.task.Task;
import juno.task.TaskList;
import juno.task.ToDo;
import juno.utility.Parser;

/**
 * Handles adding new tasks (ToDo, Deadline, Event) to the task list.
 */
public class AddCommand extends Command {
    
    /**
     * Constructs an AddCommand with the given command type, argument, and options.
     *
     * @param command  The type of task to add (e.g., "todo", "deadline", "event").
     * @param argument The main description of the task.
     * @param options  Additional parameters such as "/by" for deadlines and "/from" & "/to" for events.
     */
    public AddCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    /**
     * Executes the command by adding a task to the task list.
     *
     * @param tasks The task list to which the new task will be added.
     * @return A response message confirming the task addition.
     */
    @Override
    public String execute(TaskList tasks) {
        Task newTask;
        try {
            switch (command) {
            case "deadline":
                newTask = createDeadline();
                break;
            case "event":
                newTask = createEvent();
                break;
            default:
                newTask = new ToDo(argument);
            }
        } catch (JunoException e) {
            return "Juno Error: " + e.getMessage();
        }

        // Assert that the task is not null
        assert newTask != null : "Task should not be null after creation";

        int initialTaskCount = tasks.size();
        tasks.addTask(newTask);

        // Assert that the task list size has increased by one
        assert tasks.size() == initialTaskCount + 1 : "Task list size should increase by one after adding a task";

        String response = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
        return response;
    }

    /**
     * Creates a Deadline task using the provided options.
     *
     * @return A new Deadline task.
     * @throws JunoException If the "/by" option is missing or invalid.
     */
    private Task createDeadline() throws JunoException {
        String deadlineBy = options.get("by");
        if (deadlineBy == null) {
            throw new JunoException("Deadline option '/by' has not been provided.");
        }
        LocalDate byDate = Parser.parseDate(deadlineBy);

        // Assert that the parsed date is not null
        assert byDate != null : "Parsed date should not be null";

        return new Deadline(argument, byDate);
    }

    /**
     * Creates an Event task using the provided options.
     *
     * @return A new Event task.
     * @throws JunoException If either "/from" or "/to" options are missing or invalid.
     */
    private Task createEvent() throws JunoException {
        String from = options.get("from");
        String to = options.get("to");
        if (from == null || to == null) {
            throw new JunoException("Event requires both '/from' and '/to' options.");
        }
        LocalDate fromDate = Parser.parseDate(from);
        LocalDate toDate = Parser.parseDate(to);

        // Assert that the parsed dates are not null
        assert fromDate != null : "Parsed 'from' date should not be null";
        assert toDate != null : "Parsed 'to' date should not be null";

        return new Event(argument, fromDate, toDate);
    }
}
