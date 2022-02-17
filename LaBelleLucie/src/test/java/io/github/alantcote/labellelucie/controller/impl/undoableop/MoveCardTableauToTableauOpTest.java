package io.github.alantcote.labellelucie.controller.impl.undoableop;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;

/**
 * Test case for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToTableauOp}.
 */
public class MoveCardTableauToTableauOpTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToTableauOp#MoveCardTableauToTableauOp(io.github.alantcote.labellelucie.controller.impl.ControllerImpl, int, int)}.
	 */
	@Test
	public void testMoveCardTableauToTableauOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MoveCardTableauToTableauOp fixture = new MoveCardTableauToTableauOp(testControllerImpl, 0, 1);
		
		assertNotNull(fixture);
		
		assertTrue(testControllerImpl == fixture.controller);
		assertTrue(0 == fixture.srcFanIndex);
		assertTrue(1 == fixture.destFanIndex);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToTableauOp#doOp()}.
	 */
	@Test
	public void testDoOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MoveCardTableauToTableauOp fixture = new MoveCardTableauToTableauOp(testControllerImpl, 0, 1);
		
		testGameState.reset();
		
		fixture.doOp();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToTableauOp#undoOp()}.
	 */
	@Test
	public void testUndoOp() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MoveCardTableauToTableauOp fixture = new MoveCardTableauToTableauOp(testControllerImpl, 0, 1);
		
		testGameState.reset();
		
		fixture.doOp();
		
		fixture.undoOp();
	}

}
