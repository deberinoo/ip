package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;

/**
 * Represents an abstract command in the Juno task management application.
 * All commands must extend this class and implement the execute() method.
 * This class provides a common interface for executing commands and checking 
 * whether the command signals the termination of the program.
 */
public abstract class Command {
    
     /**
     * Executes the command with the given task list, UI, and storage.
     * This method must be implemented by subclasses to define specific command actions.
     *
     * @param tasks The task list on which the command operates.
     * @param ui The user interface that interacts with the user.
     * @param storage The storage system used to save or load tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

     /**
     * Returns a boolean indicating whether the command should exit the program.
     * By default, commands do not exit the program, so this method returns false.
     * Subclasses can override this method to return true when the program should terminate.
     *
     * @return A boolean indicating whether to exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
