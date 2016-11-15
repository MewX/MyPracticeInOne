package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 3/08/16.
 */
public class StringSegment {
    public double average(String s) {
        int partCount = 0;
        int lengthSum = 0;
        all: for (int i = 0; i < s.length(); i ++) {
            char charSave = s.charAt(i);
            partCount ++;

            if (i == s.length() - 1) {
                // the last one to count
                lengthSum += 1;
                break;
            }

            // the rest
            int lengthTemp = 1, j;
            for (j = i + 1; j < s.length(); j ++) {

                if (s.charAt(j) == s.charAt(i)) {
                    lengthTemp++;

                    // last case
                    if (j == s.length() - 1) {
                        lengthSum += lengthTemp;
                        break all;
                    }
                } else {
                    lengthSum += lengthTemp;
                    i = j - 1;
                    break;
                }
            }
        }

        return lengthSum * 1.0 / partCount;
    }
}
