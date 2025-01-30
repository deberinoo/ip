package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;

/**
 * Represents a command to exit the Juno application.
 * This command will terminate the application and display a goodbye message to the user.
 */
public class ExitCommand extends Command {
    
     /**
     * Executes the exit command, showing a goodbye message to the user.
     * This will be the last command executed before the application shuts down.
     *
     * @param tasks The task list, which is not used in this command.
     * @param ui The user interface to interact with the user and show the goodbye message.
     * @param storage The storage system, which is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return true, indicating the command results in exiting the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
