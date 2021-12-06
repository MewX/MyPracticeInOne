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
        Map<Integer, Long> timerToLantern = new HashMap<>(); // Timer to the number of lanterns.
        for (Integer i : initialTimers) {
            timerToLantern.put(i, timerToLantern.getOrDefault(i, 0L) + 1);
        }
        System.out.println("part 1: " + generate(new HashMap<>(timerToLantern), 80));

        // Part 2.
        System.out.println("part 2: " + generate(new HashMap<>(timerToLantern), 256));
    }

    private static long generate(Map<Integer, Long> map, final int days) {
        for (int day = 0; day < days; day++) {
            Map<Integer, Long> temp = new HashMap<>();
            for (Integer timerKey : map.keySet()) {
                Long count = map.get(timerKey);
                timerKey--;
                if (timerKey < 0) {
                    temp.put(6, temp.getOrDefault(6, 0L) + count);
                    temp.put(8, temp.getOrDefault(8, 0L) + count);
                } else {
                    temp.put(timerKey, temp.getOrDefault(timerKey, 0L) + count);
                }
            }
            map = temp;
//            System.out.print("Day " + day + ": ");
//            dumpMap(timerToLantern);
        }
        return map.values().stream().reduce(0L, Long::sum);
    }

    private static void dumpMap(Map<Integer, Long> timerToLantern) {
        for (Integer key : timerToLantern.keySet()) {
            System.out.print("(" + key + ": " + timerToLantern.get(key) + ")" + ", ");
        }
        System.out.println();
    }
}
