import java.util.Scanner;

public class IdentifyingLegal {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String temp = s.nextLine().trim();
		while (!temp.equals("*")) {
			try {
				Double d = Double.valueOf(temp);
				if (temp.charAt(temp.length() - 1) == '.' || temp.contains(".e") || temp.charAt(0) == '.' || temp.contains(".E") || !temp.contains(".") && !temp.contains("e") && !temp.contains("E")) throw new Exception();
				System.out.println(temp + " is legal.");
			} catch (Exception e) {
				System.out.println(temp + " is illegal.");
			}
			
			temp = s.nextLine().trim();
		}
		
		
		s.close();
	}
}
