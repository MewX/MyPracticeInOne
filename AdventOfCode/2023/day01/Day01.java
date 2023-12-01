import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day01 {

    private static final List<String> WORD_TABLE = Arrays.asList("one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine");
    private static final List<String> REVERSED_WORD_TABLE = WORD_TABLE.stream()
        .map(w -> new StringBuilder(w).reverse().toString()).collect(Collectors.toList());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.isEmpty()) {
                continue;
            }
            input.add(line);
        }
        scanner.close();

        // Pattern p = Pattern.compile("\\d");

        int result = 0;
        // for (String str : input) {
        //     List<Integer> digits = new ArrayList<>();
        //     Matcher m = p.matcher(str);
        //     while (m.find()) {
        //         digits.add(Integer.parseInt(m.group()));
        //     }
        //     result += digits.get(0) * 10 + digits.get(digits.size() - 1);
        // }
        //
        // System.out.println("part 1: " + result);

        result = 0;
        Pattern p1 = Pattern.compile("\\d|" + String.join("|", WORD_TABLE));
        Pattern p2 = Pattern.compile("\\d|" + String.join("|", REVERSED_WORD_TABLE));
        for (String str : input) {
            List<Integer> digits = new ArrayList<>();
            Matcher m = p1.matcher(str);
            m.find();
            digits.add(parseInt(m.group()));

            m = p2.matcher(new StringBuilder().append(str).reverse());
            m.find();
            digits.add(parseInt(m.group()));

            result += digits.get(0) * 10 + digits.get(digits.size() - 1);
        }
        System.out.println("part 2: " + result);
    }

    private static int parseInt(String str) {
        if (WORD_TABLE.contains(str)) {
            return 1 + WORD_TABLE.indexOf(str);
        } else if (REVERSED_WORD_TABLE.contains(str)) {
            return 1 + REVERSED_WORD_TABLE.indexOf(str);
        } else {
            return Integer.parseInt(str);
        }
    }
}
