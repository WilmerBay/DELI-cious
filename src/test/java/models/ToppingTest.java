package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToppingTest {

    @Test
    public void meatBasePriceBySize() {
        Topping t4 = new Topping("Steak", "meat", false);
        Topping t8 = new Topping("Steak", "meat", false);
        Topping t12 = new Topping("Steak", "meat", false);

        assertEquals(1.00, t4.getPrice("4"), 0.001);
        assertEquals(2.00, t8.getPrice("8"), 0.001);
        assertEquals(3.00, t12.getPrice("12"), 0.001);
    }

    @Test
    public void meatExtraChargeBySize() {
        Topping t4 = new Topping("Steak", "meat", true);
        Topping t8 = new Topping("Steak", "meat", true);
        Topping t12 = new Topping("Steak", "meat", true);

        assertEquals(1.50, t4.getPrice("4"), 0.001);
        assertEquals(3.00, t8.getPrice("8"), 0.001);
        assertEquals(4.50, t12.getPrice("12"), 0.001);
    }

    @Test
    public void cheeseBaseAndExtraBySize() {
        Topping normal  = new Topping("Cheddar", "cheese", false);
        Topping extra   = new Topping("Cheddar", "cheese", true);

        assertEquals(0.75, normal.getPrice("4"), 0.001);
        assertEquals(1.05, extra.getPrice("4"), 0.001);

        assertEquals(1.50, normal.getPrice("8"), 0.001);
        assertEquals(2.10, extra.getPrice("8"), 0.001);

        assertEquals(2.25, normal.getPrice("12"), 0.001);
        assertEquals(3.15, extra.getPrice("12"), 0.001);
    }

    @Test
    public void regularAndSauceAlwaysFree() {
        Topping reg   = new Topping("Lettuce", "regular", true);
        Topping sauce = new Topping("Mayo",    "sauce",   true);

        assertEquals(0.0, reg.getPrice("4"),   0.001);
        assertEquals(0.0, sauce.getPrice("12"),0.001);
    }
}