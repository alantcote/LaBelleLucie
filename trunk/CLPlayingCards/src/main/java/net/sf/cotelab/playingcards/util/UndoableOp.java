package net.sf.cotelab.playingcards.util;

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
