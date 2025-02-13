package juno.command;

import java.util.HashMap;
import java.util.List;

import juno.task.Task;
import juno.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * This command returns a message with all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the given parameters.
     *
     * @param command The type of command (e.g., "list").
     * @param argument The argument for the command (not used in this case).
     * @param options Additional options, if any.
     */
    public ListCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    /**
     * Executes the list command on the given task list.
     * Returns a list of all tasks currently in the task list.
     *
     * @param tasks The task list to display.
     * @return A message containing all tasks in the list, or a message indicating that the list is empty.
     */
    @Override
    public String execute(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();

        if (taskList.isEmpty()) {
            return "Juno: Your task list is empty. Add a new task to get started!";
        } else {
            StringBuilder result = new StringBuilder("Juno: Here are your current missions:\n");
            
            assert taskList.size() >= 0 : "Task list size cannot be negative";
            
            taskList.stream()
                    .map(task -> taskList.indexOf(task) + 1 + ". " + task)
                    .forEach(taskString -> result.append(taskString).append("\n"));
            return result.toString();
        }
    }
}