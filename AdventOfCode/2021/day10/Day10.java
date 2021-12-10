import java.util.*;

public class Day10 {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    List<String> input = new ArrayList<>();
    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (line.isEmpty()) {
        continue;
      }
      input.add(line);
    }
    s.close();

    // Part 1.
    final Map<Character, Integer> pointTable = new HashMap<>();
    pointTable.put(')', 3);
    pointTable.put(']', 57);
    pointTable.put('}', 1197);
    pointTable.put('>', 25137);
    final Map<Character, Character> pairs = new HashMap<>();
    pairs.put('(', ')');
    pairs.put('[', ']');
    pairs.put('{', '}');
    pairs.put('<', '>');
    pairs.put(')', '(');
    pairs.put(']', '[');
    pairs.put('}', '{');
    pairs.put('>', '<');

    int score = 0;
    for (String line : input) {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        switch (c) {
          case ')':
          case ']':
          case '}':
          case '>':
            // Need to pop out.
            Character out = stack.pop();
            if (out != pairs.get(c)) {
              // Wrong.
              score += pointTable.get(c);
            }
            break;
          default:
            stack.push(c);
        }
      }
    }
    System.out.println("part 1: " + score);
  }
}
