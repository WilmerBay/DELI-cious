public class Chip {

    private String name;

    public Chip(String name) {
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
