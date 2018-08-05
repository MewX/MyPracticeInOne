package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S868Test {

    @Test
    public void testExample1() {
        assertEquals(2, new S868().binaryGap(22));
    }

    @Test
    public void testExample2() {
        assertEquals(2, new S868().binaryGap(5));
    }

    @Test
    public void testExample3() {
        assertEquals(1, new S868().binaryGap(6));
    }

    @Test
    public void testExample4() {
        assertEquals(0, new S868().binaryGap(8));
    }
}