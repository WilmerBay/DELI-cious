public class Topping {

    private String name;
    private ToppingType type;
    private boolean isExtra;

    public enum ToppingType {

        MEAT, CHEESE, REGULAR, SAUCE // enums to use for case switch for the get price method
    }

    public Topping(String name, ToppingType type, boolean isExtra) {

        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    public double getPrice(String size) {

        double basePrice = 0.0;

        switch (type) {

            case MEAT -> {

                basePrice = switch (size) {
                    case "4" -> 1.00;
                    case "8" -> 2.00;
                    case "12" -> 3.00;
                    default -> 0.00;
                };
                if (isExtra) {
                    basePrice += switch (size) {
                        case "4" -> 0.50;
                        case "8" -> 1.00;
                        case "12" -> 1.50;
                        default -> 0.00;
                    };
                }
            }
            case CHEESE -> {
                basePrice = switch (size) {
                    case "4" -> 0.75;
                    case "8" -> 1.50;
                    case "12" -> 2.25;
                    default -> 0.00;
                };
                if (isExtra) {
                    basePrice += switch (size) {
                        case "4" -> 0.30;
                        case "8" -> 0.60;
                        case "12" -> 0.90;
                        default -> 0.00;
                    };
                }
            }
            case REGULAR, SAUCE -> {
                basePrice = 0.0; // included in sandwich, if it was an added topping we can use above format depending on size
            }
        }
        return basePrice;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return isExtra;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setType(ToppingType type) {
        this.type = type;
    }

    public void setExtra(boolean isExtra) {
        this.isExtra = isExtra;
    }
}

