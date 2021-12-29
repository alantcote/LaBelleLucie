package net.sf.cotelab.lbl.view.impl;

import javafx.application.HostServices;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.lbl.view.impl.handler.CloseRequestHandler;
import net.sf.cotelab.lbl.view.impl.listeners.GameResultListener;

public class ViewImpl implements View {
	protected CloseRequestHandler closeRequestHandler;
	protected GameResultListener gameResultListener;
	protected InputHandler inputHandler;
	protected SceneView sceneView;
	protected Window window;

	public ViewImpl(Stage stage, GameState model, InputHandler inputHandler, HostServices theHostServices) {
		super();
		
		sceneView = new SceneView(stage, model, theHostServices);
		stage.setScene(sceneView);
		
		window = stage;
		
		gameResultListener = new GameResultListener(window);
		model.getGameSummary().addListener(gameResultListener);
		
		closeRequestHandler = new CloseRequestHandler(inputHandler);
		
		stage.setOnCloseRequest(closeRequestHandler);
		
		setInputHandler(inputHandler);
	}

	@Override
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		
		sceneView.setInputHandler(inputHandler);
		closeRequestHandler.setInputHandler(inputHandler);
		gameResultListener.setInputHandler(inputHandler);
	}
}
