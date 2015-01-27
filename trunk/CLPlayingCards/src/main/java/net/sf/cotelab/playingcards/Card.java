package net.sf.cotelab.playingcards;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected final Rank rank;
	protected final Suit suit;
	
	public Card(Rank rank, Suit suit) {
		super();
		
		this.rank = rank;
		this.suit = suit;
		
		if (isJoker()) {
			if ((rank != Rank.JOKER_HIGH) && (rank != Rank.JOKER_LOW)) {
				throw new UnsupportedOperationException(
						"Inconsistent parameters");
			}
		}

		if ((rank == Rank.JOKER_HIGH) || (rank == Rank.JOKER_LOW)) {
			if (!isJoker()) {
				throw new UnsupportedOperationException(
						"Inconsistent parameters");
			}
		}
	}
	
	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Card (rank = " + rank + ", suit = " + suit + ")";
	}

	protected boolean isJoker() {
		return suit == Suit.JOKER;
	}
}
