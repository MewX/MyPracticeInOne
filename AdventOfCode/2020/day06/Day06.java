import java.util.*;

public class Day06 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Set<Character>> groups = new ArrayList<>();
        groups.add(new HashSet<>());
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                groups.add(new HashSet<>());
                continue;
            }

            Set<Character> g = groups.get(groups.size() - 1);
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                g.add(c);
            }
        }

        int count = 0;
        for (Set<Character> g : groups) {
            count += g.size();
        }
        System.out.println("part 1: " + count);
    }
}
