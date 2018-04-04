import java.util.*;

public class Day01 {
    private enum Turn {
        LEFT, RIGHT
    }

    private static class Order {
        Turn t;
        int dist;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        s.close();

        ArrayList<Order> orders = new ArrayList<>();
        for (String order : line.split(",")) {
            String temp = order.trim();
            Order o = new Order();
            o.dist = Integer.valueOf(temp.substring(1));
            if (temp.charAt(0) == 'L') {
                o.t = Turn.LEFT;
            } else {
                o.t = Turn.RIGHT;
            }
            orders.add(o);
        }

        // part 1
        //System.out.println(new Day01().calc(orders));
        // part 2
        System.out.println(new Day01().calcPart2(orders));
    }

    private int calc(List<Order> list) {
        // define:
        // 0 - north
        // 1 - west
        // 2 - south
        // 3 - east
        int direction = 0;
        int x = 0, y = 0;

        for (Order o : list) {
            if (o.t == Turn.LEFT) {
                direction ++;
            } else {
                direction += 3;
            }
            direction %= 4; // 0-3
            System.out.format("dist(%d): %d\n", direction, o.dist);

            switch (direction) {
                case 0:
                    y += o.dist;
                    break;

                case 1:
                    x -= o.dist;
                    break;

                case 2:
                    y -= o.dist;
                    break;

                case 3:
                    x += o.dist;
                    break;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    private int calcPart2(List<Order> list) {
        // define:
        // 0 - north
        // 1 - west
        // 2 - south
        // 3 - east
        int direction = 0;
        int x = 0, y = 0;
        Set<String> set = new HashSet<>();
        set.add("0,0");

        for (Order o : list) {
            if (o.t == Turn.LEFT) {
                direction ++;
            } else {
                direction += 3;
            }
            direction %= 4; // 0-3
            System.out.format("dist(%d): %d\n", direction, o.dist);

            int i = 0;
            int temp;
            switch (direction) {
                case 0:
                    for (i = 0; i < o.dist; i ++) {
                        y += 1;
                        temp = testAndRet(set, x, y);
                        if (temp != -1) return temp;
                    }
                    break;

                case 1:
                    for (i = 0; i < o.dist; i ++) {
                        x -= 1;
                        temp = testAndRet(set, x, y);
                        if (temp != -1) return temp;
                    }
                    break;

                case 2:
                    for (i = 0; i < o.dist; i ++) {
                        y -= 1;
                        temp = testAndRet(set, x, y);
                        if (temp != -1) return temp;
                    }
                    break;

                case 3:
                    for (i = 0; i < o.dist; i ++) {
                        x += 1;
                        temp = testAndRet(set, x, y);
                        if (temp != -1) return temp;
                    }
                    break;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    /**
     * @return -1 means failed
     */
    private int testAndRet(Set<String> set, int x, int y) {
        String key = "" + x + "," + y;
        if (set.contains(key)) {
            return Math.abs(x) + Math.abs(y);
        } else {
            set.add(key);
        }
        return -1;
    }
}
