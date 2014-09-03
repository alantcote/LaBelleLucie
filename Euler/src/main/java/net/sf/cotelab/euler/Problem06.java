package net.sf.cotelab.euler;

/**
 * A solution for Project Euler Problem 6.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=6">here</a>.
 * @author Al Cote'
 */
public class Problem06 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int square = squareOfSum(N);
		int sum = sumOfSquares(N);
		int difference = square - sum;
		
		System.out.println("squareOfSum = " + square);
		System.out.println("sumOfSquares = " + sum);
		System.out.println("difference = " + difference);
	}

	private static int squareOfSum(int n) {
		int sum = n * (n + 1) / 2;
		int square = sum * sum;
		
		return square;
	}

	private static int sumOfSquares(int n) {
		int sum = n * (n + 1) * ((2 * n) + 1) / 6;
		
		return sum;
	}
	
	public static final int N = 100;
}
