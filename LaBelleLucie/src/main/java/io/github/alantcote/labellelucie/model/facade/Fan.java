/**
 * 
 */
package io.github.alantcote.labellelucie.model.facade;

import javafx.collections.ObservableList;
import io.github.alantcote.playingcards.Card;

/**
 */
public interface Fan extends ObservableList<Card> {
	/**
	 * Get the top card from the fan.
	 * @return the top card from the fan.
	 */
	public Card getTopCard();
}
