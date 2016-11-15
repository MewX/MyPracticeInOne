package org.mewx.topcoder.problems;

import java.util.Arrays;

/**
 * Created by a1700831 on 14/09/16.
 */
public class ProgramSchedule {

    private class Pair {
        public int setup;
        public int exec;

        public Pair(int a, int b) {
            setup = a;
            exec = b;
        }
    }

    public int schedule(int[] A, int[] B) {
        Pair[] pairs = new Pair[A.length];
        for (int i = 0; i < A.length; i ++) {
            pairs[i] = new Pair(A[i], B[i]);
        }

        // greedy
        Arrays.sort(pairs, (a, b) -> b.exec - a.exec); // dec
        int sum = pairs[0].setup, remaining = pairs[0].exec;
        sum += remaining;
        for (int i = 1; i < pairs.length; i ++) {
            remaining -= pairs[i].setup;
            if (remaining < 0) {
                sum += -remaining + pairs[i].exec;
                remaining = pairs[i].exec;
            }
            else {
                remaining -= pairs[i].exec;
                if (remaining < 0) {
                    remaining = -remaining;
                    sum += remaining;
                    remaining = pairs[i].exec;
                } else {
                    remaining += pairs[i].exec;
                }
            }
        }
        return sum;
    }
}