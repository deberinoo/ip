package juno.command;

import java.util.HashMap;

import juno.task.TaskList;

/**
 * Represents a general command that can be executed in the task manager system.
 * Commands include specific actions like adding tasks, marking them as done, etc.
 */
public class Command {

    protected final String command;
    protected final String argument;
    protected final HashMap<String, String> options;

    /**
     * Constructs a Command with the given parameters.
     *
     * @param command The type of command (e.g., "todo", "deadline", "event").
     * @param argument The main description or argument for the command.
     * @param options Additional options such as dates for deadline/event tasks.
     */
    public Command(String command, String argument, HashMap<String, String> options) {
        this.command = command;
        this.argument = argument;
        this.options = options;
    }

    /**
     * Retrieves the command type.
     *
     * @return The type of the command.
     */
    public String getCommand() {
        return command;
    }

     /**
     * Retrieves the argument for the command.
     *
     * @return The argument passed with the command.
     */
    public String getArgument() {
        return argument;
    }

    /**
     * Executes the command on the given task list.
     * Default implementation returns a default error message.
     *
     * @param tasks The task list to operate on.
     * @return A response message after attempting to execute the command.
     */
    public String execute(TaskList tasks) {
        return "Sorry, I did not understand that command.";
    }

    /**
     * Determines if the command is an exit command.
     * The exit command is "bye".
     *
     * @return True if the command is "bye", otherwise false.
     */
    public boolean isExit() {
        return command.equalsIgnoreCase("bye");
    }
}
