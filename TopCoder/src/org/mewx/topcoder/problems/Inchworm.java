package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class Inchworm {
    // calc the largest common divider
    private int gcd(int a, int b) {
        int temp = a % b;
        if (temp != 0) return gcd(b, temp);
        else return b;
    }

    // calc the least common multiply using Euclidean Algorithm
    private int lcm(int a, int b) {
        return a * b / gcd(a, b); 
    }

    public int lunchtime(int branch, int rest, int leaf) {
        // target position is a rest and leaf position
        // System.out.format("lcm(%d, %d) = %d\n", rest, leaf, lcm(rest, leaf));
        return branch / lcm(rest, leaf) + 1;
    }
}