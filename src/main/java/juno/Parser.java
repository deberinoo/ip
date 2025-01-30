package juno;

import juno.command.*;
import task.TodoTask;  
import task.DeadlineTask; 
import task.EventTask;
import task.Task;   
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String input) throws JunoException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(Integer.parseInt(input.substring(5).trim()) - 1);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(Integer.parseInt(input.substring(7).trim()) - 1);
        } else if (input.startsWith("todo ")) {
            return new AddTodoCommand(input.substring(5).trim());
        } else if (input.startsWith("deadline ")) {
            return new AddDeadlineCommand(input.substring(9).trim());
        } else if (input.startsWith("event ")) {
            return new AddEventCommand(input.substring(6).trim());
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(Integer.parseInt(input.substring(7).trim()) - 1);
        } else if (input.startsWith("tasks on ")) {
            return new TasksOnCommand(input.substring(9).trim());
        } else {
            throw new JunoException("I don't understand your command. Can you try again?");
        }
    }

    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        DateTimeFormatter[] dateFormatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"), // Format like 2025-01-31
            DateTimeFormatter.ofPattern("MMM dd yyyy")  // Format like Jan 31 2025
        };

        switch (type) {
            case "T":
                return new TodoTask(description, isDone);
            case "D":
                LocalDate deadline = parseDate(parts[3], dateFormatters);
                return new DeadlineTask(description, deadline, isDone);
            case "E":
                LocalDate eventStart = parseDate(parts[3], dateFormatters);
                LocalDate eventEnd = parseDate(parts[4], dateFormatters);
                return new EventTask(description, eventStart, eventEnd, isDone);
            default:
                throw new IllegalArgumentException("Invalid task type in saved data.");
        }
    }

    private static LocalDate parseDate(String dateString, DateTimeFormatter[] formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (Exception e) {
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + dateString);
    }
}
