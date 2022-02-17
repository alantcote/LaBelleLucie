package io.github.alantcote.labellelucie.controller.impl.undoableop;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import io.github.alantcote.labellelucie.undo.UndoableOp;

/**
 * An {@link UndoableOp} that implements playing a tableau card to the
 * foundation.
 */
public class MoveCardTableauToFoundationOp implements UndoableOp {
	/**
	 * The controller to be used.
	 */
	protected ControllerImpl controller;
	
	/**
	 * The index of the foundation fan.
	 */
	protected int destFanIndex;
	
	/**
	 * The index of the tableau fan.
	 */
	protected int srcFanIndex;

	/**
	 * Construct a new object.
	 * 
	 * @param controller   the controller.
	 * @param srcFanIndex  the index of the tableau fan.
	 * @param destFanIndex the index of the foundation fan.
	 */
	public MoveCardTableauToFoundationOp(ControllerImpl controller, int srcFanIndex, int destFanIndex) {
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
		controller.moveTopCardTableauToFoundation(srcFanIndex, destFanIndex);

		controller.updateGameSummary();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undoOp() {
		controller.moveTopCardFoundationToTableau(destFanIndex, srcFanIndex);

		controller.getModel().getGameSummary().set(GameSummary.IN_PROGRESS);
	}

}
