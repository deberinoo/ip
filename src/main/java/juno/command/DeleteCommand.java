package juno.command;

import juno.TaskList;
import juno.Ui;
import task.Task;
import juno.JunoException;
import juno.Storage;

/**
 * Represents a command to delete a task from the task list in the Juno application.
 * The task to be deleted is identified by its index in the task list.
 */
public class DeleteCommand extends Command {
   private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the deletion of the task from the task list.
     * If the task index is invalid, an error message is shown.
     * After deletion, the task list is updated, and the task is saved to storage.
     *
     * @param tasks The task list to perform the deletion on.
     * @param ui The user interface to interact with the user and show feedback.
     * @param storage The storage system to save the updated task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showError("Task index is out of bounds.");
                return;
            }

            Task task = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);

            ui.showDeleted(task);

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError("An error occurred: " + e.getMessage());
        }
    }  
}
