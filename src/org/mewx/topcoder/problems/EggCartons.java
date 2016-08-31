package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 17/08/16.
 */
public class EggCartons {
    public int minCartons(int n) {
        // simple brute force
        int max6 = n / 6 + 1, max8 = n / 8 + 1;
        for (int i6 = 0; i6 < max6; i6 ++) {
            for (int i8 = 0; i8 < max8; i8 ++) {
                if (i6 * 6 + i8 * 8 == n) return i6 + i8;
            }
        }
        return -1;


//        int egg8 = n / 8, egg6 = 0;
//        if (egg8 * 8 + egg6 * 6 == n) {
//
//        }
    }
}