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
        int count = 0, count2 = 0;
        for (Map<String, String> pp : passports) {
            if (pp.keySet().equals(fields) || pp.keySet().equals(fields_no_cid)) {
                count ++;
            } else {
                continue;
            }

            // Validate each field.
            if (validateNumberBetween(pp.get("byr"), 1920, 2002) &&
                    validateNumberBetween(pp.get("iyr"), 2010, 2020) &&
                    validateNumberBetween(pp.get("eyr"), 2020, 2030) &&
                    validateHeight(pp.get("hgt")) &&
                    validateHcl(pp.get("hcl")) &&
                    validateEcl(pp.get("ecl")) &&
                    validatePid(pp.get("pid"))) {
                count2 ++;
                System.err.println("valid: " + pp.toString());
            } else {
                System.err.println("not valid: " + pp.toString());
            }
        }
        System.out.println("part 1: " + count);
        System.out.println("part 2: " + count2);
    }

    static boolean validateHeight(String hgt) {
        if (hgt.length() < 2) {
            return false;
        }

        String hnum = hgt.substring(0, hgt.length() - 2);
        if (hgt.endsWith("cm")) {
            return validateNumberBetween(hnum, 150, 193);
        } else if (hgt.endsWith("in")) {
            return validateNumberBetween(hnum, 59, 76);
        }
        return false;
    }

    static boolean validateHcl(String hcl) {
        if (hcl.length() != 7 && hcl.charAt(0) != '#') {
            return false;
        }

        for (int i = 1; i < 7; i++) {
            char b = hcl.charAt(i);
            if (!('0' <= b && b <= '9' || 'a' <= b && b <= 'f')) {
                return false;
            }
        }
        return true;
    }

    static boolean validateEcl(String ecl) {
        return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl);
    }

    static boolean validatePid(String pid) {
        return pid.length() == 9 && validateNumberBetween(pid, 0, 999999999L);
    }

    static boolean validateNumberBetween(String num, long min, long max) {
        try {
            long year = Long.parseLong(num);
            if (min <= year && year <= max) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}
