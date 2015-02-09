package net.sf.cotelab.lbl.controller.impl.handler;

import static org.junit.Assert.*;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class MasterInputHandlerTest extends jMockTestHelper {
	protected MasterInputHandler fixture;
	protected ControllerImpl mockControllerImpl;
	
	@Before
	public void setup() {
		mockControllerImpl =
				context.mock(ControllerImpl.class, "mockControllerImpl");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).updateGameSummary();
		}});
		
		fixture = new MasterInputHandler(mockControllerImpl);
	}

	@Test
	public void testMasterInputHandler() {
		assertNotNull(fixture);
		assertEquals(mockControllerImpl, fixture.controller);
	}

	@Test
	public void testOnCardMoveRequested() {
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).onCardMoveRequested(mockCard);
		}});
		
		fixture.onCardMoveRequested(mockCard);
	}

	@Test
	public void testOnDrawRequested() {
		final Card mockCard = context.mock(Card.class, "mockCard");
		
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).onDrawRequested(mockCard);
		}});
		
		fixture.onDrawRequested(mockCard);
	}

	@Test
	public void testOnExitRequest() {
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).onExitRequest();
		}});
		
		fixture.onExitRequest();
	}

	@Test
	public void testOnNewGameRequested() {
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).onNewGameRequested();
		}});
		
		fixture.onNewGameRequested();
	}

	@Test
	public void testOnReshuffleRequest() {
		context.checking(new Expectations() {{
			oneOf(mockControllerImpl).onReshuffleRequest();
		}});
		
		fixture.onReshuffleRequest();
	}
}
