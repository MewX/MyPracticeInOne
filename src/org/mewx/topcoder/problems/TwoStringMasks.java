package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 17/08/16.
 */
public class TwoStringMasks {

    public String shortestCommon(String s1, String s2) {
        String bak1 = s1, bak2 = s2; // for backup
        String prefix = "", suffix = "";

        // firstly, do convention
        // make "*" at the beginning or ending of the string
        while (s1.length() > 0 && s2.length() > 0 && s1.charAt(0) == s2.charAt(0)) { // from left to right
            if ((s1.charAt(0) == '*') == (s2.charAt(0) == '*')) {
                if (s1.charAt(0) == '*') {
                    // handled by next loop
                    break;
                }
                prefix = prefix + s1.charAt(0);
                s1 = s1.substring(1);
                s2 = s2.substring(1);
            }
        }
        while (s1.length() > 0 && s2.length() > 0 && s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) { // from right to left
            if ((s1.charAt(s1.length() - 1) == '*') == (s2.charAt(s2.length() - 1) == '*')) {
                suffix = s1.charAt(s1.length() - 1) + suffix;
                s1 = s1.substring(0, s1.length() - 1);
                s2 = s2.substring(0, s2.length() - 1);
            }
        }
//        System.out.println("PRE: " + prefix + "; SUR: " + suffix);

        if (s1.indexOf("*") == 0 && s2.indexOf("*") == 0) {
            // right align
            if (s1.equals("*") || s2.equals("*")) {
                return prefix + (s1.equals("*") ? s2.replace("*", "") : s1.replace("*", "")) + suffix;
            }
            if (s1.length() < s2.length()) {
                if (!s1.substring(1).equals(s2.substring(s2.length() - s1.length(), s2.length()))) return "impossible";
            } else {
                if (!s2.substring(1).equals(s1.substring(s1.length() - s2.length(), s1.length()))) return "impossible";
            }
        }

        // simplify
        if (prefix.contains("*") || suffix.contains("*")) {
            if ((prefix.length() == 0 || prefix.length() != 0 && prefix.indexOf('*') != prefix.length() - 1)
                    && (suffix.length() == 0 || suffix.length() != 0 && suffix.indexOf('*') != 0)
                    && !s1.equals(s2)) {
                return "impossible";
            }
        }

        if (s1.length() == 0 && s2.length() == 0 || s2.length() == 0) {
            return bak1.replace("*", "");
        } else if (s1.length() == 0) {
            return bak2.replace("*", "");
        }
//        else {
//            // none equals zero
//            System.out.println(s1 + "; " + s2);
//            return "";
//        }

        // simplify
        if (s1.equals("*") || s2.equals("*")) {
            return prefix + (s1.equals("*") ? s2.replace("*", "") : s1.replace("*", "")) + suffix;
        }

        // add a judge case
        if (s1.charAt(0) != '*' && s1.charAt(s1.length() - 1) != '*'
                || s2.charAt(0) != '*' && s2.charAt(s2.length() - 1) != '*')
            return "impossible";

        // now, all the problems are converted to this case;
        // *CODER; TOPCO*
        // make TOPCO*+*CODER to *CODER+TOPCO*
        if (s1.charAt(0) != '*') {
            // swap
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
//        System.out.println("test: " + s1 + "; " + s2);

        // brute force, from s1[1], find all the match the cases in s2 (no KMP)
        String r = "";
        int i = 0;
        for (; i < s2.length(); i ++) { // for s2
            if (i == s2.length() - 1 && s2.charAt(i) == '*') {
                String t = (prefix + s2.substring(0, s2.length() - 1) + s1.substring(1) + suffix).replace("*", "");
                if (t.length() < r.length() || r.length() == 0) r = t;
            }
            if (s1.charAt(1) != s2.charAt(i)) continue;

            int j = s1.length() - 1; // which is not a  '*'
            for (; j >= 0; j --) { // for s1
                if (j == 0 && s1.charAt(j) == '*') {
                    String t = (prefix + s2.substring(0, s2.length() - 1) + s1.substring(1) + suffix).replace("*", "");
                    if (t.length() < r.length() || r.length() == 0) r = t;
                }
                if (s1.charAt(j) != s2.charAt(s2.length() - 2)) continue;

//                System.out.println("TestIn: " + s1.substring(1, j + 1) + "-" + s2.substring(i, s2.length() - 1));
                if (s1.substring(1, j + 1).equals(s2.substring(i, s2.length() - 1))) {
                    // it works, return the target value
                    String t = (prefix + s2.substring(0, i) + s1.substring(1) + suffix).replace("*", "");
                    if (t.length() < r.length() || r.length() == 0) r = t;
                }
            }
        }
        return r.length() == 0 ? "impossible" : r;
    }
}
