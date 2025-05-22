import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String bread;
    private String size;
    private int price;
    private boolean isToasted;
    private List<Topping> toppings;

    public Sandwich (String size, String bread, boolean isToasted) {

        this.size = size;
        this.bread = bread;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }
    public void addTopping (Topping topping) {
        toppings.add(topping);
    }
    public double calculatePrice() {

        double basePrice = switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.00;
        };

        //for
    }
}