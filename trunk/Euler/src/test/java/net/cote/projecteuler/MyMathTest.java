package net.cote.projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyMathTest {
	@Test
	public void testEnglishNumber() {
		long[] value = {
				Long.MIN_VALUE,
				-111111111111111111L,
				-111111111111111L,
				-111111111111L,
				-111111111L,
				-111111L,
				-111L,
				0L,
				111L,
				111111L,
				111111111L,
				111111111111L,
				111111111111111L,
				111111111111111111L,
				Long.MAX_VALUE
		};
		String[] expected = {
				"minus nine quintillion two hundred and twenty-three quadrillion three hundred and seventy-two trillion thirty-six billion eight hundred and fifty-four million seven hundred and seventy-five thousand eight hundred and eight",
				"minus one hundred and eleven quadrillion one hundred and eleven trillion one hundred and eleven billion one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"minus one hundred and eleven trillion one hundred and eleven billion one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"minus one hundred and eleven billion one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"minus one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"minus one hundred and eleven thousand one hundred and eleven",
				"minus one hundred and eleven",
				"zero",
				"one hundred and eleven",
				"one hundred and eleven thousand one hundred and eleven",
				"one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"one hundred and eleven billion one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"one hundred and eleven trillion one hundred and eleven billion one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"one hundred and eleven quadrillion one hundred and eleven trillion one hundred and eleven billion one hundred and eleven million one hundred and eleven thousand one hundred and eleven",
				"nine quintillion two hundred and twenty-three quadrillion three hundred and seventy-two trillion thirty-six billion eight hundred and fifty-four million seven hundred and seventy-five thousand eight hundred and seven"
		};
		
		for (int i = 0; i < value.length; ++i) {
			String actual = MyMath.englishNumber(value[i]);
			
			assertEquals(expected[i], actual);
		}
		
//		System.out.println(
//				"Long.MAX_VALUE = " + MyMath.englishNumber(Long.MAX_VALUE));
//		System.out.println(
//				"Long.MIN_VALUE = " + MyMath.englishNumber(Long.MIN_VALUE));
	}

	@Test
	public void testEnglishNumber1_19() {
		long[] value = {
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18,
				19
		};
		String[] expected = {
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
		
		for (int i = 0; i < value.length; ++i) {
			String actual = MyMath.englishNumber1_19(value[i]);
			
			assertEquals(expected[i], actual);
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber1_19(0);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber1_19(20);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
	}

	@Test
	public void testEnglishNumber1_99() {
		long[] value = {
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18,
				19,
				21,
				32,
				43,
				54,
				60,
				65,
				76,
				87,
				98
		};
		String[] expected = {
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
				"nineteen",
				"twenty-one",
				"thirty-two",
				"forty-three",
				"fifty-four",
				"sixty",
				"sixty-five",
				"seventy-six",
				"eighty-seven",
				"ninety-eight"
		};
		
		for (int i = 0; i < value.length; ++i) {
			String actual = MyMath.englishNumber1_99(value[i]);
			
			assertEquals(expected[i], actual);
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber1_99(0);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber1_99(100);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
	}

	@Test
	public void testEnglishNumber1_999() {
		long[] value = {
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18,
				19,
				21,
				32,
				43,
				54,
				60,
				65,
				76,
				87,
				98,
				111,
				222,
				333,
				444,
				555,
				666,
				777,
				888,
				999
		};
		String[] expected = {
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
				"nineteen",
				"twenty-one",
				"thirty-two",
				"forty-three",
				"fifty-four",
				"sixty",
				"sixty-five",
				"seventy-six",
				"eighty-seven",
				"ninety-eight",
				"one hundred and eleven",
				"two hundred and twenty-two",
				"three hundred and thirty-three",
				"four hundred and forty-four",
				"five hundred and fifty-five",
				"six hundred and sixty-six",
				"seven hundred and seventy-seven",
				"eight hundred and eighty-eight",
				"nine hundred and ninety-nine"
		};
		
		for (int i = 0; i < value.length; ++i) {
			String actual = MyMath.englishNumber1_999(value[i]);
			
			assertEquals(expected[i], actual);
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber1_999(0);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber1_999(1000);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
	}

	@Test
	public void testEnglishNumber100_999() {
		long[] value = {
				111,
				222,
				333,
				444,
				555,
				666,
				777,
				888,
				999
		};
		String[] expected = {
				"one hundred and eleven",
				"two hundred and twenty-two",
				"three hundred and thirty-three",
				"four hundred and forty-four",
				"five hundred and fifty-five",
				"six hundred and sixty-six",
				"seven hundred and seventy-seven",
				"eight hundred and eighty-eight",
				"nine hundred and ninety-nine"
		};
		
		for (int i = 0; i < value.length; ++i) {
			String actual = MyMath.englishNumber100_999(value[i]);
			
			assertEquals(expected[i], actual);
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber100_999(99);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber100_999(1000);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
	}
	
	@Test
	public void testEnglishNumber20_99() {
		long[] value = {
				21,
				32,
				43,
				54,
				60,
				65,
				76,
				87,
				98
		};
		String[] expected = {
				"twenty-one",
				"thirty-two",
				"forty-three",
				"fifty-four",
				"sixty",
				"sixty-five",
				"seventy-six",
				"eighty-seven",
				"ninety-eight"
		};
		
		for (int i = 0; i < value.length; ++i) {
			String actual = MyMath.englishNumber20_99(value[i]);
			
			assertEquals(expected[i], actual);
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber20_99(19);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
		
		try {
			@SuppressWarnings("unused")
			String actual = MyMath.englishNumber20_99(100);
			fail("no exception");
		} catch (IllegalArgumentException e) {
			// nothing - this is expected
		}
	}
	
	@Test
	public void testFactorial() {
		double[] expected = {
				1,
				1,
				2,
				6,
				24,
				120
		};
		
		for (int i = 0; i < expected.length; ++i) {
			double actual = MyMath.factorial((double) i);
			
			assertEquals(expected[i], actual, 0.000000001);
		}
	}
	
	@Test
	public void testFactors() {
		long prod[] = {
				1,
				3,
				6,
				10,
				15,
				21,
				28
		};
		long fact[][] = {
				{ 1 },
				{ 1 },
				{ 1, 2, 3 },
				{ 1, 2, 5 },
				{ 1, 3, 5 },
				{ 1, 3, 7 },
				{ 1, 2, 4, 7, 14 }
		};
		
		for (int i = 0; i < prod.length; ++i) {
			long[] actual = MyMath.factors(prod[i]);
			
			assertEquals(MyMath.stringify(fact[i]), MyMath.stringify(actual));
		}
	}
	
	@Test
	public void testGcd() {
		long[][] params = {
				{4, 16},
				{16, 4},
				{15, 60},
				{15, 65},
				{1052, 52}
		};
		long[] expected = {
				4,
				4,
				15,
				5,
				4
		};
		
		for (int i = 0; i < expected.length; ++i) {
			long actual = MyMath.gcd(params[i][0], params[i][1]);
			
			assertEquals("(with i = " + i + ")", expected[i], actual);
		}
	}
	
	@Test
	public void testLcm() {
		long[][] params = {
				{4, 16},
				{16, 4},
				{15, 60},
				{15, 65},
				{1052, 52}
		};
		long[] expected = {
				16,
				16,
				60,
				195,
				13676
		};
		
		for (int i = 0; i < expected.length; ++i) {
			long actual = MyMath.lcm(params[i][0], params[i][1]);
			
			assertEquals("(with i = " + i + ")", expected[i], actual);
		}
	}
	
	@Test
	public void testProperDivisors() {
		long prod[] = {
				1,
				3,
				6,
				10,
				15,
				16,
				21,
				28
		};
		long fact[][] = {
				{ 1 },
				{ 1 },
				{ 1, 2, 3 },
				{ 1, 2, 5 },
				{ 1, 3, 5 },
				{ 1, 2, 4, 8 },
				{ 1, 3, 7 },
				{ 1, 2, 4, 7, 14 }
		};
		
		for (int i = 0; i < prod.length; ++i) {
			long[] actual = MyMath.properDivisors(prod[i]);
			
			assertEquals(MyMath.stringify(fact[i]), MyMath.stringify(actual));
		}
	}
	
	@Test
	public void testMaxPathSum() {
		long[][] triangle = {
				{ 3 },
				{ 7, 4 },
				{ 2, 4, 6 },
				{ 8, 5, 9, 3 }
		};
		long expected = 23;
		long actual = MyMath.maxPathSum(triangle);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testProduct() {
		long prod[] = {
				1,
				3,
				36,
				100,
				225,
				441,
				21952
		};
		long fact[][] = {
				{ 1 },
				{ 1, 3 },
				{ 1, 2, 3, 6 },
				{ 1, 2, 5, 10 },
				{ 1, 3, 5, 15 },
				{ 1, 3, 7, 21 },
				{ 1, 2, 4, 7, 14, 28 }
		};
		
		for (int i = 0; i < prod.length; ++i) {
			long actual = MyMath.product(fact[i]);
			
			assertEquals(prod[i], actual);
		}
	}

	@Test
	public void testStringify() {
		String[] expected = {
				"{ }",
				"{ 1 }",
				"{ 1, 3 }",
				"{ 1, 2, 3, 6 }",
				"{ 1, 2, 5, 10 }",
				"{ 1, 3, 5, 15 }",
				"{ 1, 3, 7, 21 }",
				"{ 1, 2, 4, 7, 14, 28 }"
		};
		long fact[][] = {
				{ },
				{ 1 },
				{ 1, 3 },
				{ 1, 2, 3, 6 },
				{ 1, 2, 5, 10 },
				{ 1, 3, 5, 15 },
				{ 1, 3, 7, 21 },
				{ 1, 2, 4, 7, 14, 28 }
		};
		
		for (int i = 0; i < expected.length; ++i) {
			String actual = MyMath.stringify(fact[i]);
			
			assertEquals(expected[i], actual);
		}
	}

	@Test
	public void testSum() {
		long sum[] = {
				1,
				4,
				12,
				18,
				24,
				32,
				56
		};
		long term[][] = {
				{ 1 },
				{ 1, 3 },
				{ 1, 2, 3, 6 },
				{ 1, 2, 5, 10 },
				{ 1, 3, 5, 15 },
				{ 1, 3, 7, 21 },
				{ 1, 2, 4, 7, 14, 28 }
		};
		
		for (int i = 0; i < sum.length; ++i) {
			long actual = MyMath.sum(term[i]);
			
			assertEquals(sum[i], actual);
		}
	}
	
	@Test
	public void testZeller() {
		int month = 5; // May
		int day = 3;
		int year = 2013;
		int expected = 6; // Friday
		int actual = MyMath.zeller(year, month, day);
		
		assertEquals(expected, actual);
	}
}
