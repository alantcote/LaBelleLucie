package net.sf.cotelab.playingcards.javafx;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.cotelab.playingcards.Card;

public class CardView extends ImageView {
	protected Card card;
	
	/**
	 * Construct a new object from a card and an image.
	 * @param card the card - may be null for a card-back image.
	 * @param image the image.
	 */
	public CardView(Card card, Image image) {
		super(image);
		
		this.card = card;

		Tooltip tooltip = new Tooltip();
		ImageView ttView = new ImageView(image);
		
		tooltip.setGraphic(ttView);
		Tooltip.install(this, tooltip);
	}

	public Card getCard() {
		return card;
	}
}
