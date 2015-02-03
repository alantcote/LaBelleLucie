package net.sf.cotelab.playingcards.decks;

import java.util.Arrays;

import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

public class BridgeDeck extends Deck {
	public BridgeDeck() {
		super();
		
		addCards();
	}
	
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
	
	protected Card newCard(Rank rank, Suit suit) {
		return new Card(rank, suit);
	}
}
