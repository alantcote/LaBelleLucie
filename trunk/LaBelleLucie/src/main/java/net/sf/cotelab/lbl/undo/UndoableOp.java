package net.sf.cotelab.lbl.undo;

/**
 * An undoable operation.
 * @author US80653H
 */
public interface UndoableOp {
	/**
	 * Do the operation.
	 */
	public void doOp();
	
	/**
	 * Undo the operation.
	 */
	public void undoOp();
}
