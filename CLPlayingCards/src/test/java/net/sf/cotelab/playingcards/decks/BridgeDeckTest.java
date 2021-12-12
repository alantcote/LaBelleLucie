package net.sf.cotelab.playingcards.decks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

public class BridgeDeckTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@Before
	public void runBeforeTests() throws Exception {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
	}
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
	
	@Test
	public void testAddCards() {
		final BridgeDeck mockBridgeDeck =
				context.mock(BridgeDeck.class, "mockBridgeDeck");
		final Suit[] suits = Arrays.copyOf(Suit.values(), Suit.JOKER.ordinal());
		final Rank[] ranks = Arrays.copyOf(Rank.values(), Rank.JOKER_LOW.ordinal());
		final Card[] cards = new Card[suits.length * ranks.length];
		
		for (int i = 0; i < cards.length; ++i) {
			Card card = context.mock(Card.class, "cards[" + i + "]");
			
			cards[i] = card;
		}
		
		context.checking( new Expectations() {{
			oneOf(mockBridgeDeck).newCard(Rank.ACE, Suit.CLUB);
			will(returnValue(cards[0]));
			
			oneOf(mockBridgeDeck).add(cards[0]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.DEUCE, Suit.CLUB);
			will(returnValue(cards[1]));
			
			oneOf(mockBridgeDeck).add(cards[1]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TREY, Suit.CLUB);
			will(returnValue(cards[2]));
			
			oneOf(mockBridgeDeck).add(cards[2]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FOUR, Suit.CLUB);
			will(returnValue(cards[3]));
			
			oneOf(mockBridgeDeck).add(cards[3]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FIVE, Suit.CLUB);
			will(returnValue(cards[4]));
			
			oneOf(mockBridgeDeck).add(cards[4]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SIX, Suit.CLUB);
			will(returnValue(cards[5]));
			
			oneOf(mockBridgeDeck).add(cards[5]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SEVEN, Suit.CLUB);
			will(returnValue(cards[6]));
			
			oneOf(mockBridgeDeck).add(cards[6]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.EIGHT, Suit.CLUB);
			will(returnValue(cards[7]));
			
			oneOf(mockBridgeDeck).add(cards[7]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.NINE, Suit.CLUB);
			will(returnValue(cards[8]));
			
			oneOf(mockBridgeDeck).add(cards[8]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TEN, Suit.CLUB);
			will(returnValue(cards[9]));
			
			oneOf(mockBridgeDeck).add(cards[9]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.JACK, Suit.CLUB);
			will(returnValue(cards[10]));
			
			oneOf(mockBridgeDeck).add(cards[10]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.QUEEN, Suit.CLUB);
			will(returnValue(cards[11]));
			
			oneOf(mockBridgeDeck).add(cards[11]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.KING, Suit.CLUB);
			will(returnValue(cards[12]));
			
			oneOf(mockBridgeDeck).add(cards[12]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.ACE, Suit.DIAMOND);
			will(returnValue(cards[13]));
			
			oneOf(mockBridgeDeck).add(cards[13]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.DEUCE, Suit.DIAMOND);
			will(returnValue(cards[14]));
			
			oneOf(mockBridgeDeck).add(cards[14]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TREY, Suit.DIAMOND);
			will(returnValue(cards[15]));
			
			oneOf(mockBridgeDeck).add(cards[15]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FOUR, Suit.DIAMOND);
			will(returnValue(cards[16]));
			
			oneOf(mockBridgeDeck).add(cards[16]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FIVE, Suit.DIAMOND);
			will(returnValue(cards[17]));
			
			oneOf(mockBridgeDeck).add(cards[17]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SIX, Suit.DIAMOND);
			will(returnValue(cards[18]));
			
			oneOf(mockBridgeDeck).add(cards[18]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SEVEN, Suit.DIAMOND);
			will(returnValue(cards[19]));
			
			oneOf(mockBridgeDeck).add(cards[19]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.EIGHT, Suit.DIAMOND);
			will(returnValue(cards[20]));
			
			oneOf(mockBridgeDeck).add(cards[20]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.NINE, Suit.DIAMOND);
			will(returnValue(cards[21]));
			
			oneOf(mockBridgeDeck).add(cards[21]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TEN, Suit.DIAMOND);
			will(returnValue(cards[22]));
			
			oneOf(mockBridgeDeck).add(cards[22]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.JACK, Suit.DIAMOND);
			will(returnValue(cards[23]));
			
			oneOf(mockBridgeDeck).add(cards[23]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.QUEEN, Suit.DIAMOND);
			will(returnValue(cards[24]));
			
			oneOf(mockBridgeDeck).add(cards[24]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.KING, Suit.DIAMOND);
			will(returnValue(cards[25]));
			
			oneOf(mockBridgeDeck).add(cards[25]);
			will(returnValue(mockBridgeDeck));
			
			oneOf(mockBridgeDeck).newCard(Rank.ACE, Suit.HEART);
			will(returnValue(cards[26]));
			
			oneOf(mockBridgeDeck).add(cards[26]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.DEUCE, Suit.HEART);
			will(returnValue(cards[27]));
			
			oneOf(mockBridgeDeck).add(cards[27]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TREY, Suit.HEART);
			will(returnValue(cards[28]));
			
			oneOf(mockBridgeDeck).add(cards[28]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FOUR, Suit.HEART);
			will(returnValue(cards[29]));
			
			oneOf(mockBridgeDeck).add(cards[29]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FIVE, Suit.HEART);
			will(returnValue(cards[30]));
			
			oneOf(mockBridgeDeck).add(cards[30]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SIX, Suit.HEART);
			will(returnValue(cards[31]));
			
			oneOf(mockBridgeDeck).add(cards[31]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SEVEN, Suit.HEART);
			will(returnValue(cards[32]));
			
			oneOf(mockBridgeDeck).add(cards[32]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.EIGHT, Suit.HEART);
			will(returnValue(cards[33]));
			
			oneOf(mockBridgeDeck).add(cards[33]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.NINE, Suit.HEART);
			will(returnValue(cards[34]));
			
			oneOf(mockBridgeDeck).add(cards[34]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TEN, Suit.HEART);
			will(returnValue(cards[35]));
			
			oneOf(mockBridgeDeck).add(cards[35]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.JACK, Suit.HEART);
			will(returnValue(cards[36]));
			
			oneOf(mockBridgeDeck).add(cards[36]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.QUEEN, Suit.HEART);
			will(returnValue(cards[37]));
			
			oneOf(mockBridgeDeck).add(cards[37]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.KING, Suit.HEART);
			will(returnValue(cards[38]));
			
			oneOf(mockBridgeDeck).add(cards[38]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.ACE, Suit.SPADE);
			will(returnValue(cards[39]));
			
			oneOf(mockBridgeDeck).add(cards[39]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.DEUCE, Suit.SPADE);
			will(returnValue(cards[40]));
			
			oneOf(mockBridgeDeck).add(cards[40]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TREY, Suit.SPADE);
			will(returnValue(cards[41]));
			
			oneOf(mockBridgeDeck).add(cards[41]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FOUR, Suit.SPADE);
			will(returnValue(cards[42]));
			
			oneOf(mockBridgeDeck).add(cards[42]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.FIVE, Suit.SPADE);
			will(returnValue(cards[43]));
			
			oneOf(mockBridgeDeck).add(cards[43]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SIX, Suit.SPADE);
			will(returnValue(cards[44]));
			
			oneOf(mockBridgeDeck).add(cards[44]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.SEVEN, Suit.SPADE);
			will(returnValue(cards[45]));
			
			oneOf(mockBridgeDeck).add(cards[45]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.EIGHT, Suit.SPADE);
			will(returnValue(cards[46]));
			
			oneOf(mockBridgeDeck).add(cards[46]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.NINE, Suit.SPADE);
			will(returnValue(cards[47]));
			
			oneOf(mockBridgeDeck).add(cards[47]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.TEN, Suit.SPADE);
			will(returnValue(cards[48]));
			
			oneOf(mockBridgeDeck).add(cards[48]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.JACK, Suit.SPADE);
			will(returnValue(cards[49]));
			
			oneOf(mockBridgeDeck).add(cards[49]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.QUEEN, Suit.SPADE);
			will(returnValue(cards[50]));
			
			oneOf(mockBridgeDeck).add(cards[50]);
			will(returnValue(mockBridgeDeck));

			oneOf(mockBridgeDeck).newCard(Rank.KING, Suit.SPADE);
			will(returnValue(cards[51]));
			
			oneOf(mockBridgeDeck).add(cards[51]);
			will(returnValue(mockBridgeDeck));
		}});
		
		BridgeDeck fixture = new BridgeDeck() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.Deck#add(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			public Deck add(Card card) {
				return mockBridgeDeck.add(card);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.decks.BridgeDeck#newCard(net.sf.cotelab.playingcards.Rank, net.sf.cotelab.playingcards.Suit)
			 */
			@Override
			protected Card newCard(Rank rank, Suit suit) {
				return mockBridgeDeck.newCard(rank, suit);
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testBridgeDeck() {
		final BridgeDeck mockBridgeDeck =
				context.mock(BridgeDeck.class, "mockBridgeDeck");
		
		context.checking( new Expectations() {{
			oneOf(mockBridgeDeck).addCards();
		}});
		
		BridgeDeck fixture = new BridgeDeck() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.decks.BridgeDeck#addCards()
			 */
			@Override
			protected void addCards() {
				mockBridgeDeck.addCards();
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testNewCard() {
		// This is a wrapper for a constructor; needs no testing.
		assertTrue(true);
	}
}
