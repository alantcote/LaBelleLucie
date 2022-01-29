package io.github.alantcote.labellelucie.controller.impl.undoableop;

import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import io.github.alantcote.labellelucie.undo.UndoableOp;

/**
 * An <code>UndoableOp</code> that implements playing a tableau card to the
 * foundation.
 * @author cote
 */
public class MoveCardTableauToFoundationOp implements UndoableOp {
	protected ControllerImpl controller;
	protected int destFanIndex;
	protected int srcFanIndex;

	/**
	 * Construct a new object.
	 * @param controller the controller.
	 * @param srcFanIndex the index of the tableau fan.
	 * @param destFanIndex the index of the foundation fan.
	 */
	public MoveCardTableauToFoundationOp(
			ControllerImpl controller, int srcFanIndex, int destFanIndex) {
		super();
		
		this.controller = controller;
		this.destFanIndex = destFanIndex;
		this.srcFanIndex = srcFanIndex;
	}

	@Override
	public void doOp() {
		controller.moveTopCardTableauToFoundation(srcFanIndex, destFanIndex);
		
		controller.updateGameSummary();
	}

	@Override
	public void undoOp() {
		controller.moveTopCardFoundationToTableau(destFanIndex, srcFanIndex);

		controller.getModel().getGameSummary().set(GameSummary.IN_PROGRESS);
	}

}
