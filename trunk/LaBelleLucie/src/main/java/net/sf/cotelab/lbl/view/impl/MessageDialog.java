package net.sf.cotelab.lbl.view.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MessageDialog {
	public static final double HEIGHT = 128;
	public static final double WIDTH = 600;
	
	public MessageDialog() {
		super();
	}

	public void show(
			Window owner, String title, String message, Color background) {
		Stage stage = createStage(owner, title, message, background);
		
		stage.showAndWait();
	}
	
	protected Parent createDialogPane(Stage stage, String message) {
		BorderPane dialog = new BorderPane();
		Node buttonNode = createOKButtonNode(stage);
		Node messageNode = createMessageNode(message);
		double hInset = WIDTH / 10;
		double vInset = HEIGHT / 8;
		
		BorderPane.setMargin(buttonNode, new Insets(vInset / 2, hInset, vInset, hInset));
		BorderPane.setMargin(messageNode, new Insets(vInset, hInset, vInset / 2, hInset));

		dialog.setBottom(buttonNode);
		dialog.setCenter(messageNode);
		
		return dialog;
	}

	protected Node createMessageNode(String message) {
		return new Label(message);
	}

	protected Node createOKButtonNode(final Stage stage) {
		HBox node = new HBox();
		Button button = new Button("OK");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
			}
		});
		
		node.getChildren().add(button);
		node.setAlignment(Pos.BASELINE_CENTER);
		
		return node;
	}

	protected Stage createStage(
			Window owner, String title, String message, Color background) {
		final Stage stage = new Stage();

		if (owner == null) {
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(owner);
		}
		
		stage.initStyle(StageStyle.UTILITY);
		stage.setFullScreen(false);
		stage.setIconified(false);
		stage.setResizable(false);
		stage.setScene(new Scene(createDialogPane(stage, message), background));
		stage.setTitle(title);
		stage.sizeToScene();
		
		// center stage relative to owner
		if (owner != null) {
			double ownerWidth = owner.getWidth();
			double ownerHeight = owner.getHeight();
			double ownerX = owner.getX();
			double ownerY = owner.getY();
			final double centerX = ownerX + (ownerWidth / 2);
			final double centerY = ownerY + (ownerHeight / 2);
			
			stage.setOnShown(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					double stageWidth = stage.getWidth();
					double stageHeight = stage.getHeight();
					double offsetX = stageWidth / 2;
					double offsetY = stageHeight / 2;
					double stageX = centerX - offsetX;
					double stageY = centerY - offsetY;
					
					stage.setX(stageX);
					stage.setY(stageY);
				}
			});
		}
		
		return stage;
	}
}
