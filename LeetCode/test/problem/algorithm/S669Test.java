package problem.algorithm;

import org.testng.annotations.Test;
import utils.TreeNode;
import utils.TreeNodeTools;

import static org.testng.Assert.*;

public class S669Test {

    @Test
    public void example1() {
        TreeNode root = TreeNodeTools.buildBinaryTree(new Integer[] {1, 0, 2});
        assertEquals(TreeNodeTools.fellBinaryTree(new S669().trimBST(root, 1, 2)),
                new Integer[]{1, null, 2});
    }

    @Test
    public void example2() {
        TreeNode root = TreeNodeTools.buildBinaryTree(new Integer[]{3, 0, 4, null, 2, null, null, 1});
        assertEquals(TreeNodeTools.fellBinaryTree(new S669().trimBST(root, 1, 3)),
                new Integer[]{3, 2, null, 1});
    }
}