package net.cote.projecteuler;

/**
 * A solution for Project Euler Problem 10.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=10">here</a>.
 * @author Al Cote'
 */
public class Problem10 {
	public static long LIMIT = 2000000;

	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		Primes primes = new Primes();
		long sum = 0;

//		for (long candidate = 2; candidate < LIMIT; ++candidate) {
//			if (primes.isPrime(candidate)) {
//				sum += candidate;
//			}
//		}
		for (long p = Primes.FIRST_PRIME; p < LIMIT; p = primes.nextPrime(p)) {
			sum += p;
		}
		
		// 142913828922
		System.out.println("sum = " + sum);
	}
}
