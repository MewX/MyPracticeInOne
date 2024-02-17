import java.math.BigInteger;
import java.util.Scanner;

public class Ones {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			BigInteger n = BigInteger.valueOf(scanner.nextInt());

			int x = 0;
			BigInteger t = BigInteger.ONE;
			while (t.compareTo(n) < 0) {
				t = t.multiply(BigInteger.TEN).add(BigInteger.ONE);
				x ++;
			}
			
			// try one by one
			while (t.mod(n).compareTo(BigInteger.ZERO) != 0) {
				t = t.multiply(BigInteger.TEN).add(BigInteger.ONE);
				x ++;
			}
			x ++;
			
			System.out.println(x);
		}
		
		scanner.close();
	}
}
