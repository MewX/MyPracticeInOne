package problem.algorithm;

public class S868 {
    public int binaryGap(int N) {
        String str = Integer.toBinaryString(N);

        int dist = 0;
        int begIdx = 0;
        while (begIdx < str.length() && str.charAt(begIdx) == '0') {
            begIdx ++;
        }

        for (int i = begIdx + 1; i < str.length(); i++) {
            if (str.charAt(i) != '1') continue;

            // == '1'
            dist = Math.max(dist, i - begIdx);
            begIdx = i;
        }
        return dist;
    }
}
