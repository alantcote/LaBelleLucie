package net.sf.cotelab.euler;

import java.util.HashMap;


/**
 * A solution for Project Euler Problem 26.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=26">here</a>.
 * @author Al Cote'
 */
public class Problem26 {
	/**
	 * Find the length of any repeating cycle in the decimal expansion of 1/n,
	 * where n is a positive integer.
	 * @param n a positive integer.
	 * @return the cycle length (0 if no cycle).
	 */
	private static int cycLen(int n) {
		if ((n % 2 == 0) || (n % 5 == 0)) {
			return 0;
		}
		
		// key is remainder; value is step nbr when it happened
		HashMap<Integer, Integer> rmdrWhen = new HashMap<Integer, Integer>();
		int count = 0;
		int dividend = 1;
		int divisor = n;
		int remainder;
		int result = 0;
		
		do {
			while (dividend < divisor) {
				dividend *= 10;
			}
			
			remainder = dividend % divisor;
			
			if (rmdrWhen.containsKey(remainder)) {
				int when = rmdrWhen.get(remainder);
				
				result = count - when;
				break;
			} else {
				rmdrWhen.put(remainder, count);
			}
			
			dividend = remainder;
			++count;
		} while (remainder > 0);
		
		return result;
	}
	
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int longest = 0;
		int result = 0;
		
		for (int n = 1; n < 1000; ++n) {
			int cycleLen = cycLen(n);
//			System.out.println("cycLen(" + n + ") = " + cycLen(n));
			
			if (cycleLen > longest) {
				longest = cycleLen;
				result = n;
			}
		}
		
		// 983
		System.out.println("result = " + result);
	}
}
