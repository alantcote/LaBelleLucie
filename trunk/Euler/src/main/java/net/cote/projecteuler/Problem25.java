package net.cote.projecteuler;

import java.math.BigInteger;

/**
 * A solution for Project Euler Problem 25.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=25">here</a>.
 * @author Al Cote'
 */
public class Problem25 {
	public static final double SQRT5 = Math.sqrt(5.0);
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		long result = 0;

		for (int i = 0; i < 10; ++i) {
			System.out.println("fibonacci(" + i + ") = " + fibonacci(i));
		}
		
//		double logtSQRT5 = Math.log10(SQRT5);
//		double logtPhi = Math.log10((SQRT5 + 1.0) / 2.0);
//		double approxN = (logtSQRT5 + 1000.0) / logtPhi;
//
//		System.out.println("approxN = " + approxN);
//		
//		result = Math.round(approxN);
		BigInteger grandpa = BigInteger.ONE;
		BigInteger pa = BigInteger.ONE;
		result = 2;
		
		while (true) {
			BigInteger son = pa.add(grandpa);
			String sonString = son.toString();
			
			++result;
			
			if (sonString.length() > 999) {
				break;
			}
			
			grandpa = pa;
			pa = son;
			
			if (result % 1000 == 0) {
				System.out.println("result = " + result);
			}
		}
		
		// 4782
		System.out.println("result = " + result);
	}
	
	private static double fibonacci(int n) {
		double phi = (SQRT5 + 1.0) / 2.0;
		double result = Math.pow(phi, n) / SQRT5;
		
		return result;
	}
}
