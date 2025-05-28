public class ReceiptGenerator {

    private static final double TAX_RATE = 0.095; // 9.5% tax rate set to final calculating the price later one

    public static void printReceipt(Order order) {
        //calling all classes getters
        Sandwich sandwich = order.getSandwich();
        Chip chip = order.getChip();
        Drink drink = order.getDrink();

        System.out.println("======= DELI-cious Receipt =======");

        // grabs bread type, size and lists if it is toasted or not all in an if loop
        System.out.println(sandwich.getSize() + "\" " + sandwich.getBread() + " sandwich");
        if (sandwich.isToasted()) {
            System.out.println("Toasted");
        }

        System.out.println("Toppings:");
        if (sandwich.getToppings().isEmpty()) {
            System.out.println(" - None");
        } else {
            for (Topping topping : sandwich.getToppings()) {
                System.out.println(" - " + topping);
            }
        }

        // if loop for Chips, basically prints out "none because you dont have chips"
        if (chip != null && !chip.getName().equalsIgnoreCase("None")) {
            System.out.println("Chips: " + chip);
        }

        // if loop for Drink, same as chips
        if (drink != null && !drink.getName().equalsIgnoreCase("None")) {
            System.out.println("Drink: " + drink);
        }
        // logic to run calculations, creating variables here calling to the sandwich class to get the price of sandwich, set that price to get the tax, and then finally getting the total by addint the two
        double subtotal = sandwich.calculatePrice();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        System.out.printf("%nSubtotal: $%.2f%n", subtotal); // showing subtotal formatted
        System.out.printf("Tax (%.1f%%): $%.2f%n", TAX_RATE * 100, tax); // showing tax
        System.out.printf("Total: $%.2f%n", total); // final total
        System.out.println("==================================");
    }
}
