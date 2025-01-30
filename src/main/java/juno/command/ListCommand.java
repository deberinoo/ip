package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}