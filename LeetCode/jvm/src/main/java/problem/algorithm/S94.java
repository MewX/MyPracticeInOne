package problem.algorithm;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class S94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root != null) {
            ret.addAll(inorderTraversal(root.left));
            ret.add(root.val);
            ret.addAll(inorderTraversal(root.right));
        }
        return ret;
    }
}
