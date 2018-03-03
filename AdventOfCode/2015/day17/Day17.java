import java.util.*;

public class Day17 {
//    private static HashMap<Integer, Integer> set = new HashMap<>(); // <remaining, count.
//    private static HashMap<Integer, Integer> containerSet = new HashMap<>(); // <num, count>

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> containers = new ArrayList<>();
        while (s.hasNextInt()) {
            containers.add(s.nextInt());
        }
        s.close();

        // parse container set
//        for (Integer i : containers) {
//            if (containerSet.containsKey(i)) {
//                containerSet.put(i, containerSet.get(i) + 1);
//            } else {
//                containerSet.put(i, 1);
//            }
//        }

        // calculate
        final int TESTS = 25;
        final int TEST1 = 150;
//        System.out.println(calc(TESTS, new ArrayList<>(containerSet.keySet())));
        System.out.println(calc(TEST1, containers));
    }

    public static int calc(final int total, final List<Integer> containers) {
        if (total < 0) return 0;

        int caseCount = 0;
        for (int i = 0; i < containers.size(); i ++) {
//            final int noOccur = containerSet.get(containers.get(i));
            int sum = 0;
//            for (int no = 0; no < noOccur; no++) {
                sum += containers.get(i);

                if (sum == total) {
                    // done
                    caseCount++;
                }

                final int remaining = total - sum;
                int thisCase;
//                if (set.containsKey(remaining)) {
//                    thisCase = set.get(remaining);
//                } else {
                    thisCase = calc(remaining, containers.subList(i + 1, containers.size()));
//                    set.put(remaining, thisCase);
//                }
                caseCount += thisCase;
//            }
        }
        return caseCount;
    }
}
