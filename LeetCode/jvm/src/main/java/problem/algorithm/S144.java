package problem.algorithm;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class S144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root != null) {
            ret.add(root.val);
            ret.addAll(preorderTraversal(root.left));
            ret.addAll(preorderTraversal(root.right));
        }
        return ret;
    }
}
