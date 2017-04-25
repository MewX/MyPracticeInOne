package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class Elections {
    public int visit(String[] likelihoods) {
        int idxLowest = 0;
        double lowestRate = 1.0;
        for (int i = 0; i < likelihoods.length; i ++) {
            int count1 = 0;
            for (int j = 0; j < likelihoods[i].length(); j ++) {
                if (likelihoods[i].charAt(j) == '1') count1 ++;
            }

            double temp = count1 * 1.0 / likelihoods[i].length();
            if (temp < lowestRate) {
                lowestRate = temp;
                idxLowest = i;
            }
        }
        return idxLowest;
    }
}