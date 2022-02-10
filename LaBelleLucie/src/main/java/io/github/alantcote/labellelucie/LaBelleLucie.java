package io.github.alantcote.labellelucie;

import java.util.prefs.BackingStoreException;

import io.github.alantcote.clutilities.javafx.windowprefs.WindowPrefs;
import io.github.alantcote.labellelucie.controller.facade.Controller;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.ViewImpl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The start class of LaBelleLucie.
 */
public class LaBelleLucie extends Application {
	/**
	 * Main entry point to the program.
	 * <p>
	 * Like most JavaFX-based applications, this method kicks things off by calling
	 * {@link #launch(String...)}.
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The application window.
	 */
	protected Stage stage = null;

	/**
	 * The view.
	 */
	protected View view = null;

	/**
	 * Support for tracking and persisting window geometry.
	 */
	protected WindowPrefs windowPrefs = null;

	/**
	 * {@inheritDoc}
	 * <p>
	 * This method creates the model, view, and controller, and shows the window for
	 * the first time.
	 */
	@Override
	public void start(final Stage primaryStage) {
		stage = primaryStage;
		GameState model = newGameState();
		Controller controller = newController(model);
		final InputHandler inputHandler = controller.getInputHandler();

		createView(primaryStage, model, inputHandler);

		primaryStage.setOnShown(newWindowEventHandler(inputHandler));

		// show the stage
		showStage(primaryStage);
	}

	/**
	 * Create the view.
	 * 
	 * @param primaryStage the application window.
	 * @param model        the model.
	 * @param inputHandler the input handler.
	 */
	protected void createView(final Stage primaryStage, GameState model, InputHandler inputHandler) {
		view = newView(primaryStage, model, inputHandler);
	}

	/**
	 * Establish the window geometry tracker for this application's window.
	 */
	protected void establishWindowPrefs() {
		try {
			windowPrefs = newWindowPrefs();
		} catch (BackingStoreException e) {
			System.err.println("LaBelleLucie.establishWindowPrefs(): caught: " + e.getMessage());
			e.printStackTrace();
			System.err.println("LaBelleLucie.establishWindowPrefs(): continuing . . .");
		}
	}

	/**
	 * Initialize the GUI (last step) by starting up the first game and setting up
	 * the window geometry tracker.
	 * 
	 * @param ih the input handler.
	 */
	protected void inizGUI(final InputHandler ih) {
		ih.onNewGameRequested();
		establishWindowPrefs();
	}

	/**
	 * Create the controller.
	 * 
	 * @param model the model.
	 * @return the controller.
	 */
	protected Controller newController(GameState model) {
		return new ControllerImpl(model);
	}

	/**
	 * Create the model.
	 * 
	 * @return the model.
	 */
	protected GameState newGameState() {
		return new GameStateImpl();
	}

	/**
	 * Create a new {@link View} object.
	 * @param primaryStage the application window.
	 * @param model        the model.
	 * @param inputHandler the input handler.
	 * @return the new object.
	 */
	protected View newView(Stage primaryStage, GameState model, InputHandler inputHandler) {
		return new ViewImpl(primaryStage, model, inputHandler, getHostServices());
	}

	/**
	 * Create a window event handler, called when the application window is first
	 * shown to initialize things.
	 * 
	 * Initialization includes starting the first game and establishing an object to
	 * track and persist window geometry.
	 * 
	 * @param ih the input handler.
	 * @return the event handler.
	 */
	protected EventHandler<WindowEvent> newWindowEventHandler(final InputHandler ih) {
		return new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {

				inizGUI(ih);

			}
		};
	}

	/**
	 * Create a new window geometry tracker attached to the application's window.
	 * 
	 * @return the new object.
	 * @throws BackingStoreException if thrown by
	 *                               {@link WindowPrefs#WindowPrefs(Class, Stage)}.
	 */
	protected WindowPrefs newWindowPrefs() throws BackingStoreException {
		return new WindowPrefs(LaBelleLucie.class, stage);
	}

	/**
	 * Show a window.
	 * 
	 * @param stage a window.
	 */
	protected void showStage(Stage stage) {
		stage.show();
	}
}
