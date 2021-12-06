import java.util.*;

public class JavaTmpl {
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

        System.out.println("part 1: ");
    }
}
