package org.mewx.topcoder.problems;

public class Thimbles {
	public static void main(String[] args) {
		System.out.println(new Thimbles().thimbleWithBall(new String[] {"1-2", "3-1"}));
		System.out.println(new Thimbles().thimbleWithBall(new String[] {"3-1", "2-3", "3-1", "3-2"}));
		System.out.println(new Thimbles().thimbleWithBall(new String[] {"2-3", "1-3", "2-3", "2-1", "3-1"}));
		System.out.println(new Thimbles().thimbleWithBall(new String[] {"1-2", "3-2", "1-2", "2-1", "2-1", "3-2", "1-3", "3-1", "1-2"}));
	}

	public int thimbleWithBall(String[] swaps) {
		boolean[] t = new boolean[] {true, false, false};
		for (String s : swaps) {
			// convert char to int (index)
			int idx1 = s.charAt(0) - '1';
			int idx2 = s.charAt(2) - '1';
			
			// swap the two value
			boolean temp = t[idx1];
			t[idx1] = t[idx2];
			t[idx2] = temp;
		}
		
		if (t[0]) return 1;
		else if (t[1]) return 2;
		else return 3;
	}
}
