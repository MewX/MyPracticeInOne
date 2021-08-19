package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S888Test {

    @Test
    public void testEx1() {
        assertEquals(new int[]{1, 2}, new S888().fairCandySwap(new int[]{1, 1}, new int[]{2, 2}));
    }

    @Test
    public void testEx2() {
        assertEquals(new int[]{1, 2}, new S888().fairCandySwap(new int[]{1, 2}, new int[]{2, 3}));
    }

    @Test
    public void testEx3() {
        assertEquals(new int[]{2, 3}, new S888().fairCandySwap(new int[]{2}, new int[]{1, 3}));
    }

    @Test
    public void testEx4() {
        assertEquals(new int[]{5, 4}, new S888().fairCandySwap(new int[]{1, 2, 5}, new int[]{2, 4}));
    }

}