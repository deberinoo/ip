package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.JunoException;
import juno.Storage;
import task.TodoTask;
import task.Task;

public class AddTodoCommand extends Command {
    private String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String description = input.trim();

            if (description.isEmpty()) {
                throw new JunoException("Juno: Please provide a valid description for the Todo task.");
            }

            Task newTask = new TodoTask(description, false);
            tasks.addTask(newTask);

            ui.showTodoAdded(newTask);
            ui.printTaskCount(tasks.size());

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }
    }
}
