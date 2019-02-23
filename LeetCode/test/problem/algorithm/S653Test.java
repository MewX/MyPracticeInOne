package problem.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TreeNodeTools;

public class S653Test {

    @Test
    public void testExample1() {
        Assert.assertEquals(new S653().findTarget(TreeNodeTools.buildBinaryTree(new Integer[]{5, 3, 6, 1, 4, null, 7}), 9), true);
    }

    @Test
    public void testExample2() {
        Assert.assertEquals(new S653().findTarget(TreeNodeTools.buildBinaryTree(new Integer[]{5, 3, 6, 1, 4, null, 7}), 28), false);
    }

    @Test
    public void regressionTest1() {
        // allow root.val * 2 == wanted
        Assert.assertEquals(new S653().findTarget(TreeNodeTools.buildBinaryTree(new Integer[]{2, 1, 3}), 4), true);
    }

    @Test
    public void regressionTest2() {
        // allow negative
        Assert.assertEquals(new S653().findTarget(TreeNodeTools.buildBinaryTree(new Integer[]{0, -3, 2, -4, null, 1}), 1), true);
    }

    @Test
    public void regressionTest3() {
        // allow one node * 2 = wanted
        Assert.assertEquals(new S653().findTarget(TreeNodeTools.buildBinaryTree(new Integer[]{2, null, 3}), 6), false);
    }

}