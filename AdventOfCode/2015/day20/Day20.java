import java.util.Scanner;

public class Day20 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // test input: 3326400
        final int input = s.nextInt();
        s.close();

        int houseId = 1;
        while (true) {
            int temp = calc(houseId);
            System.out.println("housrId: " + houseId + " - " + temp);
            if (temp >= input) {
                System.out.println(houseId);
                break;
            }
            houseId ++;
        }
    }

    private static int calc(int houseId) {
        int count = 0;
        final int max = new Double(Math.sqrt(houseId * 1.0)).intValue();
        for (int i = 1; i <= max; i++) {
            if (houseId % i == 0) {
                count += (i + houseId / i) * 10;
            }
        }
        if (max * max == houseId) {
            count -= max * 10;
        }
        return count;
    }
}
