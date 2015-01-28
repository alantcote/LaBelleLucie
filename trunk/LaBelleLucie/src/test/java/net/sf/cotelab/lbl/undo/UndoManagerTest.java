package net.sf.cotelab.lbl.undo;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.cotelab.lbl.undo.UndoManager;
import net.sf.cotelab.lbl.undo.UndoableOp;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class UndoManagerTest extends jMockTestHelper{

	@Test
	public void testUndoManager() {
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).reset();
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			public void reset() {
				mockUndoManager.reset();
			}
		};
		
		assertNotNull(fixture);
	}

	@Test
	public void testAdd() {
		@SuppressWarnings("unchecked")
		final List<UndoableOp> redoList = context.mock(List.class, "redoList");
		@SuppressWarnings("unchecked")
		final List<UndoableOp> undoList = context.mock(List.class, "undoList");
		final UndoableOp mockUndoableOp = context.mock(UndoableOp.class);
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));
			
			oneOf(undoList).add(with(mockUndoableOp));
			
			oneOf(redoList).clear();
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			protected List<UndoableOp> newList_UndoableOp() {
				return mockUndoManager.newList_UndoableOp();
			}
		};
		
		fixture.add(mockUndoableOp);
	}

	@Test
	public void testCanRedo() {
		@SuppressWarnings("unchecked")
		final List<UndoableOp> redoList = context.mock(List.class, "redoList");
		@SuppressWarnings("unchecked")
		final List<UndoableOp> undoList = context.mock(List.class, "undoList");
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));
			
			oneOf(redoList).isEmpty();
			will(returnValue(false));
			
			oneOf(redoList).isEmpty();
			will(returnValue(true));
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			protected List<UndoableOp> newList_UndoableOp() {
				return mockUndoManager.newList_UndoableOp();
			}
		};
		
		assertTrue(fixture.canRedo());
		assertFalse(fixture.canRedo());
	}

	@Test
	public void testCanUndo() {
		@SuppressWarnings("unchecked")
		final List<UndoableOp> redoList = context.mock(List.class, "redoList");
		@SuppressWarnings("unchecked")
		final List<UndoableOp> undoList = context.mock(List.class, "undoList");
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));
			
			oneOf(undoList).isEmpty();
			will(returnValue(false));
			
			oneOf(undoList).isEmpty();
			will(returnValue(true));
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			protected List<UndoableOp> newList_UndoableOp() {
				return mockUndoManager.newList_UndoableOp();
			}
		};
		
		assertTrue(fixture.canUndo());
		assertFalse(fixture.canUndo());
	}

	@Test
	public void testRedoOp() {
		@SuppressWarnings("unchecked")
		final List<UndoableOp> redoList = context.mock(List.class, "redoList");
		@SuppressWarnings("unchecked")
		final List<UndoableOp> undoList = context.mock(List.class, "undoList");
		final UndoableOp mockUndoableOp = context.mock(UndoableOp.class);
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));

			oneOf(mockUndoManager).canRedo();
			will(returnValue(false));

			oneOf(mockUndoManager).canRedo();
			will(returnValue(true));
			
			oneOf(redoList).size();
			will(returnValue(1));
			
			oneOf(redoList).remove(with(0));
			will(returnValue(mockUndoableOp));
			
			oneOf(undoList).add(with(mockUndoableOp));
			
			oneOf(mockUndoableOp).doOp();
			will(throwException(new NullPointerException()));

			oneOf(mockUndoManager).canRedo();
			will(returnValue(true));
			
			oneOf(redoList).size();
			will(returnValue(1));
			
			oneOf(redoList).remove(with(0));
			will(returnValue(mockUndoableOp));
			
			oneOf(undoList).add(with(mockUndoableOp));
			
			oneOf(mockUndoableOp).doOp();
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			public boolean canRedo() {
				return mockUndoManager.canRedo();
			}

			@Override
			protected List<UndoableOp> newList_UndoableOp() {
				return mockUndoManager.newList_UndoableOp();
			}
		};
		
		fixture.redoOp();
		fixture.redoOp();
		fixture.redoOp();
	}

	@Test
	public void testReset() {
		@SuppressWarnings("unchecked")
		final List<UndoableOp> redoList = context.mock(List.class, "redoList");
		@SuppressWarnings("unchecked")
		final List<UndoableOp> undoList = context.mock(List.class, "undoList");
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));
			
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			protected List<UndoableOp> newList_UndoableOp() {
				return mockUndoManager.newList_UndoableOp();
			}
		};
		
		fixture.reset();
		
		assertEquals(redoList, fixture.redoList);
		assertEquals(undoList, fixture.undoList);
	}

	@Test
	public void testUndoOp() {
		@SuppressWarnings("unchecked")
		final List<UndoableOp> redoList = context.mock(List.class, "redoList");
		@SuppressWarnings("unchecked")
		final List<UndoableOp> undoList = context.mock(List.class, "undoList");
		final UndoableOp mockUndoableOp = context.mock(UndoableOp.class);
		final UndoManager mockUndoManager = context.mock(UndoManager.class);
		
		context.checking( new Expectations() {{
			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(redoList));

			oneOf(mockUndoManager).newList_UndoableOp();
			will(returnValue(undoList));

			oneOf(mockUndoManager).canUndo();
			will(returnValue(false));

			oneOf(mockUndoManager).canUndo();
			will(returnValue(true));
			
			oneOf(undoList).size();
			will(returnValue(1));
			
			oneOf(undoList).remove(with(0));
			will(returnValue(mockUndoableOp));
			
			oneOf(redoList).add(with(mockUndoableOp));
			
			oneOf(mockUndoableOp).undoOp();
			will(throwException(new NullPointerException()));

			oneOf(mockUndoManager).canUndo();
			will(returnValue(true));
			
			oneOf(undoList).size();
			will(returnValue(1));
			
			oneOf(undoList).remove(with(0));
			will(returnValue(mockUndoableOp));
			
			oneOf(redoList).add(with(mockUndoableOp));
			
			oneOf(mockUndoableOp).undoOp();
		}});
		
		UndoManager fixture = new UndoManager() {
			@Override
			public boolean canUndo() {
				return mockUndoManager.canUndo();
			}

			@Override
			protected List<UndoableOp> newList_UndoableOp() {
				return mockUndoManager.newList_UndoableOp();
			}
		};
		
		fixture.undoOp();
		fixture.undoOp();
		fixture.undoOp();
	}

}
