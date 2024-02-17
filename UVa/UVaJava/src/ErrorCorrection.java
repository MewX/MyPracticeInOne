import java.util.Scanner;

public class ErrorCorrection {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[][] table;
		while (n != 0) {
			table = new int[n][];
			int[] rcRow = new int[n];
			int[] rcColume = new int[n];
			
			for (int i = 0; i < n; i ++) {
				table[i] = new int[n];
				int count = 0;
				for (int j = 0; j < n; j ++) {
					table[i][j] = scanner.nextInt();
					count += table[i][j];
				}
				rcRow[i] = count;
			}
			
			// calculate rcColume
			for (int i = 0; i < n; i ++) {
				int count = 0;
				for (int j = 0; j < n; j ++) {
					count += table[j][i];
				}
				rcColume[i] = count;
			}
			
			int firstOddRow = -1, firstOddColume = -1;
			int cntOddRow = 0, cntOddColume = 0;
			for (int i = 0; i < n; i ++) {
				if (rcRow[i] % 2 == 1) {
					if (firstOddRow == -1) firstOddRow = i;
					cntOddRow ++;
				}
				if (rcColume[i] % 2 == 1) {
					if (firstOddColume == -1) firstOddColume = i;
					cntOddColume ++;
				}
			}
			
			if (cntOddRow == 0 && cntOddColume == 0) System.out.println("OK");
			else if (cntOddRow == 1 && cntOddColume == 1) System.out.println("Change bit (" + (firstOddRow + 1) + "," + (firstOddColume + 1) + ")");
			else System.out.println("Corrupt");

			n = scanner.nextInt();
		}
		scanner.close();
	}
}
