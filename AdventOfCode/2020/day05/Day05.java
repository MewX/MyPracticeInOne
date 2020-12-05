import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> bp = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            assert line.length() == 10;
            bp.add(line);
        }

        int max1 = 0;
        for (String p : bp) {
            int row = parseRow(p.substring(0, 7));
            int col = parseCol(p.substring(7));
            int seat = row * 8 + col;
            max1 = Math.max(max1, seat);
            System.out.format("seat %s id is %d (row %d, col %d)\n", p, seat, row, col);
        }
        System.out.println("part 1: " + max1);
    }

    static int parseRow(String row) {
        assert row.length() == 7;
        return bsearch(row, 'F', 127);
    }

    static int parseCol(String row) {
        assert row.length() == 3;
        return bsearch(row, 'L', 7);
    }

    static int bsearch(String str, char low, int max) {
        int min = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == low) {
                max = max - (max - min + 1) / 2;
            } else {
                min = min + (max - min + 1) / 2;
            }
        }
        assert min == max;
        return min;
    }
}
