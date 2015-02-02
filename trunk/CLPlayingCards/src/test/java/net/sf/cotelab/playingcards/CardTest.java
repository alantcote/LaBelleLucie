package net.sf.cotelab.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {
	@Test
	public void testCard() {
		Rank expectedRank;
		Suit expectedSuit;
		Card fixture;
		
		// check normal operation
		expectedRank = Rank.ACE;
		expectedSuit = Suit.CLUB;
		fixture = new Card(expectedRank, expectedSuit);
		assertEquals(Rank.ACE, fixture.rank);
		assertEquals(Suit.CLUB, fixture.suit);
		
		// check for valid joker
		expectedRank = Rank.JOKER_HIGH;
		expectedSuit = Suit.JOKER;
		fixture = new Card(expectedRank, expectedSuit);
		assertEquals(Rank.JOKER_HIGH, fixture.rank);
		assertEquals(Suit.JOKER, fixture.suit);
		
		// check for invalid joker
		try {
			expectedRank = Rank.JOKER_HIGH;
			expectedSuit = Suit.CLUB;
			fixture = new Card(expectedRank, expectedSuit);
			fail("intended exception not thrown");
		}
		catch (UnsupportedOperationException e) {
			// NOTHING - this is a positive result
		}
		try {
			expectedRank = Rank.ACE;
			expectedSuit = Suit.JOKER;
			fixture = new Card(expectedRank, expectedSuit);
			fail("intended exception not thrown");
		}
		catch (UnsupportedOperationException e) {
			// NOTHING - this is a positive result
		}
	}

	@Test
	public void testGetRank() {
		Rank expectedRank;
		Suit expectedSuit;
		Card fixture;

		expectedRank = Rank.ACE;
		expectedSuit = Suit.CLUB;
		fixture = new Card(expectedRank, expectedSuit);
		assertEquals(Rank.ACE, fixture.getRank());
	}

	@Test
	public void testGetSuit() {
		Rank expectedRank;
		Suit expectedSuit;
		Card fixture;

		expectedRank = Rank.ACE;
		expectedSuit = Suit.CLUB;
		fixture = new Card(expectedRank, expectedSuit);
		assertEquals(Suit.CLUB, fixture.getSuit());
	}

	@Test
	public void testIsJoker() {
		Rank expectedRank;
		Suit expectedSuit;
		Card fixture;

		expectedRank = Rank.JOKER_HIGH;
		expectedSuit = Suit.JOKER;
		fixture = new Card(expectedRank, expectedSuit);
		assertTrue(fixture.isJoker());

		expectedRank = Rank.ACE;
		expectedSuit = Suit.CLUB;
		fixture = new Card(expectedRank, expectedSuit);
		assertFalse(fixture.isJoker());
	}
}
