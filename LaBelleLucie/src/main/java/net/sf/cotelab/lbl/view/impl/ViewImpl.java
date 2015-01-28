package net.sf.cotelab.lbl.view.impl;

import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.lbl.view.impl.handler.CloseRequestHandler;
import net.sf.cotelab.lbl.view.impl.listeners.GameResultListener;

public class ViewImpl implements View {
	protected CloseRequestHandler closeRequestHandler;
	protected InputHandler inputHandler;
	protected SceneView sceneView;
	protected Window window;

	public ViewImpl(Stage stage, GameState model) {
		super();
		
		sceneView = new SceneView(model);
		stage.setScene(sceneView);
		
		window = stage;
		
		model.getGameSummary().addListener(new GameResultListener(window));
		
		closeRequestHandler = new CloseRequestHandler(inputHandler);
		
		stage.setOnCloseRequest(closeRequestHandler);
	}

	@Override
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		
		sceneView.setInputHandler(inputHandler);
		closeRequestHandler.setInputHandler(inputHandler);
	}
}
