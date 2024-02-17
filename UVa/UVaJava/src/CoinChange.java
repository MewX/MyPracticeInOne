import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
	static final int[] coins = new int[] {50, 25, 10, 5, 1};
	static int[][] table = new int[5][7490]; // [0-4][0-7489]
	
	public static void main(String[] args) {
		// calculate all the values
		for (int i = 0; i < 5; i ++) {
			Arrays.fill(table[i], 0);
		}
		table[0][0] = table[1][0] = table[2][0] = table[3][0] = table[4][0] = 1;
		for (int n = 1; n <= 7489; n ++) {
			for (int i = 0; i < 5; i ++) {
				int lessCoinsCount = 0;
				if (i > 0) {
					lessCoinsCount = table[i - 1][n];
				}

				int lessAmountCount = 0;
				int coinVal = coins[i];
				if (n - coinVal >= 0) {
					lessAmountCount = table[i][n - coinVal];
				}
				table[i][n] = lessCoinsCount + lessAmountCount;
//				System.out.print(table[i][n] + " ");
			}
//			System.out.println();
		}
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			int value = scanner.nextInt();
			System.out.println(table[4][value]);
		}
		scanner.close();
	}
}
