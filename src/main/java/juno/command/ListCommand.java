package juno.command;

import juno.Storage;
import juno.TaskList;
import juno.Ui;

/**
 * Represents a command to list all tasks in the Juno application.
 * This command will display all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks to the user.
     * The task list will be shown to the user via the UI.
     *
     * @param tasks The task list containing all current tasks.
     * @param ui The user interface to interact with the user and show the task list.
     * @param storage The storage system, which is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}