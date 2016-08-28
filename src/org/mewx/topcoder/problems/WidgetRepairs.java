package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 3/08/16.
 */
public class WidgetRepairs {

    public int days(int[] arrivals, int numPerDay) {
        int count = 0;

        for (int i = 0; i < arrivals.length; i ++) {
            if (i + 1 < arrivals.length) {
                if (arrivals[i] > numPerDay) {
                    // move to another day
                    count += 1;
                    arrivals[i+1] += arrivals[i] - numPerDay;
                }
                else {
                    // 0 or <= numPerDay
                    if (arrivals[i] != 0) {
                        count += 1;
                    }
                }
            } else {
                // the last one
                count += (arrivals[i] / numPerDay) + (arrivals[i] % numPerDay != 0 ? 1 : 0);
            }
        }
        return count;
    }
}
