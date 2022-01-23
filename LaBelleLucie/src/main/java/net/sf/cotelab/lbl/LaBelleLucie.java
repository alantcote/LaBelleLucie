package net.sf.cotelab.lbl;

import java.util.prefs.BackingStoreException;

import io.github.alantcote.clutilities.javafx.windowprefs.WindowPrefs;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.impl.GameStateImpl;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.lbl.view.impl.ViewImpl;

/**
 * The start class of LaBelleLucie.
 */
public class LaBelleLucie extends Application {
	protected Stage stage = null;
	protected WindowPrefs windowPrefs = null;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(final Stage primaryStage) {
		stage = primaryStage;
		GameState model = newGameState();
		Controller controller = newController(model);
		final InputHandler inputHandler = controller.getInputHandler();
		@SuppressWarnings("unused")
		View view = newView(primaryStage, model, inputHandler);
		
		primaryStage.setOnShown(newWindowEventHandler(inputHandler));
		
		// show the stage
		show(primaryStage);
	}

	protected Controller newController(GameState model) {
		return new ControllerImpl(model);
	}

	protected GameState newGameState() {
		return new GameStateImpl();
	}

	protected View newView(final Stage primaryStage,
			GameState model, InputHandler inputHandler) {
		return new ViewImpl(primaryStage, model, inputHandler, getHostServices());
	}
	
	protected EventHandler<WindowEvent> newWindowEventHandler(final InputHandler ih) {
		return new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ih.onNewGameRequested();

				try {
					windowPrefs = new WindowPrefs(LaBelleLucie.class, stage);
				} catch (BackingStoreException e) {
					System.err.println("LaBelleLucie.newWindowEventHandler(): caught: " + e.getMessage());
					e.printStackTrace();
					System.err.println("LaBelleLucie.newWindowEventHandler(): continuing . . .");
				}
			}
		};
	}
	
	protected void show(Stage stage) {
		stage.show();
	}
}
