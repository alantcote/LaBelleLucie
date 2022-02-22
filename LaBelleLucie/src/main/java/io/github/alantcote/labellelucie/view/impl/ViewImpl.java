package io.github.alantcote.labellelucie.view.impl;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.handler.CloseRequestHandler;
import io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener;
import javafx.application.HostServices;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * The View implementation.
 */
public class ViewImpl implements View {
	/**
	 * The close request handler.
	 */
	protected CloseRequestHandler closeRequestHandler;
	
	/**
	 * The game result listener.
	 */
	protected GameResultListener gameResultListener;
	
	/**
	 * The input handler.
	 */
	protected InputHandler inputHandler;
	
	/**
	 * The {@link SceneView}.
	 */
	protected SceneView sceneView;
	
	/**
	 * The application window.
	 */
	protected Window window;

	/**
	 * Constructor.
	 * @param stage the application window.
	 * @param model the model.
	 * @param inputHandler the input handler.
	 * @param theHostServices the host services.
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;

		sceneView.setInputHandler(inputHandler);
		closeRequestHandler.setInputHandler(inputHandler);
		gameResultListener.setInputHandler(inputHandler);
	}
}
