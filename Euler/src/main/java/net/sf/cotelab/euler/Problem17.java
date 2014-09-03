package net.sf.cotelab.euler;

import net.sf.cotelab.util.math.MathFunctions;

/**
 * A solution for Project Euler Problem 17.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=17">here</a>.
 * @author Al Cote'
 */
public class Problem17 {
	private static int countLetters(String aString) {
		int count = 0;
		
		for (int i = 0; i < aString.length(); ++i) {
			int aChar = aString.codePointAt(i);
			
			if (Character.isLetter(aChar)) {
				++count;
			}
		}
		
		return count;
	}
	
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int sum = 0;
		
		for (long n = 1; n <= 1000; ++n) {
			String english = MathFunctions.englishNumber(n);
			int nbrLetters = countLetters(english);
			
			sum += nbrLetters;
		}
		
		// 21124
		System.out.println("sum = " + sum);
	}
}
