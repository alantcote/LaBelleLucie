package io.github.alantcote.labellelucie.controller.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.Move;
import io.github.alantcote.labellelucie.controller.facade.MoveType;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;

/**
 * Test case for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder}.
 */
public class MoveFinderTest {
	protected GameState testGameState = new GameStateImpl();

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#MoveFinder(io.github.alantcote.labellelucie.model.facade.GameState)}.
	 */
	@Test
	public void testMoveFinder() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		assertNotNull(fixture);
		assertTrue(testGameState == fixture.model);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#canPlayOnFoundation(io.github.alantcote.playingcards.Card, io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testCanPlayOnFoundation() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		assertTrue(fixture.canPlayOnFoundation(new Card(Rank.DEUCE, Suit.CLUB), new Card(Rank.ACE, Suit.CLUB)));
		assertFalse(fixture.canPlayOnFoundation(new Card(Rank.TREY, Suit.CLUB), new Card(Rank.ACE, Suit.CLUB)));
		assertFalse(fixture.canPlayOnFoundation(new Card(Rank.DEUCE, Suit.DIAMOND), new Card(Rank.ACE, Suit.CLUB)));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#canPlayOnTableau(io.github.alantcote.playingcards.Card, io.github.alantcote.playingcards.Card)}.
	 */
	@Test
	public void testCanPlayOnTableau() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		assertTrue(fixture.canPlayOnFoundation(new Card(Rank.TREY, Suit.CLUB), new Card(Rank.DEUCE, Suit.CLUB)));
		assertFalse(fixture.canPlayOnFoundation(new Card(Rank.DEUCE, Suit.DIAMOND), new Card(Rank.ACE, Suit.CLUB)));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#findMoves()}.
	 */
	@Test
	public void testFindMoves() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		testGameState.reset();
		
		List<Move> moves = fixture.findMoves();
		int size = moves.size();
		
		assertTrue(1 < size);
		assertTrue(MoveType.RESHUFFLE == moves.get(size - 2).getType());
		assertTrue(MoveType.DRAW == moves.get(size - 1).getType());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#findMovesToFoundation()}.
	 */
	@Test
	public void testFindMovesToFoundation() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		testGameState.reset();
		
		assertNotNull(fixture.findMovesToFoundation());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#findMovesToFoundation(int)}.
	 */
	@Test
	public void testFindMovesToFoundationInt() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		testGameState.reset();
		testGameState.getFoundation()[0].add(new Card(Rank.ACE, Suit.CLUB));
		
		List<Move> moves = fixture.findMovesToFoundation(0);
		
		assertNotNull(moves);
		assertTrue((0 == moves.size()) || (1 == moves.size()));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#findMovesToTableau()}.
	 */
	@Test
	public void testFindMovesToTableau() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		testGameState.reset();
		
		assertNotNull(fixture.findMovesToTableau());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#findMoveToTableau(int)}.
	 */
	@Test
	public void testFindMoveToTableau() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		testGameState.reset();
		
		fixture.findMoveToTableau(0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#findSimpleMoves()}.
	 */
	@Test
	public void testFindSimpleMoves() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		testGameState.reset();
		
		assertNotNull(fixture.findSimpleMoves());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#newListOfMove()}.
	 */
	@Test
	public void testNewListOfMove() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		assertNotNull(fixture.newListOfMove());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.impl.MoveFinder#newMove(int, int, io.github.alantcote.labellelucie.controller.facade.MoveType)}.
	 */
	@Test
	public void testNewMove() {
		MoveFinder fixture = new MoveFinder(testGameState);
		
		assertNotNull(fixture.newMove(0, 1, MoveType.TABLEAU_TO_TABLEAU));
	}

}
