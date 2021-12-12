package net.sf.cotelab.lbl.controller.impl.undoableop;

import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.lbl.undo.UndoableOp;

/**
 * An <code>UndoableOp</code> that implements playing a the top card from one
 * tableau fan to the top of another tableau fan.
 * @author cote
 */
public class MoveCardTableauToTableauOp implements UndoableOp {
	protected ControllerImpl controller;
	protected int destFanIndex;
	protected int srcFanIndex;

	/**
	 * Construct a new object.
	 * @param controller the controller.
	 * @param srcFanIndex the index of the source tableau fan.
	 * @param destFanIndex the index of the destination tableau fan.
	 */
	public MoveCardTableauToTableauOp(
			ControllerImpl controller, int srcFanIndex, int destFanIndex) {
		super();
		
		this.controller = controller;
		this.destFanIndex = destFanIndex;
		this.srcFanIndex = srcFanIndex;
	}

	@Override
	public void doOp() {
		controller.moveTopCardTableauToTableau(srcFanIndex, destFanIndex);
		
		controller.updateGameSummary();
	}

	@Override
	public void undoOp() {
		controller.moveTopCardTableauToTableau(destFanIndex, srcFanIndex);

		controller.getModel().getGameSummary().set(GameSummary.IN_PROGRESS);
	}

}
