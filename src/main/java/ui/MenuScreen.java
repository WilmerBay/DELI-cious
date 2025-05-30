// ui/MenuScreen.java
package ui;

import data.SignatureMenu;
import models.*;
import persistence.ReceiptGenerator;
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
                    1) New Order
                    2) Signature Sandwich
                    0) Exit
                    """);
            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> displayOrderScreen();
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
            System.out.printf("%d) %s - %s%n",
                    i + 1,
                    list.get(i).getName(),
                    list.get(i).getDescription()
            );
        }
        System.out.println("0) Back to app.Main Menu");

        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.equals("0")) return;

        try {
            int choice = Integer.parseInt(input) - 1;
            SignatureSandwich selected = SignatureMenu.getByIndex(choice);

            if (selected != null) {
                Order order = new Order();
                order.addSandwich(selected);
                order.addChip(new Chip("Classic"));
                order.addDrink(new Drink("Soda", "medium"));

                ReceiptGenerator.printReceipt(order);
                ReceiptGenerator.writeReceiptToFile(order);

                System.out.println("Thank you for ordering a signature sandwich!");
            } else {
                System.out.println("Invalid choice. Returning to menu.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }

    private void displayOrderScreen() {
        Order order = new Order();

        while (true) {
            System.out.println("""
                    --- Order Screen ---
                    1) Add Sandwich
                    2) Add Chip
                    3) Add Drink
                    4) Checkout
                    0) Cancel Order
                    """);
            System.out.print("> ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    Sandwich s = buildSandwich();
                    order.addSandwich(s);
                    System.out.println("Sandwich added!");
                }
                case "2" -> {
                    Chip c = selectChip();
                    order.addChip(c);
                    System.out.println("Chips added!");
                }
                case "3" -> {
                    Drink d = selectDrink();
                    order.addDrink(d);
                    System.out.println("Drink added!");
                }
                case "4" -> {
                    if (order.getSandwiches().isEmpty()) {
                        System.out.println("You need at least one sandwich.");
                    } else {
                        ReceiptGenerator.printReceipt(order);
                        ReceiptGenerator.writeReceiptToFile(order);
                        System.out.println("Thank you for your order!");
                        return;
                    }
                }
                case "0" -> {
                    System.out.println("Order canceled.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private Sandwich buildSandwich() {
        System.out.println("--- Build Your Sandwich ---");

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
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
            }
            break;
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
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
            }
            break;
        }

        boolean toasted = false;
        while (true) {
            System.out.print("Would you like it toasted? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                toasted = true;
                break;
            } else if (input.equals("n")) {
                break;
            }
            System.out.println("Please type 'y' or 'n'.");
        }

        Sandwich sandwich = new Sandwich(size, bread, toasted);

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

            if (typeChoice.equals("6")) {
                if (sandwich.getToppings().isEmpty()) {
                    System.out.println("No toppings to remove.");
                    continue;
                }
                System.out.println("Current toppings:");
                for (int i = 0; i < sandwich.getToppings().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getToppings().get(i));
                }
                System.out.print("Enter number to remove, or press enter to cancel: ");
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
                    // cancel
                }
                continue;
            }

            if (!typeChoice.matches("[1-4]")) {
                System.out.println("Invalid choice.");
                continue;
            }

            String toppingType = switch (typeChoice) {
                case "1" -> "meat";
                case "2" -> "cheese";
                case "3" -> "regular";
                default -> "sauce";
            };

            String[] options = switch (toppingType) {
                case "meat"   -> new String[]{"Turkey", "Ham", "Roast Beef", "Salami", "Chicken Breast", "Meat Ball"};
                case "cheese" -> new String[]{"Cheddar", "Swiss", "Provolone", "American", "Pepper Jack"};
                case "regular"-> new String[]{"Lettuce", "Tomato", "Onion", "Pickles", "Peppers", "Jalapeños", "Cucumbers", "Mushrooms", "Guacamole"};
                default      -> new String[]{"Mayo", "Mustard", "Chipotle", "Ranch", "Honey Mustard", "Aus jus"};
            };

            System.out.println("Available " + toppingType + "s:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.println("Press enter to leave");
            System.out.print("Select a topping by number: ");
            String indexInput = scanner.nextLine().trim();
            int index;
            try {
                index = Integer.parseInt(indexInput) - 1;
                if (index < 0 || index >= options.length) {
                    System.out.println("Invalid number.");
                    continue;
                }
            } catch (NumberFormatException e) {
                continue;
            }

            String toppingName = options[index];
            boolean extraCharge = false;
            while (true) {
                System.out.print("Add extra of this topping? (y/n): ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("y")) {
                    extraCharge = true;
                    break;
                } else if (input.equals("n")) {
                    break;
                }
                System.out.println("Please type 'y' or 'n'.");
            }

            sandwich.addTopping(new Topping(toppingName, toppingType, extraCharge));
            System.out.println("Added: " + toppingName);
        }

        return sandwich;
    }

    private Chip selectChip() {
        System.out.println("""
                Choose a chip:
                1) Classic
                2) BBQ
                3) Sour Cream & Onion
                4) None
                """);
        System.out.print("> ");
        return switch (scanner.nextLine().trim()) {
            case "1" -> new Chip("Classic");
            case "2" -> new Chip("BBQ");
            case "3" -> new Chip("Sour Cream & Onion");
            default  -> new Chip("None");
        };
    }

    private Drink selectDrink() {
        System.out.println("""
                Choose a drink:
                1) Water
                2) Soda
                3) Iced Tea
                4) Lemonade
                5) None
                """);
        System.out.print("> ");
        String c = scanner.nextLine().trim();
        if (c.equals("5")) {
            return new Drink("None", "small");
        }
        String name = switch (c) {
            case "1" -> "Water";
            case "2" -> "Soda";
            case "3" -> "Iced Tea";
            default  -> "Lemonade";
        };
        System.out.println("""
                Choose drink size:
                1) Small
                2) Medium
                3) Large
                """);
        System.out.print("> ");
        String sz = scanner.nextLine().trim();
        String size = switch (sz) {
            case "1" -> "small";
            case "3" -> "large";
            default  -> "medium";
        };
        return new Drink(name, size);
    }
}
