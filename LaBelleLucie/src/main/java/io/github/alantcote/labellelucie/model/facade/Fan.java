/**
 * 
 */
package io.github.alantcote.labellelucie.model.facade;

import io.github.alantcote.playingcards.Card;
import javafx.collections.ObservableList;

/**
 */
public interface Fan extends ObservableList<Card> {
	/**
	 * Get the top card from the fan.
	 * 
	 * @return the top card from the fan.
	 */
	public Card getTopCard();
}
