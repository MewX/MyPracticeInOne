package problem.algorithm;

import org.testng.annotations.Test;
import utils.TreeNode;
import utils.TreeNodeTools;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class S145Test {

    @Test
    public void testPostorderTraversal() {
        TreeNode example1 = TreeNodeTools.buildBinaryTree(new Integer[]{1, null, 2, 3});
        assertEquals(new S145().postorderTraversal(example1), Arrays.asList(3, 2, 1));

        TreeNode example2 = TreeNodeTools.buildBinaryTree(new Integer[]{});
        assertEquals(new S145().postorderTraversal(example2), Collections.emptyList());

        TreeNode example3 = TreeNodeTools.buildBinaryTree(new Integer[]{1});
        assertEquals(new S145().postorderTraversal(example3), Collections.singletonList(1));

        TreeNode example4 = TreeNodeTools.buildBinaryTree(new Integer[]{1, 2});
        assertEquals(new S145().postorderTraversal(example4), Arrays.asList(2, 1));

        TreeNode example5 = TreeNodeTools.buildBinaryTree(new Integer[]{1, null, 2});
        assertEquals(new S145().postorderTraversal(example5), Arrays.asList(2, 1));
    }
}