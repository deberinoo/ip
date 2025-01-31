package juno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import task.Task;

/**
 * Represents the storage system for loading and saving tasks from/to a file.
 * This class is responsible for reading tasks from a file and writing tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     * 
     * @param filePath The path to the file where tasks are stored or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the storage file. If the file doesn't exist, an empty list is returned.
     * 
     * @return A list of tasks loaded from the file.
     * @throws JunoException If an error occurs while reading the file.
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
     * Saves the list of tasks to the storage file.
     * Each task is written in a specific file format.
     * 
     * @param tasks The list of tasks to be saved.
     * @throws JunoException If an error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws JunoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new JunoException("Failed to save tasks.");
        }
    }
}