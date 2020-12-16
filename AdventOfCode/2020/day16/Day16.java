import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {
    private static Pattern info = Pattern.compile("(.+?): (\\d+?)-(\\d+?) or (\\d+?)-(\\d+?)$");

    public static void main(String[] args) {
        List<Integer> myTickets = new ArrayList<>();
        List<List<Integer>> nearbyTickets = new ArrayList<>();
        Map<String, Set<Integer>> fields = new HashMap<>();

        Scanner s = new Scanner(System.in);
        int section = 1;
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();

            // Section switcher.
            if (line.startsWith("your ticket")) {
                section = 2;
                continue;
            } else if (line.startsWith("nearby tickets")) {
                section = 3;
                continue;
            } else if (line.isEmpty()) {
                continue;
            }

            switch (section) {
                case 1:
                    // Ues the pattern.
                    Matcher m = info.matcher(line);
                    System.out.println("matching: " + line);
                    if (m.find()) {
                        fields.put(m.group(1), new HashSet<>());
                        for (int i = Integer.parseInt(m.group(2)); i <= Integer.parseInt(m.group(3)); i++) {
                            fields.get(m.group(1)).add(i);
                        }
                        for (int i = Integer.parseInt(m.group(4)); i <= Integer.parseInt(m.group(5)); i++) {
                            fields.get(m.group(1)).add(i);
                        }
                    } else {
                        assert false;
                    }
                    break;
                case 2:
                    // Parse my tickets.
                    String[] myTicketStr = line.split(",");
                    for (String str : myTicketStr) {
                        myTickets.add(Integer.parseInt(str));
                    }
                    break;
                case 3:
                    String[] nearbyTicketStr = line.split(",");
                    nearbyTickets.add(new ArrayList<>());
                    for (String str : nearbyTicketStr) {
                        nearbyTickets.get(nearbyTickets.size() - 1).add(Integer.parseInt(str));
                    }
                    break;
            }

        }
        s.close();

        // Part 1.
        int count = 0;
        for (List<Integer> row : nearbyTickets) {
            for (Integer t : row) {
                boolean matched = false;
                for (String key : fields.keySet()) {
                    if (fields.get(key).contains(t)) {
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    System.out.println("not found " + t);
                    count += t;
                }
            }
        }
        System.out.println("part 1: " + count);
    }
}
