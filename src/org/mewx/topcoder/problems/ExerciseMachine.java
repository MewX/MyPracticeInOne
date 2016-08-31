package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 31/08/16.
 */
public class ExerciseMachine {
    public int getNumber(String time) {
        String[] t = time.split(":");

        int resultTime = 0;
        resultTime += Integer.valueOf(t[0]);
        resultTime *= 60;
        resultTime += Integer.valueOf(t[1]);
        resultTime *= 60;
        resultTime += Integer.valueOf(t[2]);

        // time / 100 * i
        // time * i / 100
        int count = 0;
        for (int i = 1; i < 100; i ++) {
            if (resultTime * i % 100 == 0) count ++;
        }
        return count;
    }
}
