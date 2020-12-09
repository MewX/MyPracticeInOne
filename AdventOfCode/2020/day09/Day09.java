import java.util.*;

public class Day09 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            list.add(Long.parseLong(line));
        }
        s.close();

        for (int i = 25; i < list.size(); i++) {
            boolean hasSum = false;
            for (int j = i - 25; j < i; j++) {
                for (int k = j + 1; k < i; k++) {
                    if (list.get(j) + list.get(k) == list.get(i)) {
                        hasSum = true;
                        break;
                    }
                }
            }

            if (!hasSum) {
                System.out.println("part 1: " + list.get(i));
                break;
            }
        }
    }
}
