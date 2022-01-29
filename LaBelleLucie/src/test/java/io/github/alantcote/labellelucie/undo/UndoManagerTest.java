package io.github.alantcote.labellelucie.undo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import io.github.alantcote.labellelucie.undo.UndoManager;
import io.github.alantcote.labellelucie.undo.UndoableOp;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class UndoManagerTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@Before
	public void runBeforeTests() throws Exception {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
	}
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
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
	}
}
