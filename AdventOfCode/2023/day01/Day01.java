import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            input.add(line);
        }
        s.close();

        Pattern p = Pattern.compile("\\d");

        int result = 0;
        for (String str : input) {
            List<Integer> digits = new ArrayList<>();
            Matcher m = p.matcher(str);
            while (m.find()) {
                digits.add(Integer.parseInt(m.group()));
            }
            result += digits.get(0) * 10 + digits.get(digits.size() - 1);
        }

        System.out.println("part 1: " + result);
    }
}
