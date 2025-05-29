public class ReceiptGenerator {

    private static final double TAX_RATE = 0.095; // 9.5% tax rate set to final calculating the price later one

    public static void printReceipt(Order order) {
        Sandwich sandwich = order.getSandwich();
        Chip chip = order.getChip();
        Drink drink = order.getDrink();

        System.out.println("======= DELI-cious Receipt =======");
        System.out.println(sandwich.getSize() + "\" " + sandwich.getBread() + " sandwich");
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
}
