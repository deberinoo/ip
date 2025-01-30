package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.JunoException;
import juno.Storage;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(taskIndex);
            ui.showMarked(tasks.getTask(taskIndex));
            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError("An error occurred: " + e.getMessage());
        }
    }
}
