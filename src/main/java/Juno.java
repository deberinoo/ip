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
                listTasks(tasks);
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("todo ")) {
                addTodo(input.substring(5).trim());
            } else if (input.startsWith("deadline ")) {
                addDeadline(input.substring(9).trim());
            } else if (input.startsWith("event ")) {
                addEvent(input.substring(6).trim());
            } else {
                System.out.println("Juno: I don't understand that command.");
            }
        }
        sc.close();
    }

    public static void greet() {
        System.out.println("Juno: Hello! I'm Juno, born on Mars and eager to help. What task shall we tackle today?");
    }

    public static void exit() {
        System.out.println("Juno: Goodbye for now! Remember, even the smallest steps can lead to extraordinary destinations. See you soon!");
    }

    private static void addTodo(String description) {
        Task newTask = new TodoTask(description);
        tasks.add(newTask);
        System.out.println("Juno: Oh! That sounds fascinating. I've added \"" + description + "\" to our list.");
        printTaskCount();
    }

    private static void addDeadline(String input) {
        try {
            String[] parts = input.split(" /by ");
            String description = parts[0];
            String by = parts[1];
            Task newTask = new DeadlineTask(description, by);
            tasks.add(newTask);
            System.out.println("Juno: Got it! I've added: " + newTask + ". One small step toward completion!");
            printTaskCount();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Juno: Please specify the deadline in the format: description /by deadline.");
        }
    }

    private static void addEvent(String input) {
        try {
            String[] parts = input.split(" /from ");
            String description = parts[0];
            String[] timeParts = parts[1].split(" /to ");
            String from = timeParts[0];
            String to = timeParts[1];
            Task newTask = new EventTask(description, from, to);
            tasks.add(newTask);
            System.out.println("Juno: Event " + newTask + "added! Let's go!");
            printTaskCount();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Juno: Please specify the event in the format: description /from start_time /to end_time.");
        }
    }
    
    public static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Juno: No tasks here... yet. Ready to fill this up!");
        } else {
            System.out.println("Juno: Here are your current missions:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.println("Juno: Keep going, we’re making progress!");
        }
    }

    public static void markTask(String input) {
        int taskNumber = parseTaskNumber(input);
        if (taskNumber != -1 && isValidTaskNumber(taskNumber)) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("Juno: Amazing work! That task is officially complete.");
            System.out.println("  " + tasks.get(taskNumber));
        }
    }

    public static void unmarkTask(String input) {
        int taskNumber = parseTaskNumber(input);
        if (taskNumber != -1 && isValidTaskNumber(taskNumber)) {
            tasks.get(taskNumber).unmarkAsDone();
            System.out.println("Juno: Okay, this task is no longer marked as done. Back to work!");
            System.out.println("  " + tasks.get(taskNumber));
        }
    }

    private static int parseTaskNumber(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Juno: Please specify a valid task number.");
            return -1;
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < tasks.size();
    }

    private static void printTaskCount() {
        System.out.println("Juno: You have " + tasks.size() + " task(s) to go!");
    }
}


