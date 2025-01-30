package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import juno.JunoException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(taskIndex);
            ui.showUnmarked(tasks.getTask(taskIndex));
            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError("An error occurred: " + e.getMessage());
        }
    }
}
