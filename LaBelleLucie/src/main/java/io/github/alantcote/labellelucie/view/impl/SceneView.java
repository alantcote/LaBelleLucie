package io.github.alantcote.labellelucie.view.impl;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneView extends Scene implements View {
	protected RootView rootView;

	public SceneView(Stage parentStage, GameState model, HostServices theHostServices) {
		super(new RootView(parentStage, model, theHostServices));
		
		Parent root = getRoot();
		
		if (root instanceof RootView) {
			rootView = (RootView) root;
		}
		
		getStylesheets().add(
				getClass().getResource("LaBelleLucie.css").toExternalForm());
	}

	/**
	 * @return
	 * @see io.github.alantcote.labellelucie.view.impl.RootView#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return rootView.getInputHandler();
	}

	/**
	 * @param inputHandler
	 * @see io.github.alantcote.labellelucie.view.impl.RootView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		rootView.setInputHandler(inputHandler);
	}
}
