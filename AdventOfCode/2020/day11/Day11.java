import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

public class Day11 {
    enum STAT {
        FLOOR,
        EMPTY,
        OCCUPIED,
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        List<List<STAT>> plane = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            List<STAT> row = new ArrayList<>();
            plane.add(row);
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case '.':
                        row.add(STAT.FLOOR);
                        break;
                    case 'L':
                        row.add(STAT.EMPTY);
                        break;
                    case '#':
                        row.add(STAT.OCCUPIED);
                        break;
                }
            }
        }
        s.close();

        // Part 1.
//        while (true) {
//            List<List<STAT>> temp = change(plane);
//            if (same(temp, plane)) {
//                // Count and return.
//                int count = 0;
//                for (List<STAT> row : plane) {
//                    for (STAT seat : row) {
//                        if (seat == STAT.OCCUPIED) {
//                            count ++;
//                        }
//                    }
//                }
//                System.out.println("part 1: " + count);
//                break;
//            }
//            plane = temp;
//        }

        // Part 2.
        while (true) {
            List<List<STAT>> temp = change2(plane);
            if (same(temp, plane)) {
                // Count and return.
                int count = 0;
                for (List<STAT> row : plane) {
                    for (STAT seat : row) {
                        if (seat == STAT.OCCUPIED) {
                            count ++;
                        }
                    }
                }
                System.out.println("part 2: " + count);
                break;
            }
            plane = temp;
        }
    }

    static List<List<STAT>> change(List<List<STAT>> plane) {
        List<List<STAT>> temp = new ArrayList<>();
        for (int i = 0; i < plane.size(); i++) {
            List<STAT> row = plane.get(i);
            List<STAT> tempRow = new ArrayList<>();
            for (int j = 0; j < row.size(); j++) {
                // Count occupied.
                int count = 0;
                // Row 1.
                if (i != 0) {
                    if (j != 0) {
                        if (plane.get(i - 1).get(j - 1) == STAT.OCCUPIED) {
                            count ++;
                        }
                    }
                    if (plane.get(i - 1).get(j) == STAT.OCCUPIED) {
                        count ++;
                    }
                    if (j != row.size() - 1) {
                        if (plane.get(i - 1).get(j + 1) == STAT.OCCUPIED) {
                            count ++;
                        }
                    }
                }

                // Row 2.
                if (j != 0  && row.get(j - 1) == STAT.OCCUPIED) {
                    count++;
                }
                if (j != row.size() - 1 && row.get(j + 1) == STAT.OCCUPIED) {
                    count ++;
                }

                // Row 3.
                if (i != plane.size() - 1) {
                    if (j != 0 && plane.get(i + 1).get(j - 1) == STAT.OCCUPIED) {
                        count ++;
                    }
                    if (plane.get(i + 1).get(j) == STAT.OCCUPIED) {
                        count ++;
                    }
                    if (j != row.size() - 1 && plane.get(i + 1).get(j + 1) == STAT.OCCUPIED) {
                        count ++;
                    }
                }

                switch (row.get(j)) {
                    case EMPTY:
                        if (count == 0) {
                            tempRow.add(STAT.OCCUPIED);
                        } else {
                            tempRow.add(STAT.EMPTY);
                        }
                        break;
                    case OCCUPIED:
                        if (count >= 4) {
                            tempRow.add(STAT.EMPTY);
                        } else {
                            tempRow.add(STAT.OCCUPIED);
                        }
                        break;
                    default:
                        tempRow.add(row.get(j));
                        break;
                }
            }
            temp.add(tempRow);
        }

        // Print.
//        print(plane);
        return temp;
    }

    static boolean same(List<List<STAT>> p1, List<List<STAT>> p2) {
        assert p1.size() == p2.size();

        for (int i = 0; i < p1.size(); i++) {
            if (!p1.get(i).equals(p2.get(i))) {
                return false;
            }
        }
        return true;
    }

    static void print(List<List<STAT>> p) {
        for (List<STAT> row : p) {
            for (STAT seat : row) {
                switch (seat) {
                    case EMPTY:
                        System.out.print("L");
                        break;
                    case OCCUPIED:
                        System.out.print("#");
                        break;
                    default:
                        System.out.print(".");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static List<List<STAT>> change2(List<List<STAT>> plane) throws InterruptedException {
        List<List<STAT>> temp = new ArrayList<>();
        for (int i = 0; i < plane.size(); i++) {
            List<STAT> row = plane.get(i);
            List<STAT> tempRow = new ArrayList<>();
            for (int j = 0; j < row.size(); j++) {
                // Count occupied.
                int count = 0;
                // Row 1.
                count += firstNonFloor(-1, -1, i, j, plane) == STAT.OCCUPIED ? 1 : 0;
                count += firstNonFloor(-1, 0, i, j, plane) == STAT.OCCUPIED ? 1 : 0;
                count += firstNonFloor(-1, 1, i, j, plane) == STAT.OCCUPIED ? 1 : 0;

                // Row 2.
                count += firstNonFloor(0, -1, i, j, plane) == STAT.OCCUPIED ? 1 : 0;
                count += firstNonFloor(0, 1, i, j, plane) == STAT.OCCUPIED ? 1 : 0;

                // Row 3.
                count += firstNonFloor(1, -1, i, j, plane) == STAT.OCCUPIED ? 1 : 0;
                count += firstNonFloor(1, 0, i, j, plane) == STAT.OCCUPIED ? 1 : 0;
                count += firstNonFloor(1, 1, i, j, plane) == STAT.OCCUPIED ? 1 : 0;

                switch (row.get(j)) {
                    case EMPTY:
                        if (count == 0) {
                            tempRow.add(STAT.OCCUPIED);
                        } else {
                            tempRow.add(STAT.EMPTY);
                        }
                        break;
                    case OCCUPIED:
                        if (count >= 5) {
                            tempRow.add(STAT.EMPTY);
                        } else {
                            tempRow.add(STAT.OCCUPIED);
                        }
                        break;
                    default:
                        tempRow.add(row.get(j));
                        break;
                }
            }
            temp.add(tempRow);
        }

        // Print.
        System.out.println("after change:");
        print(plane);
        Thread.sleep(100);
        return temp;
    }

    static STAT firstNonFloor(int xoff, int yoff, int x, int y, List<List<STAT>> plane) {
        x += xoff;
        y += yoff;
        while (0 <= x && x < plane.size() && 0 <= y && y < plane.get(x).size()) {
            if (plane.get(x).get(y) != STAT.FLOOR) {
                return plane.get(x).get(y);
            }
            x += xoff;
            y += yoff;
        }
        return STAT.FLOOR;
    }
}
