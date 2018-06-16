package problem.algorithm;

import java.util.Arrays;

public class S645 {
    public int[] findErrorNums(int[] nums) {
        int lookingFor = 1;
        int theNumber = 0;

        Arrays.sort(nums);
        if (nums[0] == 1) lookingFor = 2;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == lookingFor) {
                lookingFor ++;
            }
            if (theNumber == 0 && nums[i - 1] == nums[i]) {
                theNumber = nums[i];
            }
        }
        return new int[]{theNumber, lookingFor};
    }
}
