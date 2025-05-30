package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrinkTest {

    @Test
    public void smallMediumLarge_prices() {
        Drink small  = new Drink("Soda", "small");
        Drink medium = new Drink("Iced Tea", "medium");
        Drink large  = new Drink("Water", "large");

        assertEquals(2.00, small.getPrice(),  0.001);
        assertEquals(2.50, medium.getPrice(), 0.001);
        assertEquals(3.50, large.getPrice(),  0.001);
    }

    @Test
    public void unknownSize_returnsZero() {
        Drink weird = new Drink("Lemonade", "giant");
        assertEquals(0.00, weird.getPrice(), 0.001);
    }
}