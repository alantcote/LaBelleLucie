package net.cote.projecteuler;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * A solution for Project Euler Problem 23.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=23">here</a>.
 * @author Al Cote'
 */
public class Problem23 {
	public static final int LIMIT_2_ABUNDANTS = 28124;
	public static final int MINIMUM_2_ABUNDANTS = 24;
	public static final int MINIMUM_ABUNDANT = 12;
	public static final int RANGE_2_ABUNDANTS =
			LIMIT_2_ABUNDANTS - MINIMUM_2_ABUNDANTS;
	
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		Integer[] abundants = listInterestingAbundantNumbers();
		boolean[] canBeSum = new boolean[LIMIT_2_ABUNDANTS];
		int sum = 0;
		
		Arrays.fill(canBeSum, false);
		
		for (int i = 0; i < abundants.length; ++i) {
			int a = abundants[i];
			
			for (int j = 0; j < abundants.length; ++j) {
				int b = abundants[j];
				int s = a + b;
				
				if (s < LIMIT_2_ABUNDANTS) {
					canBeSum[s] = true;
				}
			}
		}
		
		for (int i = 0; i < LIMIT_2_ABUNDANTS; ++i) {
			if (!canBeSum[i]) {
				sum += i;
				
				if (sum < 0) {
					System.err.println("overflow: sum = " + sum);
					System.exit(1);
				}
			}
		}
		
		// 4179871
		System.out.println("sum = " + sum);
	}
	
	private static Integer[] listInterestingAbundantNumbers() {
		int interestingAbundantMax = LIMIT_2_ABUNDANTS;
		LinkedList<Integer> interestingAbundantList = new LinkedList<Integer>();
		Integer[] result;
		int count;
		
		for (int i = MINIMUM_ABUNDANT; i < interestingAbundantMax; ++i) {
			boolean abundant = isAbundant(i);
			
			if (abundant) {
				interestingAbundantList.add(i);
			}
		}
		
		result = interestingAbundantList.toArray(new Integer[0]);
		
		count = result.length;
		System.out.println("number of interesting abundant numbers = " + count);
		
		return result;
	}

	private static boolean isAbundant(int number) {
		long[] properDivisors = MyMath.properDivisors(number);
		long sum = MyMath.sum(properDivisors);
		boolean abundant = (sum > number);
		
//		System.out.println("number = " + number + "; abundant = " + abundant +
//				"; sum = " + sum + "; properDivisors = " +
//				MyMath.stringify(properDivisors));
		
		return abundant;
	}
}
