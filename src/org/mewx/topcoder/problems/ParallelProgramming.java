package org.mewx.topcoder.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by MewX on 8/18/2016.
 */
public class ParallelProgramming {
    private NeedsTable[] needsTable;

    private class NeedsTable {
        public int timeRemaining = 0; // need to be assigned, if == 0, think it finished
        public boolean isRunning = false;
        public HashSet<Integer> needs = new HashSet<>();
//        public List<Integer> needs = new ArrayList<>();
    }

    public int minTime(int[] time, String[] prec) {
//		 {"NNNNNN",
//		 "NNYYYY",
//		 "YNNNNN",
//		 "NNYNYN",
//		 "NNNNNN",
//		 "NNNNNN"}

        // make dependence table
        // A needs B, C, D, ...
        needsTable = new NeedsTable[time.length];
        for (int j = 0; j < time.length; j ++) {
            needsTable[j] = new NeedsTable(); // initial
            needsTable[j].timeRemaining = time[j];
            for (int i = 0; i < time.length; i ++) {
                if (prec[i].charAt(j) == 'Y') {
                    needsTable[j].needs.add(i); // j depends on i
                }
            }
        }

        // select run, and delete
        int result = 0;
        while (hasTaskToRun()) {
            // If it's impossible for all the processes to finish, return -1.
            if (isDealLocked()) return -1;

            // make task run, find min time among running tasks
            int idxMin = -1;
            for (int i = 0; i < needsTable.length; i ++) {
                if (needsTable[i].timeRemaining > 0 && !needsTable[i].isRunning && needsTable[i].needs.size() == 0) {
                    needsTable[i].isRunning = true; // able to run, so run it
                }

                // find min
                if (needsTable[i].isRunning && needsTable[i].timeRemaining != 0) {
                    if (idxMin == -1) idxMin = i;
                    else if (needsTable[i].timeRemaining < needsTable[idxMin].timeRemaining) idxMin = i;
                }
            }

            // subtract min, and remove the dependence
            int minBak = needsTable[idxMin].timeRemaining;
            result += minBak;
            for (int i = 0; i < needsTable.length; i ++) {
                if (needsTable[i].isRunning && needsTable[i].timeRemaining != 0) {
                    needsTable[i].timeRemaining -= minBak;
                    if (needsTable[i].timeRemaining == 0) {
                        // remove the dependence
                        for (NeedsTable nt : needsTable) {
                            if (nt.needs.contains(i)) {
                                nt.needs.remove(i);
                            }
                        }
                    }
                }
            }

        }

        // add the max value
        int max = 0;
        for (int i = 0; i < needsTable.length; i ++) {
            if (needsTable[i].timeRemaining > max) max = needsTable[i].timeRemaining;
        }
        return result + max;
    }

    private boolean hasTaskToRun() {
        for (NeedsTable nt : needsTable) {
            if (nt.timeRemaining != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isDealLocked() {
        for (NeedsTable nt : needsTable) {
            if ((!nt.isRunning || nt.timeRemaining!= 0) && nt.needs.size() == 0) {
                return false;
            }
        }
        return true;
    }
}
