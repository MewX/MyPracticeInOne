import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program should be early terminated as the last one was the final result
 */
public class Day24 {
    private static int SUM, SINGLE;
    private static ArrayList<Integer> best = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            l.add(s.nextInt());
        }
        s.close();

        // update sum
        for (Integer i : l) SUM += i;
        System.out.println("SUM: " + SUM);
        SINGLE = SUM / 3;
        System.out.println("SINGLE: " + SINGLE);

        // calc
        // combination for the first group,
        // test whether the rest two can form a bigger group
        comb(0, l, new ArrayList<>());

        long res = 1;
        for (Integer i : best) res *= i;
        System.out.println(res);
    }

    private static void comb(int sum, ArrayList<Integer> remaining, ArrayList<Integer> picked) {
        for (int i = remaining.size() - 1; i >= 0; i --) {
            int pick = remaining.get(i);
            if (sum + pick > SINGLE) continue;


            // valid
            int idx = picked.size();
            picked.add(pick);
            remaining.remove(i);

            if (sum + pick == SINGLE) {
                if (validate(remaining, 0)) {
                    // compare with the best one
                    updateBest(picked);
                }
            } else {
                // next state
                comb(sum + pick, remaining, picked);
            }

            // clean everything
            picked.remove(idx);
            remaining.add(i, pick);
        }
    }

    private static boolean validate(ArrayList<Integer> remaining, int sum) {
        boolean ret = false;
        for (int i = remaining.size() - 1; i >= 0 && !ret; i --) {
            int pick = remaining.get(i);
            if (sum + pick > SINGLE) continue;
            else if (sum + pick == SINGLE) {
                return true;
            }

            // valid
            remaining.remove(i);

            // next state
            ret = validate(remaining, sum + pick);

            // clean everything
            remaining.add(i, pick);
        }
        return ret;
    }

    private static void updateBest(ArrayList<Integer> picked) {
        if (best.size() == 0 || best.size() > picked.size()) {
            best = new ArrayList<>(picked);
            printBest();
        } else if (best.size() == picked.size()) {
            // compare quantum entanglement
            long bestEq = 1;
            for (Integer i : best) bestEq *= i;

            long currentEq = 1;
            for (Integer p : picked) currentEq *= p;

            if (bestEq > currentEq) {
                best = new ArrayList<>(picked);
                printBest();
            }
        }
    }

    private static void printBest() {
        int sum = 0;
        long eq = 1;
        for (Integer i : best) {
            sum += i;
            eq *= i;
            System.out.print(i.toString() + ", ");
        }
        System.out.println(" (" + sum + " - " + eq + ")");
    }
}
