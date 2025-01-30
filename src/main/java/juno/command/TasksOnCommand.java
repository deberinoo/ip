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

/**
 * Represents a command to list all tasks that are associated with a specific date in the Juno application.
 * This command checks for tasks that have deadlines or events occurring on the given date.
 */
public class TasksOnCommand extends Command {
    private String dateInput;

    /**
     * Constructs a TasksOnCommand with the specified date input.
     *
     * @param dateInput The date string for which tasks should be displayed.
     */
    public TasksOnCommand(String dateInput) {
        this.dateInput = dateInput;
    }

    /**
     * Executes the TasksOnCommand, filtering and displaying tasks that occur on the specified date.
     * This includes tasks with a deadline or events happening on the given date.
     *
     * @param tasks The task list containing all current tasks.
     * @param ui The user interface to interact with the user and show the tasks.
     * @param storage The storage system to load the tasks from.
     */
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
