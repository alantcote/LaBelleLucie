package io.github.alantcote.labellelucie.controller.facade;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;

/**
 * Test case for
 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler}.
 */
public class DefaultInputHandlerTest {
	protected Card testCard = new Card(Rank.ACE, Suit.CLUB);

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#listMoves()}.
	 */
	@Test
	public void testListMoves() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		assertNull(fixture.listMoves());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onCardMoveRequested(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnCardMoveRequested() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onCardMoveRequested(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDragDetected(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDragDetected() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDragDetected(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDragDone(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDragDone() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDragDone(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDragDropped(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDragDropped() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDragDropped(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDragEntered(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDragEntered() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDragEntered(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDragExited(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDragExited() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDragExited(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDragOver(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDragOver() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDragOver(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onDrawRequested(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDrawRequested() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onDrawRequested(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onExitRequest()}.
	 */
	@Test
	public void testOnExitRequest() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onExitRequest();
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onMouseEntered(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnMouseEntered() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onMouseEntered(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onMouseExited(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnMouseExited() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onMouseExited(testCard);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onNewGameRequested()}.
	 */
	@Test
	public void testOnNewGameRequested() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onNewGameRequested();
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler#onReshuffleRequest()}.
	 */
	@Test
	public void testOnReshuffleRequest() {
		DefaultInputHandler fixture = new DefaultInputHandler();

		fixture.onReshuffleRequest();
	}

}
