package juno.command;

import java.util.HashMap;

import juno.task.TaskList;

/**
 * Represents a command to find tasks in the task list that match a search term.
 * This command searches for tasks containing the given argument and returns the matching tasks.
 */

public class FindCommand extends Command {
    
    /**
     * Constructs a FindCommand with the given parameters.
     *
     * @param command The type of command (e.g., "find").
     * @param argument The search term to find matching tasks.
     * @param options Additional options, if any.
     */
    public FindCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    /**
     * Executes the find command on the given task list.
     * The tasks that contain the search term in their description are returned.
     *
     * @param tasks The task list to search through.
     * @return A message indicating the result of the search, with matching tasks.
     */
    @Override
    public String execute(TaskList tasks) {
        assert argument != null && !argument.isEmpty() : "Search term cannot be null or empty";
        
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Determines whether this command is an exit command.
     * 
     * @return false, as the find command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
