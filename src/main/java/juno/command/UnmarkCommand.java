package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import juno.JunoException;

/**
 * Represents a command to unmark a task in the Juno application.
 * This command changes the task status from "done" to "not done" and updates the task list.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

     /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to unmark in the task list.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

     /**
     * Executes the UnmarkCommand, which unmarks the specified task by changing its status to "not done".
     * The updated task list is saved after the task is unmarked.
     *
     * @param tasks The task list containing all current tasks.
     * @param ui The user interface to interact with the user and show the result.
     * @param storage The storage system to save the updated task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(taskIndex);
            ui.showUnmarked(tasks.getTask(taskIndex));
            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError("An error occurred: " + e.getMessage());
        }
    }
}
