package io.github.alantcote.labellelucie.view.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;
import io.github.alantcote.playingcards.javafx.CardView;
import io.github.alantcote.playingcards.javafx.CardViewFactory;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.StockView}.
 */
public class StockViewTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.StockView#StockView(io.github.alantcote.playingcards.javafx.CardViewFactory, io.github.alantcote.labellelucie.model.facade.GameState)}.
	 */
	@Test
	public void testStockView() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		GameState testGameState = new GameStateImpl();
		StockView fixture = new StockView(testCardViewFactory, testGameState);
		
		assertTrue(testCardViewFactory == fixture.cardViewFactory);
		assertTrue(testGameState == fixture.model);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.StockView#getInputHandler()}.
	 */
	@Test
	public void testGetInputHandler() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		GameState testGameState = new GameStateImpl();
		StockView fixture = new StockView(testCardViewFactory, testGameState);
		
		assertTrue(fixture.inputHandlerSupport.getInputHandler() == fixture.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.StockView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testSetInputHandler() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		GameState testGameState = new GameStateImpl();
		StockView fixture = new StockView(testCardViewFactory, testGameState);
		InputHandler testInputHandler = new MasterInputHandler(new ControllerImpl(testGameState));
		
		assertFalse(testInputHandler == fixture.inputHandlerSupport.getInputHandler());
		
		fixture.setInputHandler(testInputHandler);

		assertTrue(testInputHandler == fixture.inputHandlerSupport.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.StockView#anchorLeft(javafx.scene.image.ImageView, double)}.
	 */
	@Test
	public void testAnchorLeft() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		GameState testGameState = new GameStateImpl();
		StockView fixture = new StockView(testCardViewFactory, testGameState);
		CardView testCardView = fixture.cardViewFactory.getBackView(new Card(Rank.ACE, Suit.CLUB));
		
		fixture.anchorLeft(testCardView, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.StockView#anchorTop(javafx.scene.image.ImageView, double)}.
	 */
	@Test
	public void testAnchorTop() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		GameState testGameState = new GameStateImpl();
		StockView fixture = new StockView(testCardViewFactory, testGameState);
		CardView testCardView = fixture.cardViewFactory.getBackView(new Card(Rank.ACE, Suit.CLUB));
		
		fixture.anchorTop(testCardView, 0);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.StockView#updateChildren()}.
	 */
	@Test
	public void testUpdateChildren() {
		CardViewFactory testCardViewFactory = new CardViewFactory();
		GameState testGameState = new GameStateImpl();
		StockView fixture = new StockView(testCardViewFactory, testGameState);
		
		fixture.updateChildren();
	}

}
