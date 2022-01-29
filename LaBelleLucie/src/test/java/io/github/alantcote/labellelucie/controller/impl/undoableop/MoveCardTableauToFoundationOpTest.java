package io.github.alantcote.labellelucie.controller.impl.undoableop;

import static org.junit.Assert.assertEquals;

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
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.undoableop.MoveCardTableauToFoundationOp;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import javafx.beans.property.ObjectProperty;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class MoveCardTableauToFoundationOpTest {
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
	public void testDoOp() {
		final ControllerImpl mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		final int srcFanIndex = 0;
		final int destFanIndex = 0;
		MoveCardTableauToFoundationOp fixture =
				new MoveCardTableauToFoundationOp(
						mockControllerImpl, srcFanIndex, destFanIndex);
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).moveTopCardTableauToFoundation(
					srcFanIndex, destFanIndex);

			oneOf(mockControllerImpl).updateGameSummary();
		}});
		
		fixture.doOp();
	}

	@Test
	public void testMoveCardTableauToFoundationOp() {
		final ControllerImpl mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		final int srcFanIndex = 0;
		final int destFanIndex = 0;
		MoveCardTableauToFoundationOp fixture =
				new MoveCardTableauToFoundationOp(
						mockControllerImpl, srcFanIndex, destFanIndex);
		
		assertEquals(mockControllerImpl, fixture.controller);
		assertEquals(srcFanIndex, fixture.srcFanIndex);
		assertEquals(destFanIndex, fixture.destFanIndex);
	}

	@Test
	public void testUndoOp() {
		final ControllerImpl mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		final int srcFanIndex = 0;
		final int destFanIndex = 0;
		MoveCardTableauToFoundationOp fixture =
				new MoveCardTableauToFoundationOp(
						mockControllerImpl, srcFanIndex, destFanIndex);
		final GameState mockGameState =
				context.mock(GameState.class, "mockGameState");
		@SuppressWarnings("unchecked")
		final ObjectProperty<GameSummary> mockGameSummary =
				(ObjectProperty<GameSummary>)
				context.mock(ObjectProperty.class, "mockGameSummary");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).moveTopCardFoundationToTableau(
					destFanIndex, srcFanIndex);

			oneOf(mockControllerImpl).getModel();
			will(returnValue(mockGameState));
			
			oneOf(mockGameState).getGameSummary();
			will(returnValue(mockGameSummary));
			
			oneOf(mockGameSummary).set(GameSummary.IN_PROGRESS);
		}});
		
		fixture.undoOp();
	}
}
