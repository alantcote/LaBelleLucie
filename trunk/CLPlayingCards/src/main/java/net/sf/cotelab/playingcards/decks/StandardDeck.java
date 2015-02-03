package net.sf.cotelab.playingcards.decks;

import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

public class StandardDeck extends BridgeDeck {
	public StandardDeck() {
		super();
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.decks.BridgeDeck#addCards()
	 */
	@Override
	protected void addCards() {
		super.addCards();
		
		add(newCard(Rank.JOKER_LOW, Suit.JOKER));
		add(newCard(Rank.JOKER_HIGH, Suit.JOKER));
	}
}
