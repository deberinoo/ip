package juno.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; 

/**
 * Represents a list of tasks that can be managed in the system. This class allows tasks to be added, removed,
 * marked/unmarked as done, and listed. It also provides methods to retrieve individual tasks and check the list's state.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a specified list of tasks.
     * 
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on the specified index.
     * 
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Marks a task as done based on the specified index.
     * 
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        }
    }

    /**
     * Unmarks a task as done based on the specified index.
     * 
     * @param index The index of the task to unmark as done.
     */
    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).unmarkAsDone();
        }
    }

     /**
     * Lists all the tasks in the task list. If the list is empty, it prints a message indicating that no tasks are available.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Juno: No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

     /**
     * Retrieves the task at the specified index.
     * 
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Searches the task list for tasks whose descriptions contain the specified keyword.
     * The search is case-insensitive, meaning both lowercase and uppercase characters are treated the same.
     * 
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks whose descriptions contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
    }

    /**
     * Retrieves the entire list of tasks.
     * 
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

     /**
     * Checks if the task list is empty.
     * 
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    
     /**
     * Retrieves the number of tasks in the task list.
     * 
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }
}
