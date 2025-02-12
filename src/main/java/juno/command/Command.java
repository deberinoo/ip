package juno.command;

import java.util.HashMap;

import juno.task.TaskList;

public class Command {

    protected final String command;
    protected final String argument;
    protected final HashMap<String, String> options;

    public Command(String command, String argument, HashMap<String, String> options) {
        this.command = command;
        this.argument = argument;
        this.options = options;
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public String execute(TaskList tasks) {
        return "Sorry, I did not understand that command.";
    }

    public boolean isExit() {
        return command.equalsIgnoreCase("bye");
    }
}
