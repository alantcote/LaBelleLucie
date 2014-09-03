package net.sf.cotelab.euler;

import net.sf.cotelab.util.math.Primes;

/**
 * A solution for Project Euler Problem 7.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=7">here</a>.
 * @author Al Cote'
 */
public class Problem07 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		Primes primes = new Primes();
		int count = 1;
		long candidate = 3;
		long lastPrime = 0;

		while (count < NBR_PRIMES) {
			lastPrime = candidate;
			candidate = primes.nextPrime(lastPrime);
			++count;
		}
		
		// 104743
		System.out.println("prime " + NBR_PRIMES + " = " + lastPrime);
	}

	public static int NBR_PRIMES = 10001;
}
