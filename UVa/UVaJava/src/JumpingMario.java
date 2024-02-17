import java.util.Scanner;

public class JumpingMario {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int c = 0; c < n; c ++ ) {
			int count = scanner.nextInt();
			int high = 0, low = 0;
			int save = scanner.nextInt();
			for (int i = 1; i < count; i ++) {
				int now = scanner.nextInt();
				if (now < save) {
					low ++;
				} else if (now > save) {
					high ++;
				}
				save = now;
			}
			System.out.println("Case " + (c + 1) + ": " + high + " " + low);
		}
		scanner.close();
	}
}
