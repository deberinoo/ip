package juno.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.HashMap;

import juno.command.AddCommand;
import juno.command.Command;
import juno.command.DeleteCommand;
import juno.command.FindCommand;
import juno.command.ListCommand;
import juno.command.MarkCommand;
import juno.error.JunoException;
import juno.task.Deadline;
import juno.task.Event;
import juno.task.Task;
import juno.task.ToDo;

public class Parser {

    public static Command parse(String commandString) throws JunoException {
        // Split the input into main command and options using "/"
        String[] parts = commandString.split("/");

        // Main Command (first part before any "/")
        String[] main = parts[0].split(" ", 2);
        String command = main[0]; // The main command (e.g., "deadline")
        String argument = "";
        if (main.length > 1) {
            argument = main[1].trim(); // The argument for the command (e.g., "submit report")
        }

        // Options (from parts after the "/")
        HashMap<String, String> options = new HashMap<>();
        for (int i = 1; i < parts.length; i++) {
            String option = parts[i].trim();
            if (!option.isEmpty()) {
                String[] optionParts = option.split(" ", 2);
                if (optionParts.length == 2) {
                    // Option with a value (e.g., "/by 2025-03-01")
                    options.put(optionParts[0].trim(), optionParts[1].trim());
                } else {
                    // Option without a value (e.g., "/priority")
                    options.put(optionParts[0].trim(), "");
                }
            }
        }

        // Return the appropriate Command object based on the parsed input
        switch (command) {
            case "list":
                return new ListCommand(command, argument, options);
            case "mark":
            case "unmark":
                return new MarkCommand(command, argument, options);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(command, argument, options);
            case "delete":
                return new DeleteCommand(command, argument, options);
            case "find":
                return new FindCommand(command, argument, options);
            default:
                throw new JunoException("I don't understand your command. Can you try again?");
        }
    }
    
    public static Task parseTask(String line) throws JunoException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description);
            case "D":
                LocalDate deadline = parseDate(parts[3]);
                return new Deadline(description, deadline);
            case "E":
                LocalDate eventStart = parseDate(parts[3]);
                LocalDate eventEnd = parseDate(parts[4]);
                return new Event(description, eventStart, eventEnd);
            default:
                throw new IllegalArgumentException("Invalid task type in saved data.");
        }
    }

    public static LocalDate parseDate(String dateString) throws JunoException {
        try {
            dateString = dateString.trim();
            
            // Create the formatter with multiple patterns
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    .parseDefaulting(ChronoField.ERA, 1)
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

            // Parse the date using the formatter
            LocalDate date = LocalDate.parse(dateString, formatter);
            return date;

        } catch (DateTimeParseException e) {
            throw new JunoException("Invalid date format: " + dateString + "\nPlease provide the date in one of the following formats:\n"
                    + "yyyy-MM-dd (e.g., 2021-10-15)\n"
                    + "dd-MM-yyyy (e.g., 15-10-2021)\n"
                    + "MMM dd yyyy (e.g., Oct 15 2021)");
        }
    }
}
