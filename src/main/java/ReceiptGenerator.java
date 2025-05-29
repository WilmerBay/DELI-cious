import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptGenerator {

    private static final double TAX_RATE = 0.095;

    public static void printReceipt(Order order) {
        Sandwich sandwich = order.getSandwich();
        Chip chip = order.getChip();
        Drink drink = order.getDrink();

        System.out.println("======= DELI-cious Receipt =======\n");
        double basePrice = switch (sandwich.getSize()) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.00;
        };
        System.out.printf("%s\" %s sandwich: $%.2f%n", sandwich.getSize(), sandwich.getBread(), basePrice);

        if (sandwich.isToasted()) {
            System.out.println("Toasted");
        }

        System.out.println("Toppings:");
        if (sandwich.getToppings().isEmpty()) {
            System.out.println(" - None");
        } else {
            for (Topping topping : sandwich.getToppings()) {
                double price = topping.getPrice(sandwich.getSize());
                System.out.printf(" - %-20s $%.2f%n", topping, price);
            }
        }

        double chipPrice = 0.0;
        if (chip != null && !chip.getName().equalsIgnoreCase("None")) {
            chipPrice = 1.50;
            System.out.printf("Chips: %-17s $%.2f%n", chip, chipPrice);
        }

        double drinkPrice = 0.0;
        if (drink != null && !drink.getName().equalsIgnoreCase("None")) {
            drinkPrice = 1.50;
            System.out.printf("Drink: %-17s $%.2f%n", drink, drinkPrice);
        }

        double subtotal = sandwich.calculatePrice() + chipPrice + drinkPrice;
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        System.out.printf("%nSubtotal: $%.2f%n", subtotal);
        System.out.printf("Tax (%.1f%%): $%.2f%n", TAX_RATE * 100, tax);
        System.out.printf("Total: $%.2f%n", total);
        System.out.println("==================================");
    }

    public static void writeReceiptToFile(Order order) {
        String filename = generateFilename();

        try (FileWriter writer = new FileWriter(filename)) {

            Sandwich sandwich = order.getSandwich();
            Chip chip = order.getChip();
            Drink drink = order.getDrink();

            writer.write("======= DELI-cious Receipt =======\n");
            double basePrice = switch (sandwich.getSize()) {
                case "4" -> 5.50;
                case "8" -> 7.00;
                case "12" -> 8.50;
                default -> 0.00;
            };
            writer.write(String.format("%s\" %s sandwich: $%.2f%n", sandwich.getSize(), sandwich.getBread(), basePrice));

            if (sandwich.isToasted()) {
                writer.write("Toasted\n");
            }

            writer.write("Toppings:\n");
            if (sandwich.getToppings().isEmpty()) {
                writer.write(" - None\n");
            } else {
                for (Topping topping : sandwich.getToppings()) {
                    double price = topping.getPrice(sandwich.getSize());
                    writer.write(String.format(" - %-20s $%.2f%n", topping, price));
                }
            }

            double chipPrice = 0.0;
            if (chip != null && !chip.getName().equalsIgnoreCase("None")) {
                chipPrice = 1.50;
                writer.write(String.format("Chips: %-17s $%.2f%n", chip, chipPrice));
            }

            double drinkPrice = 0.0;
            if (drink != null && !drink.getName().equalsIgnoreCase("None")) {
                drinkPrice = 1.50;
                writer.write(String.format("Drink: %-17s $%.2f%n", drink, drinkPrice));
            }

            double subtotal = sandwich.calculatePrice() + chipPrice + drinkPrice;
            double tax = subtotal * TAX_RATE;
            double total = subtotal + tax;

            writer.write(String.format("%nSubtotal: $%.2f%n", subtotal));
            writer.write(String.format("Tax (%.1f%%): $%.2f%n", TAX_RATE * 100, tax));
            writer.write(String.format("Total: $%.2f%n", total));
            writer.write("==================================\n");

            System.out.println("Receipt saved to file: " + filename);

        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        }
    }

    private static String generateFilename() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return "receipt_" + LocalDateTime.now().format(formatter) + ".txt";
    }
}