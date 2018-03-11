package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S766Test {

    @Test
    public void example1() {
        assertEquals(new S766().isToeplitzMatrix(
                new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}}), true);
    }

    @Test
    public void example2() {
        assertEquals(new S766().isToeplitzMatrix(new int[][]{{1, 2}, {2, 2}}), false);
    }
}