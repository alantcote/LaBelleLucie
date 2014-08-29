package net.cote.projecteuler;


/**
 * A solution for Project Euler Problem 19.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=19">here</a>.
 * @author Al Cote'
 */
public class Problem19 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int count = 0;
		
		for (int year = 1901; year <= 2000; ++year) {
			for (int month = 1; month <= 12; ++month) {
				int dayOfWeek = MyMath.zeller(year, month, 1);
				
				if (dayOfWeek == 1) {
					++count;
				}
			}
		}
		
		// 171
		System.out.println(count);
	}
}
