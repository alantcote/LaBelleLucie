package net.sf.cotelab.playingcards.javafx;

import java.net.URL;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import net.sf.cotelab.playingcards.Card;

public class CardViewFactory {
	public static final double DEFAULT_MAX_DIM = ImageFactory.DEFAULT_MAX_DIM;
	public static final String RSRC_BACK_IMAGE = "b.gif";
	public static final String RSRC_IMAGE_EXT = ".gif";
	
	protected Dimension2D dimensions;
	protected ImageFactory imageFactory;

	public CardViewFactory() {
		this(DEFAULT_MAX_DIM);
	}

	public CardViewFactory(double maxDim) {
		super();

		Image backImage;
		
		imageFactory = newImageFactory(maxDim);
		
		backImage = imageFactory.getImage(getResource(RSRC_BACK_IMAGE));
		dimensions = new Dimension2D(
				backImage.getWidth(), backImage.getHeight());
	}
	
	public CardView getBackView(Card card) {
		String resource = getResource(RSRC_BACK_IMAGE);
		Image backImage = imageFactory.getImage(resource);
		CardView backView = new CardView(card, backImage);
		
		return backView;
	}
	
	/**
	 * @return the dimensions
	 */
	public Dimension2D getDimensions() {
		return dimensions;
	}

	public CardView getFrontView(Card card) {
		String resource = imageURL(card);
		Image frontImage = imageFactory.getImage(resource);
		CardView frontView = new CardView(card, frontImage);
		
		return frontView;
	}
	
	protected String getResource(String resourceName) {
		URL url = getClass().getResource(resourceName);
		
		return url.toExternalForm();
	}

	protected String imageURL(Card card) {
		String rsrcName = rankString(card) + suitString(card) + RSRC_IMAGE_EXT;
		
		return getResource(rsrcName);
	}
	
	protected ImageFactory newImageFactory(double maxDim) {
		return new ImageFactory(maxDim);
	}
	
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
