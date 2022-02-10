package io.github.alantcote.labellelucie.view.impl;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.handler.CloseRequestHandler;
import io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener;
import javafx.application.HostServices;
import javafx.stage.Stage;
import javafx.stage.Window;

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
