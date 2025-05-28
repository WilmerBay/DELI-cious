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
    }
}
