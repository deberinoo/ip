package juno;
import juno.command.*;

public class Juno {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Juno("data/tasks.txt").run();
    }
}

