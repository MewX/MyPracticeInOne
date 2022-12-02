import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RockPaperScissors {

  private enum Shape {
    ROCK, PAPER, SCISSOR
  }

  private enum Result {
    LOSE, DRAW, WIN
  }

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
      // Check shape.
      score += getShapeScore(myChoices.get(round));

      // Check result.
      if (checkMyWin(opChoices.get(round), myChoices.get(round))) {
        score += 6;
      } else if (opChoices.get(round) == myChoices.get(round)) {
        score += 3;
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
          myShape = getLose(opChoices.get(round));
          break;
        case WIN:
          score += 6;
          myShape = getWin(opChoices.get(round));
          break;
        case DRAW:
          score += 3;
          myShape = opChoices.get(round);
          break;
      }

      // Check shape.
      score += getShapeScore(myShape);
    }
    System.out.println("Q2: " + score);

  }

  private static boolean checkMyWin(Shape op, Shape mine) {
    return op == Shape.ROCK && mine == Shape.PAPER || op == Shape.PAPER && mine == Shape.SCISSOR
        || op == Shape.SCISSOR && mine == Shape.ROCK;
  }

  private static int getShapeScore(Shape shape) {
    switch (shape) {
      case ROCK:
        return 1;
      case PAPER:
        return 2;
      case SCISSOR:
        return 3;
    }
    return 0;
  }

  private static Shape getLose(Shape op) {
    switch (op) {
      case ROCK:
        return Shape.SCISSOR;
      case PAPER:
        return Shape.ROCK;
      case SCISSOR:
        return Shape.PAPER;
    }
    return Shape.ROCK;
  }

  private static Shape getWin(Shape op) {
    switch (op) {
      case ROCK:
        return Shape.PAPER;
      case PAPER:
        return Shape.SCISSOR;
      case SCISSOR:
        return Shape.ROCK;
    }
    return Shape.ROCK;
  }

}
