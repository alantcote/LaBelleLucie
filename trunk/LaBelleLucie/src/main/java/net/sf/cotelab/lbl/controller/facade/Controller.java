package net.sf.cotelab.lbl.controller.facade;

import net.sf.cotelab.lbl.model.facade.GameState;

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