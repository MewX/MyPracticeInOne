import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {
    enum STAT {
        FLOOR,
        EMPTY,
        OCCUPIED,
    }

    static List<List<STAT>> parseInput() {
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
        return plane;
    }

    public static void main(String[] args) {
        List<List<STAT>> plane = parseInput();

        // Part 1.
        count(1, plane);

        // Part 2.
        count(2, plane);
    }

    static void count(int partID, List<List<STAT>> plane) {
        while (true) {
            List<List<STAT>> temp = null;
            if (partID == 1) {
                temp = change(plane, false, 4);
            } else if (partID == 2) {
                temp = change(plane, true, 5);
            }

            assert temp != null;
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

    static List<List<STAT>> change(List<List<STAT>> plane, boolean countInf, int threshold) {
        List<List<STAT>> temp = new ArrayList<>();
        for (int i = 0; i < plane.size(); i++) {
            List<STAT> row = plane.get(i);
            List<STAT> tempRow = new ArrayList<>();
            for (int j = 0; j < row.size(); j++) {
                // Count occupied.
                int count = countNearbyOccupied(i, j, plane, countInf);
                switch (row.get(j)) {
                    case EMPTY:
                        if (count == 0) {
                            tempRow.add(STAT.OCCUPIED);
                        } else {
                            tempRow.add(STAT.EMPTY);
                        }
                        break;
                    case OCCUPIED:
                        if (count >= threshold) {
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

    static int countNearbyOccupied(int i, int j, List<List<STAT>> plane, boolean inf) {
        // Count occupied.
        int count = 0;

        // Upper rows.
        count += firstNonFloor(-1, -1, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;
        count += firstNonFloor(-1, 0, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;
        count += firstNonFloor(-1, 1, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;

        // Same row.
        count += firstNonFloor(0, -1, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;
        count += firstNonFloor(0, 1, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;

        // Lower rows.
        count += firstNonFloor(1, -1, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;
        count += firstNonFloor(1, 0, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;
        count += firstNonFloor(1, 1, i, j, plane, inf) == STAT.OCCUPIED ? 1 : 0;
        return count;
    }

    static STAT firstNonFloor(int xoff, int yoff, int x, int y, List<List<STAT>> plane, boolean inf) {
        x += xoff;
        y += yoff;
        while (0 <= x && x < plane.size() && 0 <= y && y < plane.get(x).size()) {
            if (plane.get(x).get(y) != STAT.FLOOR) {
                return plane.get(x).get(y);
            }
            x += xoff;
            y += yoff;

            if (!inf) {
                break;
            }
        }
        return STAT.FLOOR;
    }
}
