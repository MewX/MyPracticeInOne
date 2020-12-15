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
            } else {
                busIds[i] = Integer.parseInt(busesStr[i]);
            }
        }

        // Part 1.
        long time = timestamp;
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

        // Part 2: find max and use the max as a base.
        int max = 0, maxTOffset = 0;
        for (int i = 0; i < busIds.length; i++) {
            if (busIds[i] > max) {
                max = busIds[i];
                maxTOffset = i;
            }
        }

        time = 0;
        while (true) {
            boolean found = true;
            for (int i = 0; i < busIds.length; i++) {
                if (busIds[i] <= 0) {
                    continue;
                }
                if ((time + i - maxTOffset) % busIds[i] != 0) {
                    found = false;
                    break;
                }
            }

            if (found) {
                System.out.println("part 2: " + time);
                break;
            }
            time += max;
        }

    }
}
