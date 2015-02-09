package net.sf.cotelab.lbl.controller.impl.undoableop;

import static org.junit.Assert.*;
import javafx.beans.property.ObjectProperty;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.model.facade.GameSummary;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class MoveCardTableauToFoundationOpTest extends jMockTestHelper {
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
