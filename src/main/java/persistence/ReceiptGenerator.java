package persistence;

import models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReceiptGenerator {

    private static final String PATH  = "src/main/resources/receipt/";

    private static final double TAX_RATE = 0.095;

    public static void printReceipt(Order order) {

        Sandwich sandwich = order.getSandwich();
        Chip chip = order.getChip();
        Drink drink = order.getDrink();

        System.out.println("======= DELI-cious Receipt =======");

        double sandwichBasePrice = sandwich.calculateBasePrice();
        System.out.printf("%s\" %s sandwich", sandwich.getSize(), sandwich.getBread());
        if (sandwich.isToasted()) {
            System.out.print(" (Toasted)");
        }
        System.out.printf("    $%.2f%n", sandwichBasePrice);

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
            drinkPrice = drink.getPrice();
            System.out.printf("Drink: %-17s $%.2f%n", drink, drinkPrice);
        }

        double subtotal = sandwich.calculatePrice() + chipPrice + drinkPrice;
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        System.out.printf("%nSubtotal:%21s$%.2f%n", "", subtotal);
        System.out.printf("Tax (%.1f%%):%19s$%.2f%n", TAX_RATE * 100, "", tax);
        System.out.printf("Total:%25s$%.2f%n", "", total);
        System.out.println("==================================");
    }


    public static void writeReceiptToFile(Order order) {

        String filename = generateFilename();

        try (FileWriter writer = new FileWriter(PATH + filename)) {
            Sandwich sandwich = order.getSandwich();
            Chip chip = order.getChip();
            Drink drink = order.getDrink();

            writer.write("======= DELI-cious Receipt =======\n");

            double sandwichBasePrice = sandwich.calculateBasePrice();
            writer.write(String.format("%s\" %s sandwich", sandwich.getSize(), sandwich.getBread()));
            if (sandwich.isToasted()) {
                writer.write(" (Toasted)");
            }
            writer.write(String.format("    $%.2f%n", sandwichBasePrice));

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
                drinkPrice = drink.getPrice();
                writer.write(String.format("Drink: %-17s $%.2f%n", drink, drinkPrice));
            }

            double subtotal = sandwich.calculatePrice() + chipPrice + drinkPrice;
            double tax = subtotal * TAX_RATE;
            double total = subtotal + tax;

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