import java.util.*;

public class Day04 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            input.add(line);
        }
        s.close();

        // Parse inputs.
        // First line special.
        // Then 5 lines, 5 lines, etc.
        List<Integer> chosen = new ArrayList<>();
        for (String num : input.get(0).split(",")) {
            chosen.add(Integer.valueOf(num.trim()));
        }
        List<List<List<Integer>>> matrixs = new ArrayList<>();
        for (int i = 1; i < input.size(); i += 5) {
            List<List<Integer>> m = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                // Each row.
                String row = input.get(i + j);
                List<Integer> mRow = new ArrayList<>();
                for (String cell : row.split("\\s+")) {
                    mRow.add(Integer.valueOf(cell.trim()));
                }
                m.add(mRow);
            }
            matrixs.add(m);
        }

        // Part 1.
        // Marking as min integer.
        out: for (Integer num : chosen) {
            for (List<List<Integer>> m : matrixs) {
                markCell(m, num);
                if (checkMatrixDone(m)) {
                    dumpMatrix(m);
                    System.out.println("sum: " + sumAllValidNumbers(m));
                    System.out.println("N: " + num);
                    System.out.println("part 1: " + (sumAllValidNumbers(m) * num));
                    break out;
                }

            }
        }
    }

    private static int sumAllValidNumbers(List<List<Integer>> m) {
        int sum = 0;
        for (List<Integer> row : m) {
            for (Integer num : row) {
                if (num != Integer.MIN_VALUE) {
                    sum += num;
                }
            }
        }
        return sum;
    }

    private static void markCell(List<List<Integer>> m, int targetNum) {
        for (List<Integer> row : m) {
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == targetNum) {
                    row.set(j, Integer.MIN_VALUE);
                    return;
                }
            }
        }
    }

    private static void dumpMatrix (List<List<Integer>> m) {
        for (List<Integer> row : m) {
            for (Integer num : row) {
                if (num != Integer.MIN_VALUE) {
                    System.out.print(" " + num);
                } else {
                    System.out.print(" / ");
                }
            }
            System.out.println();
        }
    }

    private static boolean checkMatrixDone(List<List<Integer>> m) {
        assert m.size() == 5;
        assert m.get(0).size() == 5;

        // Check row.
        boolean allRows = false;
        for (int row = 0; row < 5; row++) {
            boolean done = true;
            for (int col = 0; col < 5; col++) {
                if (m.get(row).get(col) != Integer.MIN_VALUE) {
                    done = false;
                    break;
                }
            }
            allRows = allRows || done;
        }

        // Check col.
        boolean allCols = false;
        for (int col = 0; col < 5; col++) {
            boolean done = true;
            for (int row = 0; row < 5; row++) {
                if (m.get(row).get(col) != Integer.MIN_VALUE) {
                    done = false;
                    break;
                }
            }
            allCols = allCols || done;
        }

        // The rest.
//        boolean leftRight = true, rightLeft = true;
//        for (int row = 0; row < 5; row++) {
//            if (m.get(row).get(row) != Integer.MIN_VALUE) {
//                leftRight = false;
//            }
//            if (m.get(4 - row).get(row) != Integer.MIN_VALUE) {
//                rightLeft = false;
//            }
//        }
//        return allRows || allCols || leftRight || rightLeft;
        return allRows || allCols;
    }

}
