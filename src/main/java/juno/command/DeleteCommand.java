package juno.command;

import java.util.HashMap;

import juno.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task based on the provided index.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the given parameters.
     *
     * @param command The type of command (e.g., "delete").
     * @param argument The task index to be deleted, passed as a string.
     * @param options Additional options, if any.
     */
    public DeleteCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    /**
     * Executes the delete command on the given task list.
     * The task at the specified index is removed.
     *
     * @param tasks The task list to operate on.
     * @return A message indicating the result of the deletion.
     */
    @Override
    public String execute(TaskList tasks) {
        int deleteIndex = Integer.parseInt(argument);

        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            return "The specified task is out of range. Please try again.";
        } else {
            assert deleteIndex < tasks.size() && deleteIndex >= 0 : "The specified task is out of range";
            
            String result = "Noted. I've removed this task:\n  " + tasks.getTask(deleteIndex - 1).toString();
            tasks.deleteTask(deleteIndex - 1);

            result += "\nNow you have " + tasks.size() + " tasks in the list.";

            return result;
        }
    }
}
