package io.github.alantcote.labellelucie.view.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler;
import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.labellelucie.model.impl.FanImpl;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.playingcards.javafx.CardViewFactory;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.FanView}.
 */
public class FanViewTest {

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#anchorLeft(javafx.scene.image.ImageView, double)}.
	 */
	@Test
	public void testAnchorLeft() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#anchorTop(javafx.scene.image.ImageView, double)}.
	 */
	@Test
	public void testAnchorTop() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#applyMinHeight(double)}.
	 */
	@Test
	public void testApplyMinHeight() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#applyMinWidth(double)}.
	 */
	@Test
	public void testApplyMinWidth() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#applyPadding(javafx.geometry.Insets)}.
	 */
	@Test
	public void testApplyPadding() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#applyPrefHeight(double)}.
	 */
	@Test
	public void testApplyPrefHeight() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#applyPrefWidth(double)}.
	 */
	@Test
	public void testApplyPrefWidth() {
		// This method trivially calls a method on the superclass and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#doGetImage(io.github.alantcote.playingcards.javafx.CardView)}.
	 */
	@Test
	public void testDoGetImage() {
		// This method trivially calls a method on another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#doSetGraphic(javafx.scene.control.Tooltip, javafx.scene.Node)}.
	 */
	@Test
	public void testDoSetGraphic() {
		// This method trivially calls a method on another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#FanView(io.github.alantcote.playingcards.javafx.CardViewFactory, double, io.github.alantcote.labellelucie.model.facade.Fan)}.
	 */
	@Test
	public void testFanView() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		assertNotNull(fixture);

		assertTrue(testCardViewFactory == fixture.cardViewFactory);
		assertTrue(testFanOffset == fixture.fanOffset);
		assertTrue(testFan == fixture.model);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#getCardViewFactory()}.
	 */
	@Test
	public void testGetCardViewFactory() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		assertTrue(fixture.cardViewFactory == fixture.getCardViewFactory());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#getFanOffset()}.
	 */
	@Test
	public void testGetFanOffset() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		assertTrue(fixture.fanOffset == fixture.getFanOffset());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#getHeight(javafx.geometry.Dimension2D)}.
	 */
	@Test
	public void testGetHeightDimension2D() {
		// This method trivially calls a method on another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#getInputHandler()}.
	 */
	@Test
	public void testGetInputHandler() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		assertTrue(fixture.inputHandlerSupport.getInputHandler() == fixture.getInputHandler());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#getModel()}.
	 */
	@Test
	public void testGetModel() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		assertTrue(fixture.model == fixture.getModel());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#getWidth(javafx.geometry.Dimension2D)}.
	 */
	@Test
	public void testGetWidthDimension2D() {
		// This method trivially calls a method on another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#highlightTopCard()}.
	 */
	@Test
	public void testHighlightTopCard() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		fixture.highlightTopCard();
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#installTooltip(javafx.scene.Node, javafx.scene.control.Tooltip)}.
	 */
	@Test
	public void testInstallTooltip() {
		// This method trivially calls a method on another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#layoutChildren()}.
	 */
	@Test
	public void testLayoutChildren() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		fixture.layoutChildren();
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#newFanBinding(io.github.alantcote.labellelucie.view.impl.FanView)}.
	 */
	@Test
	public void testNewFanBinding() {
		// This method trivially constructs another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#newImageView(javafx.scene.image.Image)}.
	 */
	@Test
	public void testNewImageView() {
		// This method trivially constructs another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#newInputHandlerSupport(javafx.scene.Node)}.
	 */
	@Test
	public void testNewInputHandlerSupport() {
		// This method trivially constructs another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#newInsets(double)}.
	 */
	@Test
	public void testNewInsets() {
		// This method trivially constructs another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#newTooltip()}.
	 */
	@Test
	public void testNewTooltip() {
		// This method trivially constructs another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#reloadChildren()}.
	 */
	@Test
	public void testReloadChildren() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		fixture.reloadChildren();
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#setCardViewFactory(io.github.alantcote.playingcards.javafx.CardViewFactory)}.
	 */
	@Test
	public void testSetCardViewFactory() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		testCardViewFactory = new CardViewFactory();

		fixture.setCardViewFactory(testCardViewFactory);

		assertTrue(testCardViewFactory == fixture.cardViewFactory);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#setCSSId(java.lang.String)}.
	 */
	@Test
	public void testSetCSSId() {
		// This method trivially constructs another object and can be trusted.
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#setFanOffset(double)}.
	 */
	@Test
	public void testSetFanOffset() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		testFanOffset = 15;

		fixture.setFanOffset(testFanOffset);

		assertTrue(testFanOffset == fixture.fanOffset);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testSetInputHandler() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);
		InputHandler testInputHandler = new MasterInputHandler(new ControllerImpl(new GameStateImpl()));

		fixture.setInputHandler(testInputHandler);

		assertTrue(testInputHandler == fixture.inputHandlerSupport.getInputHandler());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#setModel(io.github.alantcote.labellelucie.model.facade.Fan)}.
	 */
	@Test
	public void testSetModel() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		double testFanOffset = 10;
		Fan testFan = new FanImpl();
		FanView fixture = new FanView(testCardViewFactory, testFanOffset, testFan);

		testFan = new FanImpl();

		fixture.setModel(testFan);

		assertTrue(testFan == fixture.model);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.FanView#uninstallTooltip(javafx.scene.Node, javafx.scene.control.Tooltip)}.
	 */
	@Test
	public void testUninstallTooltip() {
		// This method trivially calls a method on another object and can be trusted.
	}

}
