package problem.algorithm;

import org.testng.annotations.Test;

public class S867Test extends TestBase {

    @Test
    public void testExample1() {
        assert2DArrayEqual(new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}},
                new S867().transpose(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testExample2() {
        assert2DArrayEqual(new int[][]{{1, 4}, {2, 5}, {3, 6}},
                new S867().transpose(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }
}