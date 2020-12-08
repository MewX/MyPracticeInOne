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
    }
}
