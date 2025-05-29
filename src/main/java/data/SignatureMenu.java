package data;

import models.SignatureSandwich;
import models.Topping;
import java.util.ArrayList;
import java.util.List;

public class SignatureMenu {
    private static final List<SignatureSandwich> sandwiches = new ArrayList<>();

    static {
        SignatureSandwich s1 = new SignatureSandwich(
                "The Classic Turkey Delight",
                "Turkey, Cheddar, Lettuce, Mayo (Toasted White, 8\")",
                "8", "white", true);
        s1.addTopping(new Topping("Turkey", "meat", false));
        s1.addTopping(new Topping("Cheddar", "cheese", false));
        s1.addTopping(new Topping("Lettuce", "regular", false));
        s1.addTopping(new Topping("Mayo", "sauce", false));

        SignatureSandwich s2 = new SignatureSandwich(
                "The Spicy Roast Beast",
                "Roast Beef, Swiss, Onion, Chipotle (Wheat, 12\")",
                "12", "wheat", false);
        s2.addTopping(new Topping("Roast Beef", "meat", true));
        s2.addTopping(new Topping("Swiss", "cheese", false));
        s2.addTopping(new Topping("Onion", "regular", false));
        s2.addTopping(new Topping("Chipotle", "sauce", true));

        SignatureSandwich s3 = new SignatureSandwich(
                "The Salamifornian Delight",
                "Salami, Provolone, Tomato, Ranch (Rye, 8\")",
                "8", "rye", false);
        s3.addTopping(new Topping("Salami", "meat", false));
        s3.addTopping(new Topping("Provolone", "cheese", false));
        s3.addTopping(new Topping("Tomato", "regular", false));
        s3.addTopping(new Topping("Ranch", "sauce", false));

        SignatureSandwich s4 = new SignatureSandwich(
                "The Saucy Sammy",
                "All Sauces on Wrap (4\")",
                "4", "wrap", false);
        s4.addTopping(new Topping("Mayo", "sauce", false));
        s4.addTopping(new Topping("Mustard", "sauce", false));
        s4.addTopping(new Topping("Chipotle", "sauce", false));
        s4.addTopping(new Topping("Ranch", "sauce", false));

        SignatureSandwich s5 = new SignatureSandwich(
                "The Veggie Crunch",
                "Lettuce, Tomato, Onion, Pickles, Mayo (Toasted Wheat, 12\")",
                "12", "wheat", true);
        s5.addTopping(new Topping("Lettuce", "regular", false));
        s5.addTopping(new Topping("Tomato", "regular", false));
        s5.addTopping(new Topping("Onion", "regular", false));
        s5.addTopping(new Topping("Pickles", "regular", false));
        s5.addTopping(new Topping("Mayo", "sauce", false));

        sandwiches.add(s1);
        sandwiches.add(s2);
        sandwiches.add(s3);
        sandwiches.add(s4);
        sandwiches.add(s5);
    }

    public static List<SignatureSandwich> getSandwiches() {
        return sandwiches;
    }

    public static SignatureSandwich getByIndex(int index) {
        if (index < 0 || index >= sandwiches.size()) return null;
        return sandwiches.get(index);
    }
}