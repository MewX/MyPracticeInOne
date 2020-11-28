import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int sum = 0;
        int sum2 = 0;
        while (s.hasNextInt()) {
            final int mass = s.nextInt();
            sum += calcFuel(mass);
            sum2 += calcFuelRecursive(mass);
        }
        System.out.println("part1: " + sum);
        System.out.println("part2: " + sum2);
    }

    static int calcFuel(int mass) {
        return mass / 3 - 2;
    }

    static int calcFuelRecursive(int mass) {
        int newMass = calcFuel(mass);
        if (newMass > 0) {
            return newMass + calcFuelRecursive(newMass);
        }
        return 0;
    }
}
