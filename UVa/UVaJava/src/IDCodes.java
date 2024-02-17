import java.util.Arrays;
import java.util.Scanner;

public class IDCodes {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		m:while (true) {
			String string = scanner.nextLine();
			if (string.equals("#")) break;
			
			// content
			int lastIdx = string.length() - 1;
			for (int i = lastIdx - 1; i >= 0; i --) {
				if (string.charAt(i) < string.charAt(i + 1)) {
					// rearrange substring
					System.out.println(string.substring(0, i) + rearrange(string.substring(i)));
					continue m;
				}
			}
			System.out.println("No Successor");
		}
		scanner.close();
	}

	private static String rearrange(String str) {
		// find larger one
		char a = str.charAt(0);
		
		char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        
        int i = 0;
        while (i < sorted.length() && sorted.charAt(i) <= a) {
        	i ++;
        }
        return sorted.charAt(i) + sorted.substring(0, i) + (i + 1 < sorted.length() ? sorted.substring(i + 1) : "");
	}
}
