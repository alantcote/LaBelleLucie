package net.sf.cotelab.playingcards.demo;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public class DeckViewerScene extends Scene {
	public DeckViewerScene(Paint backgroundColor) {
		super(new DeckViewPane(), backgroundColor);
	}
}
