package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class VolumeGuess {

    private class InfoPair {
        public int num1, num2, volume;
    }

    public int correctVolume(String[] queries, int numberOfBoxes, int ithBox) {
        InfoPair[] ip = new InfoPair[queries.length];

        // parse all the info pairs
        for (int i = 0; i < queries.length; i ++) {
            String[] temp = queries[i].split(",");
            ip[i] = new InfoPair();
            ip[i].num1 = Integer.valueOf(temp[0]);
            ip[i].num2 = Integer.valueOf(temp[1]);
            ip[i].volume = Integer.valueOf(temp[2]);
        }

        // compare all the queries that contain ithBox
        for (int i = 0; i < ip.length; i ++) {
            if (!containsTargetValue(ip[i], ithBox)) continue;

            for (int j = i + 1; j < ip.length; j ++) {
                if (!containsTargetValue(ip[j], ithBox)) continue;
                if (ip[i].volume == ip[j].volume)
                    return ip[i].volume;

            }
        }
        return 0;
    }

    private boolean containsTargetValue(InfoPair ip, int ith) {
        return ip.num1 == ith || ip.num2 == ith;
    }
}
