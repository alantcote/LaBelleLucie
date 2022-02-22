package io.github.alantcote.labellelucie.view.facade;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;

/**
 * A component of the scene graph that is the view.
 */
public interface View {
	/**
	 * Set the input handler.
	 * 
	 * @param inputHandler the input handler.
	 */
	public void setInputHandler(InputHandler inputHandler);
}
