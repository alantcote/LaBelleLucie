package io.github.alantcote.labellelucie.view.impl.handler;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * A handler for close requests.
 */
public class CloseRequestHandler implements EventHandler<WindowEvent> {
	/**
	 * The {@link InputHandler} to which to forward the request.
	 */
	protected InputHandler inputHandler;

	/**
	 * Construct a new object.
	 * @param inputHandler the {@link InputHandler}.
	 */
	public CloseRequestHandler(InputHandler inputHandler) {
		super();

		this.inputHandler = inputHandler;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * This method forwards the close request to {@link #inputHandler}.
	 */
	@Override
	public void handle(WindowEvent arg0) {
		inputHandler.onExitRequest();
	}

	/**
	 * Set a new input handler.
	 * @param inputHandler the new value.
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
}
