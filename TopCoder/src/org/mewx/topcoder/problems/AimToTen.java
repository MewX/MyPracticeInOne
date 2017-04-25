package org.mewx.topcoder.problems;

public class AimToTen {
    public static void main(String[] args) {
        System.out.println(new AimToTen().need(new int[] {9, 10, 10, 9}));
        System.out.println(new AimToTen().need(new int[] {8, 9}));
        System.out.println(new AimToTen().need(new int[]
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        System.out.println(new AimToTen().need(new int[] {10, 10, 10, 10}));
    }

    int need(int[] marks) {
        int sum = 0;
        for (int temp : marks) sum += temp;

        // (sum + 10 * ret) / (len + ret) >= 9.5
        // (sum + 10 * ret) >= 9.5 * (len + ret)
        // 2 * sum + 20 * ret >= 19 * len + 19 * ret
        // ret >= 19 * len - 2 * sum
        int ret = 19 * marks.length - (sum << 1);
        return ret > 0 ? ret : 0;
    }
}
