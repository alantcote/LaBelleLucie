package net.sf.cotelab.lbl.controller.facade;

import static org.junit.Assert.*;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.junit.Before;
import org.junit.Test;

public class DefaultInputHandlerTest extends jMockTestHelper {
	protected DefaultInputHandler fixture;
	protected Card mockCard;
	
	@Before
	public void setup() {
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
