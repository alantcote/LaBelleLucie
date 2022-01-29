/**
 * 
 */
package io.github.alantcote.labellelucie.model.facade;

import javafx.collections.ObservableList;
import net.sf.cotelab.playingcards.Card;

/**
 */
public interface Fan extends ObservableList<Card> {
	/**
	 * Get the top card from the fan.
	 * @return the top card from the fan.
	 */
	public Card getTopCard();
}
