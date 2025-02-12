package juno.utility;

import java.util.Scanner;

public class Ui {

    public Ui() {}

    public String readCommand() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    public static String showWelcome() {
        return "Juno: Hello! I'm Juno, born on Mars and eager to help. What task shall we tackle today?";
    }

    public static String showGoodbye() {
        return "Juno: Goodbye for now! Remember, even the smallest steps can lead to extraordinary destinations. See you soon!";
    }
}
