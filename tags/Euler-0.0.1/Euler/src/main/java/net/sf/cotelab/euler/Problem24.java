package net.sf.cotelab.euler;

import java.util.Arrays;
import java.util.Comparator;

import net.sf.cotelab.util.math.Factorial;
import net.sf.cotelab.util.math.RawPermutationIterator;

/**
 * A solution for Project Euler Problem 24.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=24">here</a>.
 * @author Al Cote'
 */
public class Problem24 {
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		RawPermutationIterator rpi = new RawPermutationIterator(PERM_ORDER);
		int[][] perm = new int[PERM_COUNT][];
		int permIndex = 0;
		String result = "";

		System.out.println("generating permutations . . .");
		while (rpi.hasNext()) {
			int[] p = rpi.next();
			
			perm[permIndex++] = p;
			
//			System.out.println(Arrays.toString(p));
		}

		System.out.println("sorting permutations . . .");
		Arrays.sort(perm, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int result = 0;
				
				// all the permutations are of length PERM_ORDER
				for (int i = 0; i < o1.length; ++i) {
					result = o1[i] - o2[i];
					
					if (result != 0) {
						break;
					}
				}
				
				return result;
			}});

		System.out.println("formatting result . . .");
		for (int i = 0; i < PERM_ORDER; ++i) {
			result += Integer.toString(perm[999999][i]);
		}
		
		// 2783915460
		System.out.println("result = " + result);
	}
	public static final int PERM_ORDER = 10;
	
	public static final int PERM_COUNT = Factorial.intFactorial(PERM_ORDER);
}
