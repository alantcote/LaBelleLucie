package net.sf.cotelab.lbl.controller.impl;

import static org.junit.Assert.*;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class MoveFinderTest extends jMockTestHelper {
	protected GameState mockGameState;
	protected MoveFinder mockMoveFinder;
	
	@Before
	public void setup() {
		mockGameState = context.mock(GameState.class, "mockGameState");
		mockMoveFinder = context.mock(MoveFinder.class, "mockMoveFinder");
	}

	@Test
	public void testCanPlayOnFoundation() {
		/*
		 * Multiple cases:
		 * 1) Old top card is null and old rank is not ACE (result: false).
		 * 2) Old top card is null and old rank isACE (result: true).
		 * 3) Card suits do not match (result: false).
		 * 4) Card suits match, but ranks are wrong (result: false).
		 * 5) Card suits match and ranks are right (result: true).
		 */
		MoveFinder fixture = new MoveFinder(mockGameState);
		final Card newTopCard = context.mock(Card.class, "newTopCard");
		final Card oldTopCard = context.mock(Card.class, "oldTopCard");
		
		context.checking(new Expectations() {{
			/*
			 * case 1:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));

			/*
			 * case 2:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.ACE));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));

			/*
			 * case 3:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));
			
			oneOf(oldTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(oldTopCard).getSuit();
			will(returnValue(Suit.SPADE));

			/*
			 * case 4:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));
			
			oneOf(oldTopCard).getRank();
			will(returnValue(Rank.FIVE));
			
			oneOf(oldTopCard).getSuit();
			will(returnValue(Suit.HEART));

			/*
			 * case 5:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));
			
			oneOf(oldTopCard).getRank();
			will(returnValue(Rank.SEVEN));
			
			oneOf(oldTopCard).getSuit();
			will(returnValue(Suit.HEART));
		}});
		
		assertFalse(fixture.canPlayOnFoundation(newTopCard, null));
		assertTrue(fixture.canPlayOnFoundation(newTopCard, null));
		assertFalse(fixture.canPlayOnFoundation(newTopCard, oldTopCard));
		assertFalse(fixture.canPlayOnFoundation(newTopCard, oldTopCard));
		assertTrue(fixture.canPlayOnFoundation(newTopCard, oldTopCard));
	}

	@Test
	public void testCanPlayOnTableau() {
		/*
		 * Multiple cases:
		 * 1) Old top card is null (result: false).
		 * 2) Card suits do not match (result: false).
		 * 3) Card suits match, but ranks are wrong (result: false).
		 * 4) Card suits match and ranks are right (result: true).
		 */
		MoveFinder fixture = new MoveFinder(mockGameState);
		final Card newTopCard = context.mock(Card.class, "newTopCard");
		final Card oldTopCard = context.mock(Card.class, "oldTopCard");
		
		context.checking(new Expectations() {{
			/*
			 * case 1:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));

			/*
			 * case 2:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));
			
			oneOf(oldTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(oldTopCard).getSuit();
			will(returnValue(Suit.SPADE));

			/*
			 * case 3:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));
			
			oneOf(oldTopCard).getRank();
			will(returnValue(Rank.FIVE));
			
			oneOf(oldTopCard).getSuit();
			will(returnValue(Suit.HEART));

			/*
			 * case 4:
			 */
			
			oneOf(newTopCard).getRank();
			will(returnValue(Rank.EIGHT));
			
			oneOf(newTopCard).getSuit();
			will(returnValue(Suit.HEART));
			
			oneOf(oldTopCard).getRank();
			will(returnValue(Rank.NINE));
			
			oneOf(oldTopCard).getSuit();
			will(returnValue(Suit.HEART));
		}});
		
		assertFalse(fixture.canPlayOnTableau(newTopCard, null));
		assertFalse(fixture.canPlayOnTableau(newTopCard, oldTopCard));
		assertFalse(fixture.canPlayOnTableau(newTopCard, oldTopCard));
		assertTrue(fixture.canPlayOnTableau(newTopCard, oldTopCard));
	}

	@Test
	public void testFindMoves() {
		MoveFinder fixture = new MoveFinder(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#findSimpleMoves()
			 */
			@Override
			public List<Move> findSimpleMoves() {
				return mockMoveFinder.findSimpleMoves();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#newListOfMove()
			 */
			@Override
			protected List<Move> newListOfMove() {
				return mockMoveFinder.newListOfMove();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#newMove(int, int, net.sf.cotelab.lbl.controller.impl.MoveType)
			 */
			@Override
			protected Move newMove(int destFanIndex, int srcFanIndex,
					MoveType type) {
				return mockMoveFinder.newMove(destFanIndex, srcFanIndex, type);
			}
		};
		
		@SuppressWarnings("unchecked")
		final List<Move> mockMoves = (List<Move>)
				context.mock(List.class, "mockListOfMove");
		@SuppressWarnings("unchecked")
		final List<Move> mockSimpleMoves = (List<Move>)
				context.mock(List.class, "mockSimpleMoves");
		final IntegerProperty mockRedealsRemaining =
				context.mock(IntegerProperty.class, "mockRedealsRemaining");
		final IntegerProperty mockDrawsRemaining =
				context.mock(IntegerProperty.class, "mockDrawsRemaining");
		final Move mockMove = context.mock(Move.class, "mockMove");

		/*
		 * Multiple cases:
		 * 1) No redeals or draws remain.
		 * 2) No redeals remain, but one draw remains.
		 * 3) No draws remain, but one redeal remains.
		 * 4) Both redeals and draws remain.
		 */
		context.checking(new Expectations() {{
			/*
			 * Case 1:
			 */
			
			oneOf(mockMoveFinder).newListOfMove();
			will(returnValue(mockMoves));
			
			oneOf(mockMoveFinder).findSimpleMoves();
			will(returnValue(mockSimpleMoves));
			
			oneOf(mockMoves).addAll(mockSimpleMoves);
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockRedealsRemaining));
			
			oneOf(mockRedealsRemaining).get();
			will(returnValue(0));
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockDrawsRemaining));
			
			oneOf(mockDrawsRemaining).get();
			will(returnValue(0));
			
			/*
			 * Case 2:
			 */
			
			oneOf(mockMoveFinder).newListOfMove();
			will(returnValue(mockMoves));
			
			oneOf(mockMoveFinder).findSimpleMoves();
			will(returnValue(mockSimpleMoves));
			
			oneOf(mockMoves).addAll(mockSimpleMoves);
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockRedealsRemaining));
			
			oneOf(mockRedealsRemaining).get();
			will(returnValue(0));
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockDrawsRemaining));
			
			oneOf(mockDrawsRemaining).get();
			will(returnValue(1));
			
			oneOf(mockMoveFinder).newMove(0, 0, MoveType.DRAW);
			will(returnValue(mockMove));
			
			oneOf(mockMoves).add(mockMove);
			
			/*
			 * Case 3:
			 */
			
			oneOf(mockMoveFinder).newListOfMove();
			will(returnValue(mockMoves));
			
			oneOf(mockMoveFinder).findSimpleMoves();
			will(returnValue(mockSimpleMoves));
			
			oneOf(mockMoves).addAll(mockSimpleMoves);
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockRedealsRemaining));
			
			oneOf(mockRedealsRemaining).get();
			will(returnValue(1));
			
			oneOf(mockMoveFinder).newMove(0, 0, MoveType.RESHUFFLE);
			will(returnValue(mockMove));
			
			oneOf(mockMoves).add(mockMove);
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockDrawsRemaining));
			
			oneOf(mockDrawsRemaining).get();
			will(returnValue(0));
			
			/*
			 * Case 4:
			 */
			
			oneOf(mockMoveFinder).newListOfMove();
			will(returnValue(mockMoves));
			
			oneOf(mockMoveFinder).findSimpleMoves();
			will(returnValue(mockSimpleMoves));
			
			oneOf(mockMoves).addAll(mockSimpleMoves);
			
			oneOf(mockGameState).getRedealsRemaining();
			will(returnValue(mockRedealsRemaining));
			
			oneOf(mockRedealsRemaining).get();
			will(returnValue(1));
			
			oneOf(mockMoveFinder).newMove(0, 0, MoveType.RESHUFFLE);
			will(returnValue(mockMove));
			
			oneOf(mockMoves).add(mockMove);
			
			oneOf(mockGameState).getDrawsRemaining();
			will(returnValue(mockDrawsRemaining));
			
			oneOf(mockDrawsRemaining).get();
			will(returnValue(1));
			
			oneOf(mockMoveFinder).newMove(0, 0, MoveType.DRAW);
			will(returnValue(mockMove));
			
			oneOf(mockMoves).add(mockMove);
		}});
		
		assertEquals(mockMoves, fixture.findMoves());
		assertEquals(mockMoves, fixture.findMoves());
		assertEquals(mockMoves, fixture.findMoves());
		assertEquals(mockMoves, fixture.findMoves());
	}

	@Test
	public void testFindMovesToFoundation() {
		MoveFinder fixture = new MoveFinder(mockGameState) {
			@Override
			public List<Move> findMovesToFoundation(int destIndex) {
				return mockMoveFinder.findMovesToFoundation(destIndex);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#newListOfMove()
			 */
			@Override
			protected List<Move> newListOfMove() {
				return mockMoveFinder.newListOfMove();
			}
		};
		
		@SuppressWarnings("unchecked")
		final List<Move> mockListOfMove = (List<Move>)
				context.mock(List.class, "mockListOfMove");
		@SuppressWarnings("unchecked")
		final List<Move> mockListOfMove2 = (List<Move>)
				context.mock(List.class, "mockListOfMove2");
		final Fan mockFan0 = context.mock(Fan.class, "mockFan0");
		final Fan mockFan1 = context.mock(Fan.class, "mockFan1");
		final Fan[] mockFoundation = { mockFan0, mockFan1 };
//		final Move mockMove = context.mock(Move.class, "mockMove");
		
		context.checking(new Expectations() {{
			oneOf(mockMoveFinder).newListOfMove();
			will(returnValue(mockListOfMove));
			
			oneOf(mockGameState).getFoundation();
			will(returnValue(mockFoundation));
			
			oneOf(mockMoveFinder).findMovesToFoundation(0);
			will(returnValue(mockListOfMove2));
			
			oneOf(mockListOfMove).addAll(mockListOfMove2);
			will(returnValue(true));
			
			oneOf(mockMoveFinder).findMovesToFoundation(1);
			will(returnValue(mockListOfMove2));
			
			oneOf(mockListOfMove).addAll(mockListOfMove2);
			will(returnValue(true));
		}});
		
		assertEquals(mockListOfMove, fixture.findMovesToFoundation());
	}

	@Test
	public void testFindMovesToTableau() {
		MoveFinder fixture = new MoveFinder(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#findMoveToFoundation(int)
			 */
			@Override
			public Move findMoveToTableau(int destIndex) {
				return mockMoveFinder.findMoveToTableau(destIndex);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#newListOfMove()
			 */
			@Override
			protected List<Move> newListOfMove() {
				return mockMoveFinder.newListOfMove();
			}
		};
		
		@SuppressWarnings("unchecked")
		final List<Move> mockListOfMove = (List<Move>)
				context.mock(List.class, "mockListOfMove");
		final Fan mockFan0 = context.mock(Fan.class, "mockFan0");
		final Fan mockFan1 = context.mock(Fan.class, "mockFan1");
		final Fan[] mockTableau = { mockFan0, mockFan1 };
		final Move mockMove = context.mock(Move.class, "mockMove");
		
		context.checking(new Expectations() {{
			oneOf(mockMoveFinder).newListOfMove();
			will(returnValue(mockListOfMove));
			
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockMoveFinder).findMoveToTableau(0);
			will(returnValue((Move) null));
			
			oneOf(mockMoveFinder).findMoveToTableau(1);
			will(returnValue(mockMove));
			
			oneOf(mockListOfMove).add(mockMove);
		}});
		
		assertEquals(mockListOfMove, fixture.findMovesToTableau());
	}

	@Test
	public void testFindMoveToFoundation() {
		MoveFinder fixture = new MoveFinder(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#canPlayOnFoundation(net.sf.cotelab.playingcards.Card, net.sf.cotelab.playingcards.Card)
			 */
			@Override
			public boolean canPlayOnFoundation(
					Card newTopCard, Card oldTopCard) {
				return mockMoveFinder.canPlayOnFoundation(
						newTopCard, oldTopCard);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#newMove(int, int, net.sf.cotelab.lbl.controller.impl.MoveType)
			 */
			@Override
			protected Move newMove(
					int destFanIndex, int srcFanIndex, MoveType type) {
				return mockMoveFinder.newMove(destFanIndex, srcFanIndex, type);
			}
		};
		
		final Fan mockDestFan = context.mock(Fan.class, "mockDestFan");
		final Fan mockSrcFan = context.mock(Fan.class, "mockSrcFan");
		final Fan[] mockFoundation = { mockDestFan };
		final Fan[] mockTableau = { mockSrcFan };
		final Card mockDestCard = context.mock(Card.class, "mockDestCard");
		final Card mockSrcCard = context.mock(Card.class, "mockSrcCard");
		final Move mockMove = context.mock(Move.class, "mockMove");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getFoundation();
			will(returnValue(mockFoundation));
			
			oneOf(mockDestFan).getTopCard();
			will(returnValue(mockDestCard));
			
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockSrcFan).getTopCard();
			will(returnValue(mockSrcCard));
			
			oneOf(mockMoveFinder).canPlayOnFoundation(
					mockSrcCard, mockDestCard);
			will(returnValue(true));
			
			oneOf(mockMoveFinder).newMove(0, 0, MoveType.TABLEAU_TO_FOUNDATION);
			will(returnValue(mockMove));
		}});
		
		fixture.findMovesToFoundation(0);
	}

	@Test
	public void testFindMoveToTableau() {
		MoveFinder fixture = new MoveFinder(mockGameState) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#canPlayOnTableau(net.sf.cotelab.playingcards.Card, net.sf.cotelab.playingcards.Card)
			 */
			@Override
			public boolean canPlayOnTableau(
					Card newTopCard, Card oldTopCard) {
				return mockMoveFinder.canPlayOnTableau(newTopCard, oldTopCard);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.controller.impl.MoveFinder#newMove(int, int, net.sf.cotelab.lbl.controller.impl.MoveType)
			 */
			@Override
			protected Move newMove(
					int destFanIndex, int srcFanIndex, MoveType type) {
				return mockMoveFinder.newMove(destFanIndex, srcFanIndex, type);
			}
		};
		
		final Fan mockDestFan = context.mock(Fan.class, "mockDestFan");
		final Fan mockSrcFan = context.mock(Fan.class, "mockSrcFan");
		final Fan[] mockTableau = { mockSrcFan, mockDestFan };
		final Card mockDestCard = context.mock(Card.class, "mockDestCard");
		final Card mockSrcCard = context.mock(Card.class, "mockSrcCard");
		final Move mockMove = context.mock(Move.class, "mockMove");
		
		context.checking(new Expectations() {{
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockDestFan).getTopCard();
			will(returnValue(mockDestCard));
			
			oneOf(mockSrcFan).getTopCard();
			will(returnValue(mockSrcCard));
			
			oneOf(mockMoveFinder).canPlayOnTableau(mockSrcCard, mockDestCard);
			will(returnValue(true));
			
			oneOf(mockMoveFinder).newMove(1, 0, MoveType.TABLEAU_TO_TABLEAU);
			will(returnValue(mockMove));
			oneOf(mockGameState).getTableau();
			will(returnValue(mockTableau));
			
			oneOf(mockDestFan).getTopCard();
			will(returnValue(mockDestCard));
			
			oneOf(mockSrcFan).getTopCard();
			will(returnValue(mockSrcCard));
			
			oneOf(mockMoveFinder).canPlayOnTableau(mockSrcCard, mockDestCard);
			will(returnValue(false));
			
			oneOf(mockDestFan).getTopCard();
			will(returnValue(mockDestCard));
			
			oneOf(mockMoveFinder).canPlayOnTableau(mockDestCard, mockDestCard);
			will(returnValue(false));
		}});
		
		assertEquals(mockMove, fixture.findMoveToTableau(1));
		assertNull(fixture.findMoveToTableau(1));
	}

	@Test
	public void testMoveFinder() {
		MoveFinder fixture = new MoveFinder(mockGameState);
		
		assertNotNull(fixture);
	}

	@Test
	public void testNewListOfMove() {
		// Just a wrapper around a constructor.
		
		assertTrue(true);
	}

	@Test
	public void testNewMove() {
		// Just a wrapper around a constructor.
		
		assertTrue(true);
	}
}
