import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by MewX on 3/5/2017.
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int total = s.nextInt();
        for (int round = 0; round < total; round ++) {
            int r = s.nextInt();
            int l = s.nextInt();

            // fill table
            long max = r > l ? r : l;
            long min = r > l ? l : r;

            // count
            long cnt = 0;
            for (int i = 1; i < min; i ++) {
                cnt += (max - i) * (min - i) * i;
                cnt %= 1000000007;
//                System.err.format("Added %d * %d * map.get(%d) = %d\n", max - i, min - i, i, map.get(i) * (max - i) * (min - i));
            }
            System.out.format("Case #%d: %d\n", round + 1, cnt % 1000000007);
            System.err.format("Case #%d: %d\n", round + 1, cnt % 1000000007);
        }

        s.close();
    }
}
