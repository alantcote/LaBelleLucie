package net.sf.cotelab.playingcards;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {
	protected Deck fixture;
	protected Rank[] ranks = {
			Rank.DEUCE,
			Rank.TREY,
			Rank.FOUR,
			Rank.FIVE,
			Rank.SIX,
			Rank.SEVEN,
			Rank.EIGHT,
			Rank.NINE,
			Rank.TEN,
			Rank.JACK,
			Rank.QUEEN,
			Rank.KING,
			Rank.ACE
	};
	protected Suit[] suits = {
			Suit.CLUB,
			Suit.DIAMOND,
			Suit.HEART,
			Suit.SPADE
	};

	@Before
	public void setUp() throws Exception {
		fixture = new Deck();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		Card card = new Card(Rank.ACE, Suit.CLUB);
		Deck actual;
		
		assertTrue(fixture.master.isEmpty());
		
		actual = fixture.add(card);
		
		assertEquals(fixture, actual);
		
		assertEquals(1, fixture.master.size());
		assertEquals(card, fixture.master.get(0));
		
		assertEquals(1, fixture.slave.size());
		assertEquals(card, fixture.slave.get(0));
	}

	@Test
	public void testDeal() {
		populate();

		for (Suit suit : suits) {
			for (Rank rank : ranks) {
				Card card = fixture.deal();
				
				assertEquals(rank, card.getRank());
				assertEquals(suit, card.getSuit());
			}
		}
		
		assertTrue(fixture.isEmpty());
	}
	
	@Test
	public void testDeck() {
		assertNotNull(fixture);
		
		assertNotNull(fixture.master);
		assertTrue(fixture.master instanceof ArrayList);
		assertTrue(fixture.master.isEmpty());
		
		assertNotNull(fixture.slave);
		assertTrue(fixture.slave instanceof ArrayList);
		assertTrue(fixture.slave.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(fixture.isEmpty());
	}

	@Test
	public void testShuffle() {
		HashSet<Card> remainingCards = new HashSet<>();
		HashSet<Card> dealtCards = new HashSet<>();
		
		populate();

		remainingCards.addAll(fixture.master);
		
		fixture.shuffle();
		
		while (!fixture.isEmpty()) {
			Card dealtCard = fixture.deal();
			
			assertTrue(remainingCards.contains(dealtCard));
			assertFalse(dealtCards.contains(dealtCard));
			
			remainingCards.remove(dealtCard);
			dealtCards.add(dealtCard);
		}
		
		assertTrue(remainingCards.isEmpty());
		assertEquals(fixture.master.size(), dealtCards.size());
	}

	protected void populate() {
		for (Suit suit : suits) {
			for (Rank rank : ranks) {
				fixture.add(new Card(rank, suit));
			}
		}
	}
}
