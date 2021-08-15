package problem.algorithm;

public class S695 {
    private class Node {
        boolean isLand;
        boolean isVisited = false;

        Node(boolean isLand) {
            this.isLand = isLand;
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        Node[][] converted = new Node[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                converted[i][j] = new Node(grid[i][j] == 1);
            }
        }

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, traverseAndGetMax(converted, i, j));
            }
        }
        return max;
    }

    private int traverseAndGetMax(Node[][] grid, int idxRow, int idxColumn) {
        if (grid[idxRow][idxColumn].isVisited) return 0;
        grid[idxRow][idxColumn].isVisited = true;
        if (!grid[idxRow][idxColumn].isLand) return 0;

        int sum = 1;
        if (idxRow - 1 >= 0) {
            sum += traverseAndGetMax(grid, idxRow - 1, idxColumn);
        }
        if (idxRow + 1 < grid.length) {
            sum += traverseAndGetMax(grid, idxRow + 1, idxColumn);
        }
        if (idxColumn - 1 >= 0) {
            sum += traverseAndGetMax(grid, idxRow, idxColumn - 1);
        }
        if (idxColumn + 1 < grid[0].length) {
            sum += traverseAndGetMax(grid, idxRow, idxColumn + 1);
        }
        return sum;
    }
}
