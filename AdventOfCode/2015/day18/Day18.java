import java.util.Scanner;

public class Day18 {
    private static final int ROUNDS = 100;
    private static final int SIZE = 100;

    public static void main(String[] args) {
        StringBuilder[] lights = new StringBuilder[SIZE];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < lights.length; i ++) {
            lights[i] = new StringBuilder(s.nextLine());
        }
        s.close();


        for (int i = 0; i < ROUNDS; i ++) {
            lights = moveToNext(lights, convert(lights), true);
        }
        System.out.println(countOn(lights, true));
    }

    private static String[] convert(StringBuilder[] stringBuilders) {
        String[] strings = new String[stringBuilders.length];
        for (int i = 0; i < stringBuilders.length; i ++) {
            strings[i] = stringBuilders[i].toString();
        }
        return strings;
    }

    private static int countOn(StringBuilder[] l, boolean doPrinting) {
        int c = 0;
        for (StringBuilder aL : l) {
            for (int j = 0; j < aL.length(); j++) {
                if (doPrinting) System.out.print(aL.charAt(j));
                if (aL.charAt(j) == '#') {
                    c++;
                }
            }
            if (doPrinting) System.out.println();
        }
        return c;
    }

    private static StringBuilder[] moveToNext(StringBuilder[] end, final String[] begin, boolean part2) {
        // init part 2
        if (part2) {
            end[0].setCharAt(0, '#');
            end[0].setCharAt(SIZE - 1, '#');
            end[SIZE - 1].setCharAt(0, '#');
            end[SIZE - 1].setCharAt(SIZE - 1, '#');
        }

        // begin is used to calculate, end is used to store
        for (int row = 0; row < begin.length; row ++) {
            for (int col = 0; col < begin[row].length(); col ++) {
                if (part2 && (row == 0 || row == SIZE - 1) && (col == 0 || col == SIZE - 1)) {
                    continue;
                }

                final int noOfOnAround = countOnAround(begin, row, col);
                if (begin[row].charAt(col) == '#' && !(noOfOnAround == 2 || noOfOnAround == 3)) {
                    end[row].setCharAt(col, '.'); // turn off
                } else if (begin[row].charAt(col) == '.' && noOfOnAround == 3) {
                    end[row].setCharAt(col, '#'); // turn on
                }
            }
        }
        return end;
    }

    private static int countOnAround(final String[] lights, final int row, final int col) {
        int count = 0;
        final int rBeg = row - 1 < 0 ? row : row - 1;
        final int rEnd = row + 1 >= lights.length ? row : row + 1;
        final int cBeg = col - 1 < 0 ? col : col - 1;
        final int cEnd = col + 1 >= lights[0].length() ? col : col + 1;
        for (int r = rBeg; r <= rEnd; r ++) {
            for (int c = cBeg; c <= cEnd; c ++) {
                if (r == row && c == col) continue; // not counting it self
                if (lights[r].charAt(c) == '#') count++;
            }
        }
        return count;
    }
}
