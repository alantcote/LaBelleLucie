package io.github.alantcote.labellelucie.controller.facade;

import static org.junit.Assert.assertNotNull;

import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import net.sf.cotelab.playingcards.Card;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class DefaultInputHandlerTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
	protected DefaultInputHandler fixture;
	protected Card mockCard;
	
	@Before
	public void setup() {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());

		fixture = new DefaultInputHandler();
		
		assertNotNull(fixture);
		
		mockCard = context.mock(Card.class, "mockCard");
		
		assertNotNull(mockCard);
	}
	
	@Test
	public void testOnCardMoveRequested() {
		fixture.onCardMoveRequested(mockCard);
	}

	@Test
	public void testOnDragDetected() {
		fixture.onDragDetected(mockCard);
	}

	@Test
	public void testOnDragDone() {
		fixture.onDragDone(mockCard);
	}

	@Test
	public void testOnDragDropped() {
		fixture.onDragDropped(mockCard);
	}

	@Test
	public void testOnDragEntered() {
		fixture.onDragEntered(mockCard);
	}

	@Test
	public void testOnDragExited() {
		fixture.onDragExited(mockCard);
	}

	@Test
	public void testOnDragOver() {
		fixture.onDragOver(mockCard);
	}

	@Test
	public void testOnDrawRequested() {
		fixture.onDrawRequested(mockCard);
	}

	@Test
	public void testOnExitRequest() {
		fixture.onExitRequest();
	}

	@Test
	public void testOnMouseEntered() {
		fixture.onMouseEntered(mockCard);
	}

	@Test
	public void testOnMouseExited() {
		fixture.onMouseExited(mockCard);
	}

	@Test
	public void testOnNewGameRequested() {
		fixture.onNewGameRequested();
	}

	@Test
	public void testOnReshuffleRequest() {
		fixture.onReshuffleRequest();
	}
}
