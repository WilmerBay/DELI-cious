package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void subtotal_withMixOfItems() {
        Order order = new Order();

        // add two sandwiches:
        Sandwich a = new Sandwich("4", "white", false); // 5.50
        Sandwich b = new Sandwich("12","wheat", true);  // 8.50
        order.addSandwich(a);
        order.addSandwich(b);

        // add chips + drink
        order.addChip(new Chip("Classic"));             // +1.50
        order.addDrink(new Drink("Soda", "large"));     // +3.50

        double expected = 5.50 + 8.50 + 1.50 + 3.50;
        assertEquals(expected, order.calculateSubtotal(), 0.001);
    }
}