import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {

  static class Operation {

    int count, from, to; // 1-based number.

    Operation(int count, int from, int to) {
      this.count = count;
      this.from = from;
      this.to = to;
    }
  }

  public static void main(String[] args) {
    List<StringBuilder> stacks = new ArrayList<>(10);
    stacks.add(null); // not using 0;
    stacks.add(new StringBuilder("BGSC"));
    stacks.add(new StringBuilder("TMWHJNVG"));
    stacks.add(new StringBuilder("MQS"));
    stacks.add(new StringBuilder("BSLTWNM"));
    stacks.add(new StringBuilder("JZFTVGWP"));
    stacks.add(new StringBuilder("CTBGQHS"));
    stacks.add(new StringBuilder("TJPBW"));
    stacks.add(new StringBuilder("GDCZFTQM"));
    stacks.add(new StringBuilder("NSHBPF"));

    Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
    List<Operation> operations = new ArrayList<>();
    Scanner s = new Scanner(System.in);
    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (!line.startsWith("move")) {
        continue;
      }

      // move 4 from 6 to 3
      Matcher m = p.matcher(line);
      if (m.matches()) {
        operations.add(new Operation(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
            Integer.parseInt(m.group(3))));
      }
    }
    s.close();

    // Q1.
    for (Operation o : operations) {
      for (int i = 0; i < o.count; i++) {
        if (stacks.get(o.from).length() == 0) {
          continue;
        }
        // pop and push.
        push(stacks.get(o.to), pop(stacks.get(o.from)));
      }
    }
    System.out.println("part 1: ");
    outputTops(stacks);
  }

  private static void push(StringBuilder sb, char c) {
    sb.append(c);
  }

  private static char pop(StringBuilder sb) {
    assert sb.length() != 0;
    char c = sb.charAt(sb.length() - 1);
    sb.deleteCharAt(sb.length() - 1);
    return c;
  }

  private static void outputTops(List<StringBuilder> stacks) {
    for (int i = 1; i < stacks.size(); i ++) {
      StringBuilder s = stacks.get(i);
      if (s.length() == 0) {
        System.out.print(" ");
      }
      System.out.print(s.charAt(s.length() - 1));
    }
    System.out.println();
  }
}
