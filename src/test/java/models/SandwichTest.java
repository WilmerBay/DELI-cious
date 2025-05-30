package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SandwichTest {

    @Test
    public void basePriceBySize() {
        Sandwich s4 = new Sandwich("4",  "white", false);
        Sandwich s8 = new Sandwich("8",  "wheat", true);
        Sandwich s12= new Sandwich("12","rye",   false);

        assertEquals(5.50, s4.calculateBasePrice(), 0.001);
        assertEquals(7.00, s8.calculateBasePrice(), 0.001);
        assertEquals(8.50, s12.calculateBasePrice(),0.001);
    }

    @Test
    public void totalPriceWithToppings() {
        Sandwich s = new Sandwich("8", "white", false);
        s.addTopping(new Topping("Turkey", "meat", false));   // +2.00
        s.addTopping(new Topping("Cheddar", "cheese", true)); // + (1.50 + 0.60)
        s.addTopping(new Topping("Lettuce", "regular", false));// +0.00

        double expected = 7.00  // base
                + 2.00  // meat
                + 2.10  // cheese + extra
                + 0.00; // regular
        assertEquals(expected, s.calculatePrice(), 0.001);
    }
}
