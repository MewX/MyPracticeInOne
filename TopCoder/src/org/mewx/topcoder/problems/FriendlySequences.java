package org.mewx.topcoder.problems;

import java.math.BigInteger;

/**
 * Created by a1700831 on 10/08/16.
 */
public class FriendlySequences {
    private class Figure {
        boolean[] count = new boolean[] {false, false, false, false, false, false, false, false, false, false};

        boolean equalTo(Figure figure) {
            for (int i = 0; i < 10; i ++) {
                if (count[i] != figure.count[i]) return false;
            }
            return true;
        }
    }

    public int count(int[] array) {
        // get length & define vals
        Figure[] figures = new Figure[array.length];

        // calc all
        for (int i = 0; i < array.length; i ++) {
            String temp = Integer.toString(array[i]);
            figures[i] = new Figure();
            for (int j = 0; j < temp.length(); j ++) {
                figures[i].count[temp.charAt(j) - '0'] = true;
            }
        }

        // compare and divided into groups, each used figure should be set true
        int tempSaveCount, totalCount = 0;
        for (int startIdx = 0, judgeIdx; startIdx < figures.length;) {
            tempSaveCount = 1;
            for (judgeIdx = startIdx + 1; judgeIdx < figures.length; judgeIdx ++) {
                // equals
                if (figures[judgeIdx].equalTo(figures[startIdx])) {
                    tempSaveCount ++;
                } else {
                    break;
                }
            }

            // add to total
            startIdx = judgeIdx;
            if (tempSaveCount == 1) continue;
            totalCount += calcCombinationBigInt(tempSaveCount, 2);
        }
        return totalCount;
    }


    // This causes integer exceeding
    private int calcCombination(int n, int k) {
        return factorial(n) / (factorial(n - k) * factorial(k));
    }

    private int factorial(int n) {
        int result = 1;
        while (n > 0) {
            result *= n;
            n --;
        }
        return result;
    }

    // use these new codes
    private int calcCombinationBigInt(int n, int k) {
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigK = BigInteger.valueOf(k);
        return factorialBigInt(BigInteger.valueOf(n)).divide( factorialBigInt(BigInteger.valueOf(n-k)).multiply(factorialBigInt(BigInteger.valueOf(k)))).intValue();
    }

    private BigInteger factorialBigInt(BigInteger n) {
        BigInteger result = BigInteger.ONE;
        while (n.compareTo(BigInteger.ZERO) == 1) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return result;
    }
}