package net.sf.cotelab.lbl.view.impl.dialog;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MessageDialog {
	public static final double HEIGHT = 128;
	public static final String INFO_ICON_RESOURCE =
			"/com/sun/java/swing/plaf/windows/icons/Inform.gif";
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
		Node iconNode = createInfoIconNode();
		Node messageNode = createMessageNode(message);
		double hInset = WIDTH / 10;
		double vInset = HEIGHT / 8;
		
		BorderPane.setMargin(buttonNode, new Insets(vInset / 2, hInset, vInset, hInset));
		BorderPane.setMargin(messageNode, new Insets(vInset, hInset, vInset / 2, hInset));

		dialog.setBottom(buttonNode);
		dialog.setCenter(messageNode);
		dialog.setLeft(iconNode);
		
		return dialog;
	}
	
	protected Node createInfoIconNode() {
		URL rsrc = getClass().getResource(INFO_ICON_RESOURCE);
		Image image = new Image(rsrc.toExternalForm());
		ImageView imageView = new ImageView(image);
		FlowPane node = new FlowPane();
		double fitWidth = image.getWidth() * 2;
		double fitHeight = image.getHeight() * 2;
		
		imageView.setFitHeight(fitHeight);
		imageView.setFitWidth(fitWidth);
		imageView.setPreserveRatio(true);
		
		node.getChildren().add(imageView);
		node.setAlignment(Pos.CENTER);
		node.setPrefSize(fitWidth * 2, fitHeight * 2);
		
		return node;
	}

	protected Node createMessageNode(String message) {
		return new Label(message);
	}

	protected Node createMessageNode(String message, Node graphic) {
		return new Label(message, graphic);
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
