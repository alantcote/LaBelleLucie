package net.cote.projecteuler;

import java.math.BigInteger;

/**
 * A solution for Project Euler Problem 16.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=16">here</a>.
 * @author Al Cote'
 */
public class Problem16 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		BigInteger base = BigInteger.ONE.add(BigInteger.ONE);
		BigInteger result = base.pow(1000);
		String resultString = result.toString();
		int sum = 0;
		
		for (int i = 0; i < resultString.length(); ++i) {
			String digitString = resultString.substring(i, i + 1);
			int digit = Integer.parseInt(digitString);
			
			sum += digit;
		}
		
		System.out.println("sum = " + sum);
	}
}
