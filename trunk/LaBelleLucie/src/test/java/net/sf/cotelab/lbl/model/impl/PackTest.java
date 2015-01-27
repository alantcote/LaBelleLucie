package net.sf.cotelab.lbl.model.impl;

import static org.junit.Assert.*;
import net.sf.cotelab.lbl.model.impl.Pack;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class PackTest extends jMockTestHelper {

	@Test
	public void testPack() {
		final Pack mockPack = context.mock(Pack.class);
		final Card mockCard = context.mock(Card.class);
		
		context.checking(new Expectations() {{
			oneOf(mockPack).newCard(with(Rank.ACE), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.DEUCE), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TREY), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FOUR), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FIVE), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SIX), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SEVEN), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.EIGHT), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.NINE), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TEN), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.JACK), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.QUEEN), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.KING), with(Suit.CLUB));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));
			
			oneOf(mockPack).newCard(with(Rank.ACE), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.DEUCE), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TREY), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FOUR), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FIVE), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SIX), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SEVEN), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.EIGHT), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.NINE), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TEN), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.JACK), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.QUEEN), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.KING), with(Suit.DIAMOND));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));
			
			oneOf(mockPack).newCard(with(Rank.ACE), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.DEUCE), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TREY), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FOUR), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FIVE), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SIX), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SEVEN), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.EIGHT), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.NINE), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TEN), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.JACK), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.QUEEN), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.KING), with(Suit.HEART));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));
			
			oneOf(mockPack).newCard(with(Rank.ACE), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.DEUCE), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TREY), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FOUR), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.FIVE), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SIX), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.SEVEN), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.EIGHT), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.NINE), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.TEN), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.JACK), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.QUEEN), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));

			oneOf(mockPack).newCard(with(Rank.KING), with(Suit.SPADE));
			will(returnValue(mockCard));
			
			oneOf(mockPack).add(with(mockCard));
			will(returnValue(mockPack));
			
		}});
		
		Pack fixture = new Pack() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.Deck#add(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			public Deck add(Card card) {
				return mockPack.add(card);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.lbl.model.Pack#newCard(net.sf.cotelab.playingcards.Rank, net.sf.cotelab.playingcards.Suit)
			 */
			@Override
			protected Card newCard(Rank rank, Suit suit) {
				return mockPack.newCard(rank, suit);
			}
		};
		
		assertNotNull(fixture);
	}
}
