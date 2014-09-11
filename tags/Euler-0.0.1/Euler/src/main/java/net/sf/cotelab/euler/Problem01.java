/**
 * 
 */
package net.sf.cotelab.euler;

/**
 * A solution for Project Euler Problem 1.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=1">here</a>.
 * @author Al Cote'
 */
public class Problem01 {
	/**
	 * The main method.
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int sum = 995;
		
		for (int n = 3; n < 1000; n += 3) {
			sum += n;
		}

		for (int n = 0; n < 990; n += 15) {
			sum += n + 5;
			sum += n + 10;
		}
		
		System.out.println("sum = " + sum);
	}

	public static final int LIMIT = 1000;

}
