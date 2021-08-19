package problem.algorithm;

public class S796 {
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) return false;

        final int LENGTH = A.length();
        for (int i = 1; i < LENGTH; i ++) {
            // i is the length of shifts
            if (A.substring(0, i).equals(B.substring(LENGTH - i))
                    && A.substring(i).equals(B.substring(0, LENGTH - i))) {
                return true;
            }
        }
        return false;
    }
}
