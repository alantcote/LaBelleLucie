package net.sf.cotelab.euler;

/**
 * A solution for Project Euler Problem 14.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=14">here</a>.
 * @author Al Cote'
 */
public class Problem14 {

	private static long collatzSeqLen(long startTerm) {
		long length = 1;
		
		for (long term = startTerm; term != 1; term = nextCollatzTerm(term)) {
			++length;
		}
		
		return length;
	}
	
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		long longestLength = 0;
		long longestStartTerm = 0;
		
		for (long startTerm = 1; startTerm < 1000000; ++startTerm) {
			long length = collatzSeqLen(startTerm);
			
			if (length > longestLength) {
				longestLength = length;
				longestStartTerm = startTerm;
			}
		}
		
		System.out.println(
				"Starting term " + longestStartTerm +
				" yields a sequence of length " + longestLength);
	}
	
	private static long nextCollatzTerm(long term) {
		long nextTerm;
		
		if (term % 2 == 0) {
			nextTerm = term / 2;
		} else {
			nextTerm = 1 + term * 3;
		}
		
		return nextTerm;
	}
}
