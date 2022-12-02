import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalorieCounting {

  public static void main(String[] args) {
    List<List<Long>> calories = new ArrayList<>();

    Scanner s = new Scanner(System.in);
    List<Long> current = new ArrayList<>();
    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (line.isEmpty()) {
        if (!current.isEmpty()) {
          calories.add(current);
        }
        current = new ArrayList<>();
      } else {
        // Has a number.
        current.add(Long.valueOf(line));
      }
    }
    s.close();

    // Problem 1.
    System.out.println("Q1: " + maxCaloriesPerElf(calories));

    // Problem 2.
    System.out.println("Q2: " + top3Calories(calories));
  }

  public static Long maxCaloriesPerElf(List<List<Long>> calories) {
    return calories.stream().map(elf -> elf.stream().mapToLong(Long::longValue).sum())
        .mapToLong(Long::longValue).max().getAsLong();
  }

  public static Long top3Calories(List<List<Long>> calories) {
    long[] sorted = calories.stream().map(elf -> elf.stream().mapToLong(Long::longValue).sum())
        .mapToLong(Long::longValue).sorted().toArray();
    assert sorted.length > 3;

    int len = sorted.length;
    return sorted[len - 1] + sorted[len - 2] + sorted[len - 3];
  }

}
