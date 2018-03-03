import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day19 {
    private static HashSet<String> set = new HashSet<>();
    private static ArrayList<String> src = new ArrayList<>(), dst = new ArrayList<>();
    private static String input = "";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
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
        final int PART = 2;
        if (PART == 1) {
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
        } else {
//            System.out.println(dfs(1, "e", Integer.MAX_VALUE));
            System.out.println(backDfs(1, input, Integer.MAX_VALUE));
        }

    }

    private static boolean foundEqHere(final String key, final String text, final int idx) {
        if (idx + key.length() > text.length()) return false;

        for (int i = 0; i < key.length(); i ++) {
            if (text.charAt(idx + i) != key.charAt(i))
                return false;
        }
        return true;
    }

    /**
     * The right logic, but way too slow!
     *
     * @param curDepth current depth
     * @param text     constructed text
     * @param foundMin the min depth found
     * @return the min depth
     */
    private static int dfs(final int curDepth, final String text, final int foundMin) {
        if (text.length() > input.length() || curDepth > foundMin) return Integer.MAX_VALUE; // not found

        // src loop
        int minRet = Integer.MAX_VALUE;
        for (int i = 0; i < src.size(); i ++) {
            int loop = text.indexOf(src.get(i));
            if (loop != -1 && text.substring(0, loop).equals(input.substring(0, loop))) {
                for (; loop < text.length(); loop++) {

                    if (foundEqHere(src.get(i), text, loop)) {
                        // build string
                        final String afterReplace = text.substring(0, loop)
                                + dst.get(i)
                                + text.substring(loop + src.get(i).length());

//                        if (set.contains(afterReplace)) continue;
//                        else set.add(afterReplace);

                        if (afterReplace.equals(input)) {
                            minRet = Math.min(minRet, curDepth);
                        } else if (afterReplace.length() <= input.length()) {
                            System.out.println(afterReplace);
                            final int tempRet = dfs(curDepth + 1, afterReplace, minRet);
                            minRet = Math.min(minRet, tempRet);
                        }

                    }
                }
            }
        }
        return minRet;
    }


    private static int backDfs(final int curDepth, final String text, final int foundMin) {
        if (text.length() > input.length() || curDepth > foundMin) return Integer.MAX_VALUE; // not found

        int minRet = Integer.MAX_VALUE;
        for (int idxDst = 0; idxDst < dst.size(); idxDst++) {
            int idxLoop = text.indexOf(dst.get(idxDst));
            while (idxLoop != -1) {
                final String afterReplace = text.substring(0, idxLoop)
                        + src.get(idxDst)
                        + text.substring(idxLoop + dst.get(idxDst).length());

                if (!set.contains(afterReplace)) {
                    set.add(afterReplace);

                    if (afterReplace.equals("e")) {
                        minRet = Math.min(minRet, curDepth);
                        System.out.println(minRet);
                        System.exit(0);
                    } else {
//                        System.out.println(afterReplace);
                        final int tempRet = backDfs(curDepth + 1, afterReplace, minRet);
                        minRet = Math.min(minRet, tempRet);
                    }

                }

                // next loop
                idxLoop = text.indexOf(dst.get(idxDst), idxLoop + 1);
            }
        }
        return minRet;
    }

}
