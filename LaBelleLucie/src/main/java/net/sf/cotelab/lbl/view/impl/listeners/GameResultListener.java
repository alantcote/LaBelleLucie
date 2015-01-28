package net.sf.cotelab.lbl.view.impl.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.view.impl.dialog.MessageDialog;

public class GameResultListener implements ChangeListener<GameSummary> {
	protected Window window;

	public GameResultListener(Window window) {
		super();
		
		this.window = window;
	}

	@Override
	public void changed(ObservableValue<? extends GameSummary> src,
			GameSummary oldValue, GameSummary newValue) {
		switch (newValue) {
		case LOST:
			newMessageDialog().show(window, "Game lost",
					"There are no legal plays remaining.", Color.RED);
			break;
		case WON:
			newMessageDialog().show(window, "Game won",
					"All cards have been moved to foundations.",
					Color.LIMEGREEN);
			break;
		default:
			break;
		}
	}

	protected MessageDialog newMessageDialog() {
		return new MessageDialog();
	}
}
