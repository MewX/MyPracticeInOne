package problem.algorithm;

public class S883 {

    public int projectionArea(int[][] grid) {
        int xy, xz, yz;
        xy = xz = yz = 0;

        // xy
        for (int[] a : grid) {
            for (int b : a) {
                if (b != 0) xy ++;
            }
        }

        // yz
        for (int[] a : grid) {
            int max = 0;
            for (int b : a) {
                max = Math.max(b, max);
            }
            yz += max;
        }

        // xz
        for (int x = 0; x < grid[0].length; x++) {
            int max = 0;
            for (int y = 0; y < grid.length; y++) {
                max = Math.max(grid[y][x], max);
            }
            xz += max;
        }

        return xy + xz + yz;
    }
}
