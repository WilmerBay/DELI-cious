public class Drink {

    private String name;

    public Drink(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // optional: override toString for clean printouts
    @Override
    public String toString() {
        return name;
    }
}
