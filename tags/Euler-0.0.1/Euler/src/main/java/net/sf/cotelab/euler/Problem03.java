package net.sf.cotelab.euler;

/**
 * A solution for Project Euler Problem 3.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=3">here</a>.
 * @author Al Cote'
 */
public class Problem03 {
	/**
	 * Find the largest prime factor of a number.
	 * @param composite the number to examine.
	 * @return the number, itself, if the number is prime, else the largest
	 *	prime factor.
	 */
	/*
	 * This implementation searches for the smallest prime factor of composite.
	 * If such a factor is not found (it must be less than or equal to the
	 * square root of composite), then composite is, in fact prime, and is
	 * returned. If the factor is found, then the method recurses, to find the
	 * largest prime factor of (composite / factor), because only the smallest
	 * prime factor of composite has been found.
	 */
	private static long largestPrimeFactor(long composite) {
		long factor = 2L;
		long largestFactor = 0L;
		
		if (composite % 2L != 0L) {
			factor = 3L;
			
			/* This implementation uses multiplication instead of a square root
			 * operation because calculation of a square root is painfully slow
			 * by comparison with multiplication.
			 */
			while (factor * factor <= composite) {
				if (composite % factor == 0L) {
					break;
				}
				
				factor += 2;
			}
		}
		
		if (factor * factor <= composite) {
			largestFactor = largestPrimeFactor(composite / factor);
		} else {
			largestFactor = composite;
		}
		
		return largestFactor;
	}

	/**
	 * The main method.
	 * @param args unused.
	 */
	public static void main(String[] args) {
		printLargestPrimeFactor(COMPOSITE);
	}

	/**
	 * Print the largest prime factor of a number.
	 * @param composite the number to examine.
	 */
	private static void printLargestPrimeFactor(long composite) {
		System.out.println(
				"largestPrimeFactor(" + composite + ") = " +
						largestPrimeFactor(composite));
	}

	public static final long COMPOSITE = 600851475143L;
}
