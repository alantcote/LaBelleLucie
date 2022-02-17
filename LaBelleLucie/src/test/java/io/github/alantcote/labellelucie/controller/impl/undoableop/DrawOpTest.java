package io.github.alantcote.labellelucie.controller.impl.undoableop;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;

/**
 * Test case for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.DrawOp}.
 */
public class DrawOpTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.DrawOp#DrawOp(io.github.alantcote.labellelucie.controller.impl.ControllerImpl, int, int)}.
	 */
	@Test
	public void testDrawOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		DrawOp fixture = new DrawOp(testControllerImpl, 0, 1);
		
		assertNotNull(fixture);
		
		assertTrue(testControllerImpl == fixture.controller);
		assertTrue(0 == fixture.fanIndex);
		assertTrue(1 == fixture.cardIndex);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.DrawOp#doOp()}.
	 */
	@Test
	public void testDoOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		DrawOp fixture = new DrawOp(testControllerImpl, 0, 1);
		
		testGameState.reset();
		
		fixture.doOp();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.DrawOp#undoOp()}.
	 */
	@Test
	public void testUndoOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		DrawOp fixture = new DrawOp(testControllerImpl, 0, 1);
		
		testGameState.reset();
		
		fixture.undoOp();
	}

}
