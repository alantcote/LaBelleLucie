package io.github.alantcote.labellelucie.undo;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UndoManager {
	private static final Logger log =
			Logger.getLogger(UndoManager.class.getName());
	
	protected List<UndoableOp> redoList;
	protected List<UndoableOp> undoList;
	
	public UndoManager() {
		super();
		
		reset();
	}
	
	public void add(UndoableOp op) {
		undoList.add(op);
		redoList.clear();
	}
	
	public boolean canRedo() {
		return !redoList.isEmpty();
	}
	
	public boolean canUndo() {
		return !undoList.isEmpty();
	}
	
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
	
	public void reset() {
		redoList = newList_UndoableOp();
		undoList = newList_UndoableOp();
	}

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
	
	protected List<UndoableOp> newList_UndoableOp() {
		return new LinkedList<>();
	}
}
