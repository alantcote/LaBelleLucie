package net.sf.cotelab.lbl.controller.impl.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.playingcards.Card;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class MasterInputHandlerTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
	protected MasterInputHandler fixture;
	protected ControllerImpl mockControllerImpl;
	
	@Before
	public void setup() {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
		
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
