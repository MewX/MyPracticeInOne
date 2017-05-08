package org.mewx.topcoder.problems;

public class RockStar {
	
	public static void main(String[] args) {
		// ff, fs, sf, ss
		System.out.println(new RockStar().getNumSongs(100, 0, 0, 200));
		System.out.println(new RockStar().getNumSongs(0, 0, 20, 200));
		System.out.println(new RockStar().getNumSongs(1, 2, 1, 1));
		System.out.println(new RockStar().getNumSongs(192, 279, 971, 249));
		System.out.println(new RockStar().getNumSongs(1, 0, 1, 1));
		System.out.println(new RockStar().getNumSongs(1000, 1000, 1000, 1000));
	}

	// greedy
	public int getNumSongs(int ff, int fs, int sf, int ss) {
		// rule 3
		int max = 0;
		if (ff > 0 || fs > 0) {
			// follow the rule 3, start from fast
			max = ff;
			if (fs > 0) {
				// able to link `fs` and `ss`
				final int common = Integer.min(fs, sf);
				max += (common << 1);
				fs -= common;
				sf -= common;
				if (fs == 0) {
					max = max - 1 + ss + 1; // remove one `sf` to make the last one is `fs`; also, it ensures that `sf > 0`, so add one for the last
				} else if (sf == 0) {
					max += 1 + ss; // because `fs > 0`, select one `fs` for linking to `ss`s
				}
			}
		} else {
			// start from slow
			max = ss + (sf > 0 ? 1 : 0);
		}
		return max;
	}
}
