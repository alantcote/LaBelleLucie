package net.sf.cotelab.euler;

import net.sf.cotelab.util.math.ArithProg;
import net.sf.cotelab.util.math.MathFunctions;

/**
 * A solution for Project Euler Problem 12.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=12">here</a>.
 * @author Al Cote'
 */
public class Problem12 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		ArithProg ap = new ArithProg(1L, 1L);
		int termCount = 1;
		long triangleNbr = 0;
		long[] factors = null;
		int factorCount = 0;

		System.out.println("Searching . . .");
		
		while (true) {
			triangleNbr = ap.sumNTerms(termCount);
			factors = MathFunctions.factors(triangleNbr);
			factorCount = factors.length;
			
			if (factorCount > FACTOR_COUNT_TARGET) {
				break;
			}
			
			++termCount;
		}
		
		System.out.println("Solution found . . .");
		System.out.println("termCount = " + termCount);
		System.out.println("triangleNbr = " + triangleNbr);
		System.out.println("factorCount = " + factorCount);
		System.out.println("factors = " + MathFunctions.stringify(factors));
	}

	public static final int FACTOR_COUNT_TARGET = 500;
}
