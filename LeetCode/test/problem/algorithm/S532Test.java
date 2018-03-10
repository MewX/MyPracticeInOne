package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class S532Test {

    @Test
    public void example1() {
        assertEquals(new S532().findPairs(new int[]{3, 1, 4, 1, 5}, 2), 2);
    }

    @Test
    public void example2() {
        assertEquals(new S532().findPairs(new int[]{1, 2, 3, 4, 5}, 1), 4);
    }

    @Test
    public void example3() {
        assertEquals(new S532().findPairs(new int[]{1, 3, 1, 5, 4}, 0), 1);
    }
}