package problem.algorithm;

public class S766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        // from top left to top right
        for (int x = 0; x < matrix.length; x++) {
            if (!startingTestingFrom(x, 0, matrix)) return false;
        }

        // from top left to bottom left
        for (int y = 1; y < matrix[0].length; y++) {
            if (!startingTestingFrom(0, y, matrix)) return false;
        }
        return true;
    }

    private boolean startingTestingFrom(final int idxX, final int idxY, final int[][] matrix) {
        final int wanted = matrix[idxX][idxY];
        for (int x = idxX, y = idxY; x < matrix.length && y < matrix[0].length; x++, y++) {
            if (matrix[x][y] != wanted) {
                return false;
            }
        }
        return true;
    }
}
