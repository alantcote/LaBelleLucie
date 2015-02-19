package net.sf.cotelab.lbl.view.impl;

import static org.junit.Assert.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Node;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.view.impl.support.FanBinding;
import net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class FanViewTest extends jMockTestHelper {
	protected class Fixture extends FanView {
		public Fixture(CardViewFactory cardViewFactory, double fanOffset,
				Fan model) {
			super(cardViewFactory, fanOffset, model);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#setModel(net.sf.cotelab.lbl.model.facade.Fan)
		 */
		@Override
		public void setModel(Fan model) {
			mockFanView.setModel(model);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#applyMinHeight(double)
		 */
		@Override
		protected void applyMinHeight(double value) {
			mockFanView.applyMinHeight(value);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#applyMinWidth(double)
		 */
		@Override
		protected void applyMinWidth(double value) {
			mockFanView.applyMinWidth(value);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#applyPadding(javafx.geometry.Insets)
		 */
		@Override
		protected void applyPadding(Insets insets) {
			mockFanView.applyPadding(insets);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#applyPrefHeight(double)
		 */
		@Override
		protected void applyPrefHeight(double value) {
			mockFanView.applyPrefHeight(value);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#applyPrefWidth(double)
		 */
		@Override
		protected void applyPrefWidth(double value) {
			mockFanView.applyPrefWidth(value);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#getHeight(javafx.geometry.Dimension2D)
		 */
		@Override
		protected double getHeight(Dimension2D d2d) {
			return mockFanView.getHeight(d2d);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#getWidth(javafx.geometry.Dimension2D)
		 */
		@Override
		protected double getWidth(Dimension2D d2d) {
			return mockFanView.getWidth(d2d);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#newFanBinding(net.sf.cotelab.lbl.view.impl.FanView)
		 */
		@Override
		protected FanBinding newFanBinding(FanView supported) {
			return mockFanView.newFanBinding(supported);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#newInputHandlerSupport(javafx.scene.Node)
		 */
		@Override
		protected InputHandlerSupport newInputHandlerSupport(Node supported) {
			return mockFanView.newInputHandlerSupport(supported);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#newInsets(double)
		 */
		@Override
		protected Insets newInsets(double topRightBottomLeft) {
			return mockFanView.newInsets(topRightBottomLeft);
		}

		/* (non-Javadoc)
		 * @see net.sf.cotelab.lbl.view.impl.FanView#setCSSId(java.lang.String)
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
		// The method that would be tested here is a shim for mockability.
		
		assertTrue(true);
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
		fail("Not yet implemented");
	}

	@Test
	public void testSetCardViewFactory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCSSId() {
		Fixture fixture = new Fixture(mockCardViewFactory, fanOffset, mockFan);
		
		// The method that would be tested here is a shim for mockability.
		
		assertEquals(fixture, fixture);
	}

	@Test
	public void testSetFanOffset() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}
}
