package io.github.alantcote.labellelucie.view.impl.handler;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class CloseRequestHandler implements EventHandler<WindowEvent> {
	protected InputHandler inputHandler;

	public CloseRequestHandler(InputHandler inputHandler) {
		super();

		this.inputHandler = inputHandler;
	}

	@Override
	public void handle(WindowEvent arg0) {
		inputHandler.onExitRequest();
	}

	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
}
