import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by MewX on 3/5/2017.
 */
public class Test2 {
    static class Star {
        public int x, y, z, r;

        public Star(int x, int y, int z, int r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        final int total = s.nextInt();
        for (int round = 0; round < total; round ++) {
            final int lines = s.nextInt();

            List<Star> list = new ArrayList<>();
            for (int line = 0; line < lines ; line ++) {
                list.add(new Star(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()));

            }
        }

        s.close();
    }
}
