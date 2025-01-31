package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import task.DeadlineTask;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import juno.JunoException;

/**
 * Represents a command to add a new deadline task.
 * This command parses the user input to create a DeadlineTask and adds it to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String input;

    /**
     * Constructs an AddDeadlineCommand with the specified user input.
     * 
     * @param input The input string provided by the user to create the deadline task.
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a deadline task.
     * The method parses the input to extract the task description and deadline date,
     * creates a new DeadlineTask, and adds it to the task list.
     * It also updates the task list in the storage and prints a success message.
     * 
     * @param tasks The task list to add the new task to.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The storage instance used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = input.split(" /by ");
            if (parts.length < 2) {
                throw new JunoException("Juno: Please specify the deadline in the format: description /by deadline.");
            }
            String description = parts[0].trim();
            String byString = parts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate by = LocalDate.parse(byString, formatter);

            Task newTask = new DeadlineTask(description, by);
            tasks.addTask(newTask);

            ui.showDeadlineAdded(newTask);
            ui.printTaskCount(tasks.size());

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }
    }
}
