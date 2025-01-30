package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import task.DeadlineTask;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import juno.JunoException;

public class AddDeadlineCommand extends Command {
    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = input.split(" /by ");
            if (parts.length < 2) {
                throw new JunoException("Juno: Please specify the deadline in the format: description /by deadline.");
            }
            String description = parts[0].trim();
            String byString = parts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate by = LocalDate.parse(byString, formatter);

            Task newTask = new DeadlineTask(description, by, false);
            tasks.addTask(newTask);

            ui.showDeadlineAdded(newTask);
            ui.printTaskCount(tasks.size());

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }
    }
}
