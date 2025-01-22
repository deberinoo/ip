import java.util.*;

public class Juno {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> arr = new ArrayList<>();

        System.out.println(greet());

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(exit());
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (arr.isEmpty()) {
                    System.out.println("Juno: The list is empty.");
                } else {
                    System.out.println("Juno: Here's what you've added:");
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println((i + 1) + ". " + arr.get(i));
                    }
                }
            } else {
                arr.add(input);
                System.out.println("added: " + input);
            }
        }
        sc.close();
    }

    public static String greet() {
        return "Hello! I'm Juno. What can I do for you?";
    }

    public static String exit() {
        return "Goodbye! Hope to see you again soon!";
    }
}
