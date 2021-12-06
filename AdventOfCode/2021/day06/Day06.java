import java.util.*;

public class Day06 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Integer> initialTimers = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            for (String i : line.split(",")) {
                initialTimers.add(Integer.parseInt(i.trim()));
            }
        }
        s.close();

        // Part 1.
        final int DAYS = 80;
        List<Integer> timers = new ArrayList<>(initialTimers);
        for (int day = 0; day < DAYS; day++) {
            final int originalCount = timers.size();
            for (int i = 0; i < originalCount; i++) {
                int lantern = timers.get(i);
                lantern --;
                if (lantern < 0) {
                    timers.set(i, 6);
                    timers.add(8);
                } else {
                    timers.set(i, lantern);
                }
            }
//            System.out.print("Day " + day + ": ");
//            dump(timers);
        }
        System.out.println("part 1: " + timers.size());

        // Part 2.
    }

    private static void dump(List<Integer> list) {
        for (Integer i : list) {
            System.out.print("" + i + ",");
        }
        System.out.println();
    }
}
