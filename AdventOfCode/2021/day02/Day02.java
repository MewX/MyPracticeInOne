import java.util.*;

public class Day02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> ops = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            ops.add(line);
        }
        s.close();

        // Part 1.
        int horizontal = 0, depth = 0;
        for (String op : ops) {
            if (op.startsWith("forward")) {
                horizontal += Integer.parseInt(op.substring(8).trim());
            } else if (op.startsWith("down")) {
                depth += Integer.parseInt(op.substring(5).trim());
            } else if (op.startsWith("up")) {
                depth -= Integer.parseInt(op.substring(3).trim());
            }
        }
        System.out.println("Part 1");
        System.out.println("h/d: " + horizontal + "/" + depth);
        System.out.println("h*d: " + horizontal * depth);

        // Part 2.
        int aim = 0;
        horizontal = depth = 0;
        for (String op : ops) {
            if (op.startsWith("forward")) {
                int x = Integer.parseInt(op.substring(8).trim());
                horizontal += x;
                depth += aim * x;
            } else if (op.startsWith("down")) {
                aim += Integer.parseInt(op.substring(5).trim());
            } else if (op.startsWith("up")) {
                aim -= Integer.parseInt(op.substring(3).trim());
            }
        }
        System.out.println("Part 2");
        System.out.println("aim: " + aim);
        System.out.println("h/d: " + horizontal + "/" + depth);
        System.out.println("h*d: " + horizontal * depth);
    }
}
