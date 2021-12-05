import java.awt.*;
import java.util.*;
import java.util.List;

public class Day05 {
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

        // Parse.
        List<Point> from = new ArrayList<>(), to = new ArrayList<>();
        for (String i : input) {
            String[] points = i.split("->");
            Point f = new Point(Integer.parseInt(points[0].split(",")[0].trim()),
                    Integer.parseInt(points[0].split(",")[1].trim())),
                    t = new Point(Integer.parseInt(points[1].split(",")[0].trim()),
                            Integer.parseInt(points[1].split(",")[1].trim()));
            from.add(f);
            to.add(t);
            System.out.format("%d, %d -> %d, %d\n", f.x, f.y, t.x, t.y);
        }

        // Part 1.
        int[][] map = new int[1000][1000];
        for (int i = 0; i < from.size(); i++) {
            // Check in line.
            if (from.get(i).x == to.get(i).x) {
                int y1 = from.get(i).y, y2 = to.get(i).y;
                if (y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }
                for (int y = y1; y <= y2; y++) {
                    map[from.get(i).x][y]++;
                }
            }
            if (from.get(i).y == to.get(i).y) {
                int x1 = from.get(i).x, x2 = to.get(i).x;
                if (x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }
                for (int x = x1; x <= x2; x++) {
                    map[x][from.get(i).y]++;
                }
            }
        }
        int count = 0;
        for (int[] row : map) {
            for (int cell : row) {
                if (cell > 1) {
                    count++;
                }
            }
        }
        dump(map, 10, 10);
        System.out.println("part 1: " + count);
    }

    private static void dump(int[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print("" + map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
