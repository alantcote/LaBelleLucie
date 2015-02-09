package net.sf.cotelab.lbl.controller.impl.undoableop;

import static org.junit.Assert.*;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class DrawOpTest extends jMockTestHelper {
	@Test
	public void testDoOp() {
		final ControllerImpl mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		final int fanIndex = 0;
		final int cardIndex = 0;
		DrawOp fixture = new DrawOp(mockControllerImpl, fanIndex, cardIndex);
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).draw(fanIndex, cardIndex);
		}});
		
		fixture.doOp();
	}

	@Test
	public void testDrawOp() {
		final ControllerImpl mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		final int fanIndex = 0;
		final int cardIndex = 0;
		DrawOp fixture = new DrawOp(mockControllerImpl, fanIndex, cardIndex);
		
		assertEquals(mockControllerImpl, fixture.controller);
		assertEquals(fanIndex, fixture.fanIndex);
		assertEquals(cardIndex, fixture.cardIndex);
	}

	@Test
	public void testUndoOp() {
		final ControllerImpl mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		final int fanIndex = 0;
		final int cardIndex = 0;
		DrawOp fixture = new DrawOp(mockControllerImpl, fanIndex, cardIndex);
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).unDraw(fanIndex, cardIndex);
		}});
		
		fixture.undoOp();
	}
}
