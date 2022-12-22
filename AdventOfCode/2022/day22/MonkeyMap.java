import java.awt.image.renderable.RenderableImage;
import java.util.*;

public class MonkeyMap {
    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    private static final Map<Direction, Integer> FACING_SCORE = Map.of(
            Direction.UP, 3,
            Direction.RIGHT, 0,
            Direction.DOWN, 1,
            Direction.LEFT, 2
    );

    private static final Map<Direction, Direction> TURN_LEFT = Map.of(
            Direction.UP, Direction.LEFT,
            Direction.RIGHT, Direction.UP,
            Direction.DOWN, Direction.RIGHT,
            Direction.LEFT, Direction.DOWN
    );

    private static final Map<Direction, Direction> TURN_RIGHT = Map.of(
            Direction.UP, Direction.RIGHT,
            Direction.RIGHT, Direction.DOWN,
            Direction.DOWN, Direction.LEFT,
            Direction.LEFT, Direction.UP
    );

    private static final char TILE = '.';
    private static final char WALL = '#';

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<StringBuilder> mapInput = new ArrayList<>();
        String passwordInput = "";
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            if (line.contains("" + TILE) || line.contains("" + WALL)) {
                mapInput.add(new StringBuilder(line));
            } else {
                assert passwordInput.isEmpty();
                passwordInput = line;
            }
        }
        s.close();

        // Optimize inputs.
        int maxLineLength = mapInput.stream().mapToInt(StringBuilder::length).max().orElse(-1);
        for (StringBuilder input : mapInput) {
            while (input.length() < maxLineLength){
                input.append(' ');
            }
        }

        // Part 1.
        int y = 0;
        int x = mapInput.get(y).indexOf("" + TILE);
        Direction direction = Direction.RIGHT;

        int pwIndex = 0;
        while (pwIndex < passwordInput.length()) {
            char currentChar = passwordInput.charAt(pwIndex);
            if (currentChar == 'L') {
                direction = TURN_LEFT.get(direction);
                pwIndex ++;
                continue;
            } else if (currentChar == 'R') {
                direction = TURN_RIGHT.get(direction);
                pwIndex ++;
                continue;
            }

            // Must be digits from here.
            StringBuilder number = new StringBuilder();
            while (pwIndex < passwordInput.length()
                    && passwordInput.charAt(pwIndex) != 'L' && passwordInput.charAt(pwIndex) != 'R') {
                number.append(passwordInput.charAt(pwIndex++));
            }

            // I hope there's not ridiculously huge numbers...
            int size = Integer.parseInt(number.toString());
            System.out.format("move: %d (%s)\n", size, direction.toString());
            for (int i = 0; i < size; i++) {
                if (direction == Direction.UP || direction == Direction.DOWN) {
                    y = findNext(direction, x, y, mapInput);
                    System.out.format("x: %d, y*: %d\n", x, y);
                } else {
                    // LEFT or RIGHT.
                    x = findNext(direction, x, y, mapInput);
                    System.out.format("x*: %d, y: %d\n", x, y);
                }
            }
        }

        int result = 4 * (x + 1) + 1000 * (y + 1) + FACING_SCORE.get(direction);
        System.out.println("part 1: " + result);
    }

    /**
     * @return x or y depending on the direction.
     */
    private static int findNext(Direction direction, int x, int y, List<StringBuilder> mapInput) {
        if (direction == Direction.UP) {
            int newY = y - 1;
            if (newY < 0) {
                // Wrapping around.
                newY = mapInput.size() - 1;
            }

            while (true) {
                char currentChar = mapInput.get(newY).charAt(x);
                if (currentChar == WALL) {
                    // Obstacle, returning old y.
                    return y;
                } else if (currentChar == TILE) {
                    // Good.
                    return newY;
                } else {
                    // Void area, continue finding.
                    newY --;
                }

                if (newY < 0) {
                    // Wrapping around.
                    newY = mapInput.size() - 1;
                }
            }
        } else if (direction == Direction.DOWN) {
            int newY = y + 1;
            if (newY >= mapInput.size()) {
                // Wrapping around.
                newY = 0;
            }

            while (true) {
                char currentChar = mapInput.get(newY).charAt(x);
                if (currentChar == WALL) {
                    // Obstacle, returning old y.
                    return y;
                } else if (currentChar == TILE) {
                    // Good.
                    return newY;
                } else {
                    // Void area, continue finding.
                    newY ++;
                }

                if (newY >= mapInput.size()) {
                    // Wrapping around.
                    newY = 0;
                }
            }
        } else if (direction == Direction.LEFT) {
            int newX = x - 1;
            if (newX < 0) {
                // Wrapping around.
                newX = mapInput.get(y).length() - 1;
            }

            while (true) {
                char currentChar = mapInput.get(y).charAt(newX);
                if (currentChar == WALL) {
                    // Obstacle, returning old x.
                    return x;
                } else if (currentChar == TILE) {
                    // Good.
                    return newX;
                } else {
                    // Void area, continue finding.
                    newX --;
                }

                if (newX < 0) {
                    // Wrapping around.
                    newX = mapInput.get(y).length() - 1;
                }
            }
        } else if (direction == Direction.RIGHT) {
            int newX = x + 1;
            if (newX >= mapInput.get(y).length()) {
                // Wrapping around.
                newX = 0;
            }

            while (true) {
                char currentChar = mapInput.get(y).charAt(newX);
                if (currentChar == WALL) {
                    // Obstacle, returning old x.
                    return x;
                } else if (currentChar == TILE) {
                    // Good.
                    return newX;
                } else {
                    // Void area, continue finding.
                    newX ++;
                }

                if (newX >= mapInput.get(y).length()) {
                    // Wrapping around.
                    newX = 0;
                }
            }
        }
        return -1;
    }

}
