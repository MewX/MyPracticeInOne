package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class FunnyFence {
    public int getLength(String s) {
        int max = 0;
        int currentCount = 1;

        for (int i = 1; i < s.length(); i ++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                // continue count
                currentCount += 1;
            }
            else {
                max = max < currentCount ? currentCount : max;
                currentCount = 1;
            }
        }
        return max < currentCount ? currentCount : max;
    }
}
