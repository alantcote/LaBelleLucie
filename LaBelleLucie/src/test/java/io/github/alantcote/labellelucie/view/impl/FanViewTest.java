package io.github.alantcote.labellelucie.view.impl;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.labellelucie.view.impl.FanView;
import io.github.alantcote.labellelucie.view.impl.support.FanBinding;
import io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class FanViewTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
	protected class Fixture extends FanView {
		public Fixture(CardViewFactory cardViewFactory, double fanOffset,
				Fan model) {
			super(cardViewFactory, fanOffset, model);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#setModel(io.github.alantcote.labellelucie.model.facade.Fan)
		 */
		@Override
		public void setModel(Fan model) {
			mockFanView.setModel(model);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#applyMinHeight(double)
		 */
		@Override
		protected void applyMinHeight(double value) {
			mockFanView.applyMinHeight(value);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#applyMinWidth(double)
		 */
		@Override
		protected void applyMinWidth(double value) {
			mockFanView.applyMinWidth(value);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#applyPadding(javafx.geometry.Insets)
		 */
		@Override
		protected void applyPadding(Insets insets) {
			mockFanView.applyPadding(insets);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#applyPrefHeight(double)
		 */
		@Override
		protected void applyPrefHeight(double value) {
			mockFanView.applyPrefHeight(value);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#applyPrefWidth(double)
		 */
		@Override
		protected void applyPrefWidth(double value) {
			mockFanView.applyPrefWidth(value);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#getHeight(javafx.geometry.Dimension2D)
		 */
		@Override
		protected double getHeight(Dimension2D d2d) {
			return mockFanView.getHeight(d2d);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#getWidth(javafx.geometry.Dimension2D)
		 */
		@Override
		protected double getWidth(Dimension2D d2d) {
			return mockFanView.getWidth(d2d);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#newFanBinding(io.github.alantcote.labellelucie.view.impl.FanView)
		 */
		@Override
		protected FanBinding newFanBinding(FanView supported) {
			return mockFanView.newFanBinding(supported);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#newInputHandlerSupport(javafx.scene.Node)
		 */
		@Override
		protected InputHandlerSupport newInputHandlerSupport(Node supported) {
			return mockFanView.newInputHandlerSupport(supported);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#newInsets(double)
		 */
		@Override
		protected Insets newInsets(double topRightBottomLeft) {
			return mockFanView.newInsets(topRightBottomLeft);
		}

		/* (non-Javadoc)
		 * @see io.github.alantcote.labellelucie.view.impl.FanView#setCSSId(java.lang.String)
		 */
		@Override
		protected void setCSSId(String id) {
			mockFanView.setCSSId(id);
		}
	}
	
	public static final double CARD_HEIGHT = 100;
	public static final double CARD_WIDTH = 80;
	public static final double FAN_OFFSET = 5;
	
	protected double cardHeight;
	protected double cardWidth;
	protected double fanOffset;
	protected double minHeight;
	protected double minWidth;
	protected CardViewFactory mockCardViewFactory;
	protected Dimension2D mockDimension2D;
	protected Fan mockFan;
	protected FanBinding mockFanBinding;
	protected FanView mockFanView;
	protected InputHandlerSupport mockInputHandlerSupport;
	protected Insets mockInsets;
	
	@Before
	public void setup() {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
		
		cardHeight = CARD_HEIGHT;
		cardWidth = CARD_WIDTH;
		fanOffset = FAN_OFFSET;
		minHeight = cardHeight + (FanView.MARGIN * 2);
		minWidth = cardWidth + (FanView.MARGIN * 2) +
				(fanOffset * (FanView.MAX_FAN_SIZE - 1));
		mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		mockDimension2D = context.mock(Dimension2D.class, "mockDimension2D");
		mockFan = context.mock(Fan.class, "mockFan");
		mockFanBinding =
				context.mock(FanBinding.class, "mockFanBinding");
		mockFanView = context.mock(FanView.class, "mockFanView");
		mockInputHandlerSupport = context.mock(
				InputHandlerSupport.class, "mockInputHandlerSupport");
		mockInsets = context.mock(Insets.class, "mockInsets");
		
		context.checking( new Expectations() {{
			oneOf(mockCardViewFactory).getDimensions();
			will(returnValue(mockDimension2D));
			
			oneOf(mockFanView).getHeight(mockDimension2D);
			will(returnValue(cardHeight));
			
			oneOf(mockFanView).getWidth(mockDimension2D);
			will(returnValue(cardWidth));
			
			oneOf(mockFanView).setCSSId(FanView.CSS_ID);
			
			oneOf(mockFanView).newInsets(FanView.MARGIN);
			will(returnValue(mockInsets));
			
			oneOf(mockFanView).applyPadding(mockInsets);
			
			oneOf(mockFanView).applyMinHeight(minHeight);
			
			oneOf(mockFanView).applyMinWidth(minWidth);
			
			oneOf(mockFanView).applyPrefHeight(minHeight);
			
			oneOf(mockFanView).applyPrefWidth(minWidth);
			
			oneOf(mockFanView).newFanBinding(with(any(FanView.class)));
			will(returnValue(mockFanBinding));
			
			oneOf(mockFanView).newInputHandlerSupport(with(any(FanView.class)));
			will(returnValue(mockInputHandlerSupport));
			
			oneOf(mockFanView).setModel(mockFan);
		}});
	}

	@Test
	public void testAnchorLeft() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testAnchorTop() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testFanView() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		assertEquals(mockCardViewFactory, fixture.cardViewFactory);
		assertEquals(mockFanBinding, fixture.fanBinding);
		assertEquals(fanOffset, fixture.fanOffset, Double.MIN_VALUE);
		assertEquals(mockInputHandlerSupport, fixture.inputHandlerSupport);
	}

	@Test
	public void testGetCardViewFactory() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		assertEquals(mockCardViewFactory, fixture.getCardViewFactory());
	}

	@Test
	public void testGetFanOffset() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		assertEquals(fanOffset, fixture.getFanOffset(), Double.MIN_VALUE);
	}

	@Test
	public void testGetInputHandler() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		final InputHandler mockInputHandler =
				context.mock(InputHandler.class, "mockInputHandler");
		
		context.checking( new Expectations() {{
			oneOf(mockInputHandlerSupport).getInputHandler();
			will(returnValue(mockInputHandler));
		}});
		
		assertEquals(mockInputHandler, fixture.getInputHandler());
	}

	@Test
	public void testGetModel() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		fixture.model = mockFan;
		assertEquals(mockFan, fixture.getModel());
	}

	@Test
	public void testInstallTooltip() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testLayoutChildren() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testNewFanBinding() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testNewImageView() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testNewInputHandlerSupport() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testNewInsets() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testNewTooltip() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testReloadChildren() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan) {
			@Override
			public ObservableList<Node> getChildren() {
				return mockFanView.getChildren();
			}

			@Override
			public InputHandler getInputHandler() {
				return mockFanView.getInputHandler();
			}

			@Override
			protected Image doGetImage(CardView cardView) {
				return mockFanView.doGetImage(cardView);
			}

			@Override
			protected void doSetGraphic(Tooltip tooltip, Node node) {
				mockFanView.doSetGraphic(tooltip, node);
			}

			@Override
			protected void installTooltip(Node node, Tooltip tooltip) {
				mockFanView.installTooltip(node, tooltip);
			}

			@Override
			protected ImageView newImageView(Image image) {
				return mockFanView.newImageView(image);
			}

			@Override
			protected Tooltip newTooltip() {
				return mockFanView.newTooltip();
			}
		};
		@SuppressWarnings("unchecked")
		final ObservableList<Node> kids = (ObservableList<Node>)
				context.mock(ObservableList.class, "kids");
		final InputHandler mockInputHandler =
				context.mock(InputHandler.class, "mockInputHandler");
		@SuppressWarnings("unchecked")
		final Iterator<Card> mockIterator_Card = (Iterator<Card>)
				context.mock(Iterator.class, "mockIterator_Card");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final CardView mockCardView = context.mock(CardView.class, "mockCardView");
		final Tooltip mockTooltip = context.mock(Tooltip.class, "mockTooltip");
		final Image mockImage = context.mock(Image.class, "mockImage");
		final ImageView mockImageView =
				context.mock(ImageView.class, "mockImageView");
		
		context.checking( new Expectations() {{
			oneOf(mockFanView).getChildren();
			will(returnValue(kids));

			oneOf(mockFanView).getInputHandler();
			will(returnValue(mockInputHandler));
			
			oneOf(kids).size();
			will(returnValue(0));
			
			oneOf(kids).clear();
			
			oneOf(mockFan).size();
			will(returnValue(1));
			
			oneOf(mockFan).iterator();
			will(returnValue(mockIterator_Card));
			
			oneOf(mockIterator_Card).hasNext();
			will(returnValue(true));
			
			oneOf(mockIterator_Card).next();
			will(returnValue(mockCard));
			
			oneOf(mockCardViewFactory).getFrontView(mockCard);
			will(returnValue(mockCardView));
			
			oneOf(mockFanView).newInputHandlerSupport(mockCardView);
			will(returnValue(mockInputHandlerSupport));

			oneOf(mockFanView).newTooltip();
			will(returnValue(mockTooltip));

			oneOf(mockFanView).doGetImage(mockCardView);
			will(returnValue(mockImage));

			oneOf(mockFanView).newImageView(mockImage);
			will(returnValue(mockImageView));

			oneOf(mockFanView).doSetGraphic(mockTooltip, mockImageView);

			oneOf(mockFanView).installTooltip(mockCardView, mockTooltip);
			
			oneOf(kids).size();
			will(returnValue(0));
			
			oneOf(kids).add(mockCardView);
			
			oneOf(mockInputHandlerSupport).setInputHandler(mockInputHandler);
			
			oneOf(mockIterator_Card).hasNext();
			will(returnValue(false));
		}});
		
		fixture.model = mockFan;
		fixture.reloadChildren();
	}

	@Test
	public void testSetCardViewFactory() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		CardViewFactory expected =
				context.mock(CardViewFactory.class, "expected");
		
		fixture.setCardViewFactory(expected);
		
		assertEquals(expected, fixture.cardViewFactory);
	}

	@Test
	public void testSetCSSId() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testSetFanOffset() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		double expected = 3.1415926;
		
		fixture.setFanOffset(expected);
		
		assertEquals(expected, fixture.fanOffset, Double.MIN_VALUE);
	}

	@Test
	public void testSetInputHandler() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan) {
			@Override
			public void reloadChildren() {
				mockFanView.reloadChildren();
			}
		};
		final InputHandler mockInputHandler =
				context.mock(InputHandler.class, "mockInputHandler");
		
		context.checking( new Expectations() {{
			oneOf(mockInputHandlerSupport).setInputHandler(mockInputHandler);
			
			oneOf(mockFanView).reloadChildren();
		}});
		
		fixture.setInputHandler(mockInputHandler);
	}

	@Test
	public void testSetModel() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// A simple setter need not be tested here.
		
		assertEquals(fixture, fixture);
	}
}
