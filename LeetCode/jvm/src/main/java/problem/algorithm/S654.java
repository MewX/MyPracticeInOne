package problem.algorithm;

import utils.TreeNode;

public class S654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return div(nums, 0, nums.length - 1);
    }

    // simple divide and conquer
    private TreeNode div(final int[] nums, final int start, final int end) {
        if (start > end) return null;

        int iMax = start;
        int numMax = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (nums[i] > numMax) {
                iMax = i;
                numMax = nums[i];
            }
        }

        TreeNode root = new TreeNode(numMax);
        root.left = div(nums, start, iMax - 1);
        root.right = div(nums, iMax + 1, end);
        return root;
    }
}
