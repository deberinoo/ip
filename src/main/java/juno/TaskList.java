package juno;

import java.util.List;
import java.util.ArrayList;
import task.Task; 

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).unmarkAsDone();
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Juno: No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public Task getTask(int index) {
        // if (index < 0 || index >= tasks.size()) {
        //     throw new JunoException("Invalid task index.");
        // }
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    
    public int size() {
        return tasks.size();
    }
}
