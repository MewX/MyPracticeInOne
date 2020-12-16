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
        List<List<Integer>> validTickets = new ArrayList<>();
        for (List<Integer> row : nearbyTickets) {
            boolean ticketValid = true;
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
                    ticketValid = false;
                }
            }

            if (ticketValid) {
                // Valid tickets.
                validTickets.add(row);
            }
        }
        System.out.println("part 1: " + count);

        // Part 2.
        validTickets.add(myTickets);
        System.out.println("valid tickets: " + validTickets.size());

        List<Set<String>> possibleMapping = new ArrayList<>();
        for (int i = 0; i < validTickets.get(0).size(); i++) {
            possibleMapping.add(new HashSet<>(fields.keySet()));

            for (List<Integer> row : validTickets) {
                Set<String> removed = new HashSet<>();
                for (String key : possibleMapping.get(i)) {
                    if (!fields.get(key).contains(row.get(i))) {
                        removed.add(key);
                    }
                }
                possibleMapping.get(i).removeAll(removed);
            }

            System.out.format("index %d: ", i);
            for (String key : possibleMapping.get(i)) {
                System.out.print(key + ", ");
            }
            System.out.println();
        }

        // Eliminations.
        String[] order = new String[fields.keySet().size()];
        out: while (true) {
            for (int i = 0; i < possibleMapping.size(); i ++) {
                if (possibleMapping.get(i).size() == 1) {
                    order[i] = possibleMapping.get(i).iterator().next();

                    // Start eliminations.
                    for (Set<String> mapping : possibleMapping) {
                        mapping.remove(order[i]);
                    }
                    continue out;
                }
            }

            // Check exist condition.
            boolean canExit = true;
            for (Set<String> mapping : possibleMapping) {
                if (mapping.size() != 0) {
                    canExit = false;
                    break;
                }
            }
            if (canExit) {
                break;
            }
        }

        long product = 1;
        for (int i = 0; i < order.length; i++) {
            if (order[i].startsWith("departure")) {
                product *= myTickets.get(i);
                System.out.format("timing '%s' %d at %d\n", order[i], myTickets.get(i), i);
            }
        }
        System.out.println("part 2: " + product);
    }

    static boolean validBuild(Map<String, Set<Integer>> fieldMap, List<String> order, List<List<Integer>> validTickets) {
        for (int i = 0; i < order.size(); i++) {
            Set<Integer> ranges = fieldMap.get(order.get(i));
            for (List<Integer> row : validTickets) {
                if (!ranges.contains(row.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
