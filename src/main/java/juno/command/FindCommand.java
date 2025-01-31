package juno.command;

import juno.Storage;
import juno.TaskList;
import juno.Ui;

/**
 * The FindCommand class represents a command that searches for tasks by keyword.
 * When executed, it will search for tasks that contain the specified keyword
 * and display the matching tasks to the user.
 * 
 * Example usage:
 * <pre>
 *     FindCommand findCommand = new FindCommand("book");
 *     findCommand.execute(taskList, ui, storage);
 * </pre>
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand instance with the given search keyword.
     * 
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand. It searches for tasks that contain the keyword
     * and displays the matching tasks to the user.
     * 
     * @param tasks The task list containing all tasks.
     * @param ui The user interface used to interact with the user.
     * @param storage The storage system used to load/save tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatchingTasks(tasks.findTasks(keyword));
    }

    /**
     * Returns whether this command will cause the application to exit.
     * 
     * @return false since the FindCommand does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
