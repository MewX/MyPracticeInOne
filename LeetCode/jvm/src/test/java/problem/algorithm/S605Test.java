package problem.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class S605Test {

    @Test
    public void testExample1() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1), true);
    }

    @Test
    public void testExample2() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2), false);
    }

    @Test
    public void testCustom1() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{1, 0, 0, 1, 0, 0}, 1), true);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{1, 0, 0, 1, 0, 0}, 2), false);
    }

    @Test
    public void testCustom2() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 0, 1, 0, 0, 1, 1}, 1), true);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 0, 1, 0, 0, 1, 1}, 2), false);
    }

    @Test
    public void testCustom3() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 1, 0, 0, 1, 1}, 0), true);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 1, 0, 0, 1, 1}, 1), false);
    }

    @Test
    public void testRegression1() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0}, 1), true);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{1}, 1), false);
    }

    @Test
    public void testRegression2() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 0}, 1), true);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 0}, 2), false);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{1, 0}, 1), false);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 1}, 1), false);
    }


    @Test
    public void testRegression3() {
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 0, 0}, 2), true);
        Assert.assertEquals(new S605().canPlaceFlowers(new int[]{0, 0, 0}, 3), false);
    }
}