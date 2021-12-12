package net.sf.cotelab.util.math;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Some math functions.
 * @author US80653H
 */
public class MathFunctions {
	public static final String AND = "and";
	public static final String[] DIGIT_GROUP = {
		"thousand",
		"million",
		"billion",
		"trillion",
		"quadrillion",
		"quintillion"
	};
	public static final String HUNDRED = "hundred";
	public static final String HYPHEN = "-";
	public static final String MINUS = "minus";
	public static final String[] NUMBER_WORDS_1_19 = {
		"one",
		"two",
		"three",
		"four",
		"five",
		"six",
		"seven",
		"eight",
		"nine",
		"ten",
		"eleven",
		"twelve",
		"thirteen",
		"fourteen",
		"fifteen",
		"sixteen",
		"seventeen",
		"eighteen",
		"nineteen"
	};
	public static final String[] NUMBER_WORDS_TENS = {
		"twenty",
		"thirty",
		"forty",
		"fifty",
		"sixty",
		"seventy",
		"eighty",
		"ninety"
	};
	public static final String SPACE = " ";
	public static final String ZERO = "zero";
	
	/**
	 * Get the word phrase that communicates a number as it is spoken in
	 * England.
	 * @param number the number.
	 * @return the word phrase.
	 */
	public static String englishNumber(long number) {
		String result = "";
		long dividend;
		long quotient;
		long remainder;
		int groupNbr = 0;
		
		if (number == 0L) {
			return ZERO;
		}
		
		// handle carefully, here, because (-Long.MAX_VALUE) cannot be stored in
		// a variable of type long.
		dividend = number;
		quotient = dividend / 1000L;
		remainder = dividend % 1000L;
		
		if (dividend < 0) {
			quotient = 0L - quotient;
			remainder = 0L - remainder;
		}
		
		if (remainder > 0) {
			result = englishNumber1_999(remainder);
		}
		
		while (quotient > 0) {
			dividend = quotient;
			quotient = dividend / 1000L;
			remainder = dividend % 1000L;
			
			if (remainder > 0) {
				String group = englishNumber1_999(remainder) +
						SPACE + DIGIT_GROUP[groupNbr];
				
				result = (result.length() == 0) ?
						group : group + SPACE + result;
				++groupNbr;
			}
		}
		
		if (number < 0) {
			result = MINUS + SPACE + result;
		}
		
		return result;
	}
	
	/**
	 * Get the word phrase that communicates a number (<code>1 &lt;= number &lt;=
	 * 19</code>) as it is spoken in England.
	 * @param number the number.
	 * @return the word phrase.
	 */
	public static String englishNumber1_19(long number) {
		if (number < 1) {
			throw new IllegalArgumentException("number < 1");
		}

		if (number > 19) {
			throw new IllegalArgumentException("number > 19");
		}
		
		return NUMBER_WORDS_1_19[(int) number - 1];
	}
	
	/**
	 * Get the word phrase that communicates a number (<code>1 &lt;= number &lt;=
	 * 99</code>) as it is spoken in England.
	 * @param number the number.
	 * @return the word phrase.
	 */
	public static String englishNumber1_99(long number) {
		String phrase = null;
		
		if (number < 20) {
			phrase = englishNumber1_19(number);
		} else {
			phrase = englishNumber20_99(number);
		}
		
		return phrase;
	}
	
	/**
	 * Get the word phrase that communicates a number (<code>1 &lt;= number &lt;=
	 * 999</code>) as it is spoken in England.
	 * @param number the number.
	 * @return the word phrase.
	 */
	public static String englishNumber1_999(long number) {
		String phrase = null;
		
		if (number < 100) {
			phrase = englishNumber1_99(number);
		} else {
			phrase = englishNumber100_999(number);
		}
		
		return phrase;
	}
	
	/**
	 * Get the word phrase that communicates a number (<code>100 &lt;= number
	 * &lt;= 999</code>) as it is spoken in England.
	 * @param number the number.
	 * @return the word phrase.
	 */
	public static String englishNumber100_999(long number) {
		String phrase = null;
		long hundreds;
		long remainder;
		
		if (number < 100) {
			throw new IllegalArgumentException("number < 100");
		}

		if (number > 999) {
			throw new IllegalArgumentException("number > 999");
		}
		
		hundreds = number / 100;
		remainder = number % 100;
		phrase = englishNumber1_19(hundreds) + SPACE + HUNDRED;
		
		if (remainder != 0) {
			phrase =
					phrase + SPACE + AND + SPACE + englishNumber1_99(remainder);
		}
		
		return phrase;
	}
	
	/**
	 * Get the word phrase that communicates a number (<code>20 &lt;= number &lt;=
	 * 99</code>) as it is spoken in England.
	 * @param number the number.
	 * @return the word phrase.
	 */
	public static String englishNumber20_99(long number) {
		String phrase = null;
		long tens;
		long units;
		
		if (number < 20) {
			throw new IllegalArgumentException("number < 20");
		}

		if (number > 99) {
			throw new IllegalArgumentException("number > 99");
		}
		
		tens = number / 10;
		units = number % 10;
		phrase = NUMBER_WORDS_TENS[(int) tens - 2];
		
		if (units != 0) {
			phrase = phrase + HYPHEN + englishNumber1_19(units);
		}
		
		return phrase;
	}
	
	/**
	 * Calculate the factorial of a number.
	 * This calculation uses <code>double</code> to handle larger values of base.
	 * @param base the number whose factorial is to be calculated.
	 * @return the factorial of <code>base</code>.
	 */
	public static double factorial(double base) {
		double result = 1;
		
		if (base >= 1) {
			result = base * factorial(base - 1);
		} else {
			result = 1;
		}
		
		return result;
	}
	
	/**
	 * Find the factors of a number.
	 * @param product the number.
	 * @return the factors.
	 */
	public static long[] factors(long product) {
		LinkedList<Long> factList = new LinkedList<Long>();
		long[] theFactors = null;
		long maxFactor = (long) Math.sqrt(product);

		for (long factor = 1; factor <= maxFactor; ++factor) {
			if (product % factor == 0) {
				factList.add(factor);
			}
		}
		
		if (product > 1) {
			int smallFactorCount = factList.size();
			
			for (int i = 0; i < smallFactorCount; ++i) {
				long factor = factList.get(i);
				
				if (factor > 1) {
					factList.add(product / factor);
				}
			}
		}
		
		theFactors = new long[factList.size()];
		for (int i = 0; i < theFactors.length; ++i) {
			theFactors[i] = factList.get(i);
		}
		
		Arrays.sort(theFactors);
		
		return theFactors;
	}
	
	/**
	 * Calculate the greatest common divisor for two numbers.
	 * @param p one number.
	 * @param q the other number.
	 * @return the greatest common divisor for p and q.
	 */
	public static long gcd(long p, long q) {
		if (q == 0) {
			return p;
		}
		
		return (gcd(q, p % q));
	}
	
	/**
	 * Calculate the least common multiple for two numbers.
	 * @param p one number.
	 * @param q the other number.
	 * @return the least common multiple for p and q.
	 */
	public static long lcm(long p, long q) {
		return (p * q) / gcd(p, q);
	}

	/**
	 * Find the maximum sum of the numbers found on a path from top to bottom of
	 * a triangle of numbers, where each step on a path may be chosen by moving
	 * down and left or down and right.
	 * <p>
	 * For example, given the triangle . . .
	 * <pre><code>
	 *    3
	 *   7 4
	 *  2 4 6
	 * 8 5 9 3
	 * </code></pre>
	 * . . . the maximum sum, 23, is the sum of the numbers on the path, { 3, 7,
	 * 4, 9 }.
	 * @param triangle the triangle.
	 * @return the maximum sum.
	 */
	public static long maxPathSum(long[][] triangle) {
		long[] sum =
				Arrays.copyOf(triangle[triangle.length - 1], triangle.length);
		
		for (int row = triangle.length - 2; row >= 0; --row) {
			long[] newSum = Arrays.copyOf(triangle[row], row + 1);
			
			for (int col = 0; col < newSum.length; ++col) {
				newSum[col] += Math.max(sum[col], sum[col + 1]);
			}
			
			sum = newSum;
		}
		
		return sum[0];
	}

	/**
	 * Calculate the product of the elements of a given array of numbers.
	 * @param seq the given array of numbers.
	 * @return the product.
	 */
	public static long product(long[] seq) {
		long prod = 1L;
		
		for (int i = 0; i < seq.length; ++i) {
			prod *= seq[i];
		}
		
		return prod;
	}

	/**
	 * Find the proper divisors of a number.
	 * <p>
	 * A proper divisor is an even factor of a number, not including the number,
	 * itself.
	 * @param number the number to be factored.
	 * @return the proper divisors.
	 */
	public static long[] properDivisors(long number) {
		LinkedList<Long> pdList = new LinkedList<Long>();
		long[] result;
		
		if (number == 1) {
			result = new long[1];
			
			result[0] = 1;
		} else {
			for (long i = 1; i < number; ++i) {
				if ((number % i) == 0) {
					pdList.add(i);
				}
			}
			
			result = new long[pdList.size()];
			for (int i = 0; i < result.length; ++i) {
				result[i] = pdList.get(i);
			}
		}
		
		return result;
	}

	/**
	 * Convert an array of numbers into a string of the form, "{ e0, e1, . . .,
	 * e2 }".
	 * @param seq the array of numbers.
	 * @return the string.
	 */
	public static String stringify(long[] seq) {
		String aString = "{";
		
		if (seq.length > 0) {
			aString = "{ " + seq[0];
			
			for (int i = 1; i < seq.length; ++i) {
				aString = aString + ", " + seq[i];
			}
		}
		
		aString = aString + " }";
		
		return aString;
	}

	/**
	 * Calculate the sum of the elements of a given array of numbers.
	 * @param seq the given array of numbers.
	 * @return the sum.
	 */
	public static long sum(long[] seq) {
		long sum = 0L;
		
		for (int i = 0; i < seq.length; ++i) {
			sum += seq[i];
		}
		
		return sum;
	}

	/**
	 * Calculate the day of the week by Zeller's Congruence.
	 * @param year the 4-digit year, AD.
	 * @param month the month (1=January, 2=February).
	 * @param day the day of the month.
	 * @return the day of the week (0=Saturday, 1=Sunday).
	 */
	public static int zeller(int year, int month, int day) {
		int q = day;
		int m;
		int j;
		int k;
		int term0, term1, term2, term3, term4, term5;
		
		if (month < 3) {
			m = month + 12;
			--year;
		} else {
			m = month;
		}
		
		j = year / 100;
		k = year - (j * 100);
		
		term0 = q;
		term1 = (13 * (m + 1)) / 5;
		term2 = k;
		term3 = k / 4;
		term4 = j / 4;
		term5 = 5 * j;
		
		return (term0 + term1 + term2 + term3 + term4 + term5) % 7;
	}
}
