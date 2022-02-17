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
	protected InputHandler inputHandler = null;
	protected Window window;

	public GameResultListener(Window window) {
		super();

		this.window = window;
	}

	@Override
	public void changed(ObservableValue<? extends GameSummary> src, GameSummary oldValue, GameSummary newValue) {
		Alert.AlertType alertType = Alert.AlertType.INFORMATION;
		Alert alert = new Alert(alertType);
		Optional<ButtonType> result;
		String tryAgain = "Return and try again";
		ButtonType tryAgainBT = new ButtonType(tryAgain);
		String newGame = "Start new game";
		ButtonType newGameBT = new ButtonType(newGame);
		String exitProgram = "Exit";
		ButtonType exitProgramBT = new ButtonType(exitProgram);

		switch (newValue) {
		case LOST:
			alert = new Alert(alertType, "There are no legal plays remaining.", tryAgainBT, newGameBT, exitProgramBT);
			alert.setHeaderText("Game lost");

			break;
		case WON:
			alert = new Alert(alertType, "All cards have been moved to foundations.", newGameBT, exitProgramBT);
			alert.setHeaderText("Game won");
			
			break;
		default:
			return;
		}
		
		result = alert.showAndWait();
		
		if (result.isPresent()) {
			ButtonType chosen = result.get();

			if (newGameBT == chosen) {
				inputHandler.onNewGameRequested();
			}

			if (exitProgramBT == chosen) {
				inputHandler.onExitRequest();
			}
		}
	}

	/**
	 * @param inputHandler the inputHandler to set
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
}
