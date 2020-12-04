import java.util.*;

public class Day04 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Map<String, String>> passports = new ArrayList<>();
        passports.add(new HashMap<>());
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                passports.add(new HashMap<>());
                continue;
            }

            Map<String, String> pp = passports.get(passports.size() - 1);
            for (String token : line.split(" ")) {
                String[] tokens = token.split(":");
                assert token.length() == 2;
                pp.put(tokens[0], tokens[1]);
            }
        }

        // Part 1, count valid passports.
        final Set<String> fields = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"));
        final Set<String> fields_no_cid = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
        int count = 0;
        for (Map<String, String> pp : passports) {
            if (pp.keySet().equals(fields) || pp.keySet().equals(fields_no_cid)) {
                count ++;
            }
        }
        System.out.println("part 1: " + count);
    }
}
