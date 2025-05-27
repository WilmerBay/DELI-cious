import java.util.Scanner;

public class MenuScreen {

    private final Scanner scanner;

    public MenuScreen() {
        scanner = new Scanner(System.in);
    }

    public void displayHomeScreen() { // called from Main.java

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
                case "1" -> startSandwichBuilder(); // starts sandwich builder
                case "0" -> {
                    System.out.println("Goodbye!");
                    return; // exits app
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

        // Makes Sandwich obj based on customers choice
        Sandwich sandwich = new Sandwich(size, bread, toasted);

        // Loop for adding toppings until user is happy
        while (true) {
            System.out.println("\nAdd a topping by entering the number or type 'done':");
            System.out.print("Topping name: ");
            String toppingName = scanner.nextLine().trim();

            if (toppingName.equalsIgnoreCase("done")) break; // ✅ allows user to finish toppings

            String toppingType = "";

            // Number options for topping type
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

            // Boolean prompt for marking topping as "extra"
            boolean extrCharge = false;

            while (true) {
                System.out.print("Add extra of this topping? (y/n): ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("y")) {
                    extrCharge = true;
                    break;
                } else if (input.equals("n")) {
                    extrCharge = false;
                    break;
                }
                System.out.println("Please type 'y' or 'n'.");
            }

            // Adds the topping to the sandwich
            Topping topping = new Topping(toppingName, toppingType, extrCharge);
            sandwich.addTopping(topping);

            System.out.println("Added: " + topping.toString());
        }

        // Summary printout using Sandwich.toString()
        System.out.println("""
                --- Sandwich Order ---
                """);

        System.out.println(sandwich);
    }
}
