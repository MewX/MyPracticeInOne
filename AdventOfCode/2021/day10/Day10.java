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
    final Map<Character, Integer> completeTable = new HashMap<>();
    completeTable.put(')', 1);
    completeTable.put(']', 2);
    completeTable.put('}', 3);
    completeTable.put('>', 4);
    final Map<Character, Character> pairs = new HashMap<>();
    pairs.put('(', ')');
    pairs.put('[', ']');
    pairs.put('{', '}');
    pairs.put('<', '>');

    int score1 = 0;
    List<Long> scores2 = new ArrayList<>();
    l: for (String line : input) {
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
            if (c != pairs.get(out)) {
              // Wrong.
              score1 += pointTable.get(c);
              continue l;
            }
            break;
          default:
            stack.push(c);
        }
      }

      // Complete the remaining lines.
      long roundScore2 = 0L;
      boolean add = !stack.isEmpty();
      while (!stack.isEmpty()) {
        Character c = stack.pop();
        roundScore2 *= 5;
        roundScore2 += completeTable.get(pairs.get(c));
      }
      if (add) {
        scores2.add(roundScore2);
      }
    }
    scores2.sort(Comparator.comparingLong(a -> a));
    System.out.println("part 1: " + score1);
    System.out.format("part 2: %d (size %d)\n", scores2.get(scores2.size() / 2), scores2.size());
  }
}
