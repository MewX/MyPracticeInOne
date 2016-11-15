package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class Elections {
    public int visit(String[] likelihoods) {
        int idxMin = 0;
        double minPerc = Double.MAX_VALUE;

        for (int i = 0; i < likelihoods.length; i ++) {
            int count = 0;
            for (int j = 0; j < likelihoods[i].length(); j ++) {
                if (likelihoods[i].charAt(j) == '1') {
                    count ++;
                }
            }

            double temp = count * 1.0 / likelihoods[i].length();
            if (temp < minPerc) {
                idxMin = i;
                minPerc = temp;
            }
        }

        return idxMin;
    }
}