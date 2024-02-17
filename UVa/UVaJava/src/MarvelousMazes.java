import java.util.Scanner;

public class MarvelousMazes {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.trim().length() == 0) {
				System.out.println();
				continue;
			}
			
			// output
			String[] list = line.split("!");
			for (String str : list) {
				int count = str.charAt(0) - '0';
				for (int i = 1; i < str.length(); i ++) {
					if (isDigit(str.charAt(i))) {
						count += str.charAt(i) - '0';
					} else {
						// not digit
						if (str.charAt(i) == 'b') {
							for (int n = 0; n < count; n ++) {
								System.out.print(" ");
							}
						} else {
							for (int n = 0; n < count; n ++) {
								System.out.print(str.charAt(i));
							}
						}
						count = 0;
					}
				}
				System.out.println();
			}
		}
		scanner.close();
	}
	
	private static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
}
