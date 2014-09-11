package net.sf.cotelab.euler;

import net.sf.cotelab.util.math.MathFunctions;

/**
 * A solution for Project Euler Problem 15.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=15">here</a>.
 * @author Al Cote'
 */
public class Problem15 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		double numer = MathFunctions.factorial(GRID_HEIGHT + GRID_WIDTH);
		double denom = MathFunctions.factorial(GRID_HEIGHT) * MathFunctions.factorial(GRID_WIDTH);
		double nbrPaths = numer / denom;
		long nbrPathsL = (long) nbrPaths;
		
		// nbrPathsL = 137846528820
		System.out.println("nbrPathsL = " + nbrPathsL);
	}
	public static final long GRID_HEIGHT = 20;

	public static final long GRID_WIDTH = 20;
}
