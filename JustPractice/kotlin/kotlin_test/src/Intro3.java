/**
 * Created by MewX on 7/26/2016.
 */
public class Intro3 {
    public static void main(String[] args) {
        System.out.println(count(new int[] {112, 12, 21, 354, 534345, 345, 2221}));
        System.out.println(count(new int[] {10, 1100, 10101, 111, 1111, 11111, 11, 1, 111}));
        System.out.println(count(new int[] {0, 0, 0, 0}));
        System.out.println(count(new int[] {123456890, 213456890, 198654320}));
        System.out.println(count(new int[] {9}));
    }

    private static class Figure {
        boolean[] count = new boolean[] {false, false, false, false, false, false, false, false, false, false};

        boolean equalTo(Figure figure) {
            for (int i = 0; i < 10; i ++) {
                if (count[i] != figure.count[i]) return false;
            }
            return true;
        }
    }

    private static int count(int[] array) {
        // get length & define vals
        Figure[] figures = new Figure[array.length];

        // calc all
        for (int i = 0; i < array.length; i ++) {
            String temp = Integer.toString(array[i]);
            figures[i] = new Figure();
            for (int j = 0; j < temp.length(); j ++) {
                figures[i].count[temp.charAt(j) - '0'] = true;
            }
        }

        // compare and divided into groups, each used figure should be set true
        int tempSaveCount, totalCount = 0;
        for (int startIdx = 0, judgeIdx; startIdx < figures.length;) {
            tempSaveCount = 1;
            for (judgeIdx = startIdx + 1; judgeIdx < figures.length; judgeIdx ++) {
                // equals
                if (figures[judgeIdx].equalTo(figures[startIdx])) {
                    tempSaveCount ++;
                } else {
                    break;
                }
            }

            // add to total
            startIdx = judgeIdx;
            if (tempSaveCount == 1) continue;
            totalCount += countCombination(tempSaveCount, 2);
        }
        return totalCount;
    }

    private static int countCombination(int n, int k) {
        return factorial(n) / (factorial(n - k) * factorial(k));
    }

    private static int factorial(int n) {
        int result = 1;
        while (n > 0) {
            result *= n;
            n --;
        }
        return result;
    }
}
