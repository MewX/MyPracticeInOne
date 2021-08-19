package problem.algorithm;

import org.testng.annotations.Test;
import utils.TreeNodeTools;

import static org.testng.Assert.*;

public class S872Test {

    @Test
    public void testLeafSimilar() {
        assertTrue(new S872().leafSimilar(
                TreeNodeTools.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4}),
                TreeNodeTools.buildBinaryTree(new Integer[]{1, 2, 3, 6, 7, 9, 8, null, null, 7, 4})
        ));
    }

    @Test
    public void testLeafSimilar2() {
        assertTrue(new S872().leafSimilar(
                TreeNodeTools.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4}),
                TreeNodeTools.buildBinaryTree(new Integer[]{1, 2, 3, 10, 7, 9, 8, 6, null, 7, 4})
        ));
    }

    @Test
    public void testLeafNotSimilar() {
        assertFalse(new S872().leafSimilar(
                TreeNodeTools.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4}),
                TreeNodeTools.buildBinaryTree(new Integer[]{1, 2, 3, 10, 7, 9, 8, 6, null, 7, 5})
        ));
    }

    @Test
    public void testOneNode() {
        assertTrue(new S872().leafSimilar(
                TreeNodeTools.buildBinaryTree(new Integer[]{1}),
                TreeNodeTools.buildBinaryTree(new Integer[]{1})
        ));
    }
}