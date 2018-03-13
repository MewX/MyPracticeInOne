package problem.algorithm;

import org.testng.annotations.Test;
import utils.TreeNode;
import utils.TreeNodeTools;

import static org.testng.Assert.*;

public class S654Test {

    @Test
    public void example() {
        TreeNode ret = new S654().constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        TreeNode wanted = TreeNodeTools.buildBinaryTree(new Integer[]{6, 3, 5, null, 2, 0, null, null, 1});
        assertTrue(TreeNodeTools.compareTwoTrees(ret, wanted));
    }
}