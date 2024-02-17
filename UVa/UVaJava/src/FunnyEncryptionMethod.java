import java.util.Scanner;

public class FunnyEncryptionMethod {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (; n > 0; n --) {
			int number = scanner.nextInt();
			
			// 1st stage
			String numberDB = Integer.toBinaryString(number);
			int count1 = 0;
			for (int i = 0; i < numberDB.length(); i ++) {
				if (numberDB.charAt(i) == '1') count1 ++;
			}
			
			// 2nd stage
			String numberHB = Integer.toBinaryString(Integer.parseInt("" + number, 16));
			int count2 = 0;
			for (int i = 0; i < numberHB.length(); i ++) {
				if (numberHB.charAt(i) == '1') count2 ++;
			}
			
			System.out.println(count1 + " " + count2);
//			System.out.println(n ^ (count1 * count2));
		}
		scanner.close();
	}
}
