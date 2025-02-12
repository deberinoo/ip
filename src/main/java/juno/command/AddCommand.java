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
