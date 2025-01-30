package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
