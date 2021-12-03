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

    // part 1.
    String gamma = "", epsilon = "";
    for (int i = 0; i < input.get(0).length(); i++) {
      int c1 = 0;
      for (String str : input) {
        if (str.charAt(i) == '1') {
          c1++;
        }
      }

      int c0 = input.size() - c1;
      if (c1 > c0) {
        gamma += '1';
        epsilon += '0';
      } else if (c1 < c0) {
        gamma += '0';
        epsilon += '1';
      } else {
        System.out.println("error c1=c0");
      }
    }
    System.out.println("part 1: " + (Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)));

    // part 2.
    System.out.println(
        "part 2: " + (part2(new ArrayList<>(input), 0, '1') * part2(new ArrayList<>(input), 0,
            '0')));
  }

  private static void dump(List<String> input) {
    System.out.println("Dumping: ");
    for (String s : input) {
      System.out.println(s);
    }
  }

  // private static String oxygen = "", co2 = "";
  private static int part2(List<String> input, int index, char target) {
    if (input.size() == 1) {
      // Good!
      return Integer.parseInt(input.get(0), 2);
    }

    // errors.
    if (input.size() == 0) {
      System.out.println("error, input 0; index is " + index);
      System.exit(-1);
    }
    if (index >= input.get(0).length()) {
      System.out.println("error, index too big");
      dump(input);
      System.exit(-2);
    }

    int c1 = 0;
    for (String str : input) {
      if (str.charAt(index) == '1') {
        c1++;
      }
    }
    int c0 = input.size() - c1;

    char toKeep;
    if (target == '1') {
      // Oxygen.
      toKeep = c1 >= c0 ? '1' : '0';
    } else {
      // CO2.
      toKeep = c0 <= c1 ? '0' : '1';
    }

    for (int i = input.size() - 1; i >= 0; i--) {
      if (input.get(i).charAt(index) != toKeep) {
        input.remove(i);
      }
    }
    System.out.println("index: " + index);
    dump(input);
    return part2(input, index + 1, target);
  }
}
