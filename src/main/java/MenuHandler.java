import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuHandler { // UI for user's to input

    private final Scanner scanner;

    public MenuHandler() {
        scanner = new Scanner(System.in);
    }

    public void displayHomeScreen() {

        while (true) {
            System.out.println("""
                    =========================
                    Welcome to DELI-cious!
                    =========================
                    1) New Sandwich
                    0) Exit
                    """);

            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> startSandwichBuilder();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private void startSandwichBuilder(){

        System.out.println("""
        --- Build Your Sandwich ---
        """);

        String size = "";

        while (true) {
            System.out.println("""
            Choose a size:
            1) 4"
            2) 8"
            3) 12"
            """);

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> size = "4";
                case "2" -> size = "8";
                case "3" -> size = "12";
                default -> System.out.println("Invalid choice. Please try again.");
            }
            if (!size.isEmpty()) break;
        }

        String bread = "";

        while (true) {
            System.out.println("""
            Choose bread:
            1) White
            2) Wheat
            3) Rye
            4) Wrap
            """);

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> bread = "white";
                case "2" -> bread = "wheat";
                case "3" -> bread = "rye";
                case "4" -> bread = "wrap";
                default -> System.out.println("Invalid choice. Please try again 1–4.");
            }
            if (!bread.isEmpty()) break;
        }
    }
}


