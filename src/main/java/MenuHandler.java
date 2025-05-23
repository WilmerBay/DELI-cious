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
    private void startSandwichBuilder(){}
}


