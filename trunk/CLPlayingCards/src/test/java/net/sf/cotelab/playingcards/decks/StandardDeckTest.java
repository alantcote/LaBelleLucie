package net.sf.cotelab.playingcards.decks;

import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class StandardDeckTest extends jMockTestHelper {
	@Test
	public void testAddCards() {
		final StandardDeck mockStandardDeck =
				context.mock(StandardDeck.class, "mockStandardDeck");
		final Suit[] suits = Arrays.copyOf(Suit.values(), Suit.JOKER.ordinal());
		final Rank[] ranks = Arrays.copyOf(Rank.values(), Rank.JOKER_LOW.ordinal());
		final Card[] cards = new Card[2 + suits.length * ranks.length];
		
		for (int i = 0; i < cards.length; ++i) {
			Card card = context.mock(Card.class, "cards[" + i + "]");
			
			cards[i] = card;
		}
		
		context.checking( new Expectations() {{
			oneOf(mockStandardDeck).newCard(Rank.ACE, Suit.CLUB);
			will(returnValue(cards[0]));
			
			oneOf(mockStandardDeck).add(cards[0]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.DEUCE, Suit.CLUB);
			will(returnValue(cards[1]));
			
			oneOf(mockStandardDeck).add(cards[1]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TREY, Suit.CLUB);
			will(returnValue(cards[2]));
			
			oneOf(mockStandardDeck).add(cards[2]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FOUR, Suit.CLUB);
			will(returnValue(cards[3]));
			
			oneOf(mockStandardDeck).add(cards[3]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FIVE, Suit.CLUB);
			will(returnValue(cards[4]));
			
			oneOf(mockStandardDeck).add(cards[4]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SIX, Suit.CLUB);
			will(returnValue(cards[5]));
			
			oneOf(mockStandardDeck).add(cards[5]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SEVEN, Suit.CLUB);
			will(returnValue(cards[6]));
			
			oneOf(mockStandardDeck).add(cards[6]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.EIGHT, Suit.CLUB);
			will(returnValue(cards[7]));
			
			oneOf(mockStandardDeck).add(cards[7]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.NINE, Suit.CLUB);
			will(returnValue(cards[8]));
			
			oneOf(mockStandardDeck).add(cards[8]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TEN, Suit.CLUB);
			will(returnValue(cards[9]));
			
			oneOf(mockStandardDeck).add(cards[9]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.JACK, Suit.CLUB);
			will(returnValue(cards[10]));
			
			oneOf(mockStandardDeck).add(cards[10]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.QUEEN, Suit.CLUB);
			will(returnValue(cards[11]));
			
			oneOf(mockStandardDeck).add(cards[11]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.KING, Suit.CLUB);
			will(returnValue(cards[12]));
			
			oneOf(mockStandardDeck).add(cards[12]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.ACE, Suit.DIAMOND);
			will(returnValue(cards[13]));
			
			oneOf(mockStandardDeck).add(cards[13]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.DEUCE, Suit.DIAMOND);
			will(returnValue(cards[14]));
			
			oneOf(mockStandardDeck).add(cards[14]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TREY, Suit.DIAMOND);
			will(returnValue(cards[15]));
			
			oneOf(mockStandardDeck).add(cards[15]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FOUR, Suit.DIAMOND);
			will(returnValue(cards[16]));
			
			oneOf(mockStandardDeck).add(cards[16]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FIVE, Suit.DIAMOND);
			will(returnValue(cards[17]));
			
			oneOf(mockStandardDeck).add(cards[17]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SIX, Suit.DIAMOND);
			will(returnValue(cards[18]));
			
			oneOf(mockStandardDeck).add(cards[18]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SEVEN, Suit.DIAMOND);
			will(returnValue(cards[19]));
			
			oneOf(mockStandardDeck).add(cards[19]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.EIGHT, Suit.DIAMOND);
			will(returnValue(cards[20]));
			
			oneOf(mockStandardDeck).add(cards[20]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.NINE, Suit.DIAMOND);
			will(returnValue(cards[21]));
			
			oneOf(mockStandardDeck).add(cards[21]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TEN, Suit.DIAMOND);
			will(returnValue(cards[22]));
			
			oneOf(mockStandardDeck).add(cards[22]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.JACK, Suit.DIAMOND);
			will(returnValue(cards[23]));
			
			oneOf(mockStandardDeck).add(cards[23]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.QUEEN, Suit.DIAMOND);
			will(returnValue(cards[24]));
			
			oneOf(mockStandardDeck).add(cards[24]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.KING, Suit.DIAMOND);
			will(returnValue(cards[25]));
			
			oneOf(mockStandardDeck).add(cards[25]);
			will(returnValue(mockStandardDeck));
			
			oneOf(mockStandardDeck).newCard(Rank.ACE, Suit.HEART);
			will(returnValue(cards[26]));
			
			oneOf(mockStandardDeck).add(cards[26]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.DEUCE, Suit.HEART);
			will(returnValue(cards[27]));
			
			oneOf(mockStandardDeck).add(cards[27]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TREY, Suit.HEART);
			will(returnValue(cards[28]));
			
			oneOf(mockStandardDeck).add(cards[28]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FOUR, Suit.HEART);
			will(returnValue(cards[29]));
			
			oneOf(mockStandardDeck).add(cards[29]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FIVE, Suit.HEART);
			will(returnValue(cards[30]));
			
			oneOf(mockStandardDeck).add(cards[30]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SIX, Suit.HEART);
			will(returnValue(cards[31]));
			
			oneOf(mockStandardDeck).add(cards[31]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SEVEN, Suit.HEART);
			will(returnValue(cards[32]));
			
			oneOf(mockStandardDeck).add(cards[32]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.EIGHT, Suit.HEART);
			will(returnValue(cards[33]));
			
			oneOf(mockStandardDeck).add(cards[33]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.NINE, Suit.HEART);
			will(returnValue(cards[34]));
			
			oneOf(mockStandardDeck).add(cards[34]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TEN, Suit.HEART);
			will(returnValue(cards[35]));
			
			oneOf(mockStandardDeck).add(cards[35]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.JACK, Suit.HEART);
			will(returnValue(cards[36]));
			
			oneOf(mockStandardDeck).add(cards[36]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.QUEEN, Suit.HEART);
			will(returnValue(cards[37]));
			
			oneOf(mockStandardDeck).add(cards[37]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.KING, Suit.HEART);
			will(returnValue(cards[38]));
			
			oneOf(mockStandardDeck).add(cards[38]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.ACE, Suit.SPADE);
			will(returnValue(cards[39]));
			
			oneOf(mockStandardDeck).add(cards[39]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.DEUCE, Suit.SPADE);
			will(returnValue(cards[40]));
			
			oneOf(mockStandardDeck).add(cards[40]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TREY, Suit.SPADE);
			will(returnValue(cards[41]));
			
			oneOf(mockStandardDeck).add(cards[41]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FOUR, Suit.SPADE);
			will(returnValue(cards[42]));
			
			oneOf(mockStandardDeck).add(cards[42]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.FIVE, Suit.SPADE);
			will(returnValue(cards[43]));
			
			oneOf(mockStandardDeck).add(cards[43]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SIX, Suit.SPADE);
			will(returnValue(cards[44]));
			
			oneOf(mockStandardDeck).add(cards[44]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.SEVEN, Suit.SPADE);
			will(returnValue(cards[45]));
			
			oneOf(mockStandardDeck).add(cards[45]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.EIGHT, Suit.SPADE);
			will(returnValue(cards[46]));
			
			oneOf(mockStandardDeck).add(cards[46]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.NINE, Suit.SPADE);
			will(returnValue(cards[47]));
			
			oneOf(mockStandardDeck).add(cards[47]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.TEN, Suit.SPADE);
			will(returnValue(cards[48]));
			
			oneOf(mockStandardDeck).add(cards[48]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.JACK, Suit.SPADE);
			will(returnValue(cards[49]));
			
			oneOf(mockStandardDeck).add(cards[49]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.QUEEN, Suit.SPADE);
			will(returnValue(cards[50]));
			
			oneOf(mockStandardDeck).add(cards[50]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.KING, Suit.SPADE);
			will(returnValue(cards[51]));
			
			oneOf(mockStandardDeck).add(cards[51]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.JOKER_LOW, Suit.JOKER);
			will(returnValue(cards[52]));
			
			oneOf(mockStandardDeck).add(cards[52]);
			will(returnValue(mockStandardDeck));

			oneOf(mockStandardDeck).newCard(Rank.JOKER_HIGH, Suit.JOKER);
			will(returnValue(cards[53]));
			
			oneOf(mockStandardDeck).add(cards[53]);
			will(returnValue(mockStandardDeck));
		}});
		
		StandardDeck fixture = new StandardDeck() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.Deck#add(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			public Deck add(Card card) {
				return mockStandardDeck.add(card);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.decks.BridgeDeck#newCard(net.sf.cotelab.playingcards.Rank, net.sf.cotelab.playingcards.Suit)
			 */
			@Override
			protected Card newCard(Rank rank, Suit suit) {
				return mockStandardDeck.newCard(rank, suit);
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testStandardDeck() {
		final StandardDeck mockStandardDeck =
				context.mock(StandardDeck.class, "mockStandardDeck");
		
		context.checking( new Expectations() {{
			oneOf(mockStandardDeck).addCards();
		}});
		
		StandardDeck fixture = new StandardDeck() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.decks.StandardDeck#addCards()
			 */
			@Override
			protected void addCards() {
				mockStandardDeck.addCards();
			}
		};
		
		assertNotNull(fixture);
	}
}
