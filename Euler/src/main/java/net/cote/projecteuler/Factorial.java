package net.cote.projecteuler;

public class Factorial {
	/**
	 * The maximum base for intFactorial().
	 */
	public static final int MAX_INT_FACTORIAL_BASE = 12;
	
	/**
	 * Calculate n!.
	 * @param n the number for which to calculate the factorial.
	 * @return n!.
	 * @throws IllegalArgumentException if n < 0 or n > MAX_INT_FACTORIAL_BASE,
	 *                                  because (1 + MAX_INT_FACTORIAL_BASE)!
	 *                                  > Integer.MAX_VALUE.
	 */
	public static final int intFactorial(int n)
			throws IllegalArgumentException {
		int f = 1;
		
		if ((n < 0) || (n > MAX_INT_FACTORIAL_BASE)) {
			throw new IllegalArgumentException(
					"n must be on the range 0.." + MAX_INT_FACTORIAL_BASE);
		}
		
		for (int i = n; i > 1; --i) {
			f *= i;
		}
		
		return f;
	}
}
