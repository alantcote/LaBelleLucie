package net.sf.cotelab.playingcards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	protected ArrayList<Card> master = new ArrayList<>();
	protected ArrayList<Card> slave = new ArrayList<>();

	public Deck() {
		super();
	}
	
	public Deck add(Card card) {
		master.add(card);

		slave = new ArrayList<>(master);
		
		return this;
	}
	
	public Card deal() {
		return slave.remove(0);
	}
	
	public boolean isEmpty() {
		return slave.isEmpty();
	}
	
	public Deck shuffle() {
		slave = new ArrayList<>(master);
		
		Collections.shuffle(slave);
		
		return this;
	}
}
