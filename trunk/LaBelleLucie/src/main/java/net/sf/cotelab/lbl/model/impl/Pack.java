package net.sf.cotelab.lbl.model.impl;

import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

/**
 * A pack of cards suitable for playing La Belle Lucie.
 */
public class Pack extends Deck {
	public static final Rank[] RANKS = {
		Rank.ACE,
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
		Rank.KING
	};
	public static final Suit[] SUITS = {
		Suit.CLUB,
		Suit.DIAMOND,
		Suit.HEART,
		Suit.SPADE
	};

	public Pack() {
		super();

		for (Suit suit : SUITS) {
			for (Rank rank : RANKS) {
				add(newCard(rank, suit));
			}
		}
	}

	protected Card newCard(Rank rank, Suit suit) {
		return new Card(rank, suit);
	}
}
