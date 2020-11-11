import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day07 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (!line.isEmpty()) {
                inputs.add(line.toLowerCase());
            }
        }
        s.close();

        // Part 1.
        int count1 = 0;
        for (String input : inputs) {
            if (supportTLS(input)) {
                count1++;
            }
        }
        System.out.println("Part 1: " + count1);
    }

    private static boolean supportTLS(String input) {
        boolean validOutside = false;
        boolean validInside = true;

        boolean in = false;
        int beg = 0;
        for (int i = 0; i < input.length(); i++) {
            if (!in && input.charAt(i) == '[') {
                in = true;
                validOutside |= containsWantedFourLetters(input.substring(beg, i));
                beg = i + 1;
            } else if (in && input.charAt(i) == ']') {
                in = false;
                validInside &= !containsWantedFourLetters(input.substring(beg, i));
                beg = i + 1;
            }
        }

        // Last one.
        validOutside |= containsWantedFourLetters(input.substring(beg));
        return validOutside && validInside;
    }

    private static boolean containsWantedFourLetters(String str) {
        for (int i = 0; i < str.length() - 3; i++) {
            if (str.charAt(i) == str.charAt(i + 3)
                    && str.charAt(i) != str.charAt(i + 1)
                    && str.charAt(i + 1) == str.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
}
