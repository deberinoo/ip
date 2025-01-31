package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import task.EventTask;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import juno.JunoException;

/**
 * Represents a command to add a new event task.
 * This command parses the user input to create an EventTask and adds it to the task list.
 */
public class AddEventCommand extends Command {
    private String input;

    /**
     * Constructs an AddEventCommand with the specified user input.
     * 
     * @param input The input string provided by the user to create the event task.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add an event task.
     * The method parses the input to extract the task description, start time, and end time,
     * creates a new EventTask, and adds it to the task list.
     * It also updates the task list in the storage and prints a success message.
     * 
     * @param tasks The task list to add the new task to.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The storage instance used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = input.split(" /from ");
            if (parts.length < 2) {
                throw new JunoException("Juno: Please specify the event in the format: description /from start_time /to end_time.");
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new JunoException("Juno: Please specify both start and end times in the format: description /from start_time /to end_time.");
            }
            String fromString = timeParts[0].trim();
            String toString = timeParts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate from = LocalDate.parse(fromString, formatter);
            LocalDate to = LocalDate.parse(toString, formatter);

            Task newTask = new EventTask(description, from, to);
            tasks.addTask(newTask);

            ui.showEventAdded(newTask);
            ui.printTaskCount(tasks.size());

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }
    }
}
