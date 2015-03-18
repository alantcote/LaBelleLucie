package net.sf.cotelab.lbl.controller.impl;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.controller.facade.Move;
import net.sf.cotelab.lbl.controller.facade.MoveType;
import net.sf.cotelab.lbl.controller.impl.undoableop.DrawOp;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToFoundationOp;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToTableauOp;
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
	public class Fixture extends ControllerImpl {
		public Fixture(GameState model) {
			super(model);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newInputHandler()
		 */
		@Override
		protected InputHandler newInputHandler() {
			return mockControllerImpl.newInputHandler();
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newMoveFinder()
		 */
		@Override
		protected MoveFinder newMoveFinder() {
			return mockControllerImpl.newMoveFinder();
		}
	}
	protected ControllerImpl mockControllerImpl;
	protected GameState mockGameState;
	protected InputHandler mockInputHandler;
	
	protected MoveFinder mockMoveFinder;

	@Before
	public void setup() {
		mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		mockGameState = context.mock(GameState.class, "mockGameState");
		mockInputHandler = context.mock(InputHandler.class, "mockInputHandler");
		mockMoveFinder = context.mock(MoveFinder.class, "mockMoveFinder");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).newMoveFinder();
			will(returnValue(mockMoveFinder));

			oneOf(mockControllerImpl).newInputHandler();
			will(returnValue(mockInputHandler));
		}});
	}

	@Test
	public void testControllerImpl() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		assertEquals(mockInputHandler, fixture.inputHandler);
		assertEquals(mockGameState, fixture.model);
	}

	@Test
	public void testCountFoundationCards() {
		ControllerImpl fixture = new Fixture(mockGameState);
		final Fan mockFan0 = context.mock(Fan.class, "mockFan0");
		final Fan mockFan1 = context.mock(Fan.class, "mockFan1");
		final Fan mockFan2 = context.mock(Fan.class, "mockFan2");
		final Fan mockFan3 = context.mock(Fan.class, "mockFan3");
		final Fan[] mockFoundation = { mockFan0, mockFan1, mockFan2, mockFan3 };
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getFoundation();
			will(returnValue(mockFoundation));

			oneOf(mockFan0).size();
			will(returnValue(13));

			oneOf(mockFan1).size();
			will(returnValue(13));

			oneOf(mockFan2).size();
			will(returnValue(13));

			oneOf(mockFan3).size();
			will(returnValue(13));
		}});
		
		assertEquals(52, fixture.countFoundationCards());
	}

	@Test
	public void testDraw() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#updateGameSummary()
			 */
			@Override
			public void updateGameSummary() {
				mockControllerImpl.updateGameSummary();
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
		ControllerImpl fixture = new Fixture(mockGameState);
		
		assertEquals(mockInputHandler, fixture.getInputHandler());
	}

	@Test
	public void testGetModel() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		assertEquals(mockGameState, fixture.getModel());
	}

	@Test
	public void testIndexOfTableauFanWithTopCard() {
		ControllerImpl fixture = new Fixture(mockGameState);
		final Fan mockFan = context.mock(Fan.class, "mockFan");
		final Fan[] mockTableau = { mockFan };
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockFan).getTopCard();
			will(returnValue(mockCard));
		}});
		
		assertEquals(0, fixture.indexOfTableauFanWithTopCard(mockCard));
	}

	@Test
	public void testIsGameWon() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#countFoundationCards()
			 */
			@Override
			protected int countFoundationCards() {
				return mockControllerImpl.countFoundationCards();
			}
		};
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).countFoundationCards();
			will(returnValue(52));
		}});
		
		assertTrue(fixture.isGameWon());
	}

	@Test
	public void testMoveCardToFoundation() {
		ControllerImpl fixture = new Fixture(mockGameState) {
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
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newMoveCardTableauToTableauOp(int, int)
			 */
			@Override
			protected MoveCardTableauToTableauOp newMoveCardTableauToTableauOp(
					int srcFanIndex, int destFanIndex) {
				return mockControllerImpl.newMoveCardTableauToTableauOp(
						srcFanIndex, destFanIndex);
			}
		};
		final int srcFanIndex = 0;
		final int destFanIndex = 1;
		final MoveCardTableauToTableauOp mockMoveCardTableauToTableauOp =
				context.mock(MoveCardTableauToTableauOp.class,
						"mockMoveCardTableauToTableauOp");
		final UndoManager mockUndoManager =
				context.mock(UndoManager.class, "mockUndoManager");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).newMoveCardTableauToTableauOp(
					srcFanIndex, destFanIndex);
			will(returnValue(mockMoveCardTableauToTableauOp));
			
			oneOf(mockMoveCardTableauToTableauOp).doOp();
			
			oneOf(mockGameState).getUndoManager();
			will(returnValue(mockUndoManager));
			
			oneOf(mockUndoManager).add(mockMoveCardTableauToTableauOp);
		}});
		
		fixture.moveCardToTableau(srcFanIndex, destFanIndex);
	}

	@Test
	public void testMoveTopCardFoundationToTableau() {
		ControllerImpl fixture = new Fixture(mockGameState);
		final int srcFanIndex = 0;
		final int destFanIndex = 0;
		final Fan mockDestFan = context.mock(Fan.class, "mockDestFan");
		final Fan[] mockTableau = {mockDestFan};
		final Fan mockSrcFan = context.mock(Fan.class, "mockSrcFan");
		final Fan[] mockFoundation = {mockSrcFan};
		final int srcFanSize = 1;
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));

			oneOf(mockGameState).getFoundation();
			will(returnValue(mockFoundation));
			
			oneOf(mockSrcFan).size();
			will(returnValue(srcFanSize));
			
			oneOf(mockSrcFan).remove(srcFanIndex);
			will(returnValue(mockCard));
			
			oneOf(mockDestFan).add(mockCard);
		}});

		fixture.moveTopCardFoundationToTableau(srcFanIndex, destFanIndex);
	}
	
	@Test
	public void testMoveTopCardTableauToFoundation() {
		ControllerImpl fixture = new Fixture(mockGameState);
		final int srcFanIndex = 0;
		final int destFanIndex = 0;
		final Fan mockTableauFan = context.mock(Fan.class, "mockTableauFan");
		final Fan[] mockTableau = { mockTableauFan };
		final Fan mockFoundationFan =
				context.mock(Fan.class, "mockFoundationFan");
		final Fan[] mockFoundation = { mockFoundationFan };
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));

			oneOf(mockGameState).getFoundation();
			will(returnValue(mockFoundation));
			
			oneOf(mockTableauFan).size();
			will(returnValue(1));
			
			oneOf(mockTableauFan).remove(0);
			will(returnValue(mockCard));
			
			oneOf(mockFoundationFan).add(mockCard);
		}});
		
		fixture.moveTopCardTableauToFoundation(srcFanIndex, destFanIndex);
	}

	@Test
	public void testMoveTopCardTableauToTableau() {
		ControllerImpl fixture = new Fixture(mockGameState);
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
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewDrawOp() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewInputHandler() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewMoveCardTableauToFoundationOp() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewMoveCardTableauToTableauOp() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testNewMoveFinder() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This trivial constructor wrapper needs no testing.
		assertNotNull(fixture);
	}

	@Test
	public void testOnCardMoveRequested() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#indexOfTableauFanWithTopCard(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			protected int indexOfTableauFanWithTopCard(Card card) {
				return mockControllerImpl.indexOfTableauFanWithTopCard(card);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#moveCardToFoundation(int, int)
			 */
			@Override
			protected void moveCardToFoundation(int srcFanIndex,
					int destFanIndex) {
				mockControllerImpl.moveCardToFoundation(srcFanIndex, destFanIndex);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#moveCardToTableau(int, int)
			 */
			@Override
			protected void moveCardToTableau(int srcFanIndex, int destFanIndex) {
				mockControllerImpl.moveCardToTableau(srcFanIndex, destFanIndex);
			}
		};
		final Card mockCard = context.mock(Card.class, "mockCard");
		final int sourceFanIndex = 1;
		@SuppressWarnings("unchecked")
		final List<Move> mockListOfMove = (List<Move>)
				context.mock(List.class, "mockListOfMove");
		@SuppressWarnings("unchecked")
		final Iterator<Move> mockIterator = (Iterator<Move>)
				context.mock(Iterator.class, "mockIterator");
		final Move mockMove = context.mock(Move.class, "mockMove");
		final int destFanIndex = 1;
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).indexOfTableauFanWithTopCard(mockCard);
			will(returnValue(sourceFanIndex));
			
			oneOf(mockMoveFinder).findSimpleMoves();
			will(returnValue(mockListOfMove));
			
			oneOf(mockListOfMove).iterator();
			will(returnValue(mockIterator));
			
			oneOf(mockIterator).hasNext();
			will(returnValue(true));
			
			oneOf(mockIterator).next();
			will(returnValue(mockMove));
			
			oneOf(mockMove).getSrcFanIndex();
			will(returnValue(sourceFanIndex));
			
			oneOf(mockMove).getType();
			will(returnValue(MoveType.TABLEAU_TO_FOUNDATION));
			
			oneOf(mockMove).getDestFanIndex();
			will(returnValue(destFanIndex));

			oneOf(mockControllerImpl).moveCardToFoundation(sourceFanIndex, destFanIndex);
			
			oneOf(mockIterator).hasNext();
			will(returnValue(false));
		}});
		
		fixture.onCardMoveRequested(mockCard);
	}

	@Test
	public void testOnDrawRequested() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#newDrawOp(int, int)
			 */
			@Override
			protected DrawOp newDrawOp(int fanIndex, int cardIndex) {
				return mockControllerImpl.newDrawOp(fanIndex, cardIndex);
			}
		};
		final IntegerProperty mockIntegerProperty =
				context.mock(IntegerProperty.class, "mockIntegerProperty");
		final Fan mockFan = context.mock(Fan.class, "mockFan");
		final Fan[] mockTableau = { mockFan };
		final Card mockCard = context.mock(Card.class, "mockCard");
		final DrawOp mockDrawOp = context.mock(DrawOp.class, "mockDrawOp");
		final UndoManager mockUndoManager =
				context.mock(UndoManager.class, "mockUndoManager");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(1));

			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockFan).indexOf(mockCard);
			will(returnValue(0));
			
			oneOf(mockControllerImpl).newDrawOp(0, 0);
			will(returnValue(mockDrawOp));
			
			oneOf(mockDrawOp).doOp();
			
			oneOf(mockGameState).getUndoManager();
			will(returnValue(mockUndoManager));
			
			oneOf(mockUndoManager).add(mockDrawOp);
		}});
		
		fixture.onDrawRequested(mockCard);
	}

	@Test
	public void testOnExitRequest() {
		ControllerImpl fixture = new Fixture(mockGameState);
		
		// This method just calls a static (thus unmockable) method;
		// no meaningful test is possible with our tool set.
		assertNotNull(fixture);
	}

	@Test
	public void testOnNewGameRequested() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#updateGameSummary()
			 */
			@Override
			public void updateGameSummary() {
				mockControllerImpl.updateGameSummary();
			}
		};
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).reset();

			oneOf(mockControllerImpl).updateGameSummary();
		}});
		
		fixture.onNewGameRequested();
	}

	@Test
	public void testOnReshuffleRequest() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#reshuffle()
			 */
			@Override
			protected void reshuffle() {
				mockControllerImpl.reshuffle();
			}
		};
		final IntegerProperty mockIntegerProperty =
				context.mock(IntegerProperty.class, "mockIntegerProperty");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(1));
			
			oneOf(mockControllerImpl).reshuffle();
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(1));
			
			oneOf(mockIntegerProperty).set(0);
		}});
		
		fixture.onReshuffleRequest();
	}

	@Test
	public void testReshuffle() {
		ControllerImpl fixture = new Fixture(mockGameState) {
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
		ControllerImpl fixture = new Fixture(mockGameState);
		final Fan mockFan = context.mock(Fan.class, "mockFan");
		final Fan[] mockTableau = { mockFan };
		final Card mockCard = context.mock(Card.class, "mockCard");
		final IntegerProperty mockIntegerProperty =
				context.mock(IntegerProperty.class, "mockIntegerProperty");
		@SuppressWarnings("unchecked")
		final ObjectProperty<GameSummary> mockGameSummary =
				(ObjectProperty<GameSummary>)
				context.mock(ObjectProperty.class, "mockGameSummary");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockFan).size();
			will(returnValue(2));
			
			oneOf(mockFan).remove(1);
			will(returnValue(mockCard));
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockIntegerProperty));
			
			oneOf(mockFan).add(0, mockCard);
			
			oneOf(mockIntegerProperty).get();
			will(returnValue(0));
			
			oneOf(mockIntegerProperty).set(1);
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.IN_PROGRESS);
		}});
		
		fixture.unDraw(0, 0);
	}

	@Test
	public void testUpdateGameSummary() {
		ControllerImpl fixture = new Fixture(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.ControllerImpl#isGameWon()
			 */
			@Override
			protected boolean isGameWon() {
				return mockControllerImpl.isGameWon();
			}
		};

		/*
		 * This will test a number of cases:
		 * 1) Game has been won ==> WON.
		 * 2) Game has not been won, but there are legal moves ==> IN_PROGRESS.
		 * 3) Game has not been won and there are no legal moves ==> LOST.
		 */
		
		@SuppressWarnings("unchecked")
		final ObjectProperty<GameSummary> mockGameSummary =
				(ObjectProperty<GameSummary>)
					context.mock(ObjectProperty.class, "mockGameSummary");
		@SuppressWarnings("unchecked")
		final List<Move> mockMoves =
				(List<Move>) context.mock(List.class, "mockMoves");
		
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
			
			oneOf(mockMoveFinder).findMoves();
			will(returnValue(mockMoves));
			
			oneOf(mockMoves).isEmpty();
			will(returnValue(false));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.IN_PROGRESS);

			/*
			 * case 3
			 */

			oneOf(mockControllerImpl).isGameWon();
			will(returnValue(false));
			
			oneOf(mockMoveFinder).findMoves();
			will(returnValue(mockMoves));
			
			oneOf(mockMoves).isEmpty();
			will(returnValue(true));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.LOST);
		}});
		
		fixture.updateGameSummary();	// case 1
		fixture.updateGameSummary();	// case 2
		fixture.updateGameSummary();	// case 3
	}
}
