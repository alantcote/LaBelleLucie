package net.cote.projecteuler;



/**
 * A solution for Project Euler Problem 27.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=27">here</a>.
 * @author Al Cote'
 */
public class Problem27 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		Primes primes = new Primes();
		int aMax = 0;
		int bMax = 0;
		int lenMax = 0;
		int result = 0;
		
		for (int a = -999; a < 1000; ++a) {
			for (int b = -999; b < 1000; ++b) {
				int len = primeSeqLen(primes, a, b);
				
				if (len > lenMax) {
					aMax = a;
					bMax = b;
					lenMax = len;

//					System.out.println(
//							"len = " + len + "; a = " + a + "; b = " + b);
				}
			}
		}
		
		result = aMax * bMax;
		
		// -59231
		System.out.println("result = " + result);
	}
	
	private static int primeSeqLen(Primes primes, int a, int b) {
		int len = 0;
		
		while (primes.isPrime(quad(len, a, b))) {
			++len;
		}
		
		return len;
	}
	
	/**
	 * Find the value <tt>f = n * n + a * n + b</tt>. where
	 * <tt>abs(a) < 1000</tt> and <tt>abs(b) < 1000</tt>, and
	 * <tt>n > -1</tt>.
	 * @param n
	 * @param a
	 * @param b
	 * @return the cycle length (0 if no cycle).
	 */
	private static int quad(int n, int a, int b) {
		int f = b + n * (n + a);
		
		return f;
	}
}
