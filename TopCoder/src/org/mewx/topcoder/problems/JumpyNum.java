package org.mewx.topcoder.problems;

public class JumpyNum {
    public static void main(String[] args) {
        System.out.println(new JumpyNum().howMany(1, 10)); // one valid
        System.out.println(new JumpyNum().howMany(9, 23));
        System.out.println(new JumpyNum().howMany(2000000000, 2000000000));
        System.out.println(new JumpyNum().howMany(8000, 20934));
    }

    private long[][][][] table = new long [12][10][2][2]; // [iDigi][curDigi][leadingDigi][meetingBound]
    private int[] digis = null;

    public int howMany(int low, int high) {
        if (low > high) return 0;

        // lower bound
        boolean lowValid = true;
        String strLow = Integer.toString(low);
        for (int i = 1; i < strLow.length(); i ++) {
            if (Math.abs(strLow.codePointAt(i - 1) - strLow.codePointAt(i)) < 2) lowValid = false;
        }

        return (int)(calc(high) - calc(low)) + (lowValid ? 1 : 0); // lower bound should be counted as well
    }

    private long calc(int num) {
        String n = Integer.toString(num);

        // reset digis[] into empty
        int len = n.length();
        digis = new int [len];
        for (int i = 0; i < len; i ++)
            digis[i] = n.codePointAt(n.length() - 1 - i) - (int)'0';

        // reset table with -1
        for (int a = 0; a < table.length; a ++)
            for (int b = 0; b < table[a].length; b ++)
                for (int c = 0; c < table[a][b].length; c ++)
                    for (int d = 0; d < table[a][b][c].length; d ++)
                        table[a][b][c][d] = -1;

        long count = 0;
        for (int i = 0; i < digis[len - 1]; i ++) {
            count += dfs(len - 1, i, true, false);
        }
        count += dfs(len - 1, digis[len - 1], true, true); // the highest digit meets the bound
        return count;
    }

    private long dfs(int ith, int digi, boolean isLeadingDigit, boolean meetingBound) {
        if (ith < 0) return 0;
        else if (ith == 0) return 1;
    
        // existing
        if (table[ith][digi][isLeadingDigit?1:0][meetingBound?1:0] != -1)
            return table[ith][digi][isLeadingDigit?1:0][meetingBound?1:0];
        
        // calc
        long count = 0;
        if (digi == 0 && isLeadingDigit) {
            for (int i = 0; i <= 9; i ++)
                count += dfs(ith - 1, i, true, false);
        } else {
            int i = 9;
            if (meetingBound) {
                i = digis[ith - 1];
                if (Math.abs(digi - i) >= 2)
                    count += dfs(ith - 1, i, false, true); // make next 'meetingBound' true
                i --;
            }

            // the rest
            for (; i >= 0; i --) {
                if (Math.abs(digi - i) >= 2)
                    count += dfs(ith - 1, i, false, false);
            }
        }
        table[ith][digi][isLeadingDigit?1:0][meetingBound?1:0] = count;
        return count;
    }
}
