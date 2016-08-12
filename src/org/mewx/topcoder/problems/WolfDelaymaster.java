package org.mewx.topcoder.problems;

public class WolfDelaymaster {
    public static final int id = 12778;

    public String check(String str) {

        int tempCount = 0;

        int i = 0;
        while (i < str.length()) {
            if (i < str.length() && str.charAt(i) != 'w') return getValidation(false);
            while (i < str.length() && str.charAt(i) == 'w') {
                tempCount ++;
                i ++;
            }
            // now str.charAt(i) == 'o'

            if (tempCount * 3 + i <= str.length()) {
                if (!checkSubstring(str.substring(i - tempCount, i + tempCount * 3))) {
                    return getValidation(false);
                }

                i += tempCount * 3;
                tempCount = 0;
            } else {
                return getValidation(false);
            }
        }

        return getValidation(i == str.length());
    }

    private String getValidation(boolean valid) {
        return valid ? "VALID" : "INVALID";
    }

    private boolean checkSubstring(String sub) {
        int w, o, l, f, i;
        i = w = o = l = f = 0;
        while (i < sub.length() && sub.charAt(i) == 'w') {
            i ++;
            w ++;
        }
        while (i < sub.length() && sub.charAt(i) == 'o') {
            i ++;
            o ++;
        }
        while (i < sub.length() && sub.charAt(i) == 'l') {
            i ++;
            l ++;
        }
        while (i < sub.length() && sub.charAt(i) == 'f') {
            i ++;
            f ++;
        }
        return w + o + l + f == sub.length() && w == o && o == l && l == f;
    }
}
