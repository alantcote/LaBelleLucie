package io.github.alantcote.labellelucie.model.impl;

import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import io.github.alantcote.labellelucie.undo.UndoManager;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Deck;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The model implementation.
 */
public class GameStateImpl implements GameState {
	/**
	 * The number of draws remaining in the game.
	 */
	protected IntegerProperty drawsRemaining;
	
	/**
	 * The foundation fans.
	 */
	protected Fan[] foundation;
	
	/**
	 * The game summary state.
	 */
	protected ObjectProperty<GameSummary> gameSummary;
	
	/**
	 * The pack of cards.
	 */
	protected Deck pack;
	
	/**
	 * The number of redeals remaining.
	 */
	protected IntegerProperty redealsRemaining;
	
	/**
	 * The cards remaining to be dealt.
	 */
	protected ObjectProperty<Deck> stock;
	
	/**
	 * The tableau fans.
	 */
	protected Fan[] tableau;
	
	/**
	 * The undo manager.
	 */
	protected UndoManager undoManager;

	/**
	 * Constructor.
	 * 
	 * There is no copy constructor, though that could make undo easier and more
	 * robust.
	 */
	public GameStateImpl() {
		super();

		inizMembers();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dealTableau(Deck deck) {
		int cardIndex = 0;

		for (Fan fan : tableau) {
			fan.clear();
		}

		deck.shuffle();

		while (!deck.isEmpty()) {
			Card card = deck.deal();
			int tableauIndex = cardIndex / TABLEAU_FAN_INIT_SIZE;

			tableau[tableauIndex].add(card);

			++cardIndex;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IntegerProperty getDrawsRemaining() {
		return drawsRemaining;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fan[] getFoundation() {
		return foundation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectProperty<GameSummary> getGameSummary() {
		return gameSummary;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IntegerProperty getRedealsRemaining() {
		return redealsRemaining;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectProperty<Deck> getStock() {
		return stock;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fan[] getTableau() {
		return tableau;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UndoManager getUndoManager() {
		return undoManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		drawsRemaining.set(1);

		for (Fan fan : foundation) {
			fan.clear();
		}

		redealsRemaining.set(2);

		stock.set(pack);
		dealTableau(pack);

		stock.set(null);
		stock.set(pack);

		gameSummary.set(GameSummary.IN_PROGRESS);
	}

	/**
	 * Initialize this object's fields and properties.
	 */
	protected void inizMembers() {
		drawsRemaining = newIntegerProperty();
		gameSummary = newObjectProperty_GameSummary(GameSummary.IN_PROGRESS);

		foundation = newFanArray(FOUNDATION_FAN_COUNT);
		for (int i = 0; i < FOUNDATION_FAN_COUNT; ++i) {
			foundation[i] = newFan();
		}

		redealsRemaining = newIntegerProperty();
		pack = newPack();
		stock = newObjectProperty_Deck();

		tableau = newFanArray(TABLEAU_FAN_COUNT);
		for (int i = 0; i < TABLEAU_FAN_COUNT; ++i) {
			tableau[i] = newFan();
		}

		undoManager = newUndoManager();
	}

	/**
	 * Create a new fan.
	 * @return a new fan.
	 */
	protected Fan newFan() {
		return new FanImpl();
	}

	/**
	 * Create a new array of fans.
	 * @param dim the dimension of the array.
	 * @return the new array.
	 */
	protected Fan[] newFanArray(int dim) {
		return new Fan[dim];
	}

	/**
	 * Create a new integer property.
	 * @return a new integer property.
	 */
	protected IntegerProperty newIntegerProperty() {
		return new SimpleIntegerProperty();
	}

	/**
	 * Create a new {@link Deck} property.
	 * @return a new {@link Deck} property.
	 */
	protected ObjectProperty<Deck> newObjectProperty_Deck() {
		return new SimpleObjectProperty<Deck>();
	}

	/**
	 * Create a new {@link GameSummary} property.
	 * @param gs the initial game summary.
	 * @return a new {@link GameSummary} property.
	 */
	protected ObjectProperty<GameSummary> newObjectProperty_GameSummary(GameSummary gs) {
		return new SimpleObjectProperty<GameSummary>(gs);
	}

	/**
	 * Create a new deck.
	 * @return a new deck.
	 */
	protected Pack newPack() {
		return new Pack();
	}

	/**
	 * Create a new undo manager.
	 * @return a new undo manager.
	 */
	protected UndoManager newUndoManager() {
		return new UndoManager();
	}
}
