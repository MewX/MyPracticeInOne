package problem.algorithm;

import java.util.Arrays;

public class S888 {

    public int[] fairCandySwap(int[] A, int[] B) {
        long sumA = 0, sumB = 0;
        for (int a : A) sumA += a;
        for (int b : B) sumB += b;

        long diff = sumA - sumB;
        int emmm = (int) (diff / 2); // the wanted difference between two candy bars

        Arrays.sort(A);
        Arrays.sort(B);

        for (int a : A) {
            // binary search
            int beg = 0, end = B.length - 1;
            while (beg <= end) {
                int mid = (beg + end) >> 1;
                int b = B[mid];
                if (a - b == emmm) {
                    // found it!
                    return new int[]{a, B[mid]};
                } else if (emmm > 0 && a - b > emmm || emmm < 0 && b - a < -emmm) {
                    beg = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return new int[]{0, 0};
    }
}
