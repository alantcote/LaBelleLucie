package io.github.alantcote.labellelucie.controller.facade;

import java.util.List;

import io.github.alantcote.playingcards.Card;

/**
 * This default <code>InputHandler</code> quietly does nothing.
 */
public class DefaultInputHandler implements InputHandler {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Move> listMoves() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCardMoveRequested(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDragDetected(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDragDone(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDragDropped(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDragEntered(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDragExited(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDragOver(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDrawRequested(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onExitRequest() {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMouseEntered(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMouseExited(Card card) {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewGameRequested() {
		// NOTHING
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReshuffleRequest() {
		// NOTHING
	}
}
