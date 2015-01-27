package net.sf.cotelab.playingcards.lbl.controller.facade;

import net.sf.cotelab.playingcards.lbl.model.facade.GameState;

public interface Controller {
	/**
	 * @return the inputHandler
	 */
	public abstract InputHandler getInputHandler();

	/**
	 * @return the model
	 */
	public abstract GameState getModel();
}