package net.sf.cotelab.lbl.controller.impl;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.controller.impl.handler.MasterInputHandler;
import net.sf.cotelab.lbl.controller.impl.undoableop.DrawOp;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToFoundationOp;
import net.sf.cotelab.lbl.controller.impl.undoableop.MoveCardTableauToTableauOp;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

/**
 * An implementation of a <tt>Controller</tt>.
 * @author cote
 */
public class ControllerImpl implements Controller {
	protected InputHandler inputHandler;
	protected GameState model;

	/**
	 * Construct a new object, using a given model.
	 * @param model the model.
	 */
	public ControllerImpl(GameState model) {
		super();
		
		this.model = model;
		
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
		Fan[] tableauFan = model.getTableau();
		Fan[] foundationFan = model.getFoundation();
		int sourceFanIndex = -1;
		
		for (int i = 0; i < tableauFan.length; ++i) {
			Card sourceCard = tableauFan[i].getTopCard();
			
			if (card == sourceCard) {
				sourceFanIndex = i;
				
				break;
			}
		}
		
		if (sourceFanIndex >= 0) {
			// sourceFanIndex is the index of a Fan in the tableau for which
			// card is on top - now look for a destination, first in foundation

			int destFanIndex = -1;
			
			for (int i = 0; i < foundationFan.length; ++i) {
				Card destCard = foundationFan[i].getTopCard();

				if (canPlayOnFoundation(card, destCard)) {
					destFanIndex = i;
					
					moveCardToFoundation(sourceFanIndex, destFanIndex);
					
					break;
				}
			}
			
			if (destFanIndex < 0) {
				for (int i = 0; i < tableauFan.length; ++i) {
					Card destCard = tableauFan[i].getTopCard();

					if (canPlayOnTableau(card, destCard)) {
						destFanIndex = i;
						
						moveCardToTableau(sourceFanIndex, destFanIndex);
						
						break;
					}
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
			if (model.getRedealsRemaining().get() < 1) {
				if (model.getDrawsRemaining().get() < 1) {
					if (!canPlay()) {
						result = GameSummary.LOST;
					}
				}
			}
		}
		
		model.getGameSummary().set(result);
	}
	
	protected boolean canPlay() {
		boolean canPlay = false;
		Fan[] tableauFan = model.getTableau();
		
		for (Fan fan : tableauFan) {
			Card srcCard = fan.getTopCard();
			
			if (srcCard != null) {
				if (canPlay(srcCard)) {
					canPlay = true;
				}
			}
		}
		
		return canPlay;
	}
	
	/**
	 * Determine whether there is a legal place to play a given card.
	 * @param srcCard the card that might be played.
	 * @return the truth-value of the assertion, "<tt>srcCard</tt> may be moved
	 * 		legally".
	 */
	protected boolean canPlay(Card srcCard) {
		return canPlayOnFoundation(srcCard) || canPlayOnTableau(srcCard);
	}
	
	/**
	 * Determine whether there is a legal place on the foundation to play a
	 * given card.
	 * @param srcCard the card that might be played.
	 * @return the truth-value of the assertion, "<tt>srcCard</tt> may be moved
	 * 		legally to the foundation".
	 */
	protected boolean canPlayOnFoundation(Card srcCard) {
		boolean result = false;
		
		for (Fan fan : model.getFoundation()) {
			Card dstCard = fan.getTopCard();
			
			if (dstCard != null) {
				if (canPlayOnFoundation(srcCard, dstCard)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}

	/**
	 * Determine whether the rules permit a prospective new top card to be
	 * played atop an existing top card, in a foundation fan.
	 * @param newTopCard the prospective new top card.
	 * @param oldTopCard the existing top card.
	 * @return the truth-value of the assertion, "<tt>newTopCard</tt> may be
	 * 		played atop <tt>oldTopCard</tt> in a foundation fan".
	 */
	protected boolean canPlayOnFoundation(Card newTopCard, Card oldTopCard) {
		boolean result = false;
		Rank newRank = newTopCard.getRank();
		Suit newSuit = newTopCard.getSuit();
		
		if (oldTopCard == null) {
			result = (newRank == Rank.ACE);
		} else {
			Rank oldRank = oldTopCard.getRank();
			Suit oldSuit = oldTopCard.getSuit();

			if (newSuit == oldSuit) {
				result = (oldRank.ordinal() + 1 == newRank.ordinal());
			}
		}
		
		return result;
	}

	/**
	 * Determine whether there is a legal place on the tableau to play a given
	 * card.
	 * @param srcCard the card that might be played.
	 * @return the truth-value of the assertion, "<tt>srcCard</tt> may be moved
	 * 		legally to the tableau".
	 */
	protected boolean canPlayOnTableau(Card srcCard) {
		boolean result = false;
		
		for (Fan fan : model.getTableau()) {
			Card dstCard = fan.getTopCard();

			if (dstCard != null) {
				if (canPlayOnTableau(srcCard, dstCard)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}

	/**
	 * Determine whether the rules permit a prospective new top card to be
	 * played atop an existing top card, in a tableau fan.
	 * @param newTopCard the prospective new top card.
	 * @param oldTopCard the existing top card.
	 * @return the truth-value of the assertion, "<tt>newTopCard</tt> may be
	 * 		played atop <tt>oldTopCard</tt> in a tableau fan".
	 */
	protected boolean canPlayOnTableau(Card newTopCard, Card oldTopCard) {
		boolean result = false;
		Rank newRank = newTopCard.getRank();
		Suit newSuit = newTopCard.getSuit();
		
		if (oldTopCard != null) {
			Rank oldRank = oldTopCard.getRank();
			Suit oldSuit = oldTopCard.getSuit();

			if (newSuit == oldSuit) {
				result = (oldRank.ordinal() == newRank.ordinal() + 1);
			}
		}
		
		return result;
	}
	
	protected int countFoundationCards() {
		Fan[] foundationFan = model.getFoundation();
		int count = 0;
		
		for (Fan fan : foundationFan) {
			count += fan.size();
		}
		
		return count;
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
}
