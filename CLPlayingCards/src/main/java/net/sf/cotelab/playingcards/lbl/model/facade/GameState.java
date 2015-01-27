package net.sf.cotelab.playingcards.lbl.model.facade;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.util.UndoManager;

public interface GameState {
	public static final int FOUNDATION_FAN_COUNT = 4;
	public static final int TABLEAU_FAN_COUNT = 18;
	public static final int TABLEAU_FAN_INIT_SIZE = 3;
	
	/**
	 * Deal the cards from a deck to the tableau.
	 * @param deck the deck.
	 */
	public void dealTableau(Deck deck);

	/**
	 * @return the drawsRemaining
	 */
	public abstract IntegerProperty getDrawsRemaining();

	/**
	 * @return the foundation
	 */
	public abstract Fan[] getFoundation();

	/**
	 * @return the game summary
	 */
	public ObjectProperty<GameSummary> getGameSummary();

	/**
	 * @return the redealsRemaining
	 */
	public abstract IntegerProperty getRedealsRemaining();

	/**
	 * @return the stock
	 */
	public abstract ObjectProperty<Deck> getStock();

	/**
	 * @return the tableau
	 */
	public abstract Fan[] getTableau();

	/**
	 * @return the undo manager
	 */
	public abstract UndoManager getUndoManager();

	/**
	 * Reset for a new game.
	 */
	public abstract void reset();
}