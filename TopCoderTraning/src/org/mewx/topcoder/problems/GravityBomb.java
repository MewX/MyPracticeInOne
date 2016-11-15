package org.mewx.topcoder.problems;

import java.util.Arrays;

public class GravityBomb {
    public static final int id = 2417;

    public String[] aftermath(String[] board) {

        int[] count = new int[board[0].length()];
        Arrays.fill(count, 0);

        for (String row : board) {
            for (int i = 0; i < row.length(); i ++) {
                if (row.charAt(i) == 'X')
                    count[i] ++;
            }
        }

        // find min
        int min = Integer.MAX_VALUE;
        for (int i : count) {
            if (i < min)
                min = i;
        }

        // subtract i
        for (int i = 0; i < count.length; i ++) {
            count[i] -= min;
        }

        // fill string arrays
        for (int i = board.length - 1; i >= 0; i --) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < count.length; j ++) {
                if (count[j] != 0) {
                    sb.append("X");
                    count[j] --;
                } else {
                    sb.append(".");
                }
            }
            board[i] = sb.toString();
        }

        return board;
    }
}
