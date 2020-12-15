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
        System.out.println("part 1: " + calc(list, 2020));

        // Part 2.
        System.out.println("part 2: " + calc(list, 30000000));
    }

    static int calc(List<Integer> list, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        // Init map.
        for (int i = 0; i < list.size() - 1; i++) {
            indexMap.put(list.get(i), i);
        }

        while (list.size() < target) {
            final int last = list.get(list.size() - 1);
            if (indexMap.containsKey(last)) {
                list.add(list.size() - indexMap.get(last) - 1);
            } else {
                list.add(0);
            }
            indexMap.put(list.get(list.size() - 2), list.size() - 2);
//            System.out.format("%d: %d\n", list.size(), list.get(list.size() - 1));
        }
        return list.get(list.size() - 1);
    }
}
