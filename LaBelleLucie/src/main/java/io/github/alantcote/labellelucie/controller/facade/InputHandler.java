package io.github.alantcote.labellelucie.controller.facade;

import java.util.List;

import io.github.alantcote.playingcards.Card;

public interface InputHandler {
	/**
	 * Get a list of available legal moves of cards.
	 * @return the list of moves.
	 */
	public List<Move> listMoves();
	
	/**
	 * Called to move a card to its best valid destination.
	 * @param card the card.
	 */
	public void onCardMoveRequested(Card card);
	
	/**
	 * Called when a drag is detected, when the mouse is over the region
	 * corresponding to a given card.
	 * @param card the card.
	 */
	public void onDragDetected(Card card);
	
	/**
	 * Called when a drag of a given card is finished.
	 * @param card the card.
	 */
	public void onDragDone(Card card);
	
	/**
	 * Called when a drag is dropped, when the mouse is over the region
	 * corresponding to a given card.
	 * @param card the card.
	 */
	public void onDragDropped(Card card);
	
	/**
	 * Called during a drag, when the mouse enters the region corresponding to a
	 * given card.
	 * @param card the card.
	 */
	public void onDragEntered(Card card);
	/**
	 * Called during a drag, when the mouse exits the region corresponding to a
	 * given card.
	 * @param card the card.
	 */
	public void onDragExited(Card card);
	
	/**
	 * Called during a drag, when the mouse is over the region corresponding to
	 * a given card.
	 * @param card the card.
	 */
	public void onDragOver(Card card);
	
	/**
	 * Called when a user requests drawing a given card.
	 * @param card the card.
	 */
	public void onDrawRequested(Card card);
	
	/**
	 * Called when a user requests that the program exit.
	 */
	public void onExitRequest();
	
	/**
	 * Called when the mouse has entered the region corresponding to a given
	 * card.
	 * @param card the card.
	 */
	public void onMouseEntered(Card card);
	
	/**
	 * Called when the mouse has left the region corresponding to a given card.
	 * @param card the card.
	 */
	public void onMouseExited(Card card);
	
	/**
	 * Called when a user requests a new game.
	 */
	public void onNewGameRequested();
	
	/**
	 * Called when a user requests a reshuffle.
	 */
	public void onReshuffleRequest();
}
