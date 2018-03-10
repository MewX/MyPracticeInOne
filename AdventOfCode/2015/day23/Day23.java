import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day23 {
    private String[] cmds;

    public static void main(String[] args) {
        ArrayList<String> cmds = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String temp = s.nextLine().trim();
            if (temp.length() > 0) cmds.add(temp);
        }
        s.close();

        new Day23(cmds.toArray(new String[cmds.size()])).run();
    }

    private Day23(String[] cmds) {
        this.cmds = cmds;
    }

    private void run() {
        HashMap<String, BigInteger> regs = new HashMap<>();
//        regs.put("a", BigInteger.ZERO);// part 1
        regs.put("a", BigInteger.ONE); // part 2
        regs.put("b", BigInteger.ZERO);

        String op1, op2;
        int i = 0;
        while (i < cmds.length && i >= 0 && regs.get("a").compareTo(BigInteger.ZERO) >= 0
                && regs.get("b").compareTo(BigInteger.ZERO) >= 0) {
            String cmd = cmds[i];
            System.out.println("Executing: " + cmd);
            String ins = cmd.substring(0, 3);

            switch (ins) {
                case "hlf":
                    op1 = cmd.substring(3).trim();
                    regs.put(op1, regs.get(op1).divide(BigInteger.valueOf(2)));
                    break;

                case "tpl":
                    op1 = cmd.substring(3).trim();
                    regs.put(op1, regs.get(op1).multiply(BigInteger.valueOf(3)));
                    break;

                case "inc":
                    op1 = cmd.substring(3).trim();
                    regs.put(op1, regs.get(op1).add(BigInteger.ONE));
                    break;

                case "jmp":
                    op1 = cmd.substring(3).trim();
                    i += Integer.valueOf(op1);
                    continue;

                case "jie":
                    op1 = cmd.substring(3, cmd.indexOf(",")).trim();
                    op2 = cmd.substring(cmd.indexOf(",") + 1).trim();
                    if (regs.get(op1).mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0) {
                        i += Integer.valueOf(op2);
                        continue;
                    }
                    break;

                case "jio":
                    op1 = cmd.substring(3, cmd.indexOf(",")).trim();
                    op2 = cmd.substring(cmd.indexOf(",") + 1).trim();
                    if (regs.get(op1).compareTo(BigInteger.ONE) == 0) {
                        i += Integer.valueOf(op2);
                        continue;
                    }
                    break;
                default:
                    i = -100;
            }

            // next instruction
            i ++;
        }

        // output something
        System.out.println(regs.get("a"));
        System.out.println(regs.get("b"));

    }
}
