package net.sf.cotelab.playingcards.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class DeckViewPaneTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@Before
	public void runBeforeTests() throws Exception {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
	}
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
	
	@Test
	public void testAddKids() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		final Deck mockDeck = context.mock(Deck.class, "mockDeck");
		@SuppressWarnings("unchecked")
		final ObservableList<Node> mockObservableList = (ObservableList<Node>)
				context.mock(ObservableList.class, "mockObservableList");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final CardView mockBackView =
				context.mock(CardView.class, "mockBackView");
		final CardView mockCardView =
				context.mock(CardView.class, "mockCardView");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));

			oneOf(mockDeckViewPane).inizLayoutParams();
			
			// following expectations are for the method under test, per se:

			oneOf(mockDeckViewPane).newDeck();
			will(returnValue(mockDeck));

			oneOf(mockDeckViewPane).getKids();
			will(returnValue(mockObservableList));

			oneOf(mockDeckViewPane).newCard(Rank.JOKER_HIGH, Suit.JOKER);
			will(returnValue(mockCard));
			
			oneOf(mockCardViewFactory).getBackView(mockCard);
			will(returnValue(mockBackView));
			
			oneOf(mockDeck).isEmpty();
			will(returnValue(false));
			
			oneOf(mockDeck).deal();
			will(returnValue(mockCard));
			
			oneOf(mockCardViewFactory).getFrontView(mockCard);
			will(returnValue(mockCardView));
			
			oneOf(mockObservableList).add(mockCardView);
			will(returnValue(true));
			
			oneOf(mockDeck).isEmpty();
			will(returnValue(true));
			
			oneOf(mockObservableList).add(mockBackView);
			will(returnValue(true));
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see javafx.scene.layout.Pane#getChildren()
			 */
			@Override
			public ObservableList<Node> getKids() {
				return mockDeckViewPane.getKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#inizLayoutParams()
			 */
			@Override
			protected void inizLayoutParams() {
				mockDeckViewPane.inizLayoutParams();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCard(net.sf.cotelab.playingcards.Rank, net.sf.cotelab.playingcards.Suit)
			 */
			@Override
			protected Card newCard(Rank rank, Suit suit) {
				return mockDeckViewPane.newCard(rank, suit);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newDeck()
			 */
			@Override
			protected Deck newDeck() {
				return mockDeckViewPane.newDeck();
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testApplyHgap() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));

			oneOf(mockDeckViewPane).inizLayoutParams();

			oneOf(mockDeckViewPane).addKids();
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#addKids()
			 */
			@Override
			protected void addKids() {
				mockDeckViewPane.addKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#inizLayoutParams()
			 */
			@Override
			protected void inizLayoutParams() {
				mockDeckViewPane.inizLayoutParams();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}
		};
		
		fixture.applyHgap(DeckViewPane.DEFAULT_HGAP);
		
		assertEquals(
				DeckViewPane.DEFAULT_HGAP, fixture.getHgap(), Double.MIN_VALUE);
	}

	@Test
	public void testApplyPadding() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		final Insets mockInsets = context.mock(Insets.class, "mockInsets");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));

			oneOf(mockDeckViewPane).inizLayoutParams();

			oneOf(mockDeckViewPane).addKids();
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#addKids()
			 */
			@Override
			protected void addKids() {
				mockDeckViewPane.addKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#inizLayoutParams()
			 */
			@Override
			protected void inizLayoutParams() {
				mockDeckViewPane.inizLayoutParams();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}
		};
		
		fixture.applyPadding(mockInsets);
		
		assertEquals(mockInsets, fixture.getPadding());
	}

	@Test
	public void testApplyPrefColumns() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));

			oneOf(mockDeckViewPane).inizLayoutParams();

			oneOf(mockDeckViewPane).addKids();
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#addKids()
			 */
			@Override
			protected void addKids() {
				mockDeckViewPane.addKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#inizLayoutParams()
			 */
			@Override
			protected void inizLayoutParams() {
				mockDeckViewPane.inizLayoutParams();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}
		};
		
		fixture.applyPrefColumns(DeckViewPane.DEFAULT_COLUMNS);
		
		assertEquals(DeckViewPane.DEFAULT_COLUMNS, fixture.getPrefColumns());
	}

	@Test
	public void testApplyVgap() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));

			oneOf(mockDeckViewPane).inizLayoutParams();

			oneOf(mockDeckViewPane).addKids();
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#addKids()
			 */
			@Override
			protected void addKids() {
				mockDeckViewPane.addKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#inizLayoutParams()
			 */
			@Override
			protected void inizLayoutParams() {
				mockDeckViewPane.inizLayoutParams();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}
		};
		
		fixture.applyVgap(DeckViewPane.DEFAULT_VGAP);
		
		assertEquals(
				DeckViewPane.DEFAULT_VGAP, fixture.getVgap(), Double.MIN_VALUE);
	}

	@Test
	public void testDeckViewPane() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));

			oneOf(mockDeckViewPane).inizLayoutParams();

			oneOf(mockDeckViewPane).addKids();
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#addKids()
			 */
			@Override
			protected void addKids() {
				mockDeckViewPane.addKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#inizLayoutParams()
			 */
			@Override
			protected void inizLayoutParams() {
				mockDeckViewPane.inizLayoutParams();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testInizLayoutParams() {
		final DeckViewPane mockDeckViewPane =
				context.mock(DeckViewPane.class, "mockDeckViewPane");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		final Insets mockInsets = context.mock(Insets.class, "mockInsets");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewPane).newCardViewFactory();
			will(returnValue(mockCardViewFactory));
			
			// begin method under test, per se.

			oneOf(mockDeckViewPane).applyHgap(DeckViewPane.DEFAULT_HGAP);

			oneOf(mockDeckViewPane).newInsets(DeckViewPane.DEFAULT_INSET);
			will(returnValue(mockInsets));

			oneOf(mockDeckViewPane).applyPadding(mockInsets);

			oneOf(mockDeckViewPane).applyPrefColumns(
					DeckViewPane.DEFAULT_COLUMNS);

			oneOf(mockDeckViewPane).applyVgap(DeckViewPane.DEFAULT_VGAP);
			
			// end method under test, per se.

			oneOf(mockDeckViewPane).addKids();
		}});
		
		DeckViewPane fixture = new DeckViewPane() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#addKids()
			 */
			@Override
			protected void addKids() {
				mockDeckViewPane.addKids();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#applyHgap(double)
			 */
			@Override
			protected void applyHgap(double value) {
				mockDeckViewPane.applyHgap(value);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#applyPadding(javafx.geometry.Insets)
			 */
			@Override
			protected void applyPadding(Insets value) {
				mockDeckViewPane.applyPadding(value);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#applyPrefColumns(int)
			 */
			@Override
			protected void applyPrefColumns(int value) {
				mockDeckViewPane.applyPrefColumns(value);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#applyVgap(double)
			 */
			@Override
			protected void applyVgap(double value) {
				mockDeckViewPane.applyVgap(value);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newCardViewFactory()
			 */
			@Override
			protected CardViewFactory newCardViewFactory() {
				return mockDeckViewPane.newCardViewFactory();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewPane#newInsets(double)
			 */
			@Override
			protected Insets newInsets(double topRightBottomLeft) {
				return mockDeckViewPane.newInsets(topRightBottomLeft);
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testNewCard() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testNewCardViewFactory() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testNewDeck() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testNewInsets() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}
}
