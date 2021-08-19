package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S645Test {

    @Test
    public void testExample1() {
        assertEquals(new S645().findErrorNums(new int[]{1, 2, 2, 4}), new int[]{2, 3});
    }

    @Test
    public void testExample2() {
        assertEquals(new S645().findErrorNums(new int[]{3, 2, 3, 4, 6, 5}), new int[]{3, 1});
    }

    @Test
    public void testExample3() {
        assertEquals(new S645().findErrorNums(new int[]{1, 5, 3, 2, 2, 7, 6, 4, 8, 9}), new int[]{2, 10});
    }
}