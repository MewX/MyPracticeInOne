package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S682Test {

    @Test
    public void example1() {
        assertEquals(new S682().calPoints(new String[]{"5", "2", "C", "D", "+"}), 30);
    }

    @Test
    public void example2() {
        assertEquals(new S682().calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}), 27);
    }
}