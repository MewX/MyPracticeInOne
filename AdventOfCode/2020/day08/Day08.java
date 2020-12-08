import java.util.*;

public class Day08 {
    static class Ins {
        String ins;
        int num;

        Ins(String ins, int num) {
            this.ins = ins;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        List<Ins> ins = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] secs = line.split(" ");
            assert secs.length == 2;
            ins.add(new Ins(secs[0].trim(), Integer.parseInt(secs[1].trim())));
        }

        Set<Integer> runSet = new HashSet<>();
        int acc = 0;
        for (int i = 0; i < ins.size(); i++) {
            if (runSet.contains(i)) {
                break;
            }
            runSet.add(i);

            Ins current = ins.get(i);
            switch (current.ins) {
                case "nop":
                    break;

                case "acc":
                    acc += current.num;
                    break;

                case "jmp":
                    i += current.num - 1;
                    break;
            }
        }
        System.out.println("part 1: " + acc);

        // Part 2.
        runSet.clear();
        acc = 0;
        for (int i = 0; i < ins.size(); i++) {
            Ins current = ins.get(i);
            if (current.ins.equals("nop")) {
                current.ins = "jmp";
                int temp = doesBreak(ins);
                if (temp != Integer.MIN_VALUE) {
                    acc = temp;
                    break;
                }
                current.ins = "nop";
            } else if (current.ins.equals("jmp")) {
                current.ins = "nop";
                int temp = doesBreak(ins);
                if (temp != Integer.MIN_VALUE) {
                    acc = temp;
                    break;
                }
                current.ins = "jmp";
            }
        }
        System.out.println("part 2: " + acc);
    }

    static int doesBreak(List<Ins> ins) {
        Set<Integer> runSet = new HashSet<>();
        int acc = 0;
        for (int i = 0; i < ins.size(); i++) {
            if (runSet.contains(i)) {
                return Integer.MIN_VALUE;
            }
            runSet.add(i);

            Ins current = ins.get(i);
            if (current.ins.equals("jmp")) {
                i += current.num - 1;
            } else if (current.ins.equals("acc")) {
                acc += current.num;
            }
        }
        return acc;
    }
}
