package net.sf.cotelab.lbl.controller.impl;

import java.util.List;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.controller.facade.Move;
import net.sf.cotelab.lbl.controller.facade.MoveType;
import net.sf.cotelab.lbl.controller.impl.handler.MasterInputHandler;
import net.sf.cotelab.lbl.controller.impl.undoableop.DrawOp;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToFoundationOp;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToTableauOp;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;

/**
 * An implementation of a <tt>Controller</tt>.
 * @author cote
 */
public class ControllerImpl implements Controller {
	@SuppressWarnings("unused")
	private static Logger log =
			Logger.getLogger(ControllerImpl.class.getName());
	
	protected InputHandler inputHandler;
	protected GameState model;
	protected MoveFinder moveFinder;

	/**
	 * Construct a new object, using a given model.
	 * @param model the model.
	 */
	public ControllerImpl(GameState model) {
		super();
		
		this.model = model;

		moveFinder = newMoveFinder();
		inputHandler = newInputHandler();
	}
	
	/**
	 * Move a card from a given index in a given fan to the top (end) of the
	 * fan.
	 * @param fanIndex the index of the fan in the tableau.
	 * @param cardIndex the index of the card in the fan.
	 */
	public void draw(int fanIndex, int cardIndex) {
		IntegerProperty drawsRemaining = model.getDrawsRemaining();
		
		if (drawsRemaining.get() > 0) {
			Fan fan = model.getTableau()[fanIndex];
			Card card = fan.remove(cardIndex);
			
			fan.add(card);
			
			drawsRemaining.set(drawsRemaining.get() - 1);
			
			updateGameSummary();
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.IController#getInputHandler()
	 */
	@Override
	public InputHandler getInputHandler() {
		return inputHandler;
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.IController#getModel()
	 */
	@Override
	public GameState getModel() {
		return model;
	}

	/**
	 * Move the top card from a given foundation fan to the top of a given
	 * tableau fan.
	 * This method's primary use is in support of undo.
	 * @param srcFanIndex the foundation fan index.
	 * @param destFanIndex the tableau fan index.
	 */
	public void moveTopCardFoundationToTableau(
			int srcFanIndex, int destFanIndex) {
		Fan[] tableauFan = model.getTableau();
		Fan[] foundationFan = model.getFoundation();
		Card card = foundationFan[srcFanIndex].remove(
				foundationFan[srcFanIndex].size() - 1);
		
		tableauFan[destFanIndex].add(card);
	}
	
	/**
	 * Move the top card from a given tableau fan to the top of a given
	 * foundation fan.
	 * @param srcFanIndex the tableau fan index.
	 * @param destFanIndex the foundation fan index.
	 */
	public void moveTopCardTableauToFoundation(
			int srcFanIndex, int destFanIndex) {
		Fan[] tableauFan = model.getTableau();
		Fan[] foundationFan = model.getFoundation();
		Card card = tableauFan[srcFanIndex].remove(
				tableauFan[srcFanIndex].size() - 1);
		
		foundationFan[destFanIndex].add(card);
	}

	/**
	 * Move a card from the top of one tableau fan to the top of another tableau
	 * fan.
	 * @param srcFanIndex the source tableau fan index.
	 * @param destFanIndex the destination tableau fan index.
	 */
	public void moveTopCardTableauToTableau(int srcFanIndex, int destFanIndex) {
		Fan[] tableauFan = model.getTableau();
		Card card = tableauFan[srcFanIndex].remove(
				tableauFan[srcFanIndex].size() - 1);
		
		tableauFan[destFanIndex].add(card);
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onMouseClicked(net.sf.cotelab.playingcards.Card)
	 */
	public void onCardMoveRequested(Card card) {
//		log.info("card = " + card.getSuit() + " " + card.getRank());
		
		int sourceFanIndex = indexOfTableauFanWithTopCard(card);

//		log.info("sourceFanIndex = " + sourceFanIndex);
		
		if (sourceFanIndex >= 0) {
			List<Move> moves = moveFinder.findSimpleMoves();

//			log.info("moves = " + moves);
			
			for (Move move : moves) {
				if (sourceFanIndex == move.getSrcFanIndex()) {
					MoveType type = move.getType();
					
					switch (type) {
					case TABLEAU_TO_FOUNDATION:
						moveCardToFoundation(
								sourceFanIndex, move.getDestFanIndex());
						break;
					case TABLEAU_TO_TABLEAU:
						moveCardToTableau(
								sourceFanIndex, move.getDestFanIndex());
						break;
					default:
						break;
					}
					
					sourceFanIndex = -1;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onDrawRequested(net.sf.cotelab.playingcards.Card)
	 */
	public void onDrawRequested(Card card) {
		IntegerProperty drawsRemaining = model.getDrawsRemaining();
		int dr = drawsRemaining.get();
		
		if (dr > 0) {
			Fan[] tableauFan = model.getTableau();
			
			for (int fanIndex = 0; fanIndex < tableauFan.length; ++fanIndex) {
				Fan fan = tableauFan[fanIndex];
				int index = fan.indexOf(card);
				
				if (index >= 0) {
					DrawOp op = newDrawOp(fanIndex, index);
					
					op.doOp();
					
					model.getUndoManager().add(op);
					
					break;
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onExitRequest()
	 */
	public void onExitRequest() {
		Platform.exit();
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onNewGameRequested()
	 */
	public void onNewGameRequested() {
		model.reset();
		
		updateGameSummary();
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onReshuffleRequest()
	 */
	public void onReshuffleRequest() {
		IntegerProperty redealsRemaining = model.getRedealsRemaining();
		
		if (redealsRemaining.get() > 0) {
			reshuffle();
			
			redealsRemaining.set(redealsRemaining.get() - 1);
		}
	}
	
	/**
	 * Reverse the effect of an invocation of <tt>draw()</tt> with corresponding
	 * parameters.
	 * @param fanIndex the index of the tableau fan to be affected.
	 * @param cardIndex the index in the fan from which the card was drawn.
	 */
	public void unDraw(int fanIndex, int cardIndex) {
		Fan fan = model.getTableau()[fanIndex];
		Card card = fan.remove(fan.size() - 1);
		IntegerProperty drawsRemaining = model.getDrawsRemaining();
		
		fan.add(cardIndex, card);
		
		drawsRemaining.set(drawsRemaining.get() + 1);
		
		model.getGameSummary().set(GameSummary.IN_PROGRESS);
	}

	/**
	 * Update the game summary to indicate the overall state of the game.
	 */
	public void updateGameSummary() {
		GameSummary result = GameSummary.IN_PROGRESS;

		if (isGameWon()) {
			result = GameSummary.WON;
		} else {
			List<Move> moves = moveFinder.findMoves();
			
			if (moves.isEmpty()) {
				result = GameSummary.LOST;
			}
		}
		
		model.getGameSummary().set(result);
	}

	protected int countFoundationCards() {
		Fan[] foundationFan = model.getFoundation();
		int count = 0;
		
		for (Fan fan : foundationFan) {
			count += fan.size();
		}
		
		return count;
	}
	
	/**
	 * Find the index of a tableau fan with a given card on top.
	 * @param card the candidate top card.
	 * @return the index (<tt>-1</tt> if <tt>card</tt> is not a tableau top
	 * 		card).
	 */
	protected int indexOfTableauFanWithTopCard(Card card) {
		int sourceFanIndex = -1;
		Fan[] tableauFan = model.getTableau();
		
		for (int i = 0; i < tableauFan.length; ++i) {
			Card sourceCard = tableauFan[i].getTopCard();
			
			if (card == sourceCard) {
				sourceFanIndex = i;
				
				break;
			}
		}
		
		return sourceFanIndex;
	}
	
	protected boolean isGameWon() {
		return (52 == countFoundationCards());
	}

	/**
	 * Move the top card from a given tableau fan to atop a given foundation
	 * fan.
	 * @param srcFanIndex the source tableau fan index.
	 * @param destFanIndex the destination foundation fan index.
	 */
	protected void moveCardToFoundation(int srcFanIndex, int destFanIndex) {
		MoveCardTableauToFoundationOp op =
				newMoveCardTableauToFoundationOp(srcFanIndex, destFanIndex);
		
		op.doOp();
		model.getUndoManager().add(op);
	}
	
	/**
	 * Move the top card from a given tableau fan to atop another given tableau
	 * fan.
	 * @param srcFanIndex the source tableau fan index.
	 * @param destFanIndex the destination tableau fan index.
	 */
	protected void moveCardToTableau(int srcFanIndex, int destFanIndex) {
		MoveCardTableauToTableauOp op =
				newMoveCardTableauToTableauOp(srcFanIndex, destFanIndex);
		
		op.doOp();
		model.getUndoManager().add(op);
	}

	/**
	 * Create a new (empty) deck.
	 * @return the new deck.
	 */
	protected Deck newDeck() {
		return new Deck();
	}

	/**
	 * Create a new draw operation.
	 * @param fanIndex the index of the tableau fan from which to draw.
	 * @param cardIndex the index in the fan of the card to be drawn.
	 * @return the new draw operation.
	 */
	protected DrawOp newDrawOp(int fanIndex, int cardIndex) {
		return new DrawOp(this, fanIndex, cardIndex);
	}
	
	/**
	 * Create a new input handler.
	 * @param controller the controller to which the new object will delegate.
	 * @return the new input handler.
	 */
	protected InputHandler newInputHandler() {
		return new MasterInputHandler(this);
	}

	/**
	 * Create a new operation to move a card from the top of a tableau fan to
	 * the top of a foundation fan.
	 * @param srcFanIndex the index of the tableau fan.
	 * @param destFanIndex the index of the foundation fan.
	 * @return the new operation.
	 */
	protected MoveCardTableauToFoundationOp newMoveCardTableauToFoundationOp(
			int srcFanIndex, int destFanIndex) {
		return new MoveCardTableauToFoundationOp(
				this, srcFanIndex, destFanIndex);
	}

	/**
	 * Create a new operation to move a card from the top of one tableau fan to
	 * the top of another foundation fan.
	 * @param srcFanIndex the index of the source tableau fan.
	 * @param destFanIndex the index of the destination tableau fan.
	 * @return the new operation.
	 */
	protected MoveCardTableauToTableauOp newMoveCardTableauToTableauOp(
			int srcFanIndex, int destFanIndex) {
		return new MoveCardTableauToTableauOp(this, srcFanIndex, destFanIndex);
	}
	
	/**
	 * Create a new <tt>MoveFinder</tt>.
	 * @return the new <tt>MoveFinder</tt>.
	 */
	protected MoveFinder newMoveFinder() {
		return new MoveFinder(model);
	}

	/**
	 * Perform the reshuffle (or "redeal") operation.
	 */
	protected void reshuffle() {
		Deck deck = newDeck();
		Fan[] tableau = model.getTableau();
		ObjectProperty<Deck> stock;
		
		for (Fan fan : tableau) {
			for (Card card : fan) {
				deck.add(card);
			}
			
			fan.clear();
		}
		
		stock = model.getStock();

		stock.set(null);
		stock.set(deck);
		
		model.dealTableau(deck);
		
		stock.set(null);
		stock.set(deck);
		
		updateGameSummary();
	}

	public List<Move> listMoves() {
		return moveFinder.findMoves();
	}
}
