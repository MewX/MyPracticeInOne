package problem.algorithm;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class S532 {

    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return 0;
        HashSet<Integer> set = new HashSet<>(10000);
        Arrays.sort(nums);

        int count = 0;
        int lastNum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (set.contains(num - k) && lastNum != num) {
                count ++;
                lastNum = num;
            }
            set.add(num);
        }
        return count;
    }
}
