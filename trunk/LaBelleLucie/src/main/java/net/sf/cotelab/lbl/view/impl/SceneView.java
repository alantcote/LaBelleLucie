package net.sf.cotelab.lbl.view.impl;

import javafx.scene.Parent;
import javafx.scene.Scene;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.facade.View;

public class SceneView extends Scene implements View {
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
	 * @see net.sf.cotelab.lbl.view.impl.RootView#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return rootView.getInputHandler();
	}

	/**
	 * @param inputHandler
	 * @see net.sf.cotelab.lbl.view.impl.RootView#setInputHandler(net.sf.cotelab.lbl.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		rootView.setInputHandler(inputHandler);
	}
}