package org.mewx.topcoder.problems;

/**
 * Created by MewX on 8/16/2016.
 */
public class ElevatorLimit {
    public int[] getRange(int[] enter, int[] exit, int physicalLimit) {
        // simulate
        int maxPeople = Integer.MIN_VALUE, minPeople = Integer.MAX_VALUE;
        int currentPeople = 0;
        for (int i = 0; i < enter.length; i ++) {
            currentPeople -= exit[i];
            if (currentPeople < minPeople) minPeople = currentPeople;
            currentPeople += enter[i];
            if (currentPeople > maxPeople) maxPeople = currentPeople;
        }

        // limitation on maxPeople and minPeople
        if (maxPeople - minPeople > physicalLimit || -minPeople > physicalLimit) return new int[0];
        else return new int[] {-minPeople, maxPeople < 0 ? physicalLimit : physicalLimit - maxPeople};
    }
}
