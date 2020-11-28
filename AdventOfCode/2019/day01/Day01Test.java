import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day01Test {

    @Test
    public void testCalcFuel() {
        assertEquals(Day01.calcFuel(12), 2);
        assertEquals(Day01.calcFuel(14), 2);
        assertEquals(Day01.calcFuel(1969), 654);
        assertEquals(Day01.calcFuel(100756), 33583);
    }

    @Test
    public void testCalcFuelRecursive() {
        assertEquals(Day01.calcFuelRecursive(14), 2);
        assertEquals(Day01.calcFuelRecursive(1969), 966);
        assertEquals(Day01.calcFuelRecursive(100756), 50346);
    }

}