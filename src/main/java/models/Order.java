package models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Chip>     chips      = new ArrayList<>();
    private List<Drink>    drinks     = new ArrayList<>();

    public Order() {
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addChip(Chip chip) {
        chips.add(chip);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Chip> getChips() {
        return chips;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public double calculateSubtotal() {
        double total = 0.0;
        for (Sandwich s : sandwiches) {
            total += s.calculatePrice();
        }
        for (Chip c : chips) {
            total += c.getPrice();
        }
        for (Drink d : drinks) {
            total += d.getPrice();
        }
        return total;
    }
}
