package net.sf.cotelab.lbl.controller.impl.undoableop;

import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.lbl.undo.UndoableOp;

/**
 * An <tt>UndoableOp</tt> that implements the draw.
 * @author cote
 */
public class DrawOp implements UndoableOp {
	protected int cardIndex;
	protected ControllerImpl controller;
	protected int fanIndex;

	/**
	 * Construct a new object.
	 * @param controller the controller.
	 * @param fanIndex the index of the tableau fan from which to draw.
	 * @param cardIndex the index of the card to draw.
	 */
	public DrawOp(
			ControllerImpl controller, int fanIndex, int cardIndex) {
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
