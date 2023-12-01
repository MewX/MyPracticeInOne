import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day01 {

    private static final List<String> WORD_TABLE = Arrays.asList("one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine");

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

        Pattern p = Pattern.compile("\\d");

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

        // OK, writing this code will get me fired by Google.
        result = 0;
        for (String str : input) {
            for (int i = 1; i <= 9; i++) {
                str = str.replace("" + i, WORD_TABLE.get(i - 1));
            }
            System.out.println("After: " + str);

            String s = str;
            List<Integer> firstWordIndex = WORD_TABLE.stream()
                .map(w -> s.contains(w) ? s.indexOf(w) : 1000).collect(Collectors.toList());
            int firstNumber = 1 + WORD_TABLE.indexOf(WORD_TABLE.stream()
                .min(Comparator.comparingInt(a -> firstWordIndex.get(WORD_TABLE.indexOf(a)))).get());
            System.out.println("First " + firstWordIndex + " -> " + firstNumber);

            List<Integer> lastWordIndex = WORD_TABLE.stream()
                .map(w -> s.contains(w) ? s.lastIndexOf(w) : -1).collect(Collectors.toList());
            int lastNumber = 1 + WORD_TABLE.indexOf(WORD_TABLE.stream()
                .max(Comparator.comparingInt(a -> lastWordIndex.get(WORD_TABLE.indexOf(a)))).get());
            System.out.println("Last " + lastWordIndex + " -> " + lastNumber);

            int num = firstNumber * 10 + lastNumber;
            System.out.println("Added " + num);
            result += num;
        }
        System.out.println("part 2: " + result);
    }
}
