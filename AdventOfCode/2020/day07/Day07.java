import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {
    private static final String TARGET = "shiny gold";
    private static final Pattern linePattern = Pattern.compile("(.+?) bags contain (.*?)\\.");
    private static final Pattern containPattern = Pattern.compile("(\\d+) (.+?) bag");

    static class BagStat extends HashMap<String, Integer> {}
    static class BagMap extends HashMap<String, BagStat> {}

    public static void main(String[] args) {
        BagMap bagMap = new BagMap();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            Matcher lineMatcher = linePattern.matcher(line);
            if (!lineMatcher.find()) {
                continue;
            }

            // Parse line.
            String color = lineMatcher.group(1);
            if (!bagMap.containsKey(color)) {
                bagMap.put(color, new BagStat());
            }

            // Parse contained bags.
            Matcher bagContainMatcher = containPattern.matcher(lineMatcher.group(2));
            while (bagContainMatcher.find()) {
                int num = Integer.parseInt(bagContainMatcher.group(1));
                String cc = bagContainMatcher.group(2);
                if (!bagMap.containsKey(cc)) {
                    bagMap.put(cc, new BagStat());
                }
                bagMap.get(color).put(cc, num);
            }
        }

        // Part 1.
        int count = 0;
        for (String color : bagMap.keySet()) {
            if (!color.equals(TARGET) && containsBag(color, TARGET, bagMap)) {
                count ++;
            }
        }
        System.out.println("part 1: " + count);

        // Part 2.
        count = countBags(TARGET, bagMap);
        System.out.println("part 2: " + count);
    }

    static boolean containsBag(String color, String target, BagMap bagMap) {
        for (String bc : bagMap.get(color).keySet()) {
            if (containsBag(bc, target, bagMap)) {
                return true;
            }
        }
        return color.equals(target);
    }

    static int countBags(String color, BagMap bagMap) {
        int c = 0;
        for (String bc : bagMap.get(color).keySet()) {
            c += bagMap.get(color).get(bc) + bagMap.get(color).get(bc) * countBags(bc, bagMap);
        }
        return c;
    }
}