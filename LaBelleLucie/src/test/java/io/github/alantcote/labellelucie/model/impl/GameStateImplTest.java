package io.github.alantcote.labellelucie.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.facade.GameSummary;

/**
 * Test case for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl}.
 */
public class GameStateImplTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#GameStateImpl()}.
	 */
	@Test
	public void testGameStateImpl() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#dealTableau(io.github.alantcote.playingcards.Deck)}.
	 */
	@Test
	public void testDealTableau() {
		GameStateImpl fixture = new GameStateImpl();
		
		fixture.dealTableau(new Pack());
		
		assertTrue(GameState.TABLEAU_FAN_COUNT == fixture.tableau.length);
		
		for (int i = 0; i < (GameState.TABLEAU_FAN_COUNT - 2); ++i) {
			assertTrue(3 == fixture.tableau[i].size());
		}
		
		assertTrue(1 == fixture.tableau[GameState.TABLEAU_FAN_COUNT - 1].size());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getDrawsRemaining()}.
	 */
	@Test
	public void testGetDrawsRemaining() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.drawsRemaining == fixture.getDrawsRemaining());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getFoundation()}.
	 */
	@Test
	public void testGetFoundation() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.foundation == fixture.getFoundation());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getGameSummary()}.
	 */
	@Test
	public void testGetGameSummary() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.gameSummary == fixture.getGameSummary());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getRedealsRemaining()}.
	 */
	@Test
	public void testGetRedealsRemaining() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.redealsRemaining == fixture.getRedealsRemaining());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getStock()}.
	 */
	@Test
	public void testGetStock() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.stock == fixture.getStock());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getTableau()}.
	 */
	@Test
	public void testGetTableau() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.tableau == fixture.getTableau());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#getUndoManager()}.
	 */
	@Test
	public void testGetUndoManager() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertTrue(fixture.undoManager == fixture.getUndoManager());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#reset()}.
	 */
	@Test
	public void testReset() {
		GameStateImpl fixture = new GameStateImpl();
		
		fixture.reset();
		
		assertTrue(GameSummary.IN_PROGRESS == fixture.gameSummary.get());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#inizMembers()}.
	 */
	@Test
	public void testInizMembers() {
		GameStateImpl fixture = new GameStateImpl();
		
		fixture.inizMembers();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newFan()}.
	 */
	@Test
	public void testNewFan() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newFan());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newFanArray(int)}.
	 */
	@Test
	public void testNewFanArray() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newFanArray(GameState.FOUNDATION_FAN_COUNT));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newIntegerProperty()}.
	 */
	@Test
	public void testNewIntegerProperty() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newIntegerProperty());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newObjectProperty_Deck()}.
	 */
	@Test
	public void testNewObjectProperty_Deck() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newObjectProperty_Deck());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newObjectProperty_GameSummary(io.github.alantcote.labellelucie.model.facade.GameSummary)}.
	 */
	@Test
	public void testNewObjectProperty_GameSummary() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newObjectProperty_GameSummary(GameSummary.IN_PROGRESS));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newPack()}.
	 */
	@Test
	public void testNewPack() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newPack());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.model.impl.GameStateImpl#newUndoManager()}.
	 */
	@Test
	public void testNewUndoManager() {
		GameStateImpl fixture = new GameStateImpl();
		
		assertNotNull(fixture.newUndoManager());
	}

}
