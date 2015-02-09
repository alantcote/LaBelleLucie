package net.sf.cotelab.lbl.model.impl;

import static org.junit.Assert.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.undo.UndoManager;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class GameStateImplTest extends jMockTestHelper {
	@Test
	public void testDealTableau() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		final Card card0 = context.mock(Card.class, "card0");
		final Card card1 = context.mock(Card.class, "card1");
		final Card card2 = context.mock(Card.class, "card2");
		final Card card3 = context.mock(Card.class, "card3");
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();

			// during dealTableau . . .
			oneOf(tableauFan00).clear();
			
			oneOf(tableauFan01).clear();
			
			oneOf(tableauFan02).clear();
			
			oneOf(tableauFan03).clear();
			
			oneOf(tableauFan04).clear();
			
			oneOf(tableauFan05).clear();
			
			oneOf(tableauFan06).clear();
			
			oneOf(tableauFan07).clear();
			
			oneOf(tableauFan08).clear();
			
			oneOf(tableauFan09).clear();

			oneOf(tableauFan10).clear();
			
			oneOf(tableauFan11).clear();
			
			oneOf(tableauFan12).clear();
			
			oneOf(tableauFan13).clear();
			
			oneOf(tableauFan14).clear();
			
			oneOf(tableauFan15).clear();
			
			oneOf(tableauFan16).clear();
			
			oneOf(tableauFan17).clear();
			
			oneOf(pack).shuffle();
			
			oneOf(pack).isEmpty();
			will(returnValue(false));
			
			oneOf(pack).deal();
			will(returnValue(card0));
			
			oneOf(tableauFan00).add(with(card0));
			
			oneOf(pack).isEmpty();
			will(returnValue(false));
			
			oneOf(pack).deal();
			will(returnValue(card1));
			
			oneOf(tableauFan00).add(with(card1));
			
			oneOf(pack).isEmpty();
			will(returnValue(false));
			
			oneOf(pack).deal();
			will(returnValue(card2));
			
			oneOf(tableauFan00).add(with(card2));
			
			oneOf(pack).isEmpty();
			will(returnValue(false));
			
			oneOf(pack).deal();
			will(returnValue(card3));
			
			oneOf(tableauFan01).add(with(card3));
			
			oneOf(pack).isEmpty();
			will(returnValue(true));
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		fixture.dealTableau(pack);
	}

	@Test
	public void testGameStateImpl() {
		final GameStateImpl mockGameStateImpl = context.mock(GameStateImpl.class);
		
		context.checking(new Expectations() {{
			oneOf(mockGameStateImpl).inizMembers();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#inizMembers()
			 */
			@Override
			protected void inizMembers() {
				mockGameStateImpl.inizMembers();
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testGetDrawsRemaining() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertEquals(drawsRemaining, fixture.getDrawsRemaining());
	}

	@Test
	public void testGetFoundation() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertArrayEquals(foundation, fixture.getFoundation());
	}

	@Test
	public void testGetGameSummary() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertEquals(gameSummary, fixture.getGameSummary());
	}

	@Test
	public void testGetRedealsRemaining() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertEquals(redealsRemaining, fixture.getRedealsRemaining());
	}

	@Test
	public void testGetStock() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertEquals(stock, fixture.getStock());
	}

	@Test
	public void testGetTableau() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertArrayEquals(tableau, fixture.getTableau());
	}

	@Test
	public void testGetUndoManager() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		assertEquals(undoManager, fixture.getUndoManager());
	}

	@Test
	public void testInizMembers() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final ObjectProperty<GameSummary> gameSummary =
				new SimpleObjectProperty<GameSummary>(GameSummary.IN_PROGRESS);
		final Fan[] foundation = new Fan[4];
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		final Pack pack = context.mock(Pack.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(ObjectProperty.class);
		final Fan[] tableau = new Fan[18];
		final Fan tableauFan00 = context.mock(Fan.class, "tableauFan00");
		final Fan tableauFan01 = context.mock(Fan.class, "tableauFan01");
		final Fan tableauFan02 = context.mock(Fan.class, "tableauFan02");
		final Fan tableauFan03 = context.mock(Fan.class, "tableauFan03");
		final Fan tableauFan04 = context.mock(Fan.class, "tableauFan04");
		final Fan tableauFan05 = context.mock(Fan.class, "tableauFan05");
		final Fan tableauFan06 = context.mock(Fan.class, "tableauFan06");
		final Fan tableauFan07 = context.mock(Fan.class, "tableauFan07");
		final Fan tableauFan08 = context.mock(Fan.class, "tableauFan08");
		final Fan tableauFan09 = context.mock(Fan.class, "tableauFan09");
		final Fan tableauFan10 = context.mock(Fan.class, "tableauFan10");
		final Fan tableauFan11 = context.mock(Fan.class, "tableauFan11");
		final Fan tableauFan12 = context.mock(Fan.class, "tableauFan12");
		final Fan tableauFan13 = context.mock(Fan.class, "tableauFan13");
		final Fan tableauFan14 = context.mock(Fan.class, "tableauFan14");
		final Fan tableauFan15 = context.mock(Fan.class, "tableauFan15");
		final Fan tableauFan16 = context.mock(Fan.class, "tableauFan16");
		final Fan tableauFan17 = context.mock(Fan.class, "tableauFan17");
		final UndoManager undoManager = context.mock(UndoManager.class);
		
		context.checking(new Expectations() {{
			// during construction . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();

			// during explicit call . . .
			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(drawsRemaining));

			oneOf(mockGameStateImpl).newObjectProperty_GameSummary(
					with(GameSummary.IN_PROGRESS));
			will(returnValue(gameSummary));

			oneOf(mockGameStateImpl).newFanArray(with(4));
			will(returnValue(foundation));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan0));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan1));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan2));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(foundationFan3));

			oneOf(mockGameStateImpl).newIntegerProperty();
			will(returnValue(redealsRemaining));

			oneOf(mockGameStateImpl).newPack();
			will(returnValue(pack));

			oneOf(mockGameStateImpl).newObjectProperty_Deck();
			will(returnValue(stock));

			oneOf(mockGameStateImpl).newFanArray(with(18));
			will(returnValue(tableau));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan00));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan01));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan02));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan03));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan04));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan05));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan06));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan07));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan08));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan09));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan10));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan11));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan12));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan13));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan14));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan15));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan16));

			oneOf(mockGameStateImpl).newFan();
			will(returnValue(tableauFan17));

			oneOf(mockGameStateImpl).newUndoManager();
			will(returnValue(undoManager));

