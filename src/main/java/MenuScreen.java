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

        // Loop for adding toppings
        while (true) {
            System.out.println("\nAdd a topping or type 'done' to finish:");
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
        System.out.println(order);
    }
}