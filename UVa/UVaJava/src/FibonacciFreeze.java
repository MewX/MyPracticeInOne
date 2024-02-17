import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciFreeze {
	private static List<BigInteger> fib = new ArrayList<>();

	public static void main(String[] args) {
		fib.add(BigInteger.ZERO); // idx - 0
		fib.add(BigInteger.ONE); // idx - 1
		fib.add(BigInteger.ONE); // idx - 2
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			int n = scanner.nextInt();
			System.out.println("The Fibonacci number for " + n + " is " + requestFib(n));
		}
		
		scanner.close();
	}
	
	private static BigInteger requestFib(int idx) {
		if (fib.size() <= idx) {
			// need calculate
			for (int i = fib.size(); i <= idx; i ++) {
				fib.add(fib.get(i - 1).add(fib.get(i - 2)));
			}
		}
		return fib.get(idx);
	}
}
