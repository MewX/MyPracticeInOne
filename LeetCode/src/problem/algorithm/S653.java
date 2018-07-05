package problem.algorithm;

import utils.TreeNode;

public class S653 {
    public boolean findTarget(TreeNode root, int k) {
        // basic idea is find the root node in this way: val * 2 > k
        root = findRealRoot(root, k);

        // search left and right, for each left, looking for right
        return root != null && (trySubtree(root, root.left, k) || trySubtree(root, root.right, k));
    }

    private TreeNode findRealRoot(TreeNode root, int k) {
        if (root == null) return null;

        if ((root.val << 1) >= k) {
            return root;
        } else {
            TreeNode left = findRealRoot(root.left, k);
            TreeNode right = findRealRoot(root.right, k);
            return left == null ? right : left;
        }
    }

    private boolean trySubtree(TreeNode root, TreeNode currentRoot, int k) {
        if (root == null || currentRoot == null) return false;
        boolean result = tryValue(root, k - currentRoot.val);
        return result || trySubtree(root, currentRoot.left, k) || trySubtree(root, currentRoot.right, k);
    }

    private boolean tryValue(TreeNode root, int requiredValueInRightBranch) {
        if (root == null) return false;
        if (root.val == requiredValueInRightBranch) return true;
        else if (root.val < requiredValueInRightBranch) return tryValue(root.right, requiredValueInRightBranch);
        else return tryValue(root.left, requiredValueInRightBranch);
    }
}
