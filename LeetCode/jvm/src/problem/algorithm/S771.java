package problem.algorithm;

import java.util.HashSet;

public class S771 {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> j = new HashSet<>();
        for (int i = 0; i < J.length(); i ++) {
            j.add(J.charAt(i));
        }

        int count = 0;
        for (int i = 0; i < S.length(); i ++) {
            if (j.contains(S.charAt(i))) count ++;
        }
        return count;
    }
}
