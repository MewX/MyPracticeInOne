import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(new Day01().calc(orders));
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
}
