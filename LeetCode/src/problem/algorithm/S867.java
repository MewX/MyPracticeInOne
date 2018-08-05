package problem.algorithm;

public class S867 {
    public int[][] transpose(int[][] A) {
        if (A == null) return null;

        int[][] B = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }
}
