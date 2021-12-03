import java.util.*;

public class Day03 {

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
    StringBuilder gamma = new StringBuilder(), epsilon = new StringBuilder();
    for (int i = 0; i < input.get(0).length(); i++) {
      final int c1 = count1(input, i);
      final int c0 = input.size() - c1;
      gamma.append(c1 >= c0 ? '1' : '0');
      epsilon.append(c1 <= c0 ? '1' : '0');
    }
    System.out.println("part 1: " +
        (Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2)));

    // Part 2.
    final int oxygen = part2(new ArrayList<>(input), 0, '1');
    final int co2 = part2(new ArrayList<>(input), 0, '0');
    System.out.println("part 2: " + (oxygen * co2));
  }

  private static int count1(List<String> input, int index) {
    int c1 = 0;
    for (String str : input) {
      if (str.charAt(index) == '1') {
        c1++;
      }
    }
    return c1;
  }

  private static void dump(List<String> input) {
    System.out.println("Dumping: ");
    for (String s : input) {
      System.out.println(s);
    }
  }

  private static int part2(List<String> input, int index, char target) {
    if (input.size() == 1) {
      // Good!
      return Integer.parseInt(input.get(0), 2);
    }

    // Errors.
    if (input.size() == 0) {
      System.out.println("error, input 0; index is " + index);
      System.exit(-1);
    }
    if (index >= input.get(0).length()) {
      System.out.println("error, index too big");
      dump(input);
      System.exit(-2);
    }

    final int c1 = count1(input, index);
    final int c0 = input.size() - c1;
    char toKeep;
    if (target == '1') {
      // Oxygen.
      toKeep = c1 >= c0 ? '1' : '0';
    } else {
      // CO2.
      toKeep = c0 <= c1 ? '0' : '1';
    }
    assert target == '1' || target == '2';

    for (int i = input.size() - 1; i >= 0; i--) {
      if (input.get(i).charAt(index) != toKeep) {
        input.remove(i);
      }
    }
    return part2(input, index + 1, target);
  }
}
