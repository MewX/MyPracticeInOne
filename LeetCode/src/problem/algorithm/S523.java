package problem.algorithm;

public class S523 {

    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                if (temp % k == 0) return true;
            }
        }
        return false;
    }
}
