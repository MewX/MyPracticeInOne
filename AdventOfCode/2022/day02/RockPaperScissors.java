import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RockPaperScissors {

  private enum Shape {
    ROCK, PAPER, SCISSOR
  }

  private static final Map<Shape, Integer> SHAPE_SCORE = Map.of(Shape.ROCK, 1, Shape.PAPER, 2,
      Shape.SCISSOR, 3);

  // Key beats Value.
  private static final Map<Shape, Shape> WINNING_MAP = Map.of(Shape.ROCK, Shape.PAPER, Shape.PAPER,
      Shape.ROCK, Shape.SCISSOR, Shape.PAPER);

  // Key loses Value.
  private static final Map<Shape, Shape> LOSING_MAP = WINNING_MAP.entrySet().stream()
      .collect(Collectors.toMap(
          Entry::getValue, Entry::getKey));

  private enum Result {
    LOSE, DRAW, WIN
  }

  private static final Map<Result, Integer> RESULT_SCORE = Map.of(Result.LOSE, 0, Result.DRAW, 3,
      Result.WIN, 6);

  public static void main(String[] args) {
    // A for Rock, B for Paper, and C for Scissors.
    // X for Rock, Y for Paper, and Z for Scissors.
    List<Shape> opChoices = new ArrayList<>();
    List<Shape> myChoices = new ArrayList<>();

    // 1 for Rock, 2 for Paper, and 3 for Scissors.
    // 0 if you lost, 3 if the round was a draw, and 6 if you won.
    Scanner s = new Scanner(System.in);
    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (line.isEmpty()) {
        continue;
      }
      assert line.length() == 3;

      switch (line.charAt(0)) {
        case 'A':
          opChoices.add(Shape.ROCK);
          break;
        case 'B':
          opChoices.add(Shape.PAPER);
          break;
        case 'C':
          opChoices.add(Shape.SCISSOR);
          break;
      }

      switch (line.charAt(2)) {
        case 'X':
          myChoices.add(Shape.ROCK);
          break;
        case 'Y':
          myChoices.add(Shape.PAPER);
          break;
        case 'Z':
          myChoices.add(Shape.SCISSOR);
          break;
      }
    }
    s.close();

    // Q1.
    int score = 0;
    for (int round = 0; round < opChoices.size(); round++) {
      score += SHAPE_SCORE.get(myChoices.get(round));

      // Check result.
      if (checkMyWin(opChoices.get(round), myChoices.get(round))) {
        score += RESULT_SCORE.get(Result.WIN);
      } else if (opChoices.get(round) == myChoices.get(round)) {
        score += RESULT_SCORE.get(Result.DRAW);
      }
    }
    System.out.println("Q1: " + score);

    // Q2.
    // X means you need to lose, Y means you need to end the round in a draw,
    // and Z means you need to win.
    List<Result> mappedChoices = myChoices.stream().map(shape -> {
      switch (shape) {
        case ROCK:
          return Result.LOSE;
        case PAPER:
          return Result.DRAW;
        case SCISSOR:
          return Result.WIN;
      }
      return Result.LOSE;
    }).collect(Collectors.toList());
    myChoices.clear();

    // Resetting score.
    score = 0;
    for (int round = 0; round < opChoices.size(); round++) {
      Shape myShape = Shape.ROCK;
      switch (mappedChoices.get(round)) {
        case LOSE:
          myShape = WINNING_MAP.get(opChoices.get(round));
          break;
        case WIN:
          myShape = LOSING_MAP.get(opChoices.get(round));
          break;
        case DRAW:
          myShape = opChoices.get(round);
          break;
      }

      score += RESULT_SCORE.get(mappedChoices.get(round));
      score += SHAPE_SCORE.get(myShape);
    }
    System.out.println("Q2: " + score);

  }

  private static boolean checkMyWin(Shape op, Shape mine) {
    return op == WINNING_MAP.get(mine);
  }

}
