/**
 * 
 */
package net.sf.cotelab.lbl.model.facade;

import javafx.collections.ObservableList;
import net.sf.cotelab.playingcards.Card;

/**
 * @author US80653H
 */
public interface Fan extends ObservableList<Card> {
	/**
	 * Get the top card from the fan.
	 * @return the top card from the fan.
	 */
	public Card getTopCard();
}
