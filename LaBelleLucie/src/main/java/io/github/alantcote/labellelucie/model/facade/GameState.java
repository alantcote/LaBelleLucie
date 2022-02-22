package io.github.alantcote.labellelucie.model.facade;

import io.github.alantcote.labellelucie.undo.UndoManager;
import io.github.alantcote.playingcards.Deck;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

/**
 * In the Model-View-Controller pattern, this is the Model.
 */
public interface GameState {
	/**
	 * The number of foundation fans.
	 */
	public static final int FOUNDATION_FAN_COUNT = 4;
	
	/**
	 * The number of tableau fans.
	 */
	public static final int TABLEAU_FAN_COUNT = 18;
	
	/**
	 * The size of all but the last tableau fans immediately after the 1st deal.
	 */
	public static final int TABLEAU_FAN_INIT_SIZE = 3;

	/**
	 * Deal the cards from a deck to the tableau.
	 * 
	 * @param deck the deck.
	 */
	public void dealTableau(Deck deck);

	/**
	 * Get the number of draws remaining in the game.
	 * @return the drawsRemaining.
	 */
	public abstract IntegerProperty getDrawsRemaining();

	/**
	 * Get the foundation fans.
	 * @return the foundation fans.
	 */
	public abstract Fan[] getFoundation();

	/**
	 * Get the game summary.
	 * @return the game summary.
	 */
	public ObjectProperty<GameSummary> getGameSummary();

	/**
	 * Get the number of redeals remaining in the game.
	 * @return the redealsRemaining.
	 */
	public abstract IntegerProperty getRedealsRemaining();

	/**
	 * Get the stock.
	 * @return the stock.
	 */
	public abstract ObjectProperty<Deck> getStock();

	/**
	 * Get the tableau fans.
	 * @return the tableau.
	 */
	public abstract Fan[] getTableau();

	/**
	 * Get the undo manager. It is arguable that this belongs in the Controller.
	 * @return the undo manager.
	 */
	public abstract UndoManager getUndoManager();

	/**
	 * Reset for a new game.
	 */
	public abstract void reset();
}