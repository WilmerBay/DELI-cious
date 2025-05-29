public class SignatureSandwich extends Sandwich {
    private final String name;
    private final String description;

    public SignatureSandwich(String name, String description, String size, String bread, boolean toasted) {
        super(size, bread, toasted);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}