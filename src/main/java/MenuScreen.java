import java.util.List;
import java.util.Scanner;

public class MenuScreen {

    private final Scanner scanner;

    public MenuScreen() {
        scanner = new Scanner(System.in);
    }

    public void displayHomeScreen() {
        while (true) {
            System.out.println("""
                    =========================
                    Welcome to DELI-cious!
                    =========================
                    1) New Sandwich
                    2) Signature Sandwich
                    0) Exit
                    """);

            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> startSandwichBuilder();
                case "2" -> displaySignatureMenu();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displaySignatureMenu() {
        System.out.println("--- Signature Sandwiches ---");
        List<SignatureSandwich> list = SignatureMenu.getSandwiches();

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s - %s%n", i + 1, list.get(i).getName(), list.get(i).getDescription());
        }
        System.out.println("0) Back to Main Menu");

        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.equals("0")) return;

        try {
            int choice = Integer.parseInt(input) - 1;
            SignatureSandwich selected = SignatureMenu.getByIndex(choice);
            if (selected != null) {

                Order order = new Order(selected, new Chip("Classic"), new Drink("Soda"));
                ReceiptGenerator.printReceipt(order);
                ReceiptGenerator.writeReceiptToFile(order);
                System.out.println("Thank you for ordering a signature sandwich!");
                System.out.println("Press Enter to exit...");
                scanner.nextLine();
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Returning to menu.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
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

        // Loop for adding toppings
        while (true) {
            System.out.println("""
            Choose a topping type:
            1) Meat
            2) Cheese
            3) Regular
            4) Sauce
            5) Done
            6) Remove Topping
            """);

            System.out.print("> ");
            String typeChoice = scanner.nextLine().trim();

            if (typeChoice.equals("5")) break;

            if (typeChoice.equals("6")) { // removing a topping list loop plus prompting useer to enter a number as a script, parsing to int and exception incase they dont enter a number.
                if (sandwich.getToppings().isEmpty()) {
                    System.out.println("No toppings to remove.");
                    continue;
                }

                System.out.println("Current toppings:");
                for (int i = 0; i < sandwich.getToppings().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getToppings().get(i));
                }

                System.out.print("Enter number to remove, or press enter to cancel removal: ");
                String removeInput = scanner.nextLine().trim();

                try {
                    int removeIndex = Integer.parseInt(removeInput) - 1;
                    if (removeIndex < 0 || removeIndex >= sandwich.getToppings().size()) {
                        System.out.println("Invalid selection.");
                    } else {
                        Topping removed = sandwich.getToppings().remove(removeIndex);
                        System.out.println("Removed: " + removed);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }

                continue;
            }

            if (!typeChoice.matches("[1-4]")) {
                System.out.println("Invalid choice. Please select 1–4 or type 'done'.");
                continue;
            }

            String toppingType = switch (typeChoice) {
                case "1" -> "meat";
                case "2" -> "cheese";
                case "3" -> "regular";
                case "4" -> "sauce";
                default -> ""; // This will never happen due to prior validation above with the break and matching types
            };

            String[] options = switch (toppingType) {
                case "meat" -> new String[]{"Turkey", "Ham", "Roast Beef", "Salami"};
                case "cheese" -> new String[]{"Cheddar", "Swiss", "Provolone", "American"};
                case "regular" -> new String[]{"Lettuce", "Tomato", "Onion", "Pickles",    };
                case "sauce" -> new String[]{"Mayo", "Mustard", "Chipotle", "Ranch"};
                default -> new String[0];
            };

            // Display numbered options
            System.out.println("Available " + toppingType + "s:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }

            System.out.print("Select a topping by number: ");
            String indexInput = scanner.nextLine().trim();

            int index;
            try {
                index = Integer.parseInt(indexInput) - 1;
                if (index < 0 || index >= options.length) {
                    System.out.println("Invalid number. Try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            String toppingName = options[index];

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

            Topping topping = new Topping(toppingName, toppingType, extrCharge);
            sandwich.addTopping(topping);
            System.out.println("Added: " + topping);
        }
        // making chip variable
        Chip chip = null;

        while (true) {
            System.out.println("""
                    Choose a chip:
                    1) Classic
                    2) BBQ
                    3) Sour Cream
                    4) Jalapeno
                    5) None
                    """);

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> chip = new Chip("Classic");
                case "2" -> chip = new Chip("BBQ");
                case "3" -> chip = new Chip("Sour Cream");
                case "4" -> chip = new Chip("Jalapeno");
                case "5" -> chip = new Chip("None");
                default -> System.out.println("Invalid choice. Please choose 1–5.");
            }

            if (chip != null) break;
        }

        // Order class introduced for all the info - sandwich drink, chip
        Drink drink = null;

        while (true) {
            System.out.println("""
                    Choose a drink:
                    1) Water
                    2) Soda
                    3) Iced Tea
                    4) Lemonade
                    5) None
                    """);

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> drink = new Drink("Water");
                case "2" -> drink = new Drink("Soda");
                case "3" -> drink = new Drink("Iced Tea");
                case "4" -> drink = new Drink("Lemonade");
                case "5" -> drink = new Drink("None");
                default -> System.out.println("Invalid choice. Please choose 1–5.");
            }

            if (drink != null) break;
        }
        // creates order with all items the customer has chosen.
        Order order = new Order(sandwich, chip, drink);

        // order print out
        System.out.println("""
                --- Final Order ---
                """);
        ReceiptGenerator.printReceipt(order);
        ReceiptGenerator.writeReceiptToFile(order);
        System.out.println("Thank you for ordering a signature sandwich!");
        System.out.println("Press Enter to exit...");
        scanner.nextLine(); // wait for user input before closing
        System.exit(0);
    }
}