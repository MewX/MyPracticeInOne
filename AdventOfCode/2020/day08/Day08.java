import java.util.*;

public class Day08 {
    static class Instruction {
        String ins;
        int num;

        Instruction(String ins, int num) {
            this.ins = ins;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        List<Instruction> program = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] secs = line.split(" ");
            assert secs.length == 2;
            program.add(new Instruction(secs[0].trim(), Integer.parseInt(secs[1].trim())));
        }
        s.close();

        // Part 1.
        Set<Integer> runSet = new HashSet<>();
        System.out.println("part 1: " + accAfterRunningThrough(program, true));

        // Part 2 (no need to reset acc).
        for (int i = 0; i < program.size(); i++) {
            Instruction current = program.get(i);

            // Try flipping the instruction.
            String saveIns = current.ins;
            if (current.ins.equals("nop")) {
                current.ins = "jmp";
            } else if (current.ins.equals("jmp")) {
                current.ins = "nop";
            }
            Integer acc = accAfterRunningThrough(program, false);
            current.ins = saveIns;
            if (acc != null) {
                System.out.println("part 2: " + acc);
                break;
            }
        }
    }

    static Integer accAfterRunningThrough(List<Instruction> program, boolean returnBroken) {
        Set<Integer> runSet = new HashSet<>();
        int acc = 0;
        for (int i = 0; i < program.size(); i++) {
            if (runSet.contains(i)) {
                return returnBroken ? acc : null;
            }
            runSet.add(i);

            Instruction current = program.get(i);
            if (current.ins.equals("jmp")) {
                i += current.num - 1;
            } else if (current.ins.equals("acc")) {
                acc += current.num;
            }
        }
        return acc;
    }
}
