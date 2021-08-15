package utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TreeNodeToolsTest {
    private TreeNode testingNode;

    @BeforeMethod
    public void setup() {
        testingNode = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        testingNode.left = left;
        TreeNode right = new TreeNode(3);
        testingNode.right = right;
        left.left = null;
        left.right = new TreeNode(4);
        TreeNode rightLeft = new TreeNode(5);
        right.left = rightLeft;
        rightLeft.right = new TreeNode(6);
    }

    @Test
    public void testBuildBinaryTree() {
        assertTrue(TreeNodeTools.compareTwoTrees(
                TreeNodeTools.buildBinaryTree(new Integer[]{1, 2, 3, null, 4, 5, null, null, null, null, 6}),
                testingNode));
    }

    @Test
    public void testMakeBinaryTree() {
        assertTrue(TreeNodeTools.compareTwoTrees(
                TreeNodeTools.makeBinaryTree(new Integer[]{1, 2, 3, null, 4, 5, null, null, null, null, 6}),
                testingNode));
    }

    @Test
    public void testFellBinaryTree() {
        assertEquals(TreeNodeTools.fellBinaryTree(testingNode),
                new Integer[]{1, 2, 3, null, 4, 5, null, null, null, null, 6});
    }

    @Test
    public void testFindBinaryTreeByInt() {
        assertEquals(testingNode, TreeNodeTools.findBinaryTreeByInt(testingNode, 1));
    }

    @Test
    public void testFindBinaryTreeByIntValidNull() {
        assertEquals(null, TreeNodeTools.findBinaryTreeByInt(testingNode, 999999));
    }

    @Test
    public void testCompareTwoTrees() {
        assertTrue(TreeNodeTools.compareTwoTrees(
                TreeNodeTools.buildBinaryTree(new Integer[]{1, 2, 3, null, 4, 5, null, null, null, null, 6}),
                TreeNodeTools.makeBinaryTree(new Integer[]{1, 2, 3, null, 4, 5, null, null, null, null, 6})));
    }
}