package io.github.alantcote.labellelucie.controller.facade;

import io.github.alantcote.labellelucie.model.facade.GameState;

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