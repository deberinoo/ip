package juno.command;

import juno.TaskList;
import juno.Ui;
import juno.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import task.DeadlineTask;
import task.EventTask;
import task.Task;

public class TasksOnCommand extends Command {
    private String dateInput;

    public TasksOnCommand(String dateInput) {
        this.dateInput = dateInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksOnDate = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateInput, formatter);
        
        for (Task task : tasks.getTasks()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.getDeadline().equals(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getFrom().equals(date) || eventTask.getTo().equals(date)) {
                    tasksOnDate.add(task);
                }
            }
        }


        ui.showTasksOnDate(tasksOnDate, date);
    }

}
