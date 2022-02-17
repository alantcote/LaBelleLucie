package io.github.alantcote.labellelucie.controller.impl.undoableop;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import io.github.alantcote.labellelucie.undo.UndoableOp;

/**
 * An {@link UndoableOp} that implements playing a the top card from one
 * tableau fan to the top of another tableau fan.
 */
public class MoveCardTableauToTableauOp implements UndoableOp {
	/**
	 * The controller to be used.
	 */
	protected ControllerImpl controller;
	
	/**
	 * The index of the destination fan.
	 */
	protected int destFanIndex;
	
	/**
	 * The index of the source fan.
	 */
	protected int srcFanIndex;

	/**
	 * Construct a new object.
	 * 
	 * @param controller   the controller.
	 * @param srcFanIndex  the index of the source tableau fan.
	 * @param destFanIndex the index of the destination tableau fan.
	 */
	public MoveCardTableauToTableauOp(ControllerImpl controller, int srcFanIndex, int destFanIndex) {
		super();

		this.controller = controller;
		this.destFanIndex = destFanIndex;
		this.srcFanIndex = srcFanIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doOp() {
		controller.moveTopCardTableauToTableau(srcFanIndex, destFanIndex);

		controller.updateGameSummary();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undoOp() {
		controller.moveTopCardTableauToTableau(destFanIndex, srcFanIndex);

		controller.getModel().getGameSummary().set(GameSummary.IN_PROGRESS);
	}

}
