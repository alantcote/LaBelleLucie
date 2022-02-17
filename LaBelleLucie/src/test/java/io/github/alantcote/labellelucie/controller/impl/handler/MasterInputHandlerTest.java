package io.github.alantcote.labellelucie.controller.impl.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;

/**
 * Test case for
 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler}.
 */
public class MasterInputHandlerTest {
	protected GameState testGameState = new GameStateImpl();

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#listMoves()}.
	 */
	@Test
	public void testListMoves() {
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MasterInputHandler fixture = new MasterInputHandler(testControllerImpl);

		testGameState.reset();

		assertNotNull(fixture.listMoves());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#MasterInputHandler(io.github.alantcote.labellelucie.controller.impl.ControllerImpl)}.
	 */
	@Test
	public void testMasterInputHandler() {
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MasterInputHandler fixture = new MasterInputHandler(testControllerImpl);

		assertNotNull(fixture);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#onCardMoveRequested(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnCardMoveRequested() {
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MasterInputHandler fixture = new MasterInputHandler(testControllerImpl);

		testGameState.reset();

		fixture.onCardMoveRequested(new Card(Rank.ACE, Suit.CLUB));
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#onDrawRequested(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDrawRequested() {
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MasterInputHandler fixture = new MasterInputHandler(testControllerImpl);

		testGameState.reset();

		fixture.onDrawRequested(new Card(Rank.ACE, Suit.CLUB));
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#onExitRequest()}.
	 */
	@Test
	public void testOnExitRequest() {
		// This method would call Application.exit(). Not good.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#onNewGameRequested()}.
	 */
	@Test
	public void testOnNewGameRequested() {
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MasterInputHandler fixture = new MasterInputHandler(testControllerImpl);

		testGameState.reset();

		fixture.onNewGameRequested();
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler#onReshuffleRequest()}.
	 */
	@Test
	public void testOnReshuffleRequest() {
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		MasterInputHandler fixture = new MasterInputHandler(testControllerImpl);

		testGameState.reset();

		fixture.onReshuffleRequest();
	}

}
