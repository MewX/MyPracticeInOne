import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int sum = 0;
        while (s.hasNextInt()) {
            int mass = s.nextInt();
            sum += calcFuel(mass);
        }
        System.out.println("part1: " + sum);
    }

    static int calcFuel(int mass) {
        return mass / 3 - 2;
    }
}
