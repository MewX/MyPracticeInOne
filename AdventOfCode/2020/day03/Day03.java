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

        int right = 3, down = 1;
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
        System.out.println("part 1: " + count);

    }

}
