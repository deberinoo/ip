package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import task.EventTask;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import juno.JunoException;

public class AddEventCommand extends Command {
    private String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = input.split(" /from ");
            if (parts.length < 2) {
                throw new JunoException("Juno: Please specify the event in the format: description /from start_time /to end_time.");
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new JunoException("Juno: Please specify both start and end times in the format: description /from start_time /to end_time.");
            }
            String fromString = timeParts[0].trim();
            String toString = timeParts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate from = LocalDate.parse(fromString, formatter);
            LocalDate to = LocalDate.parse(toString, formatter);

            Task newTask = new EventTask(description, from, to, false);
            tasks.addTask(newTask);

            ui.showEventAdded(newTask);
            ui.printTaskCount(tasks.size());

            storage.save(tasks.getTasks());
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }
    }
}
