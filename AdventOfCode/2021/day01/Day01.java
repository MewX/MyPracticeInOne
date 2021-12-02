import java.util.*;

public class Day01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Integer> sweepReports = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            sweepReports.add(Integer.valueOf(line));
        }
        s.close();

        // Do the calculation.
        System.out.println("q1: " + countIncreasing(sweepReports));
        System.out.println("q2: " + countWindowIncreasing(sweepReports));
    }

    private static int countIncreasing(List<Integer> reports) {
        int count = 0;
        for (int i = 1; i < reports.size(); i++) {
            if (reports.get(i) > reports.get(i - 1)) {
                count++;
            }
        }
        return count;
    }

    private static int countWindowIncreasing(List<Integer> reports) {
        int count = 0;
        for (int i = 3; i < reports.size(); i++) {
            int windowPrev = reports.get(i - 1) + reports.get(i - 2) + reports.get(i - 3);
            int windowNow = reports.get(i) + reports.get(i - 1) + reports.get(i - 2);
            if (windowNow > windowPrev) {
                count++;
            }
        }
        return count;
    }
}