//			oneOf(mockGameStateImpl).reset();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#reset()
			 */
			@Override
			public void reset() {
				mockGameStateImpl.reset();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFan()
			 */
			@Override
			protected Fan newFan() {
				return mockGameStateImpl.newFan();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newFanArray(int)
			 */
			@Override
			protected Fan[] newFanArray(int dim) {
				return mockGameStateImpl.newFanArray(dim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newIntegerProperty()
			 */
			@Override
			protected IntegerProperty newIntegerProperty() {
				return mockGameStateImpl.newIntegerProperty();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_Deck()
			 */
			@Override
			protected ObjectProperty<Deck> newObjectProperty_Deck() {
				return mockGameStateImpl.newObjectProperty_Deck();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newObjectProperty_GameSummary(net.sf.cotelab.lbl.model.facade.GameSummary)
			 */
			@Override
			protected ObjectProperty<GameSummary>
					newObjectProperty_GameSummary(GameSummary gs) {
				return mockGameStateImpl.newObjectProperty_GameSummary(gs);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newPack()
			 */
			@Override
			protected Pack newPack() {
				return mockGameStateImpl.newPack();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#newUndoManager()
			 */
			@Override
			protected UndoManager newUndoManager() {
				return mockGameStateImpl.newUndoManager();
			}
		};
		
		fixture.inizMembers();
		
		assertEquals(drawsRemaining, fixture.drawsRemaining);
		assertEquals(gameSummary, fixture.gameSummary);
		assertEquals(4, fixture.foundation.length);
		assertEquals(foundationFan0, fixture.foundation[0]);
		assertEquals(foundationFan1, fixture.foundation[1]);
		assertEquals(foundationFan2, fixture.foundation[2]);
		assertEquals(foundationFan3, fixture.foundation[3]);
		assertEquals(redealsRemaining, fixture.redealsRemaining);
		assertEquals(pack, fixture.pack);
		assertEquals(stock, fixture.stock);
		assertEquals(18, fixture.tableau.length);
		assertEquals(tableauFan00, fixture.tableau[0]);
		assertEquals(tableauFan01, fixture.tableau[1]);
		assertEquals(tableauFan02, fixture.tableau[2]);
		assertEquals(tableauFan03, fixture.tableau[3]);
		assertEquals(tableauFan04, fixture.tableau[4]);
		assertEquals(tableauFan05, fixture.tableau[5]);
		assertEquals(tableauFan06, fixture.tableau[6]);
		assertEquals(tableauFan07, fixture.tableau[7]);
		assertEquals(tableauFan08, fixture.tableau[8]);
		assertEquals(tableauFan09, fixture.tableau[9]);
		assertEquals(tableauFan10, fixture.tableau[10]);
		assertEquals(tableauFan11, fixture.tableau[11]);
		assertEquals(tableauFan12, fixture.tableau[12]);
		assertEquals(tableauFan13, fixture.tableau[13]);
		assertEquals(tableauFan14, fixture.tableau[14]);
		assertEquals(tableauFan15, fixture.tableau[15]);
		assertEquals(tableauFan16, fixture.tableau[16]);
		assertEquals(tableauFan17, fixture.tableau[17]);
		assertEquals(undoManager, fixture.undoManager);
	}
	
	@Test
	public void testNewFanArray() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		int dim = 5;
		Fan[] actual;
		
		context.checking(new Expectations() {{
			oneOf(mockGameStateImpl).inizMembers();
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#inizMembers()
			 */
			@Override
			protected void inizMembers() {
				mockGameStateImpl.inizMembers();
			}
		};
		
		actual = fixture.newFanArray(dim);
		
		assertEquals(dim, actual.length);
	}

	@Test
	public void testReset() {
		final GameStateImpl mockGameStateImpl =
				context.mock(GameStateImpl.class);
		final IntegerProperty drawsRemaining =
				context.mock(IntegerProperty.class, "drawsRemaining");
		final Fan foundationFan0 = context.mock(Fan.class, "foundationFan0");
		final Fan foundationFan1 = context.mock(Fan.class, "foundationFan1");
		final Fan foundationFan2 = context.mock(Fan.class, "foundationFan2");
		final Fan foundationFan3 = context.mock(Fan.class, "foundationFan3");
		final Fan[] foundation = {
				foundationFan0,
				foundationFan1,
				foundationFan2,
				foundationFan3
		};
		final IntegerProperty redealsRemaining =
				context.mock(IntegerProperty.class, "redealsRemaining");
		@SuppressWarnings("unchecked")
		final ObjectProperty<Deck> stock =
				(ObjectProperty<Deck>) context.mock(
						ObjectProperty.class, "stock");
		final Deck pack = context.mock(Deck.class);
		@SuppressWarnings("unchecked")
		final ObjectProperty<GameSummary> gameSummary =
				(ObjectProperty<GameSummary>) context.mock(
						ObjectProperty.class, "gameSummary");
		
		context.checking(new Expectations() {{
			oneOf(mockGameStateImpl).inizMembers();
			
			oneOf(drawsRemaining).set(with(1));
			
			oneOf(foundationFan0).clear();
			
			oneOf(foundationFan1).clear();
			
			oneOf(foundationFan2).clear();
			
			oneOf(foundationFan3).clear();
			
			oneOf(redealsRemaining).set(with(2));
			
			oneOf(stock).set(with(pack));
			
			oneOf(mockGameStateImpl).dealTableau(with(pack));
			
			oneOf(stock).set(with((Deck) null));
			
			oneOf(stock).set(with(pack));
			
			oneOf(gameSummary).set(with(GameSummary.IN_PROGRESS));
		}});
		
		GameStateImpl fixture = new GameStateImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#dealTableau(net.sf.cotelab.playingcards.Deck)
			 */
			@Override
			public void dealTableau(Deck deck) {
				mockGameStateImpl.dealTableau(deck);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.GameStateImpl#inizMembers()
			 */
			@Override
			protected void inizMembers() {
				mockGameStateImpl.inizMembers();
			}
		};
		
		fixture.drawsRemaining = drawsRemaining;
		fixture.foundation = foundation;
		fixture.pack = pack;
		fixture.redealsRemaining = redealsRemaining;
		fixture.stock = stock;
		fixture.gameSummary = gameSummary;
		
		fixture.reset();
	}

}
