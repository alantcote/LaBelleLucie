package net.sf.cotelab.lbl.view.impl.listeners;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.view.impl.dialog.MessageDialog;

public class GameResultListener implements ChangeListener<GameSummary> {
	protected InputHandler inputHandler = null;
	protected Window window;

	public GameResultListener(Window window) {
		super();
		
		this.window = window;
	}

	@Override
	public void changed(ObservableValue<? extends GameSummary> src,
			GameSummary oldValue, GameSummary newValue) {
		MessageDialog dialog = newMessageDialog();
		String tryAgain = "Return and try again";
		String newGame = "Start new game";
		String exitProgram = "Exit";
		Object[] options;
		Object chosen = newGame;
		
		switch (newValue) {
		case LOST:
			options = new Object[] { tryAgain, newGame, exitProgram };
			
			chosen = dialog.showInputDialog(window, "Game lost",
					"There are no legal plays remaining.", options, tryAgain);
			
			break;
		case WON:
			options = new Object[] { newGame, exitProgram };
			
			chosen = dialog.showInputDialog(
					window, "Game won",
					"All cards have been moved to foundations.", options,
					newGame);
			break;
		default:
			break;
		}
		
		if (newGame.equals(chosen)) {
			inputHandler.onNewGameRequested();
		}
		
		if (exitProgram.equals(chosen)) {
			inputHandler.onExitRequest();
		}
	}

	/**
	 * @param inputHandler the inputHandler to set
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	protected MessageDialog newMessageDialog() {
		return new MessageDialog();
	}
}
