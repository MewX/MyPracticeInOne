package org.mewx.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MewX on 8/30/2016.
 */
public class SnowyWinter {

    private class P {
        public int start;
        public int end;

        public P(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int snowyHighwayLength(int[] startPoints, int[] endPoints) {
        List<P> list = new ArrayList<>();
        for (int i = 0; i < startPoints.length; i ++) {
            list.add(new P(startPoints[i], endPoints[i]));
        }
        list.sort((a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);

//        for (int i = 0; i < list.size(); i ++) {
//            System.out.println(i + ": " + list.get(i).start + ", " + list.get(i).end);
//        }
//        System.out.println();

        int count = 0;
        for (int i = 0; i < list.size();) {
            int backSave = list.get(i).end;

            int j = i + 1;
            for (; j < startPoints.length; j ++) {
                if (list.get(j).start < backSave) {
                    if (list.get(j).end > backSave) backSave = list.get(j).end; // save it
                    continue;
                }
                break;
            }
            count += backSave - list.get(i).start;
            i = j;
        }
        return count;
    }
}
