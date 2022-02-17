package io.github.alantcote.labellelucie.undo;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.undoableop.DrawOp;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;

/**
 * Test case for {@link io.github.alantcote.labellelucie.undo.UndoManager}.
 */
public class UndoManagerTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#UndoManager()}.
	 */
	@Test
	public void testUndoManager() {
		UndoManager fixture = new UndoManager();
		
		assertNotNull(fixture);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#add(io.github.alantcote.labellelucie.undo.UndoableOp)}.
	 */
	@Test
	public void testAdd() {
		UndoManager fixture = new UndoManager();
		
		fixture.add(new DrawOp(new ControllerImpl(new GameStateImpl()), 0, 1));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#canRedo()}.
	 */
	@Test
	public void testCanRedo() {
		UndoManager fixture = new UndoManager();
		
		assertFalse(fixture.canRedo());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#canUndo()}.
	 */
	@Test
	public void testCanUndo() {
		UndoManager fixture = new UndoManager();
		
		assertFalse(fixture.canUndo());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#redoOp()}.
	 */
	@Test
	public void testRedoOp() {
		UndoManager fixture = new UndoManager();
		
		fixture.redoOp();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#reset()}.
	 */
	@Test
	public void testReset() {
		UndoManager fixture = new UndoManager();
		
		fixture.reset();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#undoOp()}.
	 */
	@Test
	public void testUndoOp() {
		UndoManager fixture = new UndoManager();
		
		fixture.undoOp();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.undo.UndoManager#newList_UndoableOp()}.
	 */
	@Test
	public void testNewList_UndoableOp() {
		UndoManager fixture = new UndoManager();
		
		assertNotNull(fixture.newList_UndoableOp());
	}

}
