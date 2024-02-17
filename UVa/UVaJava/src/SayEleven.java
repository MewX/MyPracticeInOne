import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.util.Scanner;

public class SayEleven {
	
	public static void main(String[] args) {
//		List<BigInteger> intList = new ArrayList<>(1000);
//		int[] idx = new int[1000]; // from 0
//		Arrays.fill(idx, 0); // idx[len-1]
//		
//		// pre calc
//		BigInteger temp = BigInteger.valueOf(11);
//		intList.add(temp);
//		idx[temp.toString().length() - 1] = intList.size() - 1; // save the idx in intList
//		for (int i = 0; i < 1000; i ++) {
//			BigInteger t = temp.multiply(BigInteger.valueOf(11));
//			intList.add(t);
//			temp = t;
//			
//			int len = temp.toString().length();
//			if (len >= 1000) break;
//			idx[len - 1] = intList.size() - 1;
//		}
//
//		// get and output
//		Scanner scanner = new Scanner(System.in);
//		BigInteger n = scanner.nextBigInteger();
//		while (!n.equals(BigInteger.ZERO)) {
//			int i = idx[n.toString().length() - 1];
//			System.out.println(intList.get(i));
//			System.out.println(n + " is" + (!intList.get(i).equals(n) ? " not" : "") + " a multiple of 11.");
//			
//			n = scanner.nextBigInteger();
//		}
//		scanner.close();
		

		Scanner scanner = new Scanner(System.in);
		String ns = scanner.nextLine().trim();
		BigInteger n = new BigInteger(ns);
		final BigInteger c = BigInteger.valueOf(11);
		while (!n.equals(BigInteger.ZERO)) {
			System.out.println(ns + " is" + (!n.mod(c).equals(BigInteger.ZERO) ? " not" : "") + " a multiple of 11.");

			ns = scanner.nextLine().trim();
			n = new BigInteger(ns);
		}
		scanner.close();
	}
}
