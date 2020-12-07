import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day07 {
    public static final String SEP = "bags contain";

    static class Bag {
        String color;
        Map<Bag, Integer> contains;

        Bag(String color) {
            this.color = color;
            contains = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Map<String, Bag> bagMap = new HashMap<>();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String color = line.substring(0, line.indexOf(SEP)).trim();
            String[] bagNames = line.substring(line.indexOf(SEP) + SEP.length()).trim().split(",");
            for (String name : bagNames) {
                // Current color.
                if (!bagMap.containsKey(color)) {
                    Bag b = new Bag(color);
                    bagMap.put(color, b);
                }

                name = name.trim();
                String numString = name.substring(0, name.indexOf(" ")).trim();
                if (numString.equals("no")) {
                    continue;
                }

                int num = Integer.parseInt(numString);
                String cc = name.substring(name.indexOf(" "), name.lastIndexOf(" ")).trim();

                if (!bagMap.containsKey(cc)) {
                    Bag b = new Bag(cc);
                    bagMap.put(cc, b);
                }

                Bag b = bagMap.get(color);
                b.contains.put(bagMap.get(cc), num);
            }
        }

        // DFS
        int count = 0;
        String target = "shiny gold";
        for (Bag b : bagMap.values()) {
            if (!b.color.equals(target) && dfs(b, target)) {
                count ++;
            }
        }
        System.out.println("part 1: " + count);

        // Part 2.
        count = count(bagMap.get(target));
        System.out.println("part 2: " + count);
    }

    static boolean dfs(Bag bag, String target) {
        if (bag.color.equals(target)) {
            return true;
        }

        if (bag.contains.size() == 0) {
            return false;
        }

        for (Bag b : bag.contains.keySet()) {
            if (dfs(b, target)) {
                return true;
            }
        }
        return false;
    }

    static int count(Bag bag) {
        int c = 0;
        for (Bag b : bag.contains.keySet()) {
            c += bag.contains.get(b) + bag.contains.get(b) * count(b);
        }
        return c;
    }
}