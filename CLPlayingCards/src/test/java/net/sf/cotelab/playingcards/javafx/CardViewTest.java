package net.sf.cotelab.playingcards.javafx;

import static org.junit.Assert.*;
import javafx.scene.image.Image;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.junit.Test;

public class CardViewTest extends jMockTestHelper {
	@Test
	public void testCardView() {
		Card mockCard = context.mock(Card.class, "mockCard");
		Image mockImage = context.mock(Image.class, "mockImage");
		
		CardView fixture = new CardView(mockCard, mockImage);
		
		assertEquals(mockCard, fixture.card);
		assertEquals(mockImage, fixture.getImage());
	}

	@Test
	public void testGetCard() {
		Card mockCard = context.mock(Card.class, "mockCard");
		Image mockImage = context.mock(Image.class, "mockImage");
		
		CardView fixture = new CardView(mockCard, mockImage);
		
		fixture.card = mockCard;
		
		assertEquals(mockCard, fixture.getCard());
	}
}
