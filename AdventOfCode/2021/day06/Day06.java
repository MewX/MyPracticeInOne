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
        int DAYS = 80;
        List<Integer> timers = new ArrayList<>(initialTimers);
        for (int day = 0; day < DAYS; day++) {
            final int originalCount = timers.size();
            for (int i = 0; i < originalCount; i++) {
                int lantern = timers.get(i);
                lantern--;
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
        DAYS = 256;
        Map<Integer, Long> timerToLantern = new HashMap<>(); // Timer to the number of lanterns.
        for (Integer i : initialTimers) {
            timerToLantern.put(i, timerToLantern.getOrDefault(i, 0L) + 1);
        }
        for (int day = 0; day < DAYS; day++) {
            Map<Integer, Long> temp = new HashMap<>();
            for (Integer timerKey : timerToLantern.keySet()) {
                Long count = timerToLantern.get(timerKey);
                timerKey--;
                if (timerKey < 0) {
                    temp.put(6, temp.getOrDefault(6, 0L) + count);
                    temp.put(8, temp.getOrDefault(8, 0L) + count);
                } else {
                    temp.put(timerKey, temp.getOrDefault(timerKey, 0L) + count);
                }
            }
            timerToLantern = temp;
//            System.out.print("Day " + day + ": ");
//            dumpMap(timerToLantern);
        }
        System.out.println("part 2: " + timerToLantern.values().stream().reduce(0L, Long::sum));
    }

    private static void dump(List<Integer> list) {
        for (Integer i : list) {
            System.out.print("" + i + ",");
        }
        System.out.println();
    }

    private static void dumpMap(Map<Integer, Long> timerToLantern) {
        for (Integer key : timerToLantern.keySet()) {
            System.out.print("(" + key + ": " + timerToLantern.get(key) + ")" + ", ");
        }
        System.out.println();
    }
}
