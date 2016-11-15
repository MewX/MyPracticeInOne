package org.mewx.topcoder.problems;

/**
 * Created by MewX on 8/16/2016.
 */
public class ElevatorLimit {
    public int[] getRange(int[] enter, int[] exit, int physicalLimit) {
        int[] result = new int [2];

        // brute force again
        int maxPeople = 0, minPeople = 0;
        int currentPeople = minPeople;
        for (int i = 0; i < enter.length; i ++) {
            currentPeople -= exit[i];
            if (currentPeople < minPeople) minPeople = currentPeople;
            currentPeople += enter[i];
        }

        result[0] = minPeople < 0 ? -minPeople : 0;
        currentPeople = result[0];
        for (int i = 0; i < enter.length; i ++) {
            currentPeople = currentPeople - exit[i] + enter[i];
            if (currentPeople > maxPeople) maxPeople = currentPeople;
        }

        result[1] = physicalLimit - maxPeople + result[0];
        if (result[1] > physicalLimit) result[1] = physicalLimit;

        if (maxPeople > physicalLimit || maxPeople < 0 || minPeople < -physicalLimit) return new int [0];
        return result;
    }
}
