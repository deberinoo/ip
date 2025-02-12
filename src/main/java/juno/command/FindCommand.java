package juno.command;

import java.util.HashMap;

import juno.task.TaskList;

public class FindCommand extends Command {
     public FindCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
