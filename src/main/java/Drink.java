public class Drink {
    private String name;
    private double price;

    public Drink(String name) {
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
