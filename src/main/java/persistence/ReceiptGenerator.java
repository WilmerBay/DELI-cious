// persistence/ReceiptGenerator.java
package persistence;

import models.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptGenerator {

    private static final String PATH = "src/main/resources/receipt/";
    private static final double TAX_RATE = 0.095;

    public static void printReceipt(Order order) {

        System.out.println("======= DELI-cious Receipt =======");

        // Sandwiches
        int i = 1;
        for (Sandwich s : order.getSandwiches()) {
            System.out.printf("%d) %s\" %s sandwich%s    $%.2f%n",
                    i++,
                    s.getSize(), s.getBread(),
                    s.isToasted() ? " (Toasted)" : "",
                    s.calculateBasePrice()
            );
            System.out.println("Toppings:");
            if (s.getToppings().isEmpty()) {
                System.out.println(" - None");
            } else {
                for (Topping t : s.getToppings()) {
                    System.out.printf(" - %-20s $%.2f%n", t, t.getPrice(s.getSize()));
                }
            }
            System.out.println();
        }

        // Chips
        for (Chip c : order.getChips()) {
            System.out.printf("Chips: %-17s $%.2f%n", c.getName(), c.getPrice());
        }

        // Drinks
        for (Drink d : order.getDrinks()) {
            System.out.printf("Drink: %-17s $%.2f%n", d.getName(), d.getPrice());
        }

        // Totals
        double subtotal = order.calculateSubtotal();
        double tax      = subtotal * TAX_RATE;
        double total    = subtotal + tax;

        System.out.printf("%nSubtotal:%21s$%.2f%n", "", subtotal);
        System.out.printf("Tax (%.1f%%):%19s$%.2f%n", TAX_RATE * 100, "", tax);
        System.out.printf("Total:%25s$%.2f%n", "", total);
        System.out.println("==================================");
    }

    public static void writeReceiptToFile(Order order) {

        String filename = generateFilename();

        try (FileWriter writer = new FileWriter(PATH + filename)) {
            writer.write("======= DELI-cious Receipt =======\n");

            // Sandwiches
            int i = 1;
            for (Sandwich s : order.getSandwiches()) {
                writer.write(String.format("%d) %s\" %s sandwich%s    $%.2f%n",
                        i++,
                        s.getSize(), s.getBread(),
                        s.isToasted() ? " (Toasted)" : "",
                        s.calculateBasePrice()
                ));
                writer.write("Toppings:\n");
                if (s.getToppings().isEmpty()) {
                    writer.write(" - None\n");
                } else {
                    for (Topping t : s.getToppings()) {
                        writer.write(String.format(" - %-20s $%.2f%n", t, t.getPrice(s.getSize())));
                    }
                }
                writer.write("\n");
            }

            // Chips
            for (Chip c : order.getChips()) {
                writer.write(String.format("Chips: %-17s $%.2f%n", c.getName(), c.getPrice()));
            }

            // Drinks
            for (Drink d : order.getDrinks()) {
                writer.write(String.format("Drink: %-17s $%.2f%n", d.getName(), d.getPrice()));
            }

            // Totals
            double subtotal = order.calculateSubtotal();
            double tax      = subtotal * TAX_RATE;
            double total    = subtotal + tax;

            writer.write(String.format("%nSubtotal:%21s$%.2f%n", "", subtotal));
            writer.write(String.format("Tax (%.1f%%):%19s$%.2f%n", TAX_RATE * 100, "", tax));
            writer.write(String.format("Total:%25s$%.2f%n", "", total));
            writer.write("==================================\n");

            System.out.println("Receipt saved to file: " + filename);

        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        }
    }

    private static String generateFilename() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return LocalDateTime.now().format(formatter) + ".txt";
    }
}
