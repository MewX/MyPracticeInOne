package org.mewx.topcoder.problems;

import java.util.*;

public class MatchNumbersEasy {
    public static void main(String[] args) {
        System.out.println(new MatchNumbersEasy().maxNumber(new int[] {6, 7, 8}, 21));
        System.out.println(new MatchNumbersEasy().maxNumber(new int[] {5, 23, 24}, 30));
        System.out.println(new MatchNumbersEasy().maxNumber(new int[] {1, 5, 3, 2}, 1));
        System.out.println(new MatchNumbersEasy().maxNumber(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 50));
    }

    public String maxNumber(int[] matches, int n) {
        int lowestNonZero = Integer.MAX_VALUE;
        int lowest = matches[0];
        for (int i = 1; i < matches.length; i++) {
            int match = matches[i];
            if (lowestNonZero > match) lowestNonZero = match;
            if (lowest > match) lowest = match;
        }
        
        int digits = (n - lowestNonZero) / lowest + 1; // max digits
        if (n - lowestNonZero < 0) return "0"; // impossible
 
        String ret = "";
        int remain = n;
        for (int i = 0; i < digits; i ++) {
            for (int j = matches.length - 1; j >= 0; j --) {
                // try from great to small
                if (remain - matches[j] >= lowest * (digits - i - 1)) {
                     ret = ret + j;
                     remain -= matches[j];
                     break;
                }
            }    
        }
        return ret;
    }

    /**
     * Another typical DP solution.
     * dp[n] means biggies number made by `n` matches.
     */
    public String maxNumber2(int[] matches, int n) {
        String[] dp = new String [51]; // use [1] to [50] only
        for (int i = 0; i < dp.length; i ++) dp[i] = "";
        for (int i = 0; i < matches.length; i ++) dp[matches[i]] = "" + i;

        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j <= i / 2; j ++) {
                String temp = dp[j] + dp[i - j]; // best `j` matches adds best `i - j` matches
                char[] chars = temp.toCharArray();
                Arrays.sort(chars); // reverse order
                temp = new StringBuilder(new String(chars)).reverse().toString();
                
                if (temp.length() > dp[i].length() && temp.charAt(0) != '0'
                        || temp.length() == dp[i].length() && temp.compareTo(dp[i]) > 0)
                    dp[i] = temp;
            }
        }

        if (dp[n].length() == 0 || dp[n].charAt(0) == '0') return "0";
        else return dp[n];
    }
}
