import java.math.BigInteger;

public class Day25 {
    public static void main(String[] args) {
        // test
//        System.out.println(new Day25().makeMatrix(6, 6));

        // part 1
        System.out.println(new Day25().makeMatrix(2947, 3029));
    }

    private int makeMatrix(int row, int col) {
        final int bound = row + col + 1;
        int[][] matrix = new int[bound][bound];
        int prev = 20151125;
        matrix[0][0] = prev;

        for (int beg = 2; beg < bound; beg ++) {
            for (int c = 0, r = beg - 1; c < beg; c ++, r --) {
                prev = calcNext(prev);
                matrix[r][c] = prev;
            }
        }
        return matrix[row - 1][col - 1];
    }

    private int calcNext(int prev) {
        BigInteger b = BigInteger.valueOf(prev);
        return b.multiply(BigInteger.valueOf(252533)).mod(BigInteger.valueOf(33554393)).intValue();
    }
}
