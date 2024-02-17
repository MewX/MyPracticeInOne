import java.math.BigInteger;
import java.util.Scanner;

public class Product {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextBigInteger()) {
			BigInteger a = scanner.nextBigInteger();
			BigInteger b = scanner.nextBigInteger();
			System.out.println(a.multiply(b));
		}
		
		scanner.close();
	}
}
