package net.sf.cotelab.euler;

/**
 * A solution for Project Euler Problem 4.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=4">here</a>.
 * @author Al Cote'
 */
public class Problem04 {

	private static int[] digits(int val) {
		int count = nbrDigits(val);
		int[] theDigits = new int[count];
		
		for (int i = count - 1; i >= 0; --i) {
			int quotient = val / 10;
			int product = quotient * 10;
			int aDigit = val - product;
			
			theDigits[i] = aDigit;
			val = quotient;
		}
		
		return theDigits;
	}
	
	private static boolean isPalindrome(int val) {
		int[] digit = digits(val);
		boolean result = true;
		int count = digit.length;
		int limit = (count + 1) / 2;
		
		for (int i = 0; i < limit; ++i) {
			if (digit[i] != digit[count - 1 - i]) {
				result = false;
				
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int iHigh = 0;
		int jHigh = 0;
		int pHigh = -1;
		
		for (int i = 900; i < 1000; ++i) {
			for (int j = 900; j < 1000; ++j) {
				int candidate = i * j;
				
				if (isPalindrome(candidate)) {
					if (candidate > pHigh) {
						iHigh = i;
						jHigh = j;
						pHigh = candidate;
					}
				}
			}
		}

		System.out.println("pHigh = " + pHigh);
		System.out.println("iHigh = " + iHigh);
		System.out.println("jHigh = " + jHigh);
	}

	private static int nbrDigits(int val) {
		return 1 + (int) Math.log10(val);
	}
}
