import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RotatingSentences {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		
		int maxLength = 0;
		while (scanner.hasNextLine()) {
			String r = scanner.nextLine();
			if (r.length() > maxLength) maxLength = r.length();
			list.add(r);
		}
		
		// proceed
		for (int i = 0; i < maxLength; i ++) { // idx
			
			for (int row = list.size() - 1; row >= 0; row --) {
				System.out.print(list.get(row).length() > i ? list.get(row).charAt(i) : " ");
			}
			System.out.println();
		}
		
		scanner.close();
	}
}
