package net.sf.cotelab.playingcards.lbl.model.impl;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.lbl.model.facade.Fan;
import net.sf.cotelab.playingcards.lbl.model.facade.GameState;
import net.sf.cotelab.playingcards.lbl.model.facade.GameSummary;
import net.sf.cotelab.playingcards.util.UndoManager;

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
	
	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.model.GameState#getFoundation()
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

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.model.GameState#getStock()
	 */
	@Override
	public ObjectProperty<Deck> getStock() {
		return stock;
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.model.GameState#getTableau()
	 */
	@Override
	public Fan[] getTableau() {
		return tableau;
	}
	
	@Override
	public UndoManager getUndoManager() {
		return undoManager;
	}
	
	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.model.GameState#reset()
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
		drawsRemaining = new SimpleIntegerProperty();
		gameSummary = new SimpleObjectProperty<GameSummary>(
				GameSummary.IN_PROGRESS);
		
		foundation = new Fan[FOUNDATION_FAN_COUNT];
		for (int i = 0; i < FOUNDATION_FAN_COUNT; ++i) {
			foundation[i] = newFan();
		}

		redealsRemaining = new SimpleIntegerProperty();
		pack = newPack();
		stock = new SimpleObjectProperty<Deck>();

		tableau = new Fan[TABLEAU_FAN_COUNT];
		for (int i = 0; i < TABLEAU_FAN_COUNT; ++i) {
			tableau[i] = newFan();
		}
		
		undoManager = new UndoManager();
		
		reset();
	}

	protected Fan newFan() {
		return new FanImpl();
	}

	protected Pack newPack() {
		return new Pack();
	}
}
