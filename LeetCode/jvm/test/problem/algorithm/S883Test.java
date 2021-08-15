package problem.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S883Test {

    @Test
    public void testExample1() {
        Assert.assertEquals(5, new S883().projectionArea(new int[][]{{2}}));
    }

    @Test
    public void testExample2() {
        Assert.assertEquals(17, new S883().projectionArea(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testExample3() {
        Assert.assertEquals(8, new S883().projectionArea(new int[][]{{1, 0}, {0, 2}}));
    }

    @Test
    public void testExample4() {
        Assert.assertEquals(14, new S883().projectionArea(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
    }

    @Test
    public void testExample5() {
        Assert.assertEquals(21, new S883().projectionArea(new int[][]{{2, 2, 2}, {2, 1, 2}, {2, 2, 2}}));
    }

}