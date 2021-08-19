package problem.algorithm;

import utils.TreeNode;

public class S669 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;

        TreeNode newRoot;
        if (L <= root.val && root.val <= R) {
            // valid root
            newRoot = new TreeNode(root.val);
            newRoot.left = trimBST(root.left, L, R);
            newRoot.right = trimBST(root.right, L, R);
        } else if (root.val < L) {
            // only right branch works
            newRoot = trimBST(root.right, L, R);
        } else {
            // only left branch works
            newRoot = trimBST(root.left, L, R);
        }

        return newRoot;
    }
}
