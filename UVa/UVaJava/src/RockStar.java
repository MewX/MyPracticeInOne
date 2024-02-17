
public class RockStar {
	
	public static void main(String[] args) {
		System.out.println(new RockStar().getNumSongs(100, 0, 0, 200));
		System.out.println(new RockStar().getNumSongs(0, 0, 20, 200));
		System.out.println(new RockStar().getNumSongs(1, 2, 1, 1));
		System.out.println(new RockStar().getNumSongs(192, 279, 971, 249));
	}

	public int getNumSongs(int ff, int fs, int sf, int ss) {
		// rule 3
		int max = 0;
		if (ff > 0 || fs > 0) {
			// follow the rule 3, start from fast
			max = ff;
			if (fs > 0) {
				int com = Integer.min(fs, sf);
				if (com == 0) {
					max += 1 + ss;
				} else {
					max += (com << 1) + ss;
				}
			}
		} else {
			// start from slow
			max = ss + (sf > 0 ? 1 : 0);
		}
		return max;
	}
}
