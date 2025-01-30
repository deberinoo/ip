package juno.command;

import juno.TaskList;
import juno.Ui;
import task.Task;
import juno.JunoException;
import juno.Storage;

public class DeleteCommand extends Command {
   private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showError("Task index is out of bounds.");
                return;
            }

            Task task = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);

            ui.showDeleted(task);

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError("An error occurred: " + e.getMessage());
        }
    }  
}
