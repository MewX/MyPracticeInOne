import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day02 {

  static class Record {
    int least, most;
    char c;
    String password;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    List<Record> records = new ArrayList<>();
    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (line.length() < 2) {
        continue;
      }

      Record r = new Record();
      records.add(r);
      String[] sections = line.split("[:\\- ]");
      assert sections.length == 5;
      r.least = Integer.parseInt(sections[0]);
      r.most = Integer.parseInt(sections[1]);
      assert sections[2].length() == 1;
      r.c = sections[2].charAt(0);
      assert sections[3].isEmpty();
      r.password = sections[4].trim();
    }

    int count = 0;
    for (Record r : records) {
      if (r.least > r.most) {
        System.err.println("least is greater than most:");
        System.out.format("%d-%d %c: %s\n", r.least, r.most, r.c, r.password);
      }

      int c = count(r.password, r.c);
      if (r.least <= c && c <= r.most) {
        count ++;
      }
    }
    System.out.println("part 1: " + count);

    count = 0;
    for (Record r : records) {
      int temp = r.password.charAt(r.least - 1) == r.c ? 1 : 0;
      temp += r.password.charAt(r.most - 1) == r.c ? 1 : 0;
      if (temp == 1) {
        count ++;
      }
    }
    System.out.println("part 2: " + count);
  }

  private static int count(String str, char c) {
    int cnt = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == c) {
        cnt ++;
      }
    }
    return cnt;
  }

}
