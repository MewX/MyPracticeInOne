import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Skyline {
	private static class Info {
		public int L, H, R;
		public Info(int a, int b, int c) {
			L = a;
			H = b;
			R = c;
		}
	}
	
	private static class Sp {
		public int X, H;
		public boolean isLeft = true;
		public Sp(int x, int h, boolean left) {
			X = x;
			H = h;
			isLeft = left;
		}
	}
	
	private static class Final {
		public int X, H;
		public Final(int x, int h) {
			X = x;
			H = h;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Info> info = new ArrayList<>();
		while (scanner.hasNextInt()) {
			info.add(new Info(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
		}
		scanner.close();
		
		// sort them by L and R
		Sp[] use = new Sp[info.size() * 2];
		for (int i = 0; i < info.size(); i ++) {
			Info temp = info.get(i);
			use[i << 1] = new Sp(temp.L, temp.H, true);
			use[(i << 1) + 1] = new Sp(temp.R, temp.H, false);
		}
		Arrays.sort(use, (a, b) -> {
			if (a.X != b.X) return a.X - b.X;
			else if (a.isLeft && b.isLeft) return a.H - b.H;
			else if (!a.isLeft && !b.isLeft) return b.H - a.H;
			else return a.isLeft ? 1 : -1; // left is before right
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(info.size() + 1, (a, b) -> b - a); // from large to little
		pq.add(0);
		List<Final> f = new ArrayList<>();
		f.add(new Final(0, 0));
		for (Sp sp : use) {
			Final latest = f.get(f.size() - 1);
			if (sp.isLeft) {
				pq.add(sp.H);
				if (latest.X == sp.X) latest.H = pq.peek();
				else if (latest.H < sp.H) {
					f.add(new Final(sp.X, pq.peek()));
//					System.out.println(sp.X + " [" + sp.H + "] (1");
				}
			} else {
				// right
				int highest = pq.peek();
				pq.remove(sp.H);
				if (highest == sp.H) {
					if (pq.peek() == sp.H) continue;
					else {
						if (latest.X == sp.X) latest.H = pq.peek();
						else {
							// latest.H < sp.H
							f.add(new Final(sp.X, pq.peek()));
//							System.out.println(sp.X + " [" + sp.H + "] (2) " + highest);
						}
					}
				}
			}
		}

		boolean needSpace = false;
		for (Final temp : f) {
			if (temp.X == 0 && temp.H == 0) continue;
			if (needSpace) System.out.print(" " + temp.X + " " + temp.H);
			else {
				System.out.print(temp.X + " " + temp.H);
				needSpace = true;
			}
		}
		System.out.println();
	}
}
