package net.sf.cotelab.playingcards.decks;

import java.util.Arrays;

import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

/**
 * A deck comprised of the 52 "normal" cards.
 * This deck incorporates 13 ranks of each of 4 suits.
 * @author cote
 */
public class BridgeDeck extends Deck {
	/**
	 * Construct a new object.
	 */
	public BridgeDeck() {
		super();
		
		addCards();
	}
	
	/**
	 * Add the cards to the deck.
	 */
	protected void addCards() {
		// omit jokers
		Suit[] suits = Arrays.copyOf(Suit.values(), Suit.JOKER.ordinal());
		Rank[] ranks = Arrays.copyOf(Rank.values(), Rank.JOKER_LOW.ordinal());
		
		for (Suit suit : suits) {
			for (Rank rank : ranks) {
				Card card = newCard(rank, suit);
				
				add(card);
			}
		}
	}
	
	/**
	 * Create a new card.
	 * @param rank the rank of the card.
	 * @param suit the suit of the card.
	 * @return the new card.
	 */
	protected Card newCard(Rank rank, Suit suit) {
		return new Card(rank, suit);
	}
}
