package juno.command;

import java.util.HashMap;

import juno.task.Task;
import juno.task.TaskList;

public class MarkCommand extends Command {

    public MarkCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

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
