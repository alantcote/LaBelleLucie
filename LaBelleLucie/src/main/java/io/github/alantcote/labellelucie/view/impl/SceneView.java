package io.github.alantcote.labellelucie.view.impl;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@link Scene} that hosts the view.
 */
public class SceneView extends Scene implements View {
	/**
	 * The outer container for the view.
	 */
	protected RootView rootView;

	/**
	 * Constructor.
	 * 
	 * @param parentStage     the {@link Stage} to host this {@link Scene}.
	 * @param model           the game model.
	 * @param theHostServices an instance of {@link HostServices}.
	 */
	public SceneView(Stage parentStage, GameState model, HostServices theHostServices) {
		super(new RootView(parentStage, model, theHostServices));

		Parent root = getRoot();

		if (root instanceof RootView) {
			rootView = (RootView) root;
		}

		getStylesheets().add(getClass().getResource("LaBelleLucie.css").toExternalForm());
	}

	/**
	 * Get the input handler.
	 * 
	 * @return the input handler.
	 * @see io.github.alantcote.labellelucie.view.impl.RootView#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return rootView.getInputHandler();
	}

	/**
	 * Set the input handler.
	 * 
	 * @param inputHandler the input handler to be set.
	 * @see io.github.alantcote.labellelucie.view.impl.RootView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		rootView.setInputHandler(inputHandler);
	}
}
