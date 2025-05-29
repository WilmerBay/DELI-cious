import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptGenerator {

    private static final double TAX_RATE = 0.095;

    public static void printReceipt(Order order) {

    }

    public static void writeReceiptToFile(Order order) { // writing text file

        String filename = generateFilename();

        try (FileWriter writer = new FileWriter(filename)) {

            Sandwich sandwich = order.getSandwich();
            Chip chip = order.getChip();
            Drink drink = order.getDrink();

            writer.write("======= DELI-cious Receipt =======\n");
            writer.write(sandwich.getSize() + "\" " + sandwich.getBread() + " sandwich\n");
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