package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChipTest {

    @Test
    public void noneChipIsFree() {
        Chip none = new Chip("None");
        assertEquals(0.00, none.getPrice(), 0.001);
    }

    @Test
    public void classicChipCosts150() {
        Chip classic = new Chip("Classic");
        assertEquals(1.50, classic.getPrice(), 0.001);
    }
}