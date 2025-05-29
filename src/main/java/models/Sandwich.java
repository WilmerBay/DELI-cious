package models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String bread;
    private String size;
    private int price;
    private boolean isToasted;
    private List<Topping> toppings;
    private Chip chip;

    public Sandwich (String size, String bread, boolean isToasted) {

        this.size = size;
        this.bread = bread;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }
    public void addTopping (Topping topping) { //adding to topping class

        toppings.add(topping);
    }
    public double calculatePrice() {

        double basePrice = switch (size) {

            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.00;
        };

        for (Topping topping : toppings) {
            basePrice += topping.getPrice(size);
        }
        return basePrice;
    }
    public String getSize() {
        return size;
    }

    public String getBread() {
        return bread;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public void setBread(String bread) {
        this.bread = bread.toLowerCase().trim();
    }

    public void setToasted(boolean isToasted) {
        this.isToasted = isToasted;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public Chip getChip() {
        return chip;
    }

    @Override
    public String toString() { // overide for receipt printout

        StringBuilder sb = new StringBuilder();

        sb.append(size).append("\" ").append(bread).append(" sandwich\n"); //adds size and bread to string builder

        if (isToasted) sb.append("Toasted\n"); // adds boolean to true for toasted for printing
        sb.append("Toppings:\n");

        if (toppings.isEmpty()) { //prints no toppings
            sb.append(" - None\n");
        } else {
            for (Topping topping : toppings) { // loop to read toppings and adds to sb
                sb.append(" - ").append(topping.toString()).append("\n");
            }
            if (chip != null && !chip.getName().equalsIgnoreCase("None")) {
                sb.append("Chips: ").append(chip).append("\n"); // Chip's toString() is used
            }
        }

        sb.append("Total: $").append(String.format("%.2f", calculatePrice()));
        return sb.toString(); // calculates price to have final info to send to receiptGen
    }
}