package net.sf.cotelab.lbl.controller.impl;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;

public class ControllerImpl implements Controller {
	protected InputHandler inputHandler;
	protected GameState model;

	public ControllerImpl(GameState model) {
		super();
		
		this.model = model;
		
		inputHandler = new MasterInputHandler(this);
	}

	public void draw(int fanIndex, int cardIndex) {
		Fan fan = model.getTableau()[fanIndex];
		Card card = fan.remove(cardIndex);
		IntegerProperty drawsRemaining = model.getDrawsRemaining();
		
		fan.add(card);
		
		drawsRemaining.set(drawsRemaining.get() - 1);
		
		updateGameSummary();
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
	
	public void moveTopCardFoundationToTableau(
			int srcFanIndex, int destFanIndex) {
		Fan[] tableauFan = model.getTableau();
		Fan[] foundationFan = model.getFoundation();
		Card card = foundationFan[srcFanIndex].remove(
				foundationFan[srcFanIndex].size() - 1);
		
		tableauFan[destFanIndex].add(card);
	}

	public void moveTopCardTableauToFoundation(
			int srcFanIndex, int destFanIndex) {
		Fan[] tableauFan = model.getTableau();
		Fan[] foundationFan = model.getFoundation();
		Card card = tableauFan[srcFanIndex].remove(
				tableauFan[srcFanIndex].size() - 1);
		
		foundationFan[destFanIndex].add(card);
	}

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
					DrawOp op = new DrawOp(this, fanIndex, index);
					
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
	
	public void unDraw(int fanIndex, int cardIndex) {
		Fan fan = model.getTableau()[fanIndex];
		Card card = fan.remove(fan.size() - 1);
		IntegerProperty drawsRemaining = model.getDrawsRemaining();
		
		fan.add(cardIndex, card);
		
		drawsRemaining.set(drawsRemaining.get() + 1);
		
		model.getGameSummary().set(GameSummary.IN_PROGRESS);
	}

	public void updateGameSummary() {
		GameSummary result = model.getGameSummary().get();
		
		if (result == GameSummary.IN_PROGRESS) {
			Fan[] foundationFan = model.getFoundation();
			int count = 0;
			
			for (Fan fan : foundationFan) {
				count += fan.size();
			}
			
			if (count == 52) {
				result = GameSummary.WON;
			} else {
				if (model.getRedealsRemaining().get() < 1) {
					if (model.getDrawsRemaining().get() < 1) {
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
						
						if (!canPlay) {
							result = GameSummary.LOST;
						}
					}
				}
			}
		}
		
		model.getGameSummary().set(result);
	}

	protected boolean canPlay(Card srcCard) {
		return canPlayOnFoundation(srcCard) || canPlayOnTableau(srcCard);
	}
	
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
	
	protected void moveCardToFoundation(int srcFanIndex, int destFanIndex) {
		MoveCardTableauToFoundationOp op = new MoveCardTableauToFoundationOp(
				this, srcFanIndex, destFanIndex);
		
		op.doOp();
		model.getUndoManager().add(op);
	}

	protected void moveCardToTableau(int srcFanIndex, int destFanIndex) {
		MoveCardTableauToTableauOp op =
				new MoveCardTableauToTableauOp(this, srcFanIndex, destFanIndex);
		
		op.doOp();
		model.getUndoManager().add(op);
	}
	
	protected void reshuffle() {
		Deck deck = new Deck();
		Fan[] tableau = model.getTableau();
		
		for (Fan fan : tableau) {
			for (Card card : fan) {
				deck.add(card);
			}
			
			fan.clear();
		}
		
		model.getStock().set(deck);
		
		model.dealTableau(deck);
		
		model.getStock().set(null);
		model.getStock().set(deck);
		
		updateGameSummary();
	}
}
