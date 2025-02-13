package juno.command;

import java.util.HashMap;
import juno.task.TaskList;

public class HelpCommand extends Command {

    private static final String COMMANDS = """
            Here are a list of commands I support:
            list - shows your current todo list
            todo <task name> - adds a todo task
            deadline <task name> /by <deadline> - adds a deadline task
            event <task name> /from <event start> /to <event end> - adds an event task
            mark <task number> - marks task at its index
            unmark <task number> - unmarks task at its index
            delete <task number> - deletes task at its index
            find <keyword> - find tasks containing the keyword
            list /tag <tag> - list tasks by tag
            help - shows this help message
            """;

    public HelpCommand(String command, String argument, HashMap<String, String> options) {
        super(command, argument, options);
    }

    @Override
    public String execute(TaskList tasks) {
        return COMMANDS;
    }

    @Override
    public boolean isExit() {
        return false; // Help command doesn't exit the program
    }
}
