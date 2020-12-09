import java.util.*;

public class Day09 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            list.add(Long.parseLong(line));
        }
        s.close();

        // Part 1.
        long weak = 0;
        for (int i = 25; i < list.size(); i++) {
            boolean hasSum = false;
            for (int j = i - 25; j < i; j++) {
                for (int k = j + 1; k < i; k++) {
                    if (list.get(j) + list.get(k) == list.get(i)) {
                        hasSum = true;
                        break;
                    }
                }
            }

            if (!hasSum) {
                weak = list.get(i);
                System.out.println("part 1: " + list.get(i));
                break;
            }
        }

        // Part 2.
        for (int i = 0; i < list.size(); i++) {
            long sum = 0;
            int j = i;
            while (sum < weak && j < list.size()) {
                sum += list.get(j++);
            }

            if (sum == weak) {
                long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
                for (int k = i; k < j; k++) {
                    if (list.get(k) > max) {
                        max = list.get(k);
                    }
                    if (list.get(k) < min) {
                        min = list.get(k);
                    }
                }
                System.out.println("part 2: " + (min + max));
                break;
            }
        }
    }
}
