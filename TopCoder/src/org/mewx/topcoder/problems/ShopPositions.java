package org.mewx.topcoder.problems;

/**
 * Created by MewX on 8/26/2016.
 * TODO: This problem is not yet been solved.
 */
public class ShopPositions {
    public static final int id = 13884;
    private int[] cBak;
    private int mBak;

    public int maxProfit(int n, int m, int[] c) {
        // pre
        cBak = c;
        mBak = m;
        int[][][] pre = new int [n + 1][][];
        for (int i = 0; i <= n; i ++) {
            pre[i] = new int [m + 1][]; // range: 0 - m
            for (int j = 0; j <= m; j ++) {
                pre[i][j] = new int[m + 1]; // range: 0 - m
            }
        }

        // proceed the first one
        int max = 0;
        for (int i = 0; i <= m; i ++) {
            for (int j = 0; j <= m; j ++) {
                if (i == 0 && j == 0) {
                    pre[0][i][j] = 0;
                    continue;
                }
                pre[0][i][j] = j * getProfit(0, i + j);
                if (pre[0][i][j] > max) max = pre[0][i][j];
            }
        }

        for (int i = 1; i <= n; i ++) {
            for (int b1 = 0; b1 <= m; b1 ++) { // left
                for (int b2 = 0; b2 <= m; b2 ++) { // current
                    int preProf = pre[i - 1][b1][b2];
                    for (int b3 = 0; b3 <= m; b3 ++) { // right
                        int curProf = pre[i][b2][b3] = preProf + b2 * getProfit(i, b1 + b2 + b3);
                        if (curProf > max) max = curProf;
                    }
                }
            }
        }
        return max;
    }

    private int getProfit(int x, int y) {
        return cBak[x * 3 * mBak + y - 1];
    }
}
