package net.cote.projecteuler;

/**
 * A solution for Project Euler Problem 28.
 * The problem statement is
 * <a href="http://projecteuler.net/problem=28">here</a>.
 * @author Al Cote'
 */
public class Problem28 {
	/* Derivation:
	 * 
	 * The spiral can be viewed as a set of concentric squares. Number the
	 * squares 0, 1, ..., where the center (1) is square 0, the next square
	 * (2-9) is square 1, and so forth. Then the length of a side of square N is
	 * given by s(N) = 2 * N + 1. This gives a formula for the upper right-hand
	 * value in square N as urc(N) = ( 2 * N + 1 ) ** 2. The upper left-hand
	 * value in square N is ulc(N) = urc(N) - s(N) + 1. We also have llc(N) =
	 * urc(N) - 2 * s(N) + 1 and lrc(N) = urc(N) - 3 * S(N) + 1. So the
	 * contribution to the sum of the diagonals from square N is given by sod(N)
	 * = urc(N) + ulc(N) + llc(N) + lrc(N), except for N = 0, where sod(0) = 1.
	 * Restating, we have . . .
	 * 
	 * urc(N) = ( 2 * N + 1 ) ** 2
	 * ulc(N) = urc(N) - s(N) + 1
	 * llc(N) = urc(N) - 2 * s(N) + 2
	 * lrc(N) = urc(N) - 3 * s(N) + 3
	 * 
	 * So . . .
	 * 
	 * sod(N) = urc(N) + ulc(N) + llc(N) + lrc(N)
	 * sod(N) = urc(N) + urc(N) - s(N) + 1 + urc(N) - 2 * s(N) + 2 + urc(N) - 3 * s(N) + 3
	 * 
	 * Simplifying, . . .
	 * 
	 * sod(N) = 4 * urc(N) - 6 * s(N) + 6
	 * 
	 * Substituting, . . .
	 * 
	 * sod(N) = 4 * ( ( 2 * N + 1 ) ** 2 ) - 6 * ( 2 * N + 1 ) + 6
	 * 
	 * Simplifying, . . .
	 * 
	 * sod(N) = 4 * ( ( 2 * N + 1 ) ** 2 ) - ( 12 * N + 6 ) + 6
	 * sod(N) = 4 * ( ( 2 * N + 1 ) ** 2 ) - 12 * N - 6 + 6
	 * sod(N) = 4 * ( ( 2 * N + 1 ) ** 2 ) - 12 * N
	 * sod(N) = 4 * ( 4 * N ** 2 + 4 * N + 1 ) - 12 * N
	 * sod(N) = 16 * N ** 2 + 16 * N + 4 - 12 * N
	 * sod(N) = 16 * N ** 2 + 4 * N + 4
	 * 
	 * But, to really solve the problem, we need 500 squares beyond the center.
	 * So the value will be . . .
	 * 
	 * SOD = 1 + sum[N = 1 to 500](sod(N))
	 * SOD = 1 + 4 * sum[N = 1 to 500](4 * N ** 2 + N + 1)
	 * 
	 * So, let's calculate it!
	 */
	/**
	 * @param args unused.
	 */
	public static void main(String[] args) {
		int result = 1;
		
		for (int n = 1; n <= 500; ++n) {
			int sodN = sod(n);
			
			result += sodN;
		}
		
		// 669171001
		System.out.println("result = " + result);
	}
	
	private static int sod(int n) {
		int result = 4 + n * (4 + 16 * n);
		
		return result;
	}
}
