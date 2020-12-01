import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day01 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();

        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int ex = s.nextInt();
            System.out.println(2020 - ex);
            if (set.contains(ex)) {
                System.out.println("part 1: " + (2020 - ex) * ex);
                break;
            }
            set.add(2020 - ex);
        }
    }

}
