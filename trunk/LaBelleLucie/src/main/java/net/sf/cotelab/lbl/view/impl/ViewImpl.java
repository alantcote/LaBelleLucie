package net.sf.cotelab.lbl.view.impl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.view.facade.View;

public class ViewImpl implements View {
	protected InputHandler inputHandler;
	protected SceneView sceneView;
	protected Window window;

	public ViewImpl(Stage stage, GameState model) {
		super();
		
		sceneView = new SceneView(model);
		stage.setScene(sceneView);
		
		window = stage;
		
		model.getGameSummary().addListener(new ChangeListener<GameSummary>() {
			@Override
			public void changed(ObservableValue<? extends GameSummary> src,
					GameSummary oldValue, GameSummary newValue) {
				switch (newValue) {
				case LOST:
					new MessageDialog().show(window, "Game lost",
							"There are no legal plays remaining.", Color.RED);
					break;
				case WON:
					new MessageDialog().show(window, "Game won",
							"All cards have been moved to foundations.",
							Color.LIMEGREEN);
					break;
				default:
					break;
				}
			}
		});
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				inputHandler.onExitRequest();
			}
		});
	}

	@Override
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		
		sceneView.setInputHandler(inputHandler);
	}
}
