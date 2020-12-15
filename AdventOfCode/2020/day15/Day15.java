import java.util.*;

public class Day15 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] nums = line.split(",");
            for (String n : nums) {
                list.add(Integer.parseInt(n));
            }
        }
        s.close();

        // Part 1.
        final int target = 2020;
        while (list.size() < target) {
            // Update the list.
            final int last = list.get(list.size() - 1);
            final List<Integer> sublist = list.subList(0, list.size() - 1);
            if (sublist.contains(last)) {
                int lastTurn = list.size();
                int secondLastTurn = sublist.lastIndexOf(last) + 1;
                list.add(lastTurn - secondLastTurn);
            } else {
                list.add(0);
            }
            System.out.format("%d: %d\n", list.size(), list.get(list.size() - 1));
        }
        System.out.println("part 1: " + list.get(target - 1));
    }
}
