package juno.command;

import java.util.HashMap;

import juno.task.Task;
import juno.task.TaskList;

/**
 * Represents a command to mark or unmark a task in the task list.
 * This command allows the user to mark a task as completed or unmark it as incomplete.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand with the given parameters.
     *
     * @param command The type of command (either "mark" or "unmark").
     * @param argument The task index (1-based index) to be marked or unmarked.
     * @param options Additional options, if any.
     */
    public MarkCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    /**
     * Executes the mark or unmark command on the given task list.
     * Marks a task as completed or unmarks it based on the command.
     *
     * @param tasks The task list on which the task will be marked/unmarked.
     * @return A message indicating whether the task was marked or unmarked successfully.
     */
    @Override
    public String execute(TaskList tasks) {
        assert argument != null && !argument.isEmpty() : "Argument cannot be null or empty";

        try {
            int markIndex = Integer.parseInt(argument) - 1; // Convert input to zero-based index

            assert tasks.size() >= 0 : "Task list size cannot be negative";

            if (markIndex < 0 || markIndex >= tasks.size()) {
                return "Juno: Error! The specified task is out of range. Please try again.";
            }

            assert markIndex >= 0 && markIndex < tasks.size() : "The specified task index is out of range";

            Task markEntry = tasks.getTask(markIndex);

            if (command.equals("mark")) {
                markEntry.markAsDone();
                return "Juno: Nice! I've marked this task as done:\n  " + markEntry;
            } else if (command.equals("unmark")) {
                markEntry.unmarkAsDone();
                return "Juno: OK, I've marked this task as not done yet:\n  " + markEntry;
            } else {
                return "Juno: Error! The command is not recognized.";
            }
        } catch (NumberFormatException e) {
            return "Juno: Error! Invalid task index: " + argument;
        }
    }

}
