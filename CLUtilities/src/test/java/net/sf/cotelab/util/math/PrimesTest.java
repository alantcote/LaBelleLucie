package net.sf.cotelab.util.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimesTest {
	
	private boolean slowIsPrime(long n) {
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

	@Test
	public void testIsPrime() {
		Primes fixture = new Primes();
		
		for (long i = Primes.FIRST_PRIME; i < 2000000; ++i) {
			assertEquals(slowIsPrime(i), fixture.isPrime(i));
		}
	}

	@Test
	public void testIsSievedPrime() {
		Primes fixture = new Primes();

		assertFalse(fixture.isSievedPrime(1));
		assertTrue(fixture.isSievedPrime(2));
		assertTrue(fixture.isSievedPrime(3));
		assertFalse(fixture.isSievedPrime(6));
		assertFalse(fixture.isSievedPrime(Primes.MAX_CACHED));
	}

	@Test
	public void testNextPrime() {
		Primes fixture = new Primes();
		long p = Primes.FIRST_PRIME;

		for (int i = 1; i < Primes.MAX_CACHED; ++i) {
//			System.out.println("[" + i + "] " + p);
			p = fixture.nextPrime(p);
			
			assertEquals(1, p % 2);
		}
	}

	@Test
	public void testPrimeFactors() {
		Primes fixture = new Primes();
		
		for (long i = 2; i < 100000; ++i) {
			long[] pf = fixture.primeFactors(i);
			long prod = 1;
			
			if (fixture.isPrime(i)) {
				assertEquals(1, pf.length);
				assertEquals(i, pf[0]);
			} else {
//				System.out.println("i = " + i);
//				System.out.println("pf.length = " + pf.length);
				
				assertEquals(i, MathFunctions.product(pf));
				
				for (int j = 0; j < pf.length; ++j) {
//					System.out.println("pf[j] = " + pf[j]);
					assertTrue(fixture.isPrime(pf[j]));
					prod *= pf[j];
				}
				
				assertEquals(i, prod);
			}
		}
	}

	@Test
	public void testSieveIndex() {
		Primes fixture = new Primes();

		assertEquals(0, fixture.sieveIndex(2));
		assertEquals(1, fixture.sieveIndex(3));
	}
}
