import java.util.*;

/**
 * Created by MewX on 3/5/2017.
 */
public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        final int total = Integer.valueOf(s.nextLine());
        for (int round = 0; round < total; round ++) {
            final int num = Integer.valueOf(s.nextLine());

            int bestLength = 0;
            String bestString = "";

            HashSet<Character> set = new HashSet<>();
            for (int i = 0; i < num; i ++) {
                String temp = s.nextLine();
                set.clear();
                for (int j = 0; j < temp.length(); j ++) {
                    if (temp.charAt(j) == ' ') continue;
                    set.add(temp.charAt(j));
                }

                if (bestLength < set.size()) {
                    bestLength = set.size();
                    bestString = temp;
                } else if (bestLength == set.size() && temp.compareTo(bestString) < 0) {
                    bestString = temp;
                }
            }

            System.out.format("Case #%d: %s\n", round + 1, bestString);
        }
    }
}
