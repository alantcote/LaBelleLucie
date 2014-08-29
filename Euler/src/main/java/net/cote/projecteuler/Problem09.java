package net.cote.projecteuler;

/**
 * A solution for Project Euler Problem 9.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=9">here</a>.
 * @author Al Cote'
 */
public class Problem09 {

	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		for (int a = 1; a < 1000; ++a) {
			for (int b = a + 1; b < 1000; ++b) {
				int c = 1000 - a - b;
				
				if (c > b) {
					int sumSq = a * a + b * b;
					int cSq = c * c;
					
					if (sumSq == cSq) {
						int prod = a * b * c;
						
						System.out.println("a = " + a);
						System.out.println("b = " + b);
						System.out.println("c = " + c);
						System.out.println("prod = " + prod);
					}
				}
			}
		}
	}
}
