package io.github.alantcote.labellelucie.view.impl.menu;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.menu.EditMenu}.
 */
public class EditMenuTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.menu.EditMenu#EditMenu(io.github.alantcote.labellelucie.model.facade.GameState, io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testEditMenu() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = testControllerImpl.getInputHandler();
		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		EditMenu fixture = new EditMenu(testGameState, testInputHandler) {

			@Override
			protected void establishChildren() {
				assertTrue(0 == stateProperty.get());
				
				stateProperty.set(1);
			}
			
		};

		assertTrue(1 == stateProperty.get());
		assertTrue(testGameState == fixture.model);
		assertTrue(testInputHandler == fixture.inputHandler);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.menu.EditMenu#establishChildren()}.
	 */
	@Test
	public void testEstablishChildren() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = testControllerImpl.getInputHandler();
		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		EditMenu fixture = new EditMenu(testGameState, testInputHandler) {

			@Override
			protected void establishRedoItem() {
				assertTrue(0 == stateProperty.get());
				
				stateProperty.set(1);
			}

			@Override
			protected void establishUndoItem() {
				assertTrue(1 == stateProperty.get());
				
				stateProperty.set(2);
			}
			
		};
		
		stateProperty.set(0);
		
		fixture.establishChildren();

		assertTrue(2 == stateProperty.get());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.menu.EditMenu#establishRedoItem()}.
	 */
	@Test
	public void testEstablishRedoItem() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = testControllerImpl.getInputHandler();
//		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		EditMenu fixture = new EditMenu(testGameState, testInputHandler);
		
		fixture.establishRedoItem();
		
		ObservableList<MenuItem> items = fixture.getItems();
		
		assertTrue(items.contains(fixture.redoItem));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.menu.EditMenu#establishUndoItem()}.
	 */
	@Test
	public void testEstablishUndoItem() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = testControllerImpl.getInputHandler();
//		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		EditMenu fixture = new EditMenu(testGameState, testInputHandler);
		
		fixture.establishUndoItem();
		
		ObservableList<MenuItem> items = fixture.getItems();
		
		assertTrue(items.contains(fixture.undoItem));
	}

}
