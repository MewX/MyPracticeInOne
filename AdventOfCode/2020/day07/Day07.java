import java.util.HashMap;
import java.util.Scanner;

public class Day07 {
    public static final String SEP = "bags contain";

    static class BagStat extends HashMap<String, Integer> {}
    static class BagMap extends HashMap<String, BagStat> {}

    public static void main(String[] args) {
        BagMap bagMap = new BagMap();

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
                    bagMap.put(color, new BagStat());
                }

                name = name.trim();
                String numString = name.substring(0, name.indexOf(" ")).trim();
                if (numString.equals("no")) {
                    continue;
                }

                int num = Integer.parseInt(numString);
                String cc = name.substring(name.indexOf(" "), name.lastIndexOf(" ")).trim();

                if (!bagMap.containsKey(cc)) {
                    bagMap.put(cc, new BagStat());
                }
                BagStat b = bagMap.get(color);
                b.put(cc, num);
            }
        }

        // DFS
        int count = 0;
        String target = "shiny gold";
        for (String color : bagMap.keySet()) {
            if (!color.equals(target) && dfs(color, target, bagMap)) {
                count ++;
            }
        }
        System.out.println("part 1: " + count);

        // Part 2.
        count = count(target, bagMap);
        System.out.println("part 2: " + count);
    }

    static boolean dfs(String color, String target, BagMap bagMap) {
        for (String bc : bagMap.get(color).keySet()) {
            if (dfs(bc, target, bagMap)) {
                return true;
            }
        }
        return color.equals(target);
    }

    static int count(String color, BagMap bagMap) {
        int c = 0;
        for (String bc : bagMap.get(color).keySet()) {
            c += bagMap.get(color).get(bc) + bagMap.get(color).get(bc) * count(bc, bagMap);
        }
        return c;
    }
}