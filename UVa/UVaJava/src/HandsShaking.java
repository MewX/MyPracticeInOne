public class HandsShaking {

	public long countPerfect(int n) {
		// https://en.wikipedia.org/wiki/Catalan_number
		long[] f = new long[n + 1];
	    f[0] = 1;
	    for (int i = 2; i <= n; i+=2) {
	    	for (int j = 0; j <= i-2; j+=2) {
		        f[i] += f[j] * f[i-j-2];
	    	}
	    }
	    
	    return f[n];
	}
	
	public static void main(String[] args) {
		System.out.println(new HandsShaking().countPerfect(2));
		System.out.println(new HandsShaking().countPerfect(4));
		System.out.println(new HandsShaking().countPerfect(6));
		System.out.println(new HandsShaking().countPerfect(28));
	}
}
