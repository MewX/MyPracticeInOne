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

        // Do the calculation.
        System.out.println("q1: " + countIncreasing(sweepReports));
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
}
