package org.mewx.topcoder.problems;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MewX on 10/2/2016.
 */
public class BinarySearchable {
    private class Called {
        int num = 0;
        int zero = 0;
        int one = 0;
        public Called(int num, int zero, int one) {
            this.num = num;
            this.zero = zero;
            this.one = one;
        }
    }

    public int[] fiboCallsMade(int n) {
        ArrayList<Called> list = new ArrayList<>(n + 1);
        list.add(new Called(0, 1, 0));
        list.add(new Called(1, 0, 1));

        int temp = 2;
        while (temp <= n) {
            Called c_1 = list.get(temp - 1);
            Called c_2 = list.get(temp - 2);
            list.add(new Called(c_1.num + c_2.num, c_1.zero + c_2.zero, c_1.one + c_2.one));
            temp ++;
        }

        int[] r = new int[2];
        r[0] = list.get(n).zero;
        r[1] = list.get(n).one;
        return r;
    }
}
