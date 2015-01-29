package net.sf.cotelab.playingcards.javafx;

import static org.junit.Assert.*;
import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;


public class CardViewFactoryTest extends jMockTestHelper {
	@Test
	public void testCardViewFactory() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking(new Expectations() {{
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(mockImageFactory, fixture.imageFactory);
		assertEquals(mockDimension2D, fixture.dimensions);
	}

	@Test
	public void testCardViewFactoryDouble() {
		final double expectedMaxDim = 1.234;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking(new Expectations() {{
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));
		}});
		
		CardViewFactory fixture = new CardViewFactory(expectedMaxDim) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(mockImageFactory, fixture.imageFactory);
		assertEquals(mockDimension2D, fixture.dimensions);
	}

	@Test
	public void testGetBackView() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final CardView mockCardView =
				context.mock(CardView.class, "mockCardView");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking(new Expectations() {{
			/*
			 * During construction . . .
			 */
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));

			/*
			 * During call to method under test . . .
			 */
			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).newCardView(mockCard, mockImage);
			will (returnValue(mockCardView));
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newCardView(net.sf.cotelab.playingcards.Card, javafx.scene.image.Image)
			 */
			@Override
			protected CardView newCardView(Card card, Image image) {
				return mockCardViewFactory.newCardView(card, image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(mockCardView, fixture.getBackView(mockCard));
	}

	@Test
	public void testGetDimensions() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking(new Expectations() {{
			/*
			 * During construction . . .
			 */
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));

			/*
			 * During call to method under test . . .
			 */
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newCardView(net.sf.cotelab.playingcards.Card, javafx.scene.image.Image)
			 */
			@Override
			protected CardView newCardView(Card card, Image image) {
				return mockCardViewFactory.newCardView(card, image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(mockDimension2D, fixture.getDimensions());
	}

	@Test
	public void testGetFrontView() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final CardView mockCardView =
				context.mock(CardView.class, "mockCardView");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		
		context.checking(new Expectations() {{
			/*
			 * During construction . . .
			 */
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));

			/*
			 * During call to method under test . . .
			 */
			oneOf(mockCardViewFactory).imageURL(mockCard);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).newCardView(mockCard, mockImage);
			will (returnValue(mockCardView));
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#imageURL(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			protected String imageURL(Card card) {
				return mockCardViewFactory.imageURL(card);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newCardView(net.sf.cotelab.playingcards.Card, javafx.scene.image.Image)
			 */
			@Override
			protected CardView newCardView(Card card, Image image) {
				return mockCardViewFactory.newCardView(card, image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(mockCardView, fixture.getFrontView(mockCard));
	}

	@Test
	public void testGetResource() {
		/*
		 * This method is a wrapper around
		 * getClass().getResource(resourceName).toExternalForm(). It is largely
		 * comprised of standard idiom, and is omitted from testing.
		 */
	}

	@Test
	public void testImageURL() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final String rankString = "rankString";
		final String suitString = "suitString";
		final String resourceName =
				rankString + suitString + CardViewFactory.RSRC_IMAGE_EXT;
		
		context.checking(new Expectations() {{
			/*
			 * During fixture construction . . .
			 */
			
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));

			/*
			 * During method under test . . .
			 */
			
			oneOf(mockCardViewFactory).rankString(mockCard);
			will(returnValue(rankString));
			
			oneOf(mockCardViewFactory).suitString(mockCard);
			will(returnValue(suitString));

			oneOf(mockCardViewFactory).getResource(resourceName);
			will(returnValue(url));
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#rankString(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			protected String rankString(Card card) {
				return mockCardViewFactory.rankString(card);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#suitString(net.sf.cotelab.playingcards.Card)
			 */
			@Override
			protected String suitString(Card card) {
				return mockCardViewFactory.suitString(card);
			}
		};
		
		assertEquals(url, fixture.imageURL(mockCard));
	}

	@Test
	public void testNewCardView() {
		/*
		 * This is a wrapper around a constructor, needed to enable mocking. It
		 * is not tested, here.
		 */
	}

	@Test
	public void testNewImageFactory() {
		/*
		 * This is a wrapper around a constructor, needed to enable mocking. It
		 * is not tested, here.
		 */
	}

	@Test
	public void testRankString() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final String expected = "j";
		
		context.checking(new Expectations() {{
			/*
			 * During fixture construction . . .
			 */
			
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));

			/*
			 * During method under test . . .
			 */
			
			oneOf(mockCard).getRank();
			will(returnValue(Rank.JACK));
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(expected, fixture.rankString(mockCard));
	}

	@Test
	public void testSuitString() {
		final double expectedMaxDim = CardViewFactory.DEFAULT_MAX_DIM;
		final ImageFactory mockImageFactory =
				context.mock(ImageFactory.class, "mockImageFactory");
		final String url = "url";
		final Image mockImage = context.mock(Image.class, "mockImage");
		final double width = 3;
		final double height = 4;
		final Dimension2D mockDimension2D =
				context.mock(Dimension2D.class, "mockDimension2D");
		final CardViewFactory mockCardViewFactory =
				context.mock(CardViewFactory.class, "mockCardViewFactory");
		final Card mockCard = context.mock(Card.class, "mockCard");
		final String expected = "h";
		
		context.checking(new Expectations() {{
			/*
			 * During fixture construction . . .
			 */
			
			oneOf(mockCardViewFactory).newImageFactory(expectedMaxDim);
			will(returnValue(mockImageFactory));

			oneOf(mockCardViewFactory).getResource(
					CardViewFactory.RSRC_BACK_IMAGE);
			will(returnValue(url));
			
			oneOf(mockImageFactory).getImage(url);
			will(returnValue(mockImage));
			
			oneOf(mockCardViewFactory).getWidth(mockImage);
			will(returnValue(width));
			
			oneOf(mockCardViewFactory).getHeight(mockImage);
			will(returnValue(height));
			
			oneOf(mockCardViewFactory).newDimension2D(width, height);
			will(returnValue(mockDimension2D));

			/*
			 * During method under test . . .
			 */
			
			oneOf(mockCard).getSuit();
			will(returnValue(Suit.HEART));
		}});
		
		CardViewFactory fixture = new CardViewFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getHeight(javafx.scene.image.Image)
			 */
			@Override
			protected double getHeight(Image image) {
				return mockCardViewFactory.getHeight(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getResource(java.lang.String)
			 */
			@Override
			protected String getResource(String resourceName) {
				return mockCardViewFactory.getResource(resourceName);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#getWidth(javafx.scene.image.Image)
			 */
			@Override
			protected double getWidth(Image image) {
				return mockCardViewFactory.getWidth(image);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newDimension2D(double, double)
			 */
			@Override
			protected Dimension2D newDimension2D(
					double width, double height) {
				return mockCardViewFactory.newDimension2D(width, height);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.CardViewFactory#newImageFactory(double)
			 */
			@Override
			protected ImageFactory newImageFactory(double maxDim) {
				return mockCardViewFactory.newImageFactory(maxDim);
			}
		};
		
		assertEquals(expected, fixture.suitString(mockCard));
	}
}
