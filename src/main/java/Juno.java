import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import task.TodoTask;  
import task.DeadlineTask; 
import task.EventTask;
import task.Task;  

public class Juno {
    private static List<Task> tasks;

    public static void main(String[] args) {
        tasks = Storage.loadTasks();
        greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            try {
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
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else if (input.startsWith("tasks on ")) {
                    printTasksOnDate(input);
                } else {
                    throw new JunoException("Juno: Oops! I didn't quite catch that. Can you try again?");
                }
                Storage.saveTasks(tasks);
            } catch (JunoException e) {
                System.out.println(e.getMessage());
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
        Task newTask = new TodoTask(description, false);
        tasks.add(newTask);
        System.out.println("Juno: Oh! That sounds fascinating. I've added \"" + description + "\" to our list.");
        printTaskCount();
    }

    private static void addDeadline(String input) {
        try {
            String[] parts = input.split(" /by ");
            if (parts.length < 2) {
                throw new JunoException("Juno: Please specify the deadline in the format: description /by deadline.");
            }

            String description = parts[0].trim();
            String byString = parts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate by = LocalDate.parse(byString, formatter);

            Task newTask = new DeadlineTask(description, by, false);
            tasks.add(newTask);
            System.out.println("Juno: Got it! I've added: " + newTask + ". One small step toward completion!");
            printTaskCount();
        } catch (JunoException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Juno: Invalid date format! Please use yyyy-MM-dd.");
        }
    }

    private static void addEvent(String input) {
        try {
            String[] parts = input.split(" /from ");
            if (parts.length < 2) {
                throw new JunoException("Juno: Please specify the event in the format: description /from start_time /to end_time.");
            }
            String description = parts[0];
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new JunoException("Juno: Please specify both start and end times in the format: description /from start_time /to end_time.");
            }
            String from = timeParts[0];
            String to = timeParts[1];
            Task newTask = new EventTask(description, from, to, false);
            tasks.add(newTask);
            System.out.println("Juno: Event " + newTask + " added! Let's go!");
            printTaskCount();
        } catch (JunoException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void deleteTask(String input) {
        int taskNumber = parseTaskNumber(input);
        if (taskNumber == -1) {
            return;
        }
    
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Juno: Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            printTaskCount();
        } else {
            System.out.println("Juno: Please provide a valid task number within the range.");
        }
    }
    
    public static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Juno: No tasks here... yet. Ready to fill this up!");
        } else {
            System.out.println("Juno: Here are your current missions:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
            System.out.println((i + 1) + ". [" + task.getTaskType().toString().toUpperCase() + "] " + task);
            }
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

    public static void printTasksOnDate(String input) {
        try {
            String dateString = input.substring(9).trim();
            LocalDate queryDate = LocalDate.parse(dateString);
            boolean hasTasks = false;
    
            for (Task task : tasks) {
                if (task instanceof DeadlineTask) {
                    if (((DeadlineTask) task).getBy().equals(queryDate)) {
                        System.out.println(((DeadlineTask) task).toStringWithoutDate());
                        hasTasks = true;
                    }
                } else if (task instanceof EventTask) {
                    LocalDate fromDate = ((EventTask) task).getFrom();
                    LocalDate toDate = ((EventTask) task).getTo();
    
                    if (fromDate != null && toDate != null) {
                        if ((queryDate.isEqual(fromDate) || queryDate.isAfter(fromDate)) && queryDate.isBefore(toDate.plusDays(1))) {
                            System.out.println(((EventTask) task).toStringWithoutDate());
                            hasTasks = true;
                        }
                    }
                }
            }
    
            if (!hasTasks) {
                System.out.println("Juno: No tasks found on the given date.");
            }
    
        } catch (DateTimeParseException e) {
            System.out.println("Juno: Please enter the date in the format YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Juno: An unexpected error occurred.");
        }
    }
    
}

