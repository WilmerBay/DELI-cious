package models;

public class Drink {
    private String name;
    private String size;

    public Drink(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.50;
            default -> 0.0;
        };
    }

    @Override
    public String toString() {
        return size.substring(0, 1).toUpperCase() + size.substring(1) + " " + name;
    }
}