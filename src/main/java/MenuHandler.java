import java.util.Scanner;

public class MenuHandler { // UI for user's to input

    private final Scanner scanner;

    public MenuHandler() {
        scanner = new Scanner(System.in);
    }

    public void displayHomeScreen() { // called to Main

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

    private void startSandwichBuilder() {

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
        boolean toasted = false;

        while (true) {

            System.out.print("Would you like it toasted? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) {
                toasted = true;
                break;
            } else if (input.equals("n")) {
                toasted = false;
                break;
            }
            System.out.println("Please type 'y' or 'n'.");
        }

        Sandwich sandwich = new Sandwich(size, bread, toasted);

        while (true) {

            System.out.println("\nAdd a topping by entering the number or type 'done':");
            System.out.print("Topping name: ");
            String toppingName = scanner.nextLine().trim();

            if (toppingName.equalsIgnoreCase("done")) break;

            String toppingType = "";

            while (true) {

                System.out.println("""
                        Topping type:
                        1) Meat
                        2) Cheese
                        3) Regular
                        4) Sauce
                        """);

                System.out.print("> ");
                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1" -> toppingType = "meat";
                    case "2" -> toppingType = "cheese";
                    case "3" -> toppingType = "regular";
                    case "4" -> toppingType = "sauce";
                    default -> System.out.println("Invalid choice. Please select 1–4.");
                }
                if (!toppingType.isEmpty()) break;
            }

            boolean isExtra = false;

            while (true) {

                System.out.print("Add extra of this topping? (y/n): ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("y")) {
                    isExtra = true;
                    break;
                } else if (input.equals("n")) {
                    isExtra = false;
                    break;
                }
                System.out.println("Please type 'y' or 'n'.");
            }

            Topping topping = new Topping(toppingName, toppingType, isExtra);

            sandwich.addTopping(topping);

            System.out.println("Added: " + topping.toString());
        }

        System.out.println("""
                --- Sandwich Summary ---
                """);

        System.out.println(sandwich);
    }
}


