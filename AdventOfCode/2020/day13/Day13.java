import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String timeDepartStr = s.nextLine().trim();
        String[] busesStr = s.nextLine().trim().split(",");
        s.close();

        // Parse input.
        final int timestamp = Integer.parseInt(timeDepartStr);
        final int[] busIds = new int[busesStr.length];
        for (int i = 0; i < busesStr.length; i++) {
            if (busesStr[i].equals("x")) {
                busIds[i] = -1;
            } else {
                busIds[i] = Integer.parseInt(busesStr[i]);
            }
        }

        // Part 1.
        long time = timestamp;
        out: while (true) {
            for (int id : busIds) {
                if (id <= 0) {
                    continue;
                }
                if (time % id == 0) {
                    System.out.println("part 1: " + (time - timestamp) * id);
                    break out;
                }
            }
            time ++;
        }

        // Part 2: Chinese remainder theorem.
        // https://zh.wikipedia.org/wiki/%E4%B8%AD%E5%9B%BD%E5%89%A9%E4%BD%99%E5%AE%9A%E7%90%86
        long modProduct = 1;
        for (int busId : busIds) {
            if (busId > 0) {
                modProduct *= busId;
            }
        }

        long result = 0;
        for (int i = 0; i < busIds.length; i ++) {
            if (busIds[i] < 0) {
                continue;
            }
            long M = modProduct / busIds[i];
            long t = calcModInv(M, busIds[i]);
            long a = busIds[i] - i;
            result += t * a * M;
        }

        // Make it smallest positive.
        if (result > 0) {
            result -= (result / modProduct) * modProduct;
        }
        System.out.println("part 2: " + result);
    }

    static int calcModInv(long M, long m) {
        int t = 1;
        while (true) {
            if (t * M % m == 1) {
                return t;
            }
            t ++;
        }
    }
}
