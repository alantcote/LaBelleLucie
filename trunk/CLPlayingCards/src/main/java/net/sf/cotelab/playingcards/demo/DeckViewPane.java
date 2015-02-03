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

/**
 * A node that presents the full set of card images.
 * @author cote
 */
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

	/**
	 * Construct a new object.
	 */
	public DeckViewPane() {
		super();
		
		setHgap(5);
		setPadding(newInsets(5));
		setPrefColumns(13);
		setVgap(5);

		Deck deck = createDeck();
		ObservableList<Node> kids = getChildren();
		CardView backView = cardViewFactory.getBackView(
				newCard(Rank.JOKER_HIGH, Suit.JOKER));
		
		// add the cards' front images
		while (!deck.isEmpty()) {
			Card card = deck.deal();
			CardView view = cardViewFactory.getFrontView(card);
			
			kids.add(view);
		}
		
		// add the deck's back image
		kids.add(backView);
	}

	/**
	 * Create a deck, populated with an exhaustive set of cards.
	 * @return the new deck.
	 */
	protected Deck createDeck() {
		Deck deck = newDeck();
		
		// add the normal cards
		for (Suit suit : SUITS) {
			for (Rank rank : RANKS) {
				deck.add(newCard(rank, suit));
			}
		}
		
		// add the jokers
		deck.add(newCard(Rank.JOKER_LOW, Suit.JOKER));
		deck.add(newCard(Rank.JOKER_HIGH, Suit.JOKER));
		
		return deck;
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
	
	/**
	 * Create a new factory for card views.
	 * @return the new factory.
	 */
	protected CardViewFactory newCardViewFactory() {
		return new CardViewFactory();
	}
	
	/**
	 * Create a new deck.
	 * @return the new deck.
	 */
	protected Deck newDeck() {
		return new Deck();
	}
	
	/**
	 * Create a new <tt>Insets</tt> object.
	 * @param topRightBottomLeft the inset to apply on all sides.
	 * @return the new <tt>Insets</tt> object.
	 */
	protected Insets newInsets(double topRightBottomLeft) {
		return new Insets(topRightBottomLeft);
	}
}
