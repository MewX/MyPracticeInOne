package org.mewx.topcoder.problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by a1700831 on 10/08/16.
 */
public class ChangingString {
    public int distance(String A, String B, int K) {
        Integer[] diff = new Integer[A.length()];
        for (int i = 0; i < A.length(); i ++) {
            int temp = A.charAt(i) - B.charAt(i);
            if (temp < 0) temp = -temp;
            diff[i] = temp;
        }

        List<Integer> list = Arrays.asList(diff);
        Collections.sort(list, (a, b) -> b - a);

        for (int i = 0; i < list.size() && K > 0; i ++, K --) {
            if (list.get(i) == 0) {
                list.set(i, 1);
            } else {
                list.set(i, 0);
            }
        }

        return list.stream().mapToInt(Integer::intValue).sum();
    }
}

