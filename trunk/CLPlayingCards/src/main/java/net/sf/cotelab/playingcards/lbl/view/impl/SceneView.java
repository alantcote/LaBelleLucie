package net.sf.cotelab.playingcards.lbl.view.impl;

import javafx.scene.Parent;
import javafx.scene.Scene;
import net.sf.cotelab.playingcards.lbl.controller.facade.InputHandler;
import net.sf.cotelab.playingcards.lbl.model.facade.GameState;

public class SceneView extends Scene {
	protected RootView rootView;

	public SceneView(GameState model) {
		super(new RootView(model));
		
		Parent root = getRoot();
		
		if (root instanceof RootView) {
			rootView = (RootView) root;
		}
		
		getStylesheets().add(
				getClass().getResource("LaBelleLucie.css").toExternalForm());
	}

	/**
	 * @return
	 * @see net.sf.cotelab.playingcards.lbl.view.impl.RootView#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return rootView.getInputHandler();
	}

	/**
	 * @param inputHandler
	 * @see net.sf.cotelab.playingcards.lbl.view.impl.RootView#setInputHandler(net.sf.cotelab.playingcards.lbl.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		rootView.setInputHandler(inputHandler);
	}
}
