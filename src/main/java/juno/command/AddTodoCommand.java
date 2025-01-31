package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.JunoException;
import juno.Storage;
import task.TodoTask;
import task.Task;

/**
 * Represents a command to add a new Todo task.
 * This command parses the user input to create a TodoTask and adds it to the task list.
 */
public class AddTodoCommand extends Command {
    private String input;

    /**
     * Constructs an AddTodoCommand with the specified user input.
     * 
     * @param input The input string provided by the user to create the Todo task.
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a Todo task.
     * The method parses the input to extract the task description,
     * creates a new TodoTask, and adds it to the task list.
     * It also updates the task list in the storage and prints a success message.
     * 
     * @param tasks The task list to add the new task to.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The storage instance used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String description = input.trim();

            if (description.isEmpty()) {
                throw new JunoException("Juno: Please provide a valid description for the Todo task.");
            }

            Task newTask = new TodoTask(description);
            tasks.addTask(newTask);

            ui.showTodoAdded(newTask);
            ui.printTaskCount(tasks.size());

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }
    }
}
