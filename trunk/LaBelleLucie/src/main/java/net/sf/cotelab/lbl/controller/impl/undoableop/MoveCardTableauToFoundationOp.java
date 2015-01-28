package net.sf.cotelab.lbl.controller.impl.undoableop;

import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.util.UndoableOp;

public class MoveCardTableauToFoundationOp implements UndoableOp {
	protected ControllerImpl controller;
	protected int destFanIndex;
	protected int srcFanIndex;

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
