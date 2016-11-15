package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class Inchworm {
    public int lunchtime(int branch, int rest, int leaf) {
        int count = 0;
        for (int current = 0; current <= branch; ) {
            if (current % leaf == 0) {
                count ++;
            }
            current += rest;
        }
        return count;
    }
}