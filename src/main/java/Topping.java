public class Topping {

    private String name;
    private String type;
    private boolean isExtra;

    public Topping(String name, String type, boolean isExtra) {

        this.name = name;
        this.type = type.toLowerCase().trim();
        this.isExtra = isExtra;
    }

    public double getPrice(String size) {

        double basePrice = 0.0;

        if (type.equals("meat")) {

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
        } else if (type.equals("cheese")) {
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
        } else if (type.equals("regular") || type.equals("sauce")) {
            basePrice = 0.0;  // Always included
        } else {
            System.out.println("Warning: Unknown topping type '" + type + "'");
        }

        return basePrice;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isExtra() {
        return isExtra;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type.toLowerCase().trim();
    }

    public void setExtra(boolean isExtra) {
        this.isExtra = isExtra;
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "");
    }
}

