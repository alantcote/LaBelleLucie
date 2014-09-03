package net.sf.cotelab.euler;

import net.sf.cotelab.util.math.MathFunctions;



/**
 * A solution for Project Euler Problem 21.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=21">here</a>.
 * @author Al Cote'
 */
public class Problem21 {
	private static long d(long x) {
		long sum = 0;
		long[] factor = MathFunctions.factors(x);

//		System.out.println("factor = " + MathFunctions.stringify(factor));
		
		sum = MathFunctions.sum(factor);
		
		if (factor.length > 1) {
			sum -= x;
		}
		
		return sum;
	}
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		long[] dCache = new long[LIMIT];
		boolean[] aCache = new boolean[LIMIT];
		long sum = 0;
		
		for (int n = 1; n < LIMIT; ++n) {
			dCache[n] = d(n);
			aCache[n] = false;
		}
		
		for (int a = 1; a < LIMIT; ++a) {
			double b = dCache[a];
			
			if ((b < LIMIT) && (a < b)) {
				double ab = dCache[(int) b];

				if (ab == a) {
					aCache[a] = true;
					aCache[(int) b] = true;
				}
			}
		}
		
		for (int n = 1; n < LIMIT; ++n) {
			if (aCache[n]) {
				sum += n;
			}
		}
		
		// 31626
		System.out.println("sum = " + sum);
	}
	
	public static final int LIMIT = 10000;
}
