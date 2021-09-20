package problem.algorithm;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class S145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root != null) {
            ret.addAll(postorderTraversal(root.left));
            ret.addAll(postorderTraversal(root.right));
            ret.add(root.val);
        }
        return ret;
    }
}
