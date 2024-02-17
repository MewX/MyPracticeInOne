import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DaVinciCode {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<Integer> list = new ArrayList<>();
		list.add(1); // idx - 0
		list.add(2); // idx - 1
		
		int n = Integer.valueOf(s.nextLine());
		for (int cnt = 0; cnt < n; cnt ++) {
			int vals = Integer.valueOf(s.nextLine().trim());
			
			String[] numss = s.nextLine().trim().split(" +");
			String cipherCode = s.nextLine().trim();
			
			// convert
			int max = 1;
			int[] nums = new int[numss.length];
			for (int i = 0; i < numss.length; i ++) {
				nums[i] = Integer.valueOf(numss[i]);
				if (nums[i] > max) max = nums[i];
			}
			
			// check max
			if (list.get(list.size() - 1) < max) fib(max, list);
			
			// change the script
			char[] result = new char[list.size()];
			Arrays.fill(result, ' ');
			int idx = 0;
			for (int i = 0; i < cipherCode.length() && idx < vals; i ++) {
				if (cipherCode.charAt(i) > 'Z' || cipherCode.charAt(i) < 'A') continue;
				int temp = Collections.binarySearch(list, nums[idx ++]);
				result[temp] = cipherCode.charAt(i);
			}
			
			// output
			int lastIdx = result.length - 1;
			while (result[lastIdx] == ' ') lastIdx --;
			System.out.println(String.copyValueOf(result).substring(0, lastIdx + 1));
//			boolean needSpace = false;
//			for (int i = 0; i < result.length; i ++) {
//				for (int)
//			}
		}
		
		s.close();
	}
	
	private static void fib(int max, List<Integer> list) {
		int lastIdx = list.size() - 1;
		while (list.get(lastIdx) < max) {
			list.add(list.get(lastIdx) + list.get(lastIdx - 1));
			lastIdx ++;
		}
	}
}
