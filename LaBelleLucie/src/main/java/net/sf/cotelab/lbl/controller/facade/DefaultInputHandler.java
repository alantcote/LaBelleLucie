/**
 * 
 */
package net.sf.cotelab.lbl.controller.facade;

import net.sf.cotelab.playingcards.Card;

/**
 * This default <tt>InputHandler</tt> quietly does nothing.
 */
public class DefaultInputHandler implements InputHandler {
	@Override
	public void onCardMoveRequested(Card card) {
		// NOTHING		
	}

	@Override
	public void onDragDetected(Card card) {
		// NOTHING		
	}

	@Override
	public void onDragDone(Card card) {
		// NOTHING		
	}

	@Override
	public void onDragDropped(Card card) {
		// NOTHING		
	}

	@Override
	public void onDragEntered(Card card) {
		// NOTHING		
	}

	@Override
	public void onDragExited(Card card) {
		// NOTHING		
	}

	@Override
	public void onDragOver(Card card) {
		// NOTHING		
	}

	@Override
	public void onDrawRequested(Card card) {
		// NOTHING		
	}

	@Override
	public void onExitRequest() {
		// NOTHING
	}

	@Override
	public void onMouseEntered(Card card) {
		// NOTHING		
	}

	@Override
	public void onMouseExited(Card card) {
		// NOTHING		
	}

	@Override
	public void onNewGameRequested() {
		// NOTHING
	}

	@Override
	public void onReshuffleRequest() {
		// NOTHING
	}
}
