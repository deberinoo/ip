package juno;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import task.DeadlineTask;
import task.EventTask;
import task.Task;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Juno: Hello! I'm Juno, born on Mars and eager to help. What task shall we tackle today?");
    }

    public void showGoodbye() {
        System.out.println("Juno: Goodbye for now! Remember, even the smallest steps can lead to extraordinary destinations. See you soon!");
    }

    public void showLoadingError() {
        System.out.println("Juno: Unable to load tasks from file. Starting with an empty list.");
    }

    public void showTasks(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();
        
        if (taskList.isEmpty()) {
            System.out.println("Juno: Your task list is empty. Add a new task to get started!");
        } else {
            System.out.println("Juno: Here are your current missions:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    public void showTasksOnDate(List<Task> tasks, LocalDate date) {
        boolean found = false;
        System.out.println("Here are your tasks for " + date + ":");
        for (Task task : tasks) {
            if (task instanceof DeadlineTask) {
                if (((DeadlineTask) task).getDeadline().equals(date)) {
                    System.out.println(((DeadlineTask) task).toStringWithoutDate());
                    found = true;
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getFrom().equals(date) || eventTask.getTo().equals(date)) {
                    System.out.println(eventTask.toStringWithoutDate());
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("No tasks found for this date.");
        }
    }
    

    public void showTodoAdded(Task task) {
        System.out.println("Juno: Oh! That sounds fascinating. I've added \"" + task + "\" to our list.");
    }

    public void showDeadlineAdded(Task task) {
        System.out.println("Juno: Got it! I've added: " + task + ". One small step toward completion!");
    }

    public void showEventAdded(Task task) {
        System.out.println("Juno: Event " + task + " added! Let's go!");
    }

    public void showMarked(Task task) {
        System.out.println("Juno: Amazing work! That task is officially complete.");
        System.out.println("  " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("Juno: Okay, this task is no longer marked as done. Back to work!");
        System.out.println("  " + task);
    }

    public void showDeleted(Task task) {
        System.out.println("Juno: Noted. I've removed this task:");
        System.out.println("  " + task);
    }

    public void showError(String errorMessage) {
        System.out.println("Juno: " + errorMessage);
    }

    public void printTaskCount(int taskCount) {
        System.out.println("Juno: You have " + taskCount + " task(s) to go!");
    }

    public void showInvalidInput() {
        System.out.println("Juno: Please specify a valid task number.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
