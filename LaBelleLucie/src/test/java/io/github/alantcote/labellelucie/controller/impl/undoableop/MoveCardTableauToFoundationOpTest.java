package io.github.alantcote.labellelucie.controller.impl.undoableop;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;

/**
 * Test case for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToFoundationOp}.
 */
public class MoveCardTableauToFoundationOpTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToFoundationOp#MoveCardTableauToFoundationOp(io.github.alantcote.labellelucie.controller.impl.ControllerImpl, int, int)}.
	 */
	@Test
	public void testMoveCardTableauToFoundationOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MoveCardTableauToFoundationOp fixture = new MoveCardTableauToFoundationOp(testControllerImpl, 0, 1);
		
		assertNotNull(fixture);
		
		assertTrue(testControllerImpl == fixture.controller);
		assertTrue(0 == fixture.srcFanIndex);
		assertTrue(1 == fixture.destFanIndex);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToFoundationOp#doOp()}.
	 */
	@Test
	public void testDoOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MoveCardTableauToFoundationOp fixture = new MoveCardTableauToFoundationOp(testControllerImpl, 0, 1);
		
		testGameState.reset();
		
		fixture.doOp();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToFoundationOp#undoOp()}.
	 */
	@Test
	public void testUndoOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MoveCardTableauToFoundationOp fixture = new MoveCardTableauToFoundationOp(testControllerImpl, 0, 1);
		
		testGameState.reset();
		
		fixture.doOp();
		
		fixture.undoOp();
	}

}
