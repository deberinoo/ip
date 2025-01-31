package juno;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import task.DeadlineTask;
import task.EventTask;
import task.Task;

/**
 * Represents the user interface of Juno. This class is responsible for interacting with the user
 * and displaying information based on user inputs and task status.
 */
public class Ui {
    private final Scanner scanner;

     /**
     * Constructs a new Ui instance that will be used to interact with the user.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the command entered by the user.
     * 
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

     /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Juno: Hello! I'm Juno, born on Mars and eager to help. What task shall we tackle today?");
    }

     /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Juno: Goodbye for now! Remember, even the smallest steps can lead to extraordinary destinations. See you soon!");
    }

     /**
     * Displays an error message when tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        System.out.println("Juno: Unable to load tasks from file. Starting with an empty list.");
    }

    /**
     * Displays the current list of tasks.
     * 
     * @param tasks The task list to display.
     */
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

     /**
     * Displays tasks that are scheduled for a specific date.
     * 
     * @param tasks The list of tasks to check.
     * @param date The date to filter tasks by.
     */
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

    /**
     * Displays the list of tasks that match the search keyword.
     * If no tasks match the search criteria, a message indicating this is shown.
     * Otherwise, it displays the matching tasks with their indices.
     * 
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
    
    /**
     * Displays a message indicating a Todo task was added successfully.
     * 
     * @param task The task that was added.
     */
    public void showTodoAdded(Task task) {
        System.out.println("Juno: Oh! That sounds fascinating. I've added \"" + task + "\" to our list.");
    }

     /**
     * Displays a message indicating a Deadline task was added successfully.
     * 
     * @param task The task that was added.
     */
    public void showDeadlineAdded(Task task) {
        System.out.println("Juno: Got it! I've added: " + task + ". One small step toward completion!");
    }

    /**
     * Displays a message indicating an Event task was added successfully.
     * 
     * @param task The task that was added.
     */
    public void showEventAdded(Task task) {
        System.out.println("Juno: Event " + task + " added! Let's go!");
    }

     /**
     * Displays a message indicating a task was marked as complete.
     * 
     * @param task The task that was marked.
     */
    public void showMarked(Task task) {
        System.out.println("Juno: Amazing work! That task is officially complete.");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating a task was unmarked as complete.
     * 
     * @param task The task that was unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println("Juno: Okay, this task is no longer marked as done. Back to work!");
        System.out.println("  " + task);
    }

     /**
     * Displays a message indicating a task was deleted.
     * 
     * @param task The task that was deleted.
     */
    public void showDeleted(Task task) {
        System.out.println("Juno: Noted. I've removed this task:");
        System.out.println("  " + task);
    }

     /**
     * Displays an error message.
     * 
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("Juno: " + errorMessage);
    }

    /**
     * Displays the current task count.
     * 
     * @param taskCount The number of tasks.
     */
    public void printTaskCount(int taskCount) {
        System.out.println("Juno: You have " + taskCount + " task(s) to go!");
    }

     /**
     * Displays a message indicating an invalid task number was provided.
     */
    public void showInvalidInput() {
        System.out.println("Juno: Please specify a valid task number.");
    }

    /**
     * Displays a custom message.
     * 
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
