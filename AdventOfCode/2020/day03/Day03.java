import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<StringBuilder> area = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            area.add(new StringBuilder(line));
        }

        int count1 = countTrees(area, 3, 1);
        System.out.println("part 1: " + count1);

        long count2 = (long)count1 *
                countTrees(area, 1, 1) *
                countTrees(area, 5, 1) *
                countTrees(area, 7, 1) *
                countTrees(area, 1, 2);
        System.out.println("part 2: " + count2);
    }

    static int countTrees(List<StringBuilder> area, int right, int down) {
        int x = right, y = down;
        int count = 0;
        while (y < area.size()) {
            StringBuilder row = area.get(y);
            while (x >= row.length()) {
                row.append(row);
            }

            if (row.charAt(x) == '#') {
                count ++;
            }

            x += right;
            y += down;
        }
        return count;
    }

}
