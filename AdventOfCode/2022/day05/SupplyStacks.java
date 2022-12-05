import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SupplyStacks {

  private static class Operation {

    int count;
    int from, to; // 0-based numbers.

    Operation(int count, int from, int to) {
      this.count = count;
      this.from = from;
      this.to = to;
    }
  }

  public static void main(String[] args) {
    List<StringBuilder> stacks = new ArrayList<>();
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

      Matcher m = p.matcher(line);
      if (m.matches()) {
        operations.add(new Operation(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)) - 1,
            Integer.parseInt(m.group(3)) - 1));
      }
    }
    s.close();

    // Q1.
    List<StringBuilder> s1 = deepCopy(stacks);
    for (Operation o : operations) {
      for (int i = 0; i < o.count; i++) {
        push(s1.get(o.to), pop(s1.get(o.from), 1));
      }
    }
    System.out.print("part 1: ");
    outputTops(s1);

    // Q2.
    List<StringBuilder> s2 = deepCopy(stacks);
    for (Operation o : operations) {
      push(s2.get(o.to), pop(s2.get(o.from), o.count));
    }
    System.out.print("part 2: ");
    outputTops(s2);
  }

  private static List<StringBuilder> deepCopy(List<StringBuilder> stacks) {
    return stacks.stream().map(StringBuilder::new).collect(Collectors.toList());
  }

  private static void push(StringBuilder sb, String s) {
    sb.append(s);
  }

  private static String pop(StringBuilder sb, int count) {
    if (sb.length() == 0) {
      return "";
    }
    String s = sb.substring(Math.max(0, sb.length() - count), sb.length());
    sb.delete(Math.max(0, sb.length() - count), sb.length());
    return s;
  }

  private static void outputTops(List<StringBuilder> stacks) {
    System.out.println(
        stacks.stream().map(sb -> sb.length() == 0 ? " " : sb.charAt(sb.length() - 1))
            .map(Object::toString).collect(Collectors.joining()));
  }
}