package org.mewx.topcoder.problems;

/**
 * Created by MewX on 8/16/2016.
 */
public class ProblemsToSolve {
    public int minNumber(int[] pleasantness, int variety) {
        // Privoded 2 solutions
        switch(1) {
        case 1:
            return mySolution(pleasantness, variety);

        case 2:
            // This solution is from online, in case you don't understand
            // From: https://community.topcoder.com/stat?c=problem_solution&cr=22655228&rd=10664&pm=7504
            // I think it's slower than mine because mine is O(n), online is O(n^2)
            return onlineBestSolution(pleasantness, variety);
        }
        return 0;
    }


    private int mySolution(int[] pleasantness, int variety) {
        // find max and min position
        int idxMin = 0, idxMax = 0, i;
        for (i = 0; i < pleasantness.length; i ++) {
            if (pleasantness[i] < pleasantness[idxMin]) idxMin = i;
            if (pleasantness[i] > pleasantness[idxMax]) idxMax = i;
            if (pleasantness[idxMax] - pleasantness[idxMin] >= variety) break; // enough
        }
        if (i == pleasantness.length) return pleasantness.length; // no satisfied solution

        // divide this problem into two parts, sort of greedy
        int min = Integer.min(idxMax, idxMin); // get the lower index
        int max = Integer.max(idxMax, idxMin); // get the higher index

        // for all eligible values
        // one exception: 2 2 2 1 5 (3), should be 2(dp) rather than 3 (greedy)
        int save = roads(0, min, pleasantness) + roads(min, max, pleasantness) - 1; // subtract one repetition
        for (i = 0; i < max; i ++) {
            if (Math.abs(pleasantness[i] - pleasantness[max]) >= variety) {
                int temp = roads(0, i, pleasantness) + roads(i, max, pleasantness) - 1;
                if (temp < save) save = temp;
            }
        }
        return save;
    }

    private int roads(int from, int to, int[] pl) {
        // include [from] and [to]
        // use a draft formula to simplified the calculation
        int temp = to - from;
        if (temp % 2 == 1) temp += 1;
        temp = temp / 2 + 1;
        return temp;
    }

    private int onlineBestSolution(int[] pleasantness, int variety) {
        int res = pleasantness.length;
        for (int i = 0; i < pleasantness.length; i++) {
            for (int j = i+1; j < pleasantness.length; j++) {
                int ras = pleasantness[i] - pleasantness[j];
                if (ras < 0) ras = -ras;
                if (ras >= variety) {
                    // you might not understand this part
                    // int tans;
                    // tans = (i+3)/2;
                    // tans = tans+(j-i+3)/2;
                    // tans = tans-1;
                    // let me show you in this way
                    int tans = roads(0, i, pleasantness) + roads(i, j, pleasantness) - 1;

                    if (tans < res) res = tans;
                }          
            }
        }
        return res;
    }
}
