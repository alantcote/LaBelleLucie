package io.github.alantcote.labellelucie.view.impl.menu;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.undo.UndoManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCharacterCombination;

/**
 * The Edit menu.
 */
public class EditMenu extends Menu {
	/**
	 * The input handler.
	 */
	protected InputHandler inputHandler;
	
	/**
	 * The model.
	 */
	protected GameState model;
	
	/**
	 * The Redo menu item.
	 */
	protected MenuItem redoItem;
	
	/**
	 * The Undo menu item.
	 */
	protected MenuItem undoItem;

	/**
	 * Construct a new object.
	 * @param aModel the model.
	 * @param anInputHandler the input handler.
	 */
	public EditMenu(GameState aModel, InputHandler anInputHandler) {
		super("Edit");

		model = aModel;
		inputHandler = anInputHandler;

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

	/**
	 * Establish the menu items for the menu.
	 */
	protected void establishChildren() {
		establishRedoItem();
		establishUndoItem();
	}

	/**
	 * Establish the Redo menu item.
	 */
	protected void establishRedoItem() {
		redoItem = new MenuItem("Redo");

		redoItem.setAccelerator(new KeyCharacterCombination("y", KeyCharacterCombination.CONTROL_DOWN));

		redoItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				model.getUndoManager().redoOp();
			}
		});

		getItems().add(redoItem);
	}

	/**
	 * Establish the Undo menu item.
	 */
	protected void establishUndoItem() {
		undoItem = new MenuItem("Undo");

		undoItem.setAccelerator(new KeyCharacterCombination("z", KeyCharacterCombination.CONTROL_DOWN));

		undoItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				model.getUndoManager().undoOp();
			}
		});

		getItems().add(undoItem);
	}
}
