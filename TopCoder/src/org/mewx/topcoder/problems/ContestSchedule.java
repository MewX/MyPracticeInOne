package org.mewx.topcoder.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MewX on 8/18/2016.
 */
public class ContestSchedule {
    private List<Data> inputData = new ArrayList<>();
    private HashMap<Integer, Integer> idxStartTime = new HashMap<>();
//	private HashMap<Integer, Integer> refMap = new HashMap<>();

    private class Data {
        public int start, end, percentage;
        public Data(int s, int t, int p) {
            start = s;
            end = t;
            percentage = p;
        }
    }

    public double expectedWinnings(String[] contests) {
        // retrieve input data
        for (String str : contests) {
            String[] temp = str.split(" ");
            inputData.add( new Data(Integer.valueOf(temp[0]),
                    Integer.valueOf(temp[1]), Integer.valueOf(temp[2])));
        }

        // sort by start time, and set index map
        inputData.sort((a, b) -> a.start - b.start == 0 ? a.end - b.end : a.start - b.start);
        int temp = inputData.get(0).start;
        idxStartTime.put(temp, 0);
        for (int i = 1; i < inputData.size(); i ++) {
            if (inputData.get(i).start != temp) {
                temp = inputData.get(i).start;
                idxStartTime.put(temp, i);
            }
        }

        // try every branches
        return distribute(0) * 1.0 / 100;
    }

    private int distribute(int startIdx) {
//		if (refMap.containsKey(startIdx)) {
//			System.out.println("Reffed key: " + startIdx);
//			return refMap.get(startIdx);
//		}

        int s = inputData.get(startIdx).start;
        int e = inputData.get(startIdx).end;
        int max = tryEveryBranch(startIdx);

        startIdx += 1;
        while (startIdx < inputData.size() && inputData.get(startIdx).start >= s && inputData.get(startIdx).start < e) {
            // same startIdx
            int temp = tryEveryBranch(startIdx);
            if (temp > max) max = temp;
            startIdx += 1;
        }

//		refMap.put(startIdx, max);
        return max;
    }

    private int tryEveryBranch(int startIdx) {
        int nextStart = inputData.get(startIdx).end;
        int nextIdx = startIdx;
        while (nextIdx < inputData.size() && inputData.get(nextIdx).start < nextStart) {
            nextIdx += 1;
        }
        if (nextIdx >= inputData.size()) {
            return inputData.get(startIdx).percentage;
        }

        return inputData.get(startIdx).percentage + distribute(nextIdx);
    }
}
