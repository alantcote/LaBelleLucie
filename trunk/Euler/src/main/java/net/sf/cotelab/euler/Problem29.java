package net.sf.cotelab.euler;

import java.util.Arrays;
import java.util.LinkedList;

import net.sf.cotelab.util.math.Primes;

/**
 * A solution for Project Euler Problem 29.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=29">here</a>.
 * @author Al Cote'
 */
public class Problem29 {
	private class Facts implements Comparable<Facts> {
		private long[] factorCounts = null;
		private long n = -1;
		private long[] primeFactors = null;
		private Primes primes = null;
		private long[] uniqueFactors = null;
		
		public Facts(Primes primes, long n) {
			this.n = n;
			this.primes = primes;
		}

		private long[] calcFactorCounts() {
			long[] uf = getUniqueFactors();
			long[] pf = getPrimeFactors();
			long[] fc = new long[uf.length];
			int pfc = 0;
			
			for (int i = 0; i < uf.length; ++i) {
				long f = uf[i];
				fc[i] = 0;
				
				for (; pfc < pf.length && f == pf[pfc]; ++pfc) {
					++fc[i];
				}
			}
			
			return fc;
		}

		private long[] calcUniqueFactors() {
			long[] result = null;
			long[] raw = getPrimeFactors();
			LinkedList<Long> clean = new LinkedList<Long>();
			long oldEntry = -1;
			int count;
			
			for (int i = 0; i < raw.length; ++i) {
				if (raw[i] != oldEntry) {
					oldEntry = raw[i];
					clean.add(oldEntry);
				}
			}
			
			count = clean.size();
			result = new long[count];
			for (int i = 0; i < count; ++i) {
				result[i] = clean.get(i);
			}
			
			return result;
		}

		@Override
		public int compareTo(Facts o) {
			long[] myUF = getUniqueFactors();
			long[] hisUF = o.getUniqueFactors();
			int compLen = Math.min(myUF.length, hisUF.length);
			int lengthDif = myUF.length - hisUF.length;
			
			for (int i = 0; i < compLen; ++i) {
				long diff = myUF[i] - hisUF[i];
				
				if (diff < 0) {
					return -1;
				}
				
				if (diff > 0) {
					return 1;
				}
			}
			
			return lengthDif;
		}

		public long[] getFactorCounts() {
			if (factorCounts == null) {
				factorCounts = calcFactorCounts();
			}
			
			return factorCounts;
		}

		public long getN() {
			return n;
		}

		public long[] getPrimeFactors() {
			if (primeFactors == null) {
				primeFactors = primes.primeFactors(n);
			}
			
			return primeFactors;
		}

		public long[] getUniqueFactors() {
			if (uniqueFactors == null) {
				uniqueFactors = calcUniqueFactors();
			}
			
			return uniqueFactors;
		}
	}
	
	/* Derivation:
	 * 
	 * This is all about factors. We know that if x = y ** z, then x can be
	 * factored into z copies of y. If we consider the prime factors of y, then
	 * each such factor of y appears z times as often in the prime factorization
	 * of x.
	 * 
	 * Another approach looks at w ** x = y ** z. Observe that w and y must have
	 * the same prime factors, ignoring repetition. If we then pay attention to
	 * the repetitions of them, the factorization of y must have x/z times the
	 * number of instances of any given prime factor as w does. Note that if w
	 * and y don't have the same prime factors in them, then no x and z can make
	 * the equation valid.
	 * 
	 * I'm baffled, so I'm inclined to go brute force. The hard thing, though,
	 * is that we can't actually calculate these beasts with longs; we'd have to
	 * use BigInteger. Bummer - that'd be slow. We can't accumulate in a Set,
	 * either; there'll be something like 100 ** 100 of them.
	 * 
	 * So, let's calculate it!
	 */
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		Problem29 prob = new Problem29();
		int result = prob.calc();
		
		// ???
		System.out.println("result = " + result);
	}
	
	public int calc() {
		Primes primes = new Primes();
		Facts[] facts = new Facts[99];
		
		for (int n = 2; n <= 100; ++n) {
			facts[n - 2] = new Facts(primes, n);
		}
		
		Arrays.sort(facts);
		
		for (int i = 0; i < facts.length; ++i) {
			System.out.println(
					"{" + facts[i].getN() + "} " + Arrays.toString(
							facts[i].getUniqueFactors()) + " " +
							Arrays.toString(facts[i].getFactorCounts()) + " " +
							Arrays.toString(facts[i].getPrimeFactors()));
		}
		
		return 0;
	}
}
