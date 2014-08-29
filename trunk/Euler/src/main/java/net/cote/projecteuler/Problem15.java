package net.cote.projecteuler;

/**
 * A solution for Project Euler Problem 15.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=15">here</a>.
 * @author Al Cote'
 */
public class Problem15 {
	public static final long GRID_HEIGHT = 20;
	public static final long GRID_WIDTH = 20;

	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		double numer = MyMath.factorial(GRID_HEIGHT + GRID_WIDTH);
		double denom = MyMath.factorial(GRID_HEIGHT) * MyMath.factorial(GRID_WIDTH);
		double nbrPaths = numer / denom;
		long nbrPathsL = (long) nbrPaths;
		
		// nbrPathsL = 137846528820
		System.out.println("nbrPathsL = " + nbrPathsL);
	}
}
