package net.sf.cotelab.lbl.view.impl.menu;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.undo.UndoManager;

public class EditMenu extends Menu {
	protected InputHandler inputHandler;
	protected GameState model;
	protected MenuItem redoItem;
	protected MenuItem undoItem;

	public EditMenu(final GameState model, InputHandler inputHandler) {
		super("Edit");
		
		this.model = model;
		this.inputHandler = inputHandler;
		
		establishChildren();
		
		setOnShowing(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				UndoManager um = model.getUndoManager();
				
				redoItem.setDisable(!um.canRedo());
				undoItem.setDisable(!um.canUndo());
			}
		});
	}
	
	protected void establishChildren() {
		establishRedoItem();
		establishUndoItem();
//		getItems().add(new MenuItem("DummyItem"));
	}
	
	protected void establishRedoItem() {
		redoItem = new MenuItem("Redo");
		
		redoItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				model.getUndoManager().redoOp();
			}
		});
		
		getItems().add(redoItem);
	}
	
	protected void establishUndoItem() {
		undoItem = new MenuItem("Undo");
		
		undoItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				model.getUndoManager().undoOp();
			}
		});
		
		getItems().add(undoItem);
	}
}
