import java.util.Scanner;

public class CollatzSequence {
//	private HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int A, limit;
		A = scanner.nextInt();
		limit = scanner.nextInt();
		int caseCount = 1;
		while (A != -1 || limit != -1) {
			int result = 0;
			long a = A;
			while (a != 1 && a <= limit) {
				result += 1;
				a = (a % 2 == 1 ? a * 3 + 1 : a >> 1);
			}
			if (a == 1) result += 1;
			
			System.out.println("Case " + caseCount + ": A = " + A + ", limit = " + limit + ", number of terms = " + result);
			caseCount ++;
			A = scanner.nextInt();
			limit = scanner.nextInt();
		}
		scanner.close();
	}
}
