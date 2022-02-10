package io.github.alantcote.labellelucie.controller.facade;

import io.github.alantcote.labellelucie.model.facade.GameState;

/**
 * Interface for a controller in this application's MVC architecture.
 */
public interface Controller {
	/**
	 * Get this controller's {@link InputHandler}.
	 * 
	 * @return the inputHandler
	 */
	public abstract InputHandler getInputHandler();

	/**
	 * Get the model this controller is controlling.
	 * 
	 * @return the model
	 */
	public abstract GameState getModel();
}