package juno.command;

import java.util.HashMap;
import java.util.List;

import juno.task.Task;
import juno.task.TaskList;

public class ListCommand extends Command {

    public ListCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    @Override
    public String execute(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();

        if (taskList.isEmpty()) {
            return "Juno: Your task list is empty. Add a new task to get started!";
        } else {
            StringBuilder result = new StringBuilder("Juno: Here are your current missions:\n");
            
            assert taskList.size() >= 0 : "Task list size cannot be negative";
            
            for (int i = 0; i < taskList.size(); i++) {
                result.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            return result.toString();
        }
    }
}