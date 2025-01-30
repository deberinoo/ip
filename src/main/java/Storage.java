import java.io.*;
import java.time.LocalDate;
import java.util.*;
import task.*;

public class Storage {
    private static final String FILE_PATH = "./data/juno.txt";

    public static void saveTasks(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure the directory exists
            FileWriter writer = new FileWriter(file);
            
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            
            writer.close();
        } catch (IOException e) {
            System.out.println("Juno: Oops! I couldn't save your tasks.");
        }
    }

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return empty list if file does not exist
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Juno: No previous tasks found. Starting fresh!");
        } catch (Exception e) {
            System.out.println("Juno: The file is corrupted. Starting fresh!");
        }
        
        return tasks;
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        try {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    return new TodoTask(description, isDone);
                case "D":
                    return new DeadlineTask(description, LocalDate.parse(parts[3]), isDone);
                case "E":
                    return new EventTask(description, parts[3], parts[4], isDone);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null; 
        }
    }
}