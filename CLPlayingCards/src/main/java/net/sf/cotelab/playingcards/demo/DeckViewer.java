package net.sf.cotelab.playingcards.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class DeckViewer extends Application {
	public static final Paint BACKGROUND_COLOR = Color.DARKGREEN;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(final Stage primaryStage) {
		Scene scene;
		
		// create the scene
		scene = newScene();
		
		// inform the stage what scene is to play
		primaryStage.setScene(scene);
		
		// show the stage
		primaryStage.show();
	}

	protected Scene newScene() {
		return new DeckViewerScene(BACKGROUND_COLOR);
	}
}
