package io.github.alantcote.labellelucie.view.impl.listeners;

import java.util.Optional;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class GameResultListener implements ChangeListener<GameSummary> {
	public static final Alert.AlertType ALERT_TYPE = Alert.AlertType.INFORMATION;
	public static final String ALL_CARDS_PLAYED = "All cards have been moved to foundations.";
	public static final String EXIT_PROGRAM = "Exit";
	public static final ButtonType EXIT_PROGRAM_BT = new ButtonType(EXIT_PROGRAM);
	public static final String GAME_LOST = "Game lost";
	public static final String GAME_WON = "Game won";
	public static final String NEW_GAME = "Start new game";
	public static final ButtonType NEW_GAME_BT = new ButtonType(NEW_GAME);
	public static final String NO_LEGAL_PLAYS = "There are no legal plays remaining.";
	public static final String TRY_AGAIN = "Return and try again";
	public static final ButtonType TRY_AGAIN_BT = new ButtonType(TRY_AGAIN);
	protected InputHandler inputHandler = null;
	protected Window window;

	public GameResultListener(Window window) {
		super();

		this.window = window;
	}

	@Override
	public void changed(ObservableValue<? extends GameSummary> src, GameSummary oldValue, GameSummary newValue) {
		processEvent(newValue);
	}

	/**
	 * @param inputHandler the inputHandler to set
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	protected void initAlertOwner(Alert alert) {
		alert.initOwner(window);
		alert.getDialogPane().setId("alert");
	}

	/**
	 * @return
	 */
	protected Alert newGameLostAlert() {
		Alert alert = new Alert(ALERT_TYPE, NO_LEGAL_PLAYS, TRY_AGAIN_BT, NEW_GAME_BT, EXIT_PROGRAM_BT);

		alert.setHeaderText(GAME_LOST);

		return alert;
	}
	
	/**
	 * @return
	 */
	protected Alert newGameWonAlert() {
		Alert alert = new Alert(ALERT_TYPE, ALL_CARDS_PLAYED, NEW_GAME_BT, EXIT_PROGRAM_BT);

		alert.setHeaderText(GAME_WON);

		return alert;
	}

	/**
	 * @param newValue
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
	 * 
	 */
	protected void requestNewGame() {
		inputHandler.onNewGameRequested();
	}

	/**
	 * 
	 */
	protected void requestProgramExit() {
		inputHandler.onExitRequest();
	}

	/**
	 * @param alert
	 * @return
	 */
	protected Optional<ButtonType> showAndWait(Alert alert) {
		return alert.showAndWait();
	}
}
