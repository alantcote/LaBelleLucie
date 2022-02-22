package io.github.alantcote.labellelucie.view.impl.listeners;

import java.util.Optional;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 * A listener for game state changes.
 */
public class GameResultListener implements ChangeListener<GameSummary> {
	/**
	 * The type of all alerts presented by this class.
	 */
	public static final Alert.AlertType ALERT_TYPE = Alert.AlertType.INFORMATION;

	/**
	 * The explanatory message for the game won alert.
	 */
	public static final String ALL_CARDS_PLAYED = "All cards have been moved to foundations.";

	/**
	 * The text for exit program buttons.
	 */
	public static final String EXIT_PROGRAM = "Exit";

	/**
	 * The type of exit program buttons.
	 */
	public static final ButtonType EXIT_PROGRAM_BT = new ButtonType(EXIT_PROGRAM);

	/**
	 * The summary text for the game lost alert.
	 */
	public static final String GAME_LOST = "Game lost";

	/**
	 * The summary text for the game won alert.
	 */
	public static final String GAME_WON = "Game won";

	/**
	 * The text for start new game buttons.
	 */
	public static final String NEW_GAME = "Start new game";

	/**
	 * The type of start new game buttons.
	 */
	public static final ButtonType NEW_GAME_BT = new ButtonType(NEW_GAME);

	/**
	 * The explanatory message for the game lost alert.
	 */
	public static final String NO_LEGAL_PLAYS = "There are no legal plays remaining.";

	/**
	 * The text for try again buttons.
	 */
	public static final String TRY_AGAIN = "Return and try again";

	/**
	 * The type for try again buttons.
	 */
	public static final ButtonType TRY_AGAIN_BT = new ButtonType(TRY_AGAIN);

	/**
	 * The input handler.
	 */
	protected InputHandler inputHandler = null;

	/**
	 * The owner window.
	 */
	protected Window window;

	/**
	 * Constructor
	 * 
	 * @param window the owner window.
	 */
	public GameResultListener(Window window) {
		super();

		this.window = window;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changed(ObservableValue<? extends GameSummary> src, GameSummary oldValue, GameSummary newValue) {
		processEvent(newValue);
	}

	/**
	 * Set the input handler.
	 * 
	 * @param inputHandler the inputHandler to set.
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	/**
	 * Set the owner window of an alert.
	 * 
	 * This also sets the CSS id for the alert's dialog pane.
	 * 
	 * @param alert the alert.
	 */
	protected void initAlertOwner(Alert alert) {
		alert.initOwner(window);
		alert.getDialogPane().setId("alert");
	}

	/**
	 * Create a new game lost alert.
	 * 
	 * @return the new object.
	 */
	protected Alert newGameLostAlert() {
		Alert alert = new Alert(ALERT_TYPE, NO_LEGAL_PLAYS, TRY_AGAIN_BT, NEW_GAME_BT, EXIT_PROGRAM_BT);

		alert.setHeaderText(GAME_LOST);

		return alert;
	}

	/**
	 * Create a new game won alert.
	 * 
	 * @return the new object.
	 */
	protected Alert newGameWonAlert() {
		Alert alert = new Alert(ALERT_TYPE, ALL_CARDS_PLAYED, NEW_GAME_BT, EXIT_PROGRAM_BT);

		alert.setHeaderText(GAME_WON);

		return alert;
	}

	/**
	 * Process a game state change event.
	 * 
	 * @param newValue the new value of the game state.
	 */
	protected void processEvent(GameSummary newValue) {
		Alert alert;
		Optional<ButtonType> result;

		switch (newValue) {
		case LOST:
			alert = newGameLostAlert();

			break;
		case WON:
			alert = newGameWonAlert();

			break;
		default:
			return;
		}

		initAlertOwner(alert);

		result = showAndWait(alert);

		if (result.isPresent()) {
			ButtonType chosen = result.get();

			if (NEW_GAME_BT == chosen) {
				requestNewGame();
			}

			if (EXIT_PROGRAM_BT == chosen) {
				requestProgramExit();
			}
		}
	}

	/**
	 * Ask the input handler for a new game.
	 */
	protected void requestNewGame() {
		inputHandler.onNewGameRequested();
	}

	/**
	 * Ask the input handler to exit the program.
	 */
	protected void requestProgramExit() {
		inputHandler.onExitRequest();
	}

	/**
	 * Show a given alert and wait for user action.
	 * 
	 * @param alert the alert.
	 * @return the user action.
	 */
	protected Optional<ButtonType> showAndWait(Alert alert) {
		return alert.showAndWait();
	}
}
