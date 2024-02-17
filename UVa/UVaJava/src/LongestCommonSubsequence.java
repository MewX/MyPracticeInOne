import java.util.Arrays;
import java.util.Scanner;

public class LongestCommonSubsequence {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line1, line2;
			line1 = scanner.nextLine();
			line2 = scanner.nextLine();
			
			// LCS common solution
			int[] records = new int [(line1.length() + 1) * (line2.length() + 1)];
			Arrays.fill(records, 0);
			int max = 0;
			for (int i = line1.length() - 1; i >= 0; i --) {
				for (int j = line2.length() - 1; j >= 0; j --) {
					if (line1.charAt(i) == line2.charAt(j)) {
						int temp = 1 + records[(line2.length() + 1) * (i + 1) + (j + 1)];
						if (temp > max) max = temp;
						records[(line2.length() + 1) * i + j] = temp;
					} else {
						records[(line2.length() + 1) * i + j] = Integer.max(records[(line2.length() + 1) * i + (j + 1)], records[(line2.length() + 1) * (i + 1) + j]);
					}
				}
			}
			
			System.out.println(max);
		}
		scanner.close();
	}
}
