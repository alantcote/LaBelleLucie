package net.sf.cotelab.playingcards.demo;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

public class DeckViewPane extends TilePane {
	public static final Rank[] RANKS = {
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
		Rank.KING,
		Rank.ACE
	};
	public static final Suit[] SUITS = {
		Suit.CLUB,
		Suit.DIAMOND,
		Suit.HEART,
		Suit.SPADE
	};
	
	protected CardViewFactory cardViewFactory = newCardViewFactory();

	public DeckViewPane() {
		super();
		
		setHgap(5);
		setPadding(new Insets(5));
		setPrefColumns(13);
		setVgap(5);

		Deck deck = createDeck();
		ObservableList<Node> kids = getChildren();
		CardView backView = cardViewFactory.getBackView(
				newCard(Suit.JOKER, Rank.JOKER_HIGH));
		
		// add the cards' front images
		while (!deck.isEmpty()) {
			Card card = deck.deal();
			CardView view = cardViewFactory.getFrontView(card);
			
			kids.add(view);
		}
		
		// add the deck's back image
		kids.add(backView);
	}

	protected Deck createDeck() {
		Deck deck = newDeck();
		
		// add the normal card images
		for (Suit suit : SUITS) {
			for (Rank rank : RANKS) {
				deck.add(newCard(suit, rank));
			}
		}
		
		// add the joker images
		deck.add(newCard(Suit.JOKER, Rank.JOKER_LOW));
		deck.add(newCard(Suit.JOKER, Rank.JOKER_HIGH));
		
		return deck;
	}
	
	protected Card newCard(Suit suit, Rank rank) {
		return new Card(rank, suit);
	}
	
	protected CardViewFactory newCardViewFactory() {
		return new CardViewFactory();
	}
	
	protected Deck newDeck() {
		return new Deck();
	}
}
