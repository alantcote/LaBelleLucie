package net.sf.cotelab.lbl.view.impl.handler;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import net.sf.cotelab.lbl.controller.facade.InputHandler;

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
