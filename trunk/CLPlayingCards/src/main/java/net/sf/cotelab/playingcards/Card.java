package net.sf.cotelab.playingcards;

import java.io.Serializable;

/**
 * A playing card.
 * Playing cards are immutable: We wouldn't want them to change during play.
 */
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected final Rank rank;
	protected final Suit suit;
	
	/**
	 * Construct a new object, with a given rank and suit.
	 * @param rank the rank.
	 * @param suit the suit.
	 * @throws UnsupportedOperationException if an attempt is made to create a
	 * 		card with either joker rank or suit, and the other property
	 * 		appropriate to an ordinary card (e.g., a joker of spades makes no
	 * 		sense).
	 */
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
	
	/**
	 * Get the rank.
	 * @return the rank.
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Get the suit.
	 * @return the suit.
	 */
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

	/**
	 * Determine whether this card is a joker.
	 * @return the truth-value of the assertion, "this card is a joker".
	 */
	protected boolean isJoker() {
		return suit == Suit.JOKER;
	}
}
