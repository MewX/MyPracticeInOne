package org.mewx.topcoder.problems;

/**
 * Created by MewX on 8/16/2016.
 */
public class ProblemsToSolve {
    public int minNumber(int[] pleasantness, int variety) {
        // find max and min position
        int idxMin = 0, idxMax = 0, i;
        for (i = 0; i < pleasantness.length; i ++) {
            if (pleasantness[i] < pleasantness[idxMin]) idxMin = i;
            if (pleasantness[i] > pleasantness[idxMax]) idxMax = i;
            if (pleasantness[idxMax] - pleasantness[idxMin] >= variety) break;
        }
        if (i == pleasantness.length) return pleasantness.length;

        // divide this problem into two parts
        int min = Integer.min(idxMax, idxMin);
        int max = Integer.max(idxMax, idxMin);
        // for all eligible values
        int save = roads(0, min, pleasantness) + roads(min, max, pleasantness) - 1; // subtract one repetition
        for (i = 0; i < max; i ++) {
            if (abs(pleasantness[i] - pleasantness[max]) >= variety) {
                int temp = roads(0, i, pleasantness) + roads(i, max, pleasantness) - 1;
                if (temp < save) save = temp;
            }
        }
        return save;
    }

    private int abs(int a) {
        return a < 0 ? -a : a;
    }

    private int roads(int from, int to, int[] pl) {
        // include [from] and [to]
        // use a draft formula to simplified the calculation
        int temp = to - from;
        if (temp % 2 == 1) temp += 1;
        temp = temp / 2 + 1;
        return temp;
    }
}
