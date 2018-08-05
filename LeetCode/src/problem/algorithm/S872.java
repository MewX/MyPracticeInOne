package problem.algorithm;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class S872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return getLeaves(root1).equals(getLeaves(root2));
    }

    private List<Integer> getLeaves(TreeNode root) {
        List<Integer> temp = new ArrayList<>();
        if (root == null) return temp;

        temp.addAll(getLeaves(root.left));
        temp.addAll(getLeaves(root.right));
        if (root.left == null && root.right == null) temp.add(root.val);
        return temp;
    }
}
