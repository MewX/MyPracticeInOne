import java.util.*;

public class Day06 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Set<Character>> groups = new ArrayList<>();
        List<Set<Character>> groups2 = new ArrayList<>();
        groups.add(new HashSet<>());
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                groups.add(new HashSet<>());
                continue;
            }

            Set<Character> g = new HashSet<>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                g.add(c);
            }

            // part 1
            groups.get(groups.size() - 1).addAll(g);

            // part 2
            if (groups2.size() != groups.size()) {
                groups2.add(new HashSet<>(g));
            }
            groups2.get(groups2.size() - 1).retainAll(g);
        }

        int count = 0;
        for (Set<Character> g : groups) {
            count += g.size();
        }
        System.out.println("part 1: " + count);

        count = 0;
        for (Set<Character> g : groups2) {
            count += g.size();
        }
        System.out.println("part 2: " + count);
    }
}
