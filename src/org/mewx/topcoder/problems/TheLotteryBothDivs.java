package org.mewx.topcoder.problems;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Created by MewX on 8/12/2016.
 */
public class TheLotteryBothDivs {
    public static final int id = 11359;


    public double find(String[] goodSuffixes) {
        List<String> list = Arrays.asList(goodSuffixes);
        for (int i = 0; i < goodSuffixes.length; i ++) {
            for (int j = i + 1; j < goodSuffixes.length; j ++) {
                if (list.get(i).length() == 0 || list.get(j).length() == 0) continue;

                // delete the longer one
                if (list.get(i).length() > list.get(j).length()) {
                    if (list.get(i).lastIndexOf(list.get(j)) == list.get(i).length() - list.get(j).length()) {
                        list.set(i, "");
                    }
                } else {
                    if (list.get(j).lastIndexOf(list.get(i)) == list.get(j).length() - list.get(i).length()) {
                        list.set(j, "");
                    }
                }
            }
        }

        // count
        double result = 0.0;
        for (String temp : list) {
            if (temp.length() != 0) {
                double t = 1;
                for (int i = 0; i < temp.length(); i ++) {
                    t *= 0.1;
                }
                result += t;
            }
        }

        return result;
    }
}