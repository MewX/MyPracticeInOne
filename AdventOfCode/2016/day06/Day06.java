import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Day06 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (!line.isEmpty()) {
                inputs.add(line.toLowerCase());
            }
        }
        s.close();

        // Part 1.
        for (int i = 0; i < inputs.get(0).length(); i++) {
            char[] chars = new char[inputs.size()];
            for (int j = 0; j < inputs.size(); j++) {
                chars[j] = inputs.get(j).charAt(i);
            }
            System.out.print(highestFrequency(chars));
        }
        System.out.println();
    }

    public static char highestFrequency(char[] chars) {
        // Ascending.
        Arrays.sort(chars);

        char high = chars[0], tempChar = high;
        int freq = 1, tempFreq = freq;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == tempChar) {
                tempFreq ++;
            } else {
                if (tempFreq > freq) {
                    high = tempChar;
                    freq = tempFreq;
                }

                // Next possible one.
                tempChar = chars[i];
                tempFreq = 1;
            }
        }
        if (tempFreq > freq) {
            high = tempChar;
        }
        return high;
    }
}