import java.util.Scanner;

public class Juno {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(exit());
                break;
            } else {
                System.out.println(input);
            }
        }
    }

    public static String greet() {
        return "Hello! I'm Juno. What can I do for you?";
    }

    public static String exit() {
        return "Goodbye! Hope to see you again soon!";
    }
}
