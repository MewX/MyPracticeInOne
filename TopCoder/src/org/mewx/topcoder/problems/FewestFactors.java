package org.mewx.topcoder.problems;

import java.util.*;

public class FewestFactors {
    public static void main(String[] args) {
        System.out.println(new FewestFactors().number(new int[] {1, 2}));
        System.out.println(new FewestFactors().number(new int[] {6, 0}));
        System.out.println(new FewestFactors().number(new int[] {4, 7, 4}));
        System.out.println(new FewestFactors().number(new int[] {1, 3, 7, 9}));
        System.out.println(new FewestFactors().number(new int[] {7, 5, 4, 3, 6}));
        System.out.println(new FewestFactors().number(new int[] {1, 2, 4}));
    }

    public int number(int[] digits) {
        // generate permutations
        generateAllPossibleNumbers(digits);

        // output all values
        // for (Integer i : set) System.out.println(i);
        
        // brute force, count all factors
        int minNum = Integer.MAX_VALUE;
        int minFactor = Integer.MAX_VALUE;
        for (Integer num : set) {
            int temp = countFactor(num);
            if (temp == minFactor && num < minNum) {
                minNum = num;
            } else if (temp < minFactor) {
                minFactor = temp;
                minNum = num; // must reset
            }
        }
        return minNum;
    }

    private HashSet<Integer> set = new HashSet<>();
    private void generateAllPossibleNumbers(int[] digits) {
        permutation(0, digits, digits.length);
    }

    private void permutation(int prefix, int[] digits, int numOfDigit) {
        if (numOfDigit < 0) return;
        // if (prefix != 0) set.add(prefix); // save dynamic length
        if (numOfDigit == 0 && prefix != 0) set.add(prefix);

        int idxLast = numOfDigit - 1;
        for (int beg = 0; beg < numOfDigit; beg ++) {
            // swap [i] with [last]
            int temp = digits[idxLast];
            digits[idxLast] = digits[beg];
            digits[beg] = temp;

            // recursion
            permutation(prefix * 10 + digits[idxLast], digits, numOfDigit - 1);

            // recover
            temp = digits[idxLast];
            digits[idxLast] = digits[beg];
            digits[beg] = temp;
        }
    }

    private int countFactor(int num) {
        int count = 0;
        final int max = Math.round((int)Math.sqrt(num));
        for (int i = 1; i <= max; i ++) {
            if (num % i == 0) count += 2;
        }
        if (max * max == num) count -= 1;
        return count;

    }
}
