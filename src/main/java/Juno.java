import java.util.*;

public class Juno {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printTaskList(tasks);
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else {
                addTask(input);
            }
        }
        sc.close();
    }

    public static void greet() {
        System.out.println("Hello! I'm Juno. What can I do for you?");
    }

    public static void exit() {
        System.out.println("Goodbye! Hope to see you again soon!");
    }

    private static void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Juno: added: " + description);
    }
    
    public static void printTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Juno: The list is empty.");
        } else {
            System.out.println("Juno: Here's what you've added:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public static void markTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            if (isValidTaskNumber(taskNumber)) {
                tasks.get(taskNumber).markAsDone();
                System.out.println("Juno: Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskNumber));
            } else {
                System.out.println("Juno: Invalid task number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Juno: Please specify a valid task number to mark.");
        }
    }

    public static void unmarkTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            if (isValidTaskNumber(taskNumber)) {
                tasks.get(taskNumber).unmarkAsDone();
                System.out.println("Juno: OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskNumber));
            } else {
                System.out.println("Juno: Invalid task number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Juno: Please specify a valid task number to unmark.");
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < tasks.size();
    }
}
