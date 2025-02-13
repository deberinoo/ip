package juno.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import juno.error.JunoException;
import juno.task.Task;
import juno.task.TaskList;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws JunoException If the file cannot be read.
     */
    public List<Task> load() throws JunoException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        
        if (!file.exists()) {
            return tasks; // No existing tasks file, return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Parser.parseTask(line));
            }
        } catch (IOException e) {
            throw new JunoException("Failed to read saved tasks.");
        }

        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws JunoException If the tasks cannot be saved.
     */
    public void save(TaskList tasks) throws JunoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new JunoException("Failed to save tasks.");
        }
    }
}