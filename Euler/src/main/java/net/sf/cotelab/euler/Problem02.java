/**
 * 
 */
package net.sf.cotelab.euler;

/**
 * A solution for Project Euler Problem 2.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=2">here</a>.
 * @author Al Cote'
 */
public class Problem02 {
	/**
	 * The main method.
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int sum = 2;
		int older = 1;
		int old = 2;
		int newest;
		
		while ((newest = older + old) <= LIMIT) {
			if ((newest % 2) == 0) {
				sum += newest;
			}
			
			older = old;
			old = newest;
		}
		
		System.out.println("sum = " + sum);
	}

	public static final int LIMIT = 4000000;

}
