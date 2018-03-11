package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S788Test {

    @Test
    public void example() {
        assertEquals(new S788().rotatedDigits(10), 4);
    }

    @Test
    public void custom() {
        assertEquals(new S788().rotatedDigits(101), 40);
    }
}