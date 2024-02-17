import java.util.Scanner;

public class CarryOrNot {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLong()) {
			long a = scanner.nextLong(), b = scanner.nextLong();
			
			long result = 0;
			for (int i = 0; i < 32; i ++, a >>= 1, b >>= 1) {
				result >>= 1;
				if (((a & 1l) ^ (b & 1l)) == 1l) {
					result |= 0x4000000000000000l;
				}
			}
			result >>= 31;
			System.out.println(result);
		}
		scanner.close();
	}
}
