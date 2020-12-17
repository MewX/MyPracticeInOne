import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day17 {
    public static void main(String[] args) {
        List<List<List<List<Character>>>> space = new ArrayList<>();
        space.add(new ArrayList<>(new ArrayList<>()));

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            // # is active; . is inactive.
            List<Character> l = new ArrayList<>();
            space.get(0).get(0).add(l);
            for (int i = 0; i < line.length(); i++) {
                l.add(line.charAt(i));
            }
        }
        s.close();

        // Part 1 omitted (see git history).

        // Part 2.
        List<List<List<List<Character>>>> temp = space;
        for (int i = 0; i < 6; i++) {
            temp = nextCycle(temp);
            System.out.format("Cycle #%d: %d\n", i + 1, countActive(temp));
        }
    }

    static int countActive(List<List<List<List<Character>>>> space) {
        int count = 0;
        for (List<List<List<Character>>> layer : space) {
            for (List<List<Character>> row : layer) {
                for (List<Character> www : row) {
                    for (Character c : www) {
                        if (c == '#') {
                            count ++;
                        }
                    }
                }
            }
        }
        return count;
    }

    static List<List<List<List<Character>>>> nextCycle(List<List<List<List<Character>>>> space) {
        // Make a copy.
        List<List<List<List<Character>>>> ns = copy(space);

        // Add two empty layers.
        ns.add(0, createEmptyLayer(ns.get(0).size() + 2, ns.get(0).get(0).size() + 2, ns.get(0).get(0).get(0).size() + 2));
        ns.add(createEmptyLayer(ns.get(0).size() + 2, ns.get(0).get(0).size() + 2, ns.get(0).get(0).get(0).size() + 2));
        for (int z = 1; z < ns.size() - 1; z++) {
            // Two rows.
            ns.get(z).add(0, createEmptyRow(ns.get(0).get(0).size(), ns.get(0).get(0).get(0).size()));
            ns.get(z).add(createEmptyRow(ns.get(0).get(0).size(), ns.get(0).get(0).get(0).size()));

            for (int x = 1; x < ns.get(z).size() - 1; x++) {
                // Two WWW.
                ns.get(z).get(x).add(0, createEmptyWWW(ns.get(0).get(0).get(0).size()));
                ns.get(z).get(x).add(createEmptyWWW(ns.get(0).get(0).get(0).size()));

                for (int y = 1; y < ns.get(z).get(x).size() - 1; y++) {
                    // Two chars.
                    ns.get(z).get(x).get(y).add(0, '.');
                    ns.get(z).get(x).get(y).add('.');
                }
            }
        }

        // Make change.
        List<List<List<List<Character>>>> nsCopy = copy(ns);
        for (int z = 0; z < ns.size(); z++) {
            List<List<List<Character>>> layer = ns.get(z);
            for (int x = 0; x < layer.size(); x++) {
                List<List<Character>> row = layer.get(x);
                for (int y = 0; y < row.size(); y++) {
                    List<Character> www = row.get(y);
                    for (int w = 0; w < www.size(); w++) {
                        int c = countNeighbour(nsCopy, x, y, z, w);
                        boolean selfActive = checkActive(nsCopy, x, y, z, w);

                        if (selfActive && !(c == 2 || c == 3)) {
                            ns.get(z).get(x).get(y).set(w, '.');
                        } else if (!selfActive && c == 3) {
                            // Make active.
                            ns.get(z).get(x).get(y).set(w, '#');
                        }
                    }
                }
            }
        }
        return ns;
    }

    static List<Character> createEmptyWWW(int w) {
        List<Character> ret = new ArrayList<>();
        for (int j = 0; j < w; j++) {
            ret.add('.');
        }
        return ret;
    }

    static List<List<Character>> createEmptyRow(int y, int w) {
        List<List<Character>> ret = new ArrayList<>();
        for (int i = 0; i < y; i++) {
            ret.add(createEmptyWWW(w));
        }
        return ret;
    }

    static List<List<List<Character>>> createEmptyLayer(int x, int y, int w) {
        List<List<List<Character>>> ret = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            ret.add(createEmptyRow(y, w));
        }
        return ret;
    }

    static int countNeighbour(List<List<List<List<Character>>>> space, int x, int y, int z, int w) {
        int count = 0;
        for (int i = -1; i <= 1; i++) { // z
            for (int j = -1; j <= 1; j++) { // x
                for (int k = -1; k <= 1; k++) { // y
                    for (int m = -1; m <= 1; m++) { // w
                        if (i == j && j == k && k == m && m == 0) {
                            // Skip self.
                            continue;
                        }

                        if (checkActive(space, x + i, y + j, z + k, w + m)) {
                            count ++;
                        }
                    }
                }
            }
        }
        return count;
    }

    static boolean checkActive(List<List<List<List<Character>>>> space, int x, int y, int z, int w) {
        if (z < 0 || space.size() <= z ||
                x < 0 || space.get(0).size() <= x ||
                y < 0 || space.get(0).get(0).size() <= y ||
                w < 0 || space.get(0).get(0).get(0).size() <= w) {
            return false;
        }
        return space.get(z).get(x).get(y).get(w) == '#';
    }

    static List<List<List<List<Character>>>> copy(List<List<List<List<Character>>>> space) {
        List<List<List<List<Character>>>> ns = new ArrayList<>();
        for (List<List<List<Character>>> layer : space) {
            List<List<List<Character>>> lcopy = new ArrayList<>();
            for (List<List<Character>> row : layer) {
                List<List<Character>> rcopy = new ArrayList<>();
                for (List<Character> www : row) {
                    rcopy.add(new ArrayList<>(www));
                }
                lcopy.add(rcopy);
            }
            ns.add(lcopy);
        }
        return ns;
    }
}
