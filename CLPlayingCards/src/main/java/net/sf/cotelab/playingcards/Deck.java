package net.sf.cotelab.playingcards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A deck of playing cards.
 * This class does not presuppose any particular composition, other than that
 * the cards are playing cards. A subclass or external code must establish the
 * nature of the deck (i.e., the game for which it is intended) after initial
 * construction, generally by multiple invocations of the <code>add()</code> method.
 */
public class Deck {
	protected ArrayList<Card> master = new ArrayList<>();
	protected ArrayList<Card> slave = new ArrayList<>();

	/**
	 * Construct a new object.
	 */
	public Deck() {
		super();
	}
	
	/**
	 * Add a card to the deck.
	 * @param card the card.
	 * @return this object.
	 */
	public Deck add(Card card) {
		master.add(card);

		slave = new ArrayList<>(master);
		
		return this;
	}
	
	/**
	 * Deal a random card from this deck.
	 * @return a random card from this deck.
	 */
	public Card deal() {
		return slave.remove(0);
	}
	
	/**
	 * Determine whether there are any more cards to deal.
	 * @return the truth-value of the assertion, "there are no more cards to
	 * 		deal".
	 */
	public boolean isEmpty() {
		return slave.isEmpty();
	}
	
	/**
	 * Reconstitute the deck with the added cards, and shuffle it.
	 * @return this object.
	 */
	public Deck shuffle() {
		slave = new ArrayList<>(master);
		
		Collections.shuffle(slave);
		
		return this;
	}
}
