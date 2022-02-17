package io.github.alantcote.labellelucie.controller.impl.undoableop;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.undo.UndoableOp;

/**
 * An {@link UndoableOp} that implements the draw.
 */
public class DrawOp implements UndoableOp {
	/**
	 * The index of the card to be drawn.
	 */
	protected int cardIndex;
	
	/**
	 * The controller to be used.
	 */
	protected ControllerImpl controller;
	
	/**
	 * The index of the fan containing the card.
	 */
	protected int fanIndex;

	/**
	 * Construct a new object.
	 * 
	 * @param controller the controller.
	 * @param fanIndex   the index of the tableau fan from which to draw.
	 * @param cardIndex  the index of the card to draw.
	 */
	public DrawOp(ControllerImpl controller, int fanIndex, int cardIndex) {
		super();

		this.controller = controller;
		this.fanIndex = fanIndex;
		this.cardIndex = cardIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doOp() {
		controller.draw(fanIndex, cardIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undoOp() {
		controller.unDraw(fanIndex, cardIndex);
	}

}
