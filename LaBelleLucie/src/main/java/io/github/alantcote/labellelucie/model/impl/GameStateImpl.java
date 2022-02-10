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

public class GameStateImpl implements GameState {
	protected IntegerProperty drawsRemaining;
	protected Fan[] foundation;
	protected ObjectProperty<GameSummary> gameSummary;
	protected Deck pack;
	protected IntegerProperty redealsRemaining;
	protected ObjectProperty<Deck> stock;
	protected Fan[] tableau;
	protected UndoManager undoManager;

	public GameStateImpl() {
		super();

		inizMembers();
	}

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

	@Override
	public IntegerProperty getDrawsRemaining() {
		return drawsRemaining;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.alantcote.playingcards.lbl.model.GameState#getFoundation()
	 */
	@Override
	public Fan[] getFoundation() {
		return foundation;
	}

	@Override
	public ObjectProperty<GameSummary> getGameSummary() {
		return gameSummary;
	}

	@Override
	public IntegerProperty getRedealsRemaining() {
		return redealsRemaining;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.alantcote.playingcards.lbl.model.GameState#getStock()
	 */
	@Override
	public ObjectProperty<Deck> getStock() {
		return stock;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.alantcote.playingcards.lbl.model.GameState#getTableau()
	 */
	@Override
	public Fan[] getTableau() {
		return tableau;
	}

	@Override
	public UndoManager getUndoManager() {
		return undoManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.alantcote.playingcards.lbl.model.GameState#reset()
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

//		reset();
	}

	protected Fan newFan() {
		return new FanImpl();
	}

	protected Fan[] newFanArray(int dim) {
		return new Fan[dim];
	}

	protected IntegerProperty newIntegerProperty() {
		return new SimpleIntegerProperty();
	}

	protected ObjectProperty<Deck> newObjectProperty_Deck() {
		return new SimpleObjectProperty<Deck>();
	}

	protected ObjectProperty<GameSummary> newObjectProperty_GameSummary(GameSummary gs) {
		return new SimpleObjectProperty<GameSummary>(gs);
	}

	protected Pack newPack() {
		return new Pack();
	}

	protected UndoManager newUndoManager() {
		return new UndoManager();
	}
}
