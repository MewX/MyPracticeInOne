package org.mewx.topcoder.problems;

import static java.lang.Math.min;

/**
 * Created by a1700831 on 3/08/16.
 */
public class AlternateColors {
    enum COLOR {
        RED, GREEN, BLUE
    }

    public String ballColor(long r, long g, long b, long k) {
        // RED, GREEN, BLUE

        // only one is not 0 case:
        if (r == 0 && g == 0) return COLOR.BLUE.toString();
        else if (r == 0 && b == 0) return COLOR.GREEN.toString();
        else if (g == 0 && b == 0) return COLOR.RED.toString();

        // min * 3 > k ==> in the min ranges
        // min * 3 < k ==> further calc
        long minVal = min(min(r, g), b);
        if (minVal != 0 && minVal * 3 > k) {
            long temp = k % 3;
            if (temp == 0) return COLOR.BLUE.toString();
            else if (temp == 1) return COLOR.RED.toString();
            else return COLOR.GREEN.toString();
        }

        // substract minVal each
        r -= minVal;
        g -= minVal;
        b -= minVal;
        k -= minVal * 3;
        if (k == 0) {
            return COLOR.BLUE.toString();
        }

        // check the rest two
        int maximum = 0, maxless = 0;
        if (r == 0) {
            if (g > b) {
                long min = min(g, b);
                if (min * 2 > k) {
                    long temp = k % 2;
                    if (temp == 0) return COLOR.BLUE.toString();
                    else return COLOR.GREEN.toString();
                } else return COLOR.GREEN.toString();
            } else {
                // blue is larger
                long min = min(g, b);
                if (min * 2 > k) {
                    long temp = k % 2;
                    if (temp == 0) return COLOR.BLUE.toString();
                    else return COLOR.GREEN.toString();
                } else return COLOR.BLUE.toString();
            }
        } else if (g == 0) {
            if (r > b) {
                long min = min(r, b);
                if (min * 2 > k) {
                    long temp = k % 2;
                    if (temp == 0) return COLOR.BLUE.toString();
                    else return COLOR.RED.toString();
                } else return COLOR.RED.toString();
            } else {
                // blue is larger
                long min = min(r, b);
                if (min * 2 > k) {
                    long temp = k % 2;
                    if (temp == 0) return COLOR.BLUE.toString();
                    else return COLOR.RED.toString();
                } else return COLOR.BLUE.toString();
            }
        } else {
            // b == 0
            if (r > g) {
                long min = min(r, g);
                if (min * 2 > k) {
                    long temp = k % 2;
                    if (temp == 0) return COLOR.GREEN.toString();
                    else return COLOR.RED.toString();
                } else return COLOR.RED.toString();
            } else {
                long min = min(r, g);
                if (min * 2 > k) {
                    long temp = k % 2;
                    if (temp == 0) return COLOR.GREEN.toString();
                    else return COLOR.RED.toString();
                } else return COLOR.GREEN.toString();
            }
        }

    }
}
