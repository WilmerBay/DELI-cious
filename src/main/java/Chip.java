public class Chip {
    private String name;
    private double price;

    public Chip(String name) {
        this.name = name;
        this.price = name.equalsIgnoreCase("None") ? 0.00 : 1.50;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
