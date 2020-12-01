import java.util.*;

public class Day01 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int ex = s.nextInt();
            list.add(ex);
            if (set.contains(ex)) {
                System.out.println("part 1: " + (2020 - ex) * ex);
            }
            set.add(2020 - ex);
        }

        // Part 2, stupid brute force.
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == 2020) {
                        System.out.println("part 2: " + list.get(i) * list.get(j) * list.get(k));
                    }
                }
            }
        }
    }
}
