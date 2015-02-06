package net.sf.cotelab.lbl.controller.impl;

import static org.junit.Assert.*;

import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToFoundationOp;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.undo.UndoManager;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class ControllerImplTest extends jMockTestHelper {
	protected ControllerImpl mockControllerImpl;
	protected GameState mockGameState;
	protected InputHandler mockInputHandler;
	
	@Before
	public void setup() {
		mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		mockGameState = context.mock(GameState.class, "mockGameState");
		mockInputHandler = context.mock(InputHandler.class, "mockInputHandler");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).newInputHandler();
			will(returnValue(mockInputHandler));
		}});
	}
	
	@Test
	public void testCanPlay_Card() {
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).canPlayOnFoundation(mockCard);
			will(returnValue(false));

			oneOf(mockControllerImpl).canPlayOnTableau(mockCard);
			will(returnValue(true));
		}});
		
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#canPlayOnFoundation(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			protected boolean canPlayOnFoundation(Card srcCard) {
				return mockControllerImpl.canPlayOnFoundation(srcCard);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#canPlayOnTableau(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			protected boolean canPlayOnTableau(Card srcCard) {
				return mockControllerImpl.canPlayOnTableau(srcCard);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		assertTrue(fixture.canPlay(mockCard));
	}

	@Test
	public void testCanPlayOnFoundationCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanPlayOnFoundationCardCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanPlayOnTableauCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanPlayOnTableauCardCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testControllerImpl() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		assertEquals(mockInputHandler, fixture.inputHandler);
		assertEquals(mockGameState, fixture.model);
	}

	@Test
	public void testCountFoundationCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testDraw() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#updateGameSummary()
			 */
			@Override
			public void updateGameSummary() {
				mockControllerImpl.updateGameSummary();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final Fan mockFan = context.mock(Fan.class, "mockFan");
		final Fan[] mockTableau = {mockFan};
		final Card mockCard = context.mock(Card.class, "mockCard");
		final int fanIndex = 0;
		final int cardIndex = 0;
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(drawsRemaining));
			
			oneOf(drawsRemaining).get();
			will(returnValue(1));
			
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockFan).remove(cardIndex);
			will(returnValue(mockCard));
			
			oneOf(mockFan).add(mockCard);
			
			oneOf(drawsRemaining).get();
			will(returnValue(1));
			
			oneOf(drawsRemaining).set(0);
			
			oneOf(mockControllerImpl).updateGameSummary();
		}});
		
		fixture.draw(fanIndex, cardIndex);
	}

	@Test
	public void testGetInputHandler() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		assertEquals(mockInputHandler, fixture.getInputHandler());
	}

	@Test
	public void testGetModel() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		assertEquals(mockGameState, fixture.getModel());
	}

	@Test
	public void testIsGameWon() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveCardToFoundation() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newMoveCardTableauToFoundationOp(int, int)
			 */
			@Override
			protected MoveCardTableauToFoundationOp
					newMoveCardTableauToFoundationOp(
							int srcFanIndex, int destFanIndex) {
				return mockControllerImpl.newMoveCardTableauToFoundationOp(
						srcFanIndex, destFanIndex);
			}
		};
		final int srcFanIndex = 0;
		final int destFanIndex = 1;
		final MoveCardTableauToFoundationOp mockMoveCardTableauToFoundationOp =
				context.mock(MoveCardTableauToFoundationOp.class,
						"mockMoveCardTableauToFoundationOp");
		final UndoManager mockUndoManager =
				context.mock(UndoManager.class, "mockUndoManager");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).newMoveCardTableauToFoundationOp(
					srcFanIndex, destFanIndex);
			will(returnValue(mockMoveCardTableauToFoundationOp));
			
			oneOf(mockMoveCardTableauToFoundationOp).doOp();
			
			oneOf(mockGameState).getUndoManager();
			will(returnValue(mockUndoManager));
			
			oneOf(mockUndoManager).add(mockMoveCardTableauToFoundationOp);
		}});
		
		fixture.moveCardToFoundation(srcFanIndex, destFanIndex);
	}

	@Test
	public void testMoveCardToTableau() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveTopCardFoundationToTableau() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveTopCardTableauToFoundation() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveTopCardTableauToTableau() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		int srcFanIndex = 0;
		final int lastSrcIndex = 2;
		final Fan mockSrcFan = context.mock(Fan.class, "mockSrcFan");
		final Card mockCard = context.mock(Card.class, "mockCard");
		int destFanIndex = 1;
		final Fan mockDestFan = context.mock(Fan.class, "mockDestFan");
		final Fan[] mockTableau = {mockSrcFan, mockDestFan};
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockSrcFan).size();
			will(returnValue(lastSrcIndex + 1));
			
			oneOf(mockSrcFan).remove(lastSrcIndex);
			will(returnValue(mockCard));
			
			oneOf(mockDestFan).add(mockCard);
		}});
		
		fixture.moveTopCardTableauToTableau(srcFanIndex, destFanIndex);
	}

	@Test
	public void testNewDeck() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewDrawOp() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewInputHandler() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewMoveCardTableauToFoundationOp() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewMoveCardTableauToTableauOp() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testOnCardMoveRequested() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnDrawRequested() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnExitRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnNewGameRequested() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnReshuffleRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testReshuffle() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#updateGameSummary()
			 */
			@Override
			public void updateGameSummary() {
				mockControllerImpl.updateGameSummary();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newDeck()
			 */
			@Override
			protected Deck newDeck() {
				return mockControllerImpl.newDeck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};
		final Deck mockDeck = context.mock(Deck.class, "mockDeck");
		final Fan mockFan = context.mock(Fan.class, "mockFan");
		final Fan[] mockTableau = {mockFan};
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> mockStock = (ObjectProperty<Deck>)
				context.mock(ObjectProperty.class, "mockStock");
		@SuppressWarnings("unchecked")
		final Iterator<Card> mockIterator = (Iterator<Card>)
				context.mock(Iterator.class, "mockIterator");
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).newDeck();
			will(returnValue(mockDeck));
			
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockFan).iterator();
			will(returnValue(mockIterator));
			
			oneOf(mockIterator).hasNext();
			will(returnValue(true));
			
			oneOf(mockIterator).next();
			will(returnValue(mockCard));
			
			oneOf(mockDeck).add(mockCard);
			
			oneOf(mockIterator).hasNext();
			will(returnValue(false));
			
			oneOf(mockFan).clear();
			
			oneOf(mockGameState).getStock();
			will(returnValue(mockStock));
			
			oneOf(mockStock).set(null);
			
			oneOf(mockStock).set(mockDeck);
			
			oneOf(mockGameState).dealTableau(mockDeck);
			
			oneOf(mockStock).set(null);
			
			oneOf(mockStock).set(mockDeck);
			
			oneOf(mockControllerImpl).updateGameSummary();
		}});
		
		fixture.reshuffle();
	}

	@Test
	public void testUnDraw() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGameSummary() {
		ControllerImpl fixture = new ControllerImpl(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#canPlay()
			 */
			@Override
			protected boolean canPlay() {
				return mockControllerImpl.canPlay();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#isGameWon()
			 */
			@Override
			protected boolean isGameWon() {
				return mockControllerImpl.isGameWon();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
			 */
			@Override
			protected InputHandler newInputHandler() {
				return mockControllerImpl.newInputHandler();
			}
		};

		/*
		 * This will test a number of cases:
		 * 1) Game has been won ==> WON.
		 * 2) Game has not been won, but there are redeals remaining ==>
		 *    IN_PROGRESS.
		 * 3) Game has not been won and there are no redeals remaining, but
		 *    there are draws remaining ==> IN_PROGRESS.
		 * 4) Game has not been won and there are no redeals or draws remaining,
		 *    but there is a legal play ==> IN_PROGRESS.
		 * 5) Game has not been won, there are no redeals or draws remaining,
		 *    and there is no legal play ==> LOST.
		 */
		
		@SuppressWarnings("unchecked")
		final ObjectProperty<GameSummary> mockGameSummary =
				(ObjectProperty<GameSummary>)
					context.mock(ObjectProperty.class, "mockGameSummary");
		final IntegerProperty mockIntegerProperty =
				context.mock(IntegerProperty.class, "mockIntegerProperty");
		
		context.checking(new Expectations() {{
			/*
			 * case 1
			 */
			
			oneOf(mockControllerImpl).isGameWon();
			will(returnValue(true));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.WON);

			/*
			 * case 2
			 */
			
			oneOf(mockControllerImpl).isGameWon();
			will(returnValue(false));
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(1));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.IN_PROGRESS);

			/*
			 * case 3
			 */
			
			oneOf(mockControllerImpl).isGameWon();
			will(returnValue(false));
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(0));
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(1));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.IN_PROGRESS);

			/*
			 * case 4
			 */
			
			oneOf(mockControllerImpl).isGameWon();
			will(returnValue(false));
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(0));
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(0));
			
			oneOf(mockControllerImpl).canPlay();
			will(returnValue(true));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.IN_PROGRESS);

			/*
			 * case 5
			 */
			
			oneOf(mockControllerImpl).isGameWon();
			will(returnValue(false));
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(0));
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(0));
			
			oneOf(mockControllerImpl).canPlay();
			will(returnValue(false));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.LOST);
		}});
		
		fixture.updateGameSummary();	// case 1
		fixture.updateGameSummary();	// case 2
		fixture.updateGameSummary();	// case 3
		fixture.updateGameSummary();	// case 4
		fixture.updateGameSummary();	// case 5
	}
}
