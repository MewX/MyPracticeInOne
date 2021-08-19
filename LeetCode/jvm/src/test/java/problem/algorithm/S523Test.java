package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S523Test {

    @Test
    public void example1() {
        assertEquals(new S523().checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6), true);
    }

    @Test
    public void example2() {
        assertEquals(new S523().checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6), true);
    }
}