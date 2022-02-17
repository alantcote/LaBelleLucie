package io.github.alantcote.labellelucie.controller.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;

/**
 * Test case for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl}.
 */
public class ControllerImplTest {
	Card testCard = new Card(Rank.ACE, Suit.CLUB);
	GameState testGameState = new GameStateImpl();

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#ControllerImpl(io.github.alantcote.labellelucie.model.facade.GameState)}.
	 */
	@Test
	public void testControllerImpl() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture);
		assertTrue(testGameState == fixture.model);
		assertNotNull(fixture.moveFinder);
		assertNotNull(fixture.inputHandler);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#draw(int, int)}.
	 */
	@Test
	public void testDraw() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.draw(0, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#getInputHandler()}.
	 */
	@Test
	public void testGetInputHandler() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertTrue(fixture.inputHandler == fixture.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#getModel()}.
	 */
	@Test
	public void testGetModel() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertTrue(fixture.model == fixture.getModel());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#listMoves()}.
	 */
	@Test
	public void testListMoves() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.listMoves());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#moveTopCardFoundationToTableau(int, int)}.
	 */
	@Test
	public void testMoveTopCardFoundationToTableau() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		fixture.model.getFoundation()[0].add(testCard);
		
		fixture.moveTopCardFoundationToTableau(0, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#moveTopCardTableauToFoundation(int, int)}.
	 */
	@Test
	public void testMoveTopCardTableauToFoundation() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		
		fixture.moveTopCardTableauToFoundation(0, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#moveTopCardTableauToTableau(int, int)}.
	 */
	@Test
	public void testMoveTopCardTableauToTableau() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		
		fixture.moveTopCardTableauToFoundation(0, 1);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#onCardMoveRequested(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnCardMoveRequested() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.onCardMoveRequested(testCard);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#onDrawRequested(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testOnDrawRequested() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.onDrawRequested(testCard);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#onExitRequest()}.
	 */
	@Test
	public void testOnExitRequest() {
		// The method under test just calls Platform.exit(). Not a good bit of
		// code to run here.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#onNewGameRequested()}.
	 */
	@Test
	public void testOnNewGameRequested() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.onNewGameRequested();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#onReshuffleRequest()}.
	 */
	@Test
	public void testOnReshuffleRequest() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.onReshuffleRequest();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#unDraw(int, int)}.
	 */
	@Test
	public void testUnDraw() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		
		fixture.unDraw(0, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#updateGameSummary()}.
	 */
	@Test
	public void testUpdateGameSummary() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.updateGameSummary();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#countFoundationCards()}.
	 */
	@Test
	public void testCountFoundationCards() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertTrue(0 == fixture.countFoundationCards());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#indexOfTableauFanWithTopCard(io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testIndexOfTableauFanWithTopCard() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		
		assertTrue(0 == fixture.indexOfTableauFanWithTopCard(fixture.model.getTableau()[0].getTopCard()));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#isGameWon()}.
	 */
	@Test
	public void testIsGameWon() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertFalse(fixture.isGameWon());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#moveCardToFoundation(int, int)}.
	 */
	@Test
	public void testMoveCardToFoundation() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		
		fixture.moveCardToFoundation(0, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#moveCardToTableau(int, int)}.
	 */
	@Test
	public void testMoveCardToTableau() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.model.reset();
		
		fixture.moveCardToTableau(0, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#newDeck()}.
	 */
	@Test
	public void testNewDeck() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.newDeck());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#newDrawOp(int, int)}.
	 */
	@Test
	public void testNewDrawOp() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.newDrawOp(0, 0));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#newInputHandler()}.
	 */
	@Test
	public void testNewInputHandler() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.newInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#newMoveCardTableauToFoundationOp(int, int)}.
	 */
	@Test
	public void testNewMoveCardTableauToFoundationOp() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.newMoveCardTableauToFoundationOp(0, 0));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#newMoveCardTableauToTableauOp(int, int)}.
	 */
	@Test
	public void testNewMoveCardTableauToTableauOp() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.newMoveCardTableauToTableauOp(0, 0));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#newMoveFinder()}.
	 */
	@Test
	public void testNewMoveFinder() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		assertNotNull(fixture.newMoveFinder());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.ControllerImpl#reshuffle()}.
	 */
	@Test
	public void testReshuffle() {
		ControllerImpl fixture = new ControllerImpl(testGameState);
		
		fixture.reshuffle();
	}

}
