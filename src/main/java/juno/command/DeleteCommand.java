package juno.command;

import java.util.HashMap;

import juno.task.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

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
