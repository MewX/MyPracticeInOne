package problem.algorithm;

import utils.TreeNode;

public class S653 {
    public boolean findTarget(TreeNode root, int k) {
        // search left and right, for each left, looking for right
        return root != null && (trySubtree(root, root.left, k) || trySubtree(root, root.right, k));
    }

    private boolean trySubtree(TreeNode root, TreeNode currentRoot, int k) {
        if (root == null || currentRoot == null) return false;
        boolean result = currentRoot.val * 2 != k && tryValue(root, k - currentRoot.val);
        return result || trySubtree(root, currentRoot.left, k) || trySubtree(root, currentRoot.right, k);
    }

    private boolean tryValue(TreeNode root, int requiredValueInRightBranch) {
        if (root == null) return false;
        if (root.val == requiredValueInRightBranch) return true;
        else if (root.val < requiredValueInRightBranch) return tryValue(root.right, requiredValueInRightBranch);
        else return tryValue(root.left, requiredValueInRightBranch);
    }
}
