import com.sun.javafx.binding.SelectBinding;
import utils.TreeNode;
import utils.TreeNodeTools;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
@SuppressWarnings("unused")
public class Solution {


    public static void main(String[] var) {
        // init value
        Integer[] treeVal = new Integer[]{1,null,3,2,5,4,2}; // {1,2,2,null,3,3,null,4,5,5,4};
        TreeNode source = TreeNodeTools.makeBinaryTree(treeVal);

        System.out.println(new Solution().checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 6));
    }

    public int findPairs(int[] nums, int k) {
        if (nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(nums[i])) map.put(nums[i], map.get(nums[i]));
            else map.put(nums[i], 1);
        }

        int count = 0;
        Integer[] array = map.keySet().toArray(new Integer[0]);
        Arrays.sort(array);
        if (k == 0) {
            for (Integer i : array) {
                int temp = map.get(i);
                if (i != 1) count += 1;
            }
        } else {
            for (int i = 1; i < array.length; i ++) {
                if (array[i] - array[i - 1] == k) count += map.get(array[i]) * map.get(array[i - 1]);
            }
        }
        return count;
    }


    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i ++) {
            int temp = nums[i];
            for (int j = i + 1; j < nums.length; j ++) {
                temp += nums[j];
                if (temp % k == 0) return true;
            }
        }
        return false;
    }
}
