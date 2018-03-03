import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day19 {
    private static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> src = new ArrayList<>(), dst = new ArrayList<>();
        String input = "";
        while (s.hasNextLine()) {
            String temp = s.nextLine().trim();
            int idx = temp.indexOf(" => ");
            if (idx == -1) {
                if (temp.length() != 0) {
                    input = temp;
                    break;
                }
                // else continue
            } else {
                src.add(temp.substring(0, idx));
                dst.add(temp.substring(idx + 4));
            }
        }
        s.close();

        // calculate
        for (int idxReplacement = 0; idxReplacement < src.size(); idxReplacement++) {
            for (int i = 0; i < input.length(); i++) {
                if (foundEqHere(src.get(idxReplacement), input, i)) {
                    set.add(input.substring(0, i)
                            + dst.get(idxReplacement)
                            + input.substring(i + src.get(idxReplacement).length()));
                }

            }

        }
        System.out.println(set.size());
    }

    private static boolean foundEqHere(final String key, final String text, final int idx) {
        for (int i = 0; i < key.length(); i ++) {
            if (text.charAt(idx + i) != key.charAt(i))
                return false;
        }
        return true;
    }


}
