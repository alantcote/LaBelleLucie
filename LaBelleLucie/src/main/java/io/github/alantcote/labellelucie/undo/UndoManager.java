package io.github.alantcote.labellelucie.undo;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An undo manager.
 */
public class UndoManager {
	private static final Logger log = Logger.getLogger(UndoManager.class.getName());
	/**
	 * Operations that have been undone and may be redone.
	 */
	protected List<UndoableOp> redoList;
	
	/**
	 * Operations that may be undone.
	 */
	protected List<UndoableOp> undoList;

	/**
	 * Construct a new object.
	 */
	public UndoManager() {
		super();

		reset();
	}

	/**
	 * Add an undoable operation.
	 * @param op an undoable operation.
	 */
	public void add(UndoableOp op) {
		undoList.add(op);
		redoList.clear();
	}

	/**
	 * Ask whether there are any redoable operations.
	 * @return the answer.
	 */
	public boolean canRedo() {
		return !redoList.isEmpty();
	}

	/**
	 * Ask whether there are any undoable operations.
	 * @return the answer.
	 */
	public boolean canUndo() {
		return !undoList.isEmpty();
	}

	/**
	 * Redo an operation, if any redoable operations exist.
	 */
	public void redoOp() {
		if (canRedo()) {
			UndoableOp op = redoList.remove(redoList.size() - 1);

			undoList.add(op);

			try {
				op.doOp();
			} catch (Throwable t) {
				log.log(Level.WARNING, "caught Throwable", t);
			}
		}
	}

	/**
	 * Reset this object.
	 */
	public void reset() {
		redoList = newList_UndoableOp();
		undoList = newList_UndoableOp();
	}

	/**
	 * Undo an operation if there are any undoable operations.
	 */
	public void undoOp() {
		if (canUndo()) {
			UndoableOp op = undoList.remove(undoList.size() - 1);

			redoList.add(op);

			try {
				op.undoOp();
			} catch (Throwable t) {
				log.log(Level.WARNING, "caught Throwable", t);
			}
		}
	}

	/**
	 * Create a new list of undoable operations.
	 * @return a new list of undoable operations.
	 */
	protected List<UndoableOp> newList_UndoableOp() {
		return new LinkedList<>();
	}
}
