package io.github.alantcote.labellelucie.view.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.javafx.CardViewFactory;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.TableView}.
 */
public class TableViewTest {
	protected final SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#createContextMenu(java.util.List)}.
	 */
	@Test
	public void testCreateContextMenu() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);

		assertNotNull(fixture.createContextMenu(new ArrayList<Card>()));
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#getInputHandler()}.
	 */
	@Test
	public void testGetInputHandler() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);

		assertTrue(fixture.inputHandlerSupport.getInputHandler() == fixture.getInputHandler());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#highlightTopTableauCard(int)}.
	 */
	@Test
	public void testHighlightTopTableauCard() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);

		testGameState.reset();

		fixture.highlightTopTableauCard(0);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#inizChildren()}.
	 */
	@Test
	public void testInizChildren() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();

		stateProperty.set(0);

		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState) {

			@Override
			protected void inizFoundation() {
				assertTrue(0 == stateProperty.get());
				stateProperty.set(1);
			}

			@Override
			protected void inizStock() {
				assertTrue(2 == stateProperty.get());
				stateProperty.set(3);
			}

			@Override
			protected void inizTableau() {
				assertTrue(1 == stateProperty.get());
				stateProperty.set(2);
			}

		};

		stateProperty.set(0);

		fixture.inizChildren();

		assertTrue(3 == stateProperty.get());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#inizFoundation()}.
	 */
	@Test
	public void testInizFoundation() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);

		fixture.inizFoundation();

		assertNotNull(fixture.foundationFanView);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#inizStock()}.
	 */
	@Test
	public void testInizStock() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);

		fixture.inizStock();

		assertNotNull(fixture.stockView);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#inizTableau()}.
	 */
	@Test
	public void testInizTableau() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);

		fixture.inizTableau();

		assertNotNull(fixture.tableauFanView);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testSetInputHandler() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState);
		InputHandler testInputHandler = new MasterInputHandler(new ControllerImpl(testGameState));

		assertTrue(testInputHandler != fixture.inputHandlerSupport.getInputHandler());

		fixture.setInputHandler(testInputHandler);

		assertTrue(testInputHandler == fixture.inputHandlerSupport.getInputHandler());
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.TableView#TableView(io.github.alantcote.playingcards.javafx.CardViewFactory, double, io.github.alantcote.labellelucie.model.facade.GameState)}.
	 */
	@Test
	public void testTableView() {
		final CardViewFactory testCardViewFactory = new CardViewFactory();
		final double testFanOffset = 5;
		final GameState testGameState = new GameStateImpl();
		TableView fixture = new TableView(testCardViewFactory, testFanOffset, testGameState) {

			@Override
			protected void inizChildren() {
				assertTrue(0 == stateProperty.get());
				stateProperty.set(1);

				assertTrue(testCardViewFactory == cardViewFactory);
				assertTrue(testFanOffset == fanOffset);
				assertTrue(testGameState == model);
			}

		};

		assertNotNull(fixture);

		assertTrue(1 == stateProperty.get());
	}

}
