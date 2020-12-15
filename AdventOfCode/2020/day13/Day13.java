import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String timeDepartStr = s.nextLine().trim();
        String[] busesStr = s.nextLine().trim().split(",");
        s.close();

        // Parse input.
        final int timestamp = Integer.parseInt(timeDepartStr);
        final int[] busIds = new int[busesStr.length];
        for (int i = 0; i < busesStr.length; i++) {
            if (busesStr[i].equals("x")) {
                busIds[i] = -1;
                continue;
            } else {
                busIds[i] = Integer.parseInt(busesStr[i]);
            }
        }

        // Part 1.
        int time = timestamp;
        out: while (true) {
            for (int id : busIds) {
                if (id <= 0) {
                    continue;
                }
                if (time % id == 0) {
                    System.out.println("part 1: " + (time - timestamp) * id);
                    break out;
                }
            }
            time ++;
        }
    }
}
