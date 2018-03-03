import java.util.*;

public class Day17 {
    private static HashMap<Integer, Integer> depthTable = new HashMap<>(); // <depth, count>
    private static HashMap<Integer, Integer> containerSet = new HashMap<>(); // <num, count>

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> containers = new ArrayList<>();
        while (s.hasNextInt()) {
            containers.add(s.nextInt());
        }
        s.close();

        // parse container set
        for (Integer i : containers) {
            containerSet.put(i, containerSet.getOrDefault(i, 0) + 1);
        }

        // calculate
        final int TESTS = 25;
        final int TEST1 = 150;
        System.out.println(calcQuest1(TEST1, containers));

        calcQuest2(TEST1, containers, 0);
        int min = depthTable.keySet().stream().min(Integer::compareTo).get();
        System.out.println(depthTable.get(min));
    }

    public static int calcQuest3(final int total, final List<Integer> containers) {
        if (total < 0) return 0;

        int caseCount = 0;
        for (int i = 0; i < containers.size(); i ++) {
            final int noOccur = containerSet.get(containers.get(i));
            int sum = 0;
            for (int no = 0; no < noOccur; no++) {
                sum += containers.get(i);

                if (sum == total) {
                    // done
                    caseCount++;
                    break;
                } else if (sum < total) {
                    caseCount += calcQuest3(total - sum, containers.subList(i + 1, containers.size()));
                }

            }
        }
        return caseCount;
    }

    public static int calcQuest2(final int total, final List<Integer> containers, final int depth) {
        if (total < 0) return 0;

        final int currentDepth = depth + 1;
        int caseCount = 0;
        for (int i = 0; i < containers.size(); i++) {
            int sum = containers.get(i);
            if (sum == total) {
                // done
                caseCount++;

                // set depth
                depthTable.put(currentDepth, depthTable.getOrDefault(currentDepth, 0) + 1);
            } else if (sum < total) {
                caseCount += calcQuest2(total - sum, containers.subList(i + 1, containers.size()), currentDepth);
            }

        }
        return caseCount;
    }

    public static int calcQuest1(final int total, final List<Integer> containers) {
        if (total < 0) return 0;

        int caseCount = 0;
        for (int i = 0; i < containers.size(); i++) {
            int sum = containers.get(i);
            if (sum == total) {
                // done
                caseCount++;
            } else if (sum < total) {
                caseCount += calcQuest1(total - sum, containers.subList(i + 1, containers.size()));
            }

        }
        return caseCount;
    }
}
