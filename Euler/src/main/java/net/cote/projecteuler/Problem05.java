package net.cote.projecteuler;

/**
 * A solution for Project Euler Problem 5.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=5">here</a>.
 * @author Al Cote'
 */
public class Problem05 {
	public static final long MAX = 20L;
	public static final long MIN = 11L;
	public static final long BASE = 2520L;

	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		for (long candidate = BASE; true; candidate += BASE) {
			if (satisfiesRange(candidate, MIN, MAX)) {
				System.out.println(candidate);
				
				break;
			}
		}
	}
	
	private static boolean satisfiesRange(long candidate, long min, long max) {
		for (long i = min; i <= max; ++i) {
			if (candidate % i != 0) {
				return false;
			}
		}
		
		return true;
	}
}
