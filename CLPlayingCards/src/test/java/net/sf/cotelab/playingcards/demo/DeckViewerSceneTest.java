package net.sf.cotelab.playingcards.demo;

import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class DeckViewerSceneTest {
	@Test
	public void testDeckViewerScene() {
		DeckViewerScene fixture = new DeckViewerScene(Color.DARKGREEN);
		
		assertNotNull(fixture);
	}
}
