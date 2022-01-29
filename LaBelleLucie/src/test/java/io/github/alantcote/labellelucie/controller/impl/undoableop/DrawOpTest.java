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
import io.github.alantcote.labellelucie.controller.impl.undoableop.DrawOp;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class DrawOpTest {
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
