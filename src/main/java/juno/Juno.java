package juno;

import juno.command.Command;
import juno.error.JunoException;
import juno.task.TaskList;
import juno.utility.Parser;
import juno.utility.Storage;
import juno.utility.Ui;

public class Juno {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private static final String DEFAULT_FILE_PATH = "./data/juno.txt";

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

    public static void main(String[] args) {
        new Juno(DEFAULT_FILE_PATH).run();
    }

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
}

