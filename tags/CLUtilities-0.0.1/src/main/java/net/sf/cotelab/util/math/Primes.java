package net.sf.cotelab.util.math;

import java.util.LinkedList;

/**
 * A prime number generator/tester with a Sieve of Eratosthenes cache.
 * @author US80653H
 */
public class Primes {
	/**
	 * The smallest prime number.
	 */
	public static final long FIRST_PRIME = 2;
	
	/**
	 * The cache holds prime numbers that are smaller than this value.
	 */
	public static final int MAX_CACHED = 10001;
	
	/**
	 * The cache.
	 */
	private boolean[] sieve = null;
	
	/**
	 * Determine whether a given number is a prime number.
	 * @param n the given number.
	 * @return the truth-value of the assertion, "<tt>candidate</tt> is a prime
	 *		   number".
	 */
	public boolean isPrime(long n) {
		if (sieve == null) {
			createSieve();
		}
		
		if (n < FIRST_PRIME) {
			return false;
		} else if (n < MAX_CACHED) {
			return isSievedPrime(n);
		} else {
			if (n < 2) {
				return false;
			} else if (n < 4) {
				// 2 and 3 are prime
				return true;
			} else if (n % 2 == 0) {
				// above 3, all primes are odd
				return false;
			} else if (n < 9) {
				// we have already excluded 4, 6, and 8
				return true;
			} else if (n % 3 == 0) {
				return false;
			} else {
				long r = (long) (Math.sqrt((double) n) + 1.0);
				long f = 5;
				
				while (f <= r) {
					if (n % f == 0) {
						return false;
					}

					if (n % (f + 2) == 0) {
						return false;
					}
					
					f += 6;
				}
			}
			
			return true;
		}
	}

	/**
	 * Get the next prime number after a given number.
	 * <p>
	 * The next prime number is the smallest prime number that is greater than
	 * the given number.
	 * @param p the given number.
	 * @return the next prime number.
	 */
	public long nextPrime(long p) {
		long n = p + 1;
		
		// get it from the cache, if it is there
		for (; n < MAX_CACHED; ++n) {
			if (isSievedPrime(n)) {
				return n;
			}
		}
		
		// calculate it
		while (true) {
			if ((n % 2 != 0) && (n % 3 != 0)) {
				if (isPrime(n)) {
					return n;
				}
			}
			
			++n;
		}
	}

	/**
	 * Find the prime factors of a number.
	 * @param composite the number to examine.
	 * @return the prime factors (some may appear multiple times).
	 */
	public long[] primeFactors(long composite) {
		LinkedList<Long> pfList = new LinkedList<Long>();
		long[] pf;
		long dividend = composite;
		long candidate = FIRST_PRIME;
		
		while (candidate * candidate <= dividend) {
			if (dividend % candidate == 0) {
				pfList.add(candidate);
				dividend /= candidate;
			} else {
				candidate = nextPrime(candidate);
			}
		}
		
		if (dividend > 1) {
			pfList.add(dividend);
		}
		
		pf = new long[pfList.size()];
		for (int i = 0; i < pf.length; ++i) {
			pf[i] = pfList.get(i);
		}
		
		return pf;
	}

	protected void createSieve() {
		int limit = MAX_CACHED;
		int limitIndex = sieveIndex(limit);
		int crosslimit = (int) Math.sqrt(limit);
		
		sieve = new boolean[limitIndex];
		for (int i = 0; i < limitIndex; ++i) {
			sieve[i] = false;
		}
		
		// cross out even numbers > 2
		for (int n = 4; n < limit; n += 2) {
			sieve[sieveIndex(n)] = true;
		}
		
		for (int n = 3; n <= crosslimit; n += 2) {
			if (!sieve[sieveIndex(n)]) {
				// n not crossed out, hence prime; cross out all multiples
				for (int m = n * n; m < limit; m += n + n) {
					sieve[sieveIndex(m)] = true;
				}
			}
		}
	}

	protected boolean isSievedPrime(long candidate) {
		boolean sp = false;
		
		if (sieve == null) {
			createSieve();
		}
		
		if ((candidate > 1) && (candidate < MAX_CACHED)) {
			int si = sieveIndex(candidate);
			
			sp = !sieve[si];
		}
		
		return sp;
	}

	protected int sieveIndex(long candidate) {
		int si = (int) (candidate - 2);
		
		return si;
	}
}
