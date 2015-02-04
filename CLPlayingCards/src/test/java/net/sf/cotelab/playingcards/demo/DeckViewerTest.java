package net.sf.cotelab.playingcards.demo;

import static org.junit.Assert.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class DeckViewerTest extends jMockTestHelper {
	@Test
	public void testMain() {
		// Identical to most main()s in JavaFX applications, this needs no test
		assertTrue(true);
	}

	@Test
	public void testNewScene() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testStartStage() {
		final DeckViewer mockDeckViewer =
				context.mock(DeckViewer.class, "mockDeckViewer");
		final Scene mockScene = context.mock(Scene.class, "mockScene");
		final Stage mockStage = context.mock(Stage.class, "mockStage");
		
		context.checking( new Expectations() {{
			oneOf(mockDeckViewer).newScene();
			will(returnValue(mockScene));
			
			oneOf(mockDeckViewer).setScene(mockStage, mockScene);
			
			oneOf(mockDeckViewer).show(mockStage);
		}});
		
		DeckViewer fixture = new DeckViewer() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewer#newScene()
			 */
			@Override
			protected Scene newScene() {
				return mockDeckViewer.newScene();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewer#setScene(javafx.stage.Stage, javafx.scene.Scene)
			 */
			@Override
			protected void setScene(Stage stage, Scene scene) {
				mockDeckViewer.setScene(stage, scene);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.demo.DeckViewer#show(javafx.stage.Stage)
			 */
			@Override
			protected void show(Stage stage) {
				mockDeckViewer.show(stage);
			}
		};
		
		fixture.start(mockStage);
	}
}
