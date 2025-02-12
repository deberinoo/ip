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

public class AddCommand extends Command {
    
    public AddCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    @Override
    public String execute(TaskList tasks) {
        Task task;
        try {
            switch (command) {
            case "deadline":
                task = createDeadline();
                break;
            case "event":
                task = createEvent();
                break;
            default:
                task = new ToDo(argument);
            }
        } catch (JunoException e) {
            return "Juno Error: " + e.getMessage();
        }

        // Assert that the task is not null
        assert task != null : "Task should not be null after creation";

        int initialSize = tasks.size();
        tasks.addTask(task);

        // Assert that the task list size has increased by one
        assert tasks.size() == initialSize + 1 : "Task list size should increase by one after adding a task";

        String response = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
        return response;
    }

    private Task createDeadline() throws JunoException {
        String by = options.get("by");
        if (by == null) {
            throw new JunoException("Deadline option '/by' has not been provided.");
        }
        LocalDate byDate = Parser.parseDate(by);

        // Assert that the parsed date is not null
        assert byDate != null : "Parsed date should not be null";

        return new Deadline(argument, byDate);
    }

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
