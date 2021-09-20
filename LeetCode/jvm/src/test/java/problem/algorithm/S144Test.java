package problem.algorithm;

import org.testng.annotations.Test;
import utils.TreeNode;
import utils.TreeNodeTools;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class S144Test {

    @Test
    public void testPreorderTraversal() {
        TreeNode example1 = TreeNodeTools.buildBinaryTree(new Integer[]{1, null, 2, 3});
        assertEquals(new S144().preorderTraversal(example1), Arrays.asList(1, 2, 3));

        TreeNode example2 = TreeNodeTools.buildBinaryTree(new Integer[]{});
        assertEquals(new S144().preorderTraversal(example2), Collections.emptyList());

        TreeNode example3 = TreeNodeTools.buildBinaryTree(new Integer[]{1});
        assertEquals(new S144().preorderTraversal(example3), Collections.singletonList(1));

        TreeNode example4 = TreeNodeTools.buildBinaryTree(new Integer[]{1, 2});
        assertEquals(new S144().preorderTraversal(example4), Arrays.asList(1, 2));

        TreeNode example5 = TreeNodeTools.buildBinaryTree(new Integer[]{1, null, 2});
        assertEquals(new S144().preorderTraversal(example5), Arrays.asList(1, 2));
    }
}