package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 31/08/16.
 */
public class Reppity {
    public int longestRep(String input) {
        int longest = 0;
        for (int len = input.length() / 2; len > 0; len --) {
            for (int i = 0; i < input.length() - len; i ++) { // start point
                for (int match = i + len; match < input.length(); match ++) { // compare point
                    int iSave = i;
                    int matchSave = match;

                    int tempCount = 0;
                    while (matchSave < input.length() && iSave < match && input.charAt(iSave) == input.charAt(matchSave)) {
                        tempCount ++;
                        iSave ++;
                        matchSave ++;
                    }
                    if (tempCount > longest)  {
//                        System.out.println("Str: " + input);
//                        System.out.println("a: " + i + "; b: " + match);
                        longest = tempCount;
                    }
                }
            }
        }
        return longest;
    }
}
