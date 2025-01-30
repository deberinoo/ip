package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.JunoException;
import juno.Storage;

/**
 * Represents a command to mark a task as completed in the Juno application.
 * This command marks a specific task by its index as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking the specified task as completed.
     * It shows the updated task status to the user and saves the updated task list.
     *
     * @param tasks The task list containing all current tasks.
     * @param ui The user interface to interact with the user and show the task status.
     * @param storage The storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(taskIndex);
            ui.showMarked(tasks.getTask(taskIndex));
            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError("An error occurred: " + e.getMessage());
        }
    }
}
