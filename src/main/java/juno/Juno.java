package juno;
import juno.command.Command;

/**
 * The Juno class represents the main application that manages tasks, handles user interaction,
 * and stores/retrieves tasks from a file.
 * It serves as the entry point for the task management system.
 */
public class Juno {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

     /**
     * Constructs a new Juno instance with a specified file path to load/save tasks.
     * 
     * @param filePath The path of the file to load tasks from and save tasks to.
     */
    public Juno(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (JunoException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        
        this.tasks = loadedTasks;
    }

    /**
     * Starts the Juno application and runs the main task management loop.
     * The loop reads user commands, executes them, and continues until the exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (JunoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Juno application.
     * It creates a new Juno instance and runs the application with the specified file path.
     * 
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Juno("data/tasks.txt").run();
    }
}

