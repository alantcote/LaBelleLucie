package net.sf.cotelab.playingcards.javafx;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.scene.image.Image;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.playingcards.Card;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class CardViewTest {
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

	/**
	 * Get the URL of a given resource.
	 * @param resourceName the resource path, relative to this class' package.
	 * @return the URL.
	 */
	protected String getResource(String resourceName) {
		URL url = getClass().getResource(resourceName);
		
		return url.toExternalForm();
	}
	
	@Test
	public void testCardView() {
		Card mockCard = context.mock(Card.class, "mockCard");
		ImageFactory imageFactory = new ImageFactory();
//		This is evil, but there's no way to make a mock for the image that works
		Image mockImage = imageFactory.getImage(getResource(CardViewFactory.RSRC_BACK_IMAGE));
		
		CardView fixture = new CardView(mockCard, mockImage);
		
		assertEquals(mockCard, fixture.card);
		assertEquals(mockImage, fixture.getImage());
	}

	@Test
	public void testGetCard() {
		Card mockCard = context.mock(Card.class, "mockCard");
		ImageFactory imageFactory = new ImageFactory();
//		This is evil, but there's no way to make a mock for the image that works
		Image mockImage = imageFactory.getImage(getResource(CardViewFactory.RSRC_BACK_IMAGE));
		
		/*
		 * context.checking( new Expectations() {{ oneOf(mockImage).isAnimation();
		 * will(returnValue(false)); }});
		 */
		
		CardView fixture = new CardView(mockCard, mockImage);
		
		fixture.card = mockCard;
		
		assertEquals(mockCard, fixture.getCard());
	}
}
