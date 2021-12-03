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

    System.out.println("part 1: ");
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
  }
}
