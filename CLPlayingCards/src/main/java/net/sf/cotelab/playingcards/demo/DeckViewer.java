package net.sf.cotelab.playingcards.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * An application to demonstrate the playing cards, by displaying their images.
 * @author cote
 */
public class DeckViewer extends Application {
	public static final Paint BACKGROUND_COLOR = Color.DARKGREEN;
	
	/**
	 * The standard entry-point for a Java application.
	 * @param args the command-line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * The standard entry-point for JavaFX applications.
	 */
	@Override
	public void start(final Stage primaryStage) {
		Scene scene;
		
		// create the scene
		scene = newScene();
		
		// inform the stage what scene is to play
		setScene(primaryStage, scene);
		
		// show the stage
		show(primaryStage);
	}

	/**
	 * Create a new scene.
	 * @return the new scene.
	 */
	protected Scene newScene() {
		return new DeckViewerScene(BACKGROUND_COLOR);
	}
	
	/**
	 * Set a scene on a stage.
	 * This method exists to wrap a call to a final (and thus, unmockable)
	 * method, in support of unit testing.
	 * @param stage the stage.
	 * @param scene the scene.
	 */
	protected void setScene(Stage stage, Scene scene) {
		stage.setScene(scene);
	}
	
	/**
	 * Show a stage.
	 * This method exists to wrap a call to a final (and thus, unmockable)
	 * method, in support of unit testing.
	 * @param stage the stage.
	 */
	protected void show(Stage stage) {
		stage.show();
	}
}
