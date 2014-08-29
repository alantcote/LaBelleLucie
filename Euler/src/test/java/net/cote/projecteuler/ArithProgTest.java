package net.cote.projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArithProgTest {
	@Test
	public void testArithProg() {
		ArithProg fixtureLong = new ArithProg(1L, 2L);
		
		assertEquals(1L, fixtureLong.first);
		assertEquals(2L, fixtureLong.delta);
	}
	
	@Test
	public void testGetTerm() {
		long first = 7;
		long delta = 9;
		ArithProg fixture = new ArithProg(first, delta);
		
		for (long i = 0; i < 10; ++i) {
			long expected = first + i * delta;
			long actual = fixture.getTerm(i);
			
			assertEquals(expected, actual);
		}
	}
	
	@Test
	public void testSumNTerms() {
		ArithProg fixture = new ArithProg(1L, 1L);
		long[] expected = { 1, 3, 6, 10, 15, 21, 28, 36, 45, 55 };
		
		for (int i = 1; i <= expected.length; ++i) {
			long actual = fixture.sumNTerms(i);
			
			assertEquals(expected[i - 1], actual);
		}
	}
}
