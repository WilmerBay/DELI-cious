public class Order {

    private Sandwich sandwich;
    private Chip chip;
    private Drink drink;

    public Order(Sandwich sandwich, Chip chip, Drink drink) {
        this.sandwich = sandwich;
        this.chip = chip;
        this.drink = drink;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public Chip getChip() {
        return chip;
    }

    public Drink getDrink() {
        return drink;
    }

    // Setters
    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return """
                --- Final Order ---
                """ +
                sandwich.toString() + "\n" +
                (chip != null ? "Chips: " + chip + "\n" : "") +
                (drink != null ? "Drink: " + drink + "\n" : "");
    }
}
