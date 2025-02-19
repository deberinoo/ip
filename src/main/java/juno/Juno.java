package juno;

import juno.command.Command;
import juno.error.JunoException;
import juno.task.TaskList;
import juno.utility.Parser;
import juno.utility.Storage;
import juno.utility.Ui;

/**
 * The main class for the Juno task management application.
 * Handles initialization, user interaction, and task management.
 */
public class Juno {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private static final String DEFAULT_FILE_PATH = "./data/juno.txt";

    /**
     * Initializes Juno with the specified file path for task storage.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Juno(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (JunoException e) {
            loadedTasks = new TaskList();
        }
        
        this.tasks = loadedTasks;
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user input string.
     * @return A response based on the executed command.
     */
    public String getResponse(String input) {
        if (input.isEmpty()) {
            return Ui.showWelcome();
        }

        try {
            Command c = Parser.parse(input);
            String reply = c.execute(tasks);
            storage.save(tasks);
            if (c.isExit()) {
                return Ui.showGoodbye();
            }
            return reply;
        } catch (JunoException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the Juno application and handles user interaction.
     */
    public void run() {
        Ui.showWelcome();
        boolean isRunning = true;
    
        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks);
                isRunning = command.isExit();
                
            } catch (JunoException e) {
                System.out.println(e.getMessage());
            }
        }
    
        Ui.showGoodbye();
    }

    /**
     * Initializes a new instance of Juno with the default file path
     * and starts the application by calling the {@link #run()} method.
     *
     * @param args Command-line arguments (ignored in this implementation).
     */
    public static void main(String[] args) {
        new Juno(DEFAULT_FILE_PATH).run();
    }
}

