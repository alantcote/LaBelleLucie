package net.sf.cotelab.lbl;

/*
 * Wish list
 * 
 * . undo/redo command hot-keys
 * . card size preference
 * . hinting (integrated with loss detection?)
 * . trefoil rule preference (aces begin game on foundations)
 * . tableau stacking by alternating colors preference
 * . restart game command
 * . animation of card moves
 * . save/resume games
 * . allow draw only when both reshuffles have completed preference
 * . deck design preference
 * . table background color preference
 */

import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.impl.GameStateImpl;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.lbl.view.impl.ViewImpl;

public class LaBelleLucie extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(final Stage primaryStage) {
		GameState model = new GameStateImpl();
		Controller controller = new ControllerImpl(model);
		View view = new ViewImpl(primaryStage, model);
		
		view.setInputHandler(controller.getInputHandler());
		
		// show the stage
		primaryStage.show();
	}
}
