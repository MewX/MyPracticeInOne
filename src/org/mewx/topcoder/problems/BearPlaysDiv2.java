package org.mewx.topcoder.problems;

import java.util.HashSet;

/**
 * Created by MewX on 10/2/2016.
 */
public class BearPlaysDiv2 {
    private static String RI = "impossible";
    private static String RP = "possible";
    private static int MAX_LEVEL = 10;

    public String equalPiles(int A, int B, int C) {
        return dfs(0, A, B, C) ? RP : RI;
    }

    private boolean dfs(int level, int A, int B, int C) {
        if (A == B && B == C) return true;
        if (!isLegal(A, B, C) || level > MAX_LEVEL) return false;

        // sort first
        if (A > C) {
            int temp = A;
            A = C;
            C = temp;
        }
        if (A > B) {
            int temp = A;
            A = B;
            B = temp;
        }
        if (B > C) {
            int temp = B;
            B = C;
            C = temp;
        }

        // now: A <= B <= C
        // select pairs to the next dfs
//        boolean result = false;
//        if (A != B) result |= dfs(level + 1, A << 1, B - A, C);
//        if (B != C) result |= dfs(level + 1, A, B << 1, C - B);
//        if (A != C) result |= dfs(level + 1, A << 1, B, C - A);
//        return result;

        return A != B && dfs(level + 1, A << 1, B - A, C) ||
                B != C && dfs(level + 1, A, B << 1, C - B) ||
                A != C && dfs(level + 1, A << 1, B, C - A);
    }

    private boolean isLegal(int A, int B, int C) {
        return (A + B + C) % 3 == 0;
    }
}

