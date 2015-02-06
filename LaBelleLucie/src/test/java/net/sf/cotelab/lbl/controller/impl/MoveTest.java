package net.sf.cotelab.lbl.controller.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {
	@Test
	public void testGetDestFanIndex() {
		int expected = 1;
		int actual;
		Move fixture = new Move(expected, 0, MoveType.TABLEAU_TO_FOUNDATION);
		
		actual = fixture.destFanIndex;
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetSrcFanIndex() {
		int expected = 1;
		int actual;
		Move fixture = new Move(0, expected, MoveType.TABLEAU_TO_FOUNDATION);
		
		actual = fixture.srcFanIndex;
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetType() {
		MoveType expected = MoveType.TABLEAU_TO_FOUNDATION;
		MoveType actual;
		Move fixture = new Move(0, 0, expected);
		
		actual = fixture.type;
		
		assertEquals(expected, actual);
	}

	@Test
	public void testMove() {
		int expectedDestIndex = 1;
		int expectedSrcIndex = 2;
		MoveType expectedType = MoveType.TABLEAU_TO_FOUNDATION;
		Move fixture =
				new Move(expectedDestIndex, expectedSrcIndex, expectedType);
		int actualDestIndex = fixture.destFanIndex;
		int actualSrcIndex = fixture.srcFanIndex;
		MoveType actualType = fixture.type;
		
		assertEquals(expectedDestIndex, actualDestIndex);
		assertEquals(expectedSrcIndex, actualSrcIndex);
		assertEquals(expectedType, actualType);
	}

	@Test
	public void testMove_badArgs_draw() {
		Move fixture;
		
		try {
			fixture = new Move(0, 1, MoveType.DRAW);
			
			fail("fixture accepted a non-zero source index");
		} catch (IllegalArgumentException e) {
			// NOTHING - this is how this test is passed.
		}

		try {
			fixture = new Move(1, 0, MoveType.DRAW);
			
			fail("fixture accepted a non-zero destination index");
		} catch (IllegalArgumentException e) {
			// NOTHING - this is how this test is passed.
		}

		fixture = new Move(0, 0, MoveType.DRAW);
		
		assertNotNull(fixture);
	}

	@Test
	public void testMove_badArgs_reshuffle() {
		Move fixture;
		
		try {
			fixture = new Move(0, 1, MoveType.RESHUFFLE);
			
			fail("fixture accepted a non-zero source index");
		} catch (IllegalArgumentException e) {
			// NOTHING - this is how this test is passed.
		}

		try {
			fixture = new Move(1, 0, MoveType.RESHUFFLE);
			
			fail("fixture accepted a non-zero destination index");
		} catch (IllegalArgumentException e) {
			// NOTHING - this is how this test is passed.
		}

		fixture = new Move(0, 0, MoveType.RESHUFFLE);
		
		assertNotNull(fixture);
	}

	@Test
	public void testMove_badArgs_toTableau() {
		Move fixture;
		
		try {
			fixture = new Move(0, 0, MoveType.TABLEAU_TO_TABLEAU);
			
			fail("fixture accepted matching source and destination indexes");
		} catch (IllegalArgumentException e) {
			// NOTHING - this is how this test is passed.
		}

		fixture = new Move(0, 1, MoveType.TABLEAU_TO_TABLEAU);
		
		assertNotNull(fixture);
	}
}
