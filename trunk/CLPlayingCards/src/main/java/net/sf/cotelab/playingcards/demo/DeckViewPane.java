package net.sf.cotelab.playingcards.demo;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.playingcards.decks.StandardDeck;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

/**
 * A node that presents the full set of card images.
 * @author cote
 */
public class DeckViewPane extends TilePane {
	public static final int DEFAULT_COLUMNS = 13;
	public static final double DEFAULT_HGAP = 5;
	public static final double DEFAULT_INSET = 5;
	public static final double DEFAULT_VGAP = 5;
	
	protected CardViewFactory cardViewFactory = newCardViewFactory();

	/**
	 * Construct a new object.
	 */
	public DeckViewPane() {
		super();
		
		inizLayoutParams();

		addKids();
	}

	protected void addKids() {
		Deck deck = newDeck();
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

	protected void inizLayoutParams() {
		setHgap(DEFAULT_HGAP);
		setPadding(newInsets(DEFAULT_INSET));
		setPrefColumns(DEFAULT_COLUMNS);
		setVgap(DEFAULT_VGAP);
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
		return new StandardDeck();
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
