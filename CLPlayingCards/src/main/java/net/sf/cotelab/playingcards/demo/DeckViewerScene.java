package net.sf.cotelab.playingcards.demo;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;

/**
 * The scene for the deck viewer.
 * @author cote
 */
public class DeckViewerScene extends Scene {
	/**
	 * Construct a new object, using a specified background color.
	 * @param backgroundColor the background color.
	 */
	public DeckViewerScene(Paint backgroundColor) {
		super(new DeckViewPane(), backgroundColor);
	}
}
