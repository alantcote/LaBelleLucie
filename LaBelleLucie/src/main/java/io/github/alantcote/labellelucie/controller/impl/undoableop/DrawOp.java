package io.github.alantcote.labellelucie.controller.impl.undoableop;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.undo.UndoableOp;

/**
 * An <code>UndoableOp</code> that implements the draw.
 * 
 * @author cote
 */
public class DrawOp implements UndoableOp {
	protected int cardIndex;
	protected ControllerImpl controller;
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

	@Override
	public void doOp() {
		controller.draw(fanIndex, cardIndex);
	}

	@Override
	public void undoOp() {
		controller.unDraw(fanIndex, cardIndex);
	}

}
