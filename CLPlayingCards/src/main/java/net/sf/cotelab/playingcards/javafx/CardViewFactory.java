package net.sf.cotelab.playingcards.javafx;

import java.net.URL;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import net.sf.cotelab.playingcards.Card;

/**
 * A factory for <code>CardView</code> objects.
 * A view can only have one parent at a time, so views, per se, are not cached.
 * <p>
 * This class uses an instance of <code>ImageFactory</code>, to provide caching and
 * image scaling.
 */
public class CardViewFactory {
	public static final double DEFAULT_MAX_DIM = ImageFactory.DEFAULT_MAX_DIM;
	public static final String RSRC_BACK_IMAGE = "b.gif";
	public static final String RSRC_IMAGE_EXT = ".gif";
	
	protected Dimension2D dimensions;
	protected ImageFactory imageFactory;

	/**
	 * Construct a new object, using default values.
	 */
	public CardViewFactory() {
		this(DEFAULT_MAX_DIM);
	}

	/**
	 * Construct a new object, specifying the image bounding box dimension.
	 * Each image is scaled to fit in a square bounding box. The aspect ratio is
	 * preserved, so the effect is that the image's maximum dimension is given
	 * by <code>maxDim</code>.
	 * @param maxDim the image bounding box dimension.
	 */
	public CardViewFactory(double maxDim) {
		super();

		Image backImage;
		String url;
		double width, height;
		
		imageFactory = newImageFactory(maxDim);
		url = getResource(RSRC_BACK_IMAGE);
		backImage = imageFactory.getImage(url);
		width = getWidth(backImage);
		height = getHeight(backImage);
		dimensions = newDimension2D(width, height);
	}

	/**
	 * Get the back view of a card.
	 * All of the cards share a back image, but the view is of a specific card.
	 * @param card the card.
	 * @return the back view of the card.
	 */
	public CardView getBackView(Card card) {
		String resource = getResource(RSRC_BACK_IMAGE);
		Image backImage = imageFactory.getImage(resource);
		CardView backView = newCardView(card, backImage);
		
		return backView;
	}

	/**
	 * Get the dimensions of the views manufactured by this object.
	 * @return the dimensions of the views manufactured by this object.
	 */
	public Dimension2D getDimensions() {
		return dimensions;
	}

	/**
	 * Get the front view of a card.
	 * @param card the card.
	 * @return the front view of the card.
	 */
	public CardView getFrontView(Card card) {
		String resource = imageURL(card);
		Image frontImage = imageFactory.getImage(resource);
		CardView frontView = newCardView(card, frontImage);
		
		return frontView;
	}
	
	/**
	 * A workaround to enable mocking of calls to a final method.
	 * @param image the image on which to invoke the method.
	 * @return the final method's return value.
	 */
	protected double getHeight(Image image) {
		return image.getHeight();
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
	
	/**
	 * A workaround to enable mocking of calls to a final method.
	 * @param image the image on which to invoke the method.
	 * @return the final method's return value.
	 */
	protected double getWidth(Image image) {
		return image.getWidth();
	}

	/**
	 * Get the URL of a given card's front image resource.
	 * @param card the card.
	 * @return the URL.
	 */
	protected String imageURL(Card card) {
		String rsrcName = rankString(card) + suitString(card) + RSRC_IMAGE_EXT;
		
		return getResource(rsrcName);
	}
	
	/**
	 * Create a new <code>CardView</code>.
	 * @param card the card.
	 * @param image the image.
	 * @return the new object.
	 */
	protected CardView newCardView(Card card, Image image) {
		return new CardView(card, image);
	}

	/**
	 * Create a new <code>Dimension2D</code>.
	 * @param width the width.
	 * @param height the height.
	 * @return the new object.
	 */
	protected Dimension2D newDimension2D(double width, double height) {
		return new Dimension2D(width, height);
	}
	
	/**
	 * Create a new <code>ImageFactory</code>.
	 * @param maxDim the bounding box maximum dimension.
	 * @return the new object.
	 */
	protected ImageFactory newImageFactory(double maxDim) {
		return new ImageFactory(maxDim);
	}
	
	/**
	 * Get a string that is the part of a card's front image's resource name
	 * that designates the rank of the card.
	 * @param card the card.
	 * @return the string.
	 */
	protected String rankString(Card card) {
		String result = "X";
		
		switch (card.getRank()) {
		case DEUCE:
			result = "2";
			break;
		case TREY:
			result = "3";
			break;
		case FOUR:
			result = "4";
			break;
		case FIVE:
			result = "5";
			break;
		case SIX:
			result = "6";
			break;
		case SEVEN:
			result = "7";
			break;
		case EIGHT:
			result = "8";
			break;
		case NINE:
			result = "9";
			break;
		case TEN:
			result = "t";
			break;
		case JACK:
			result = "j";
			break;
		case QUEEN:
			result = "q";
			break;
		case KING:
			result = "k";
			break;
		case ACE:
			result = "a";
			break;
		case JOKER_LOW:
			result = "l";
			break;
		case JOKER_HIGH:
			result = "h";
			break;
		}
		
		return result;
	}
	
	/**
	 * Get a string that is the part of a card's front image's resource name
	 * that designates the suit of the card.
	 * @param card the card.
	 * @return the string.
	 */
	protected String suitString(Card card) {
		String result = "X";
		
		switch (card.getSuit()) {
		case CLUB:
			result = "c";
			break;
		case DIAMOND:
			result = "d";
			break;
		case HEART:
			result = "h";
			break;
		case JOKER:
			result = "j";
			break;
		case SPADE:
			result = "s";
			break;
		}
		
		return result;
	}
}
