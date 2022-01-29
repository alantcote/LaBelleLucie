package io.github.alantcote.labellelucie.view.impl.dialog;

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
	
	/**
	 * Show a modal dialog with multiple response options.
	 * Waits for a response, returning the response option chosen.
	 * @param owner the window relative to which the dialog is modal and
	 *		positioned.
	 * @param message the object to display - must be a <code>String</code> or a
	 *		<code>Node</code>.
	 * @param selectionValues the response options - each must be a
	 *		<code>String</code> (used as the text on a <code>Button</code>) or a
	 *		<code>Button</code> (used as-is) - the dialog is closed when one of
	 *		these is selected.
	 * @param title the title of the dialog.
	 * @param defaultSelectionValue the default response.
	 * @return the selected response option.
	 */
	public Object showInputDialog(Window owner, String title, Object message,
			Object[] selectionValues, Object defaultSelectionValue) {
		final Stage stage = createStage(owner, title);
		Node messageNode;
		HBox buttonBox;
		BorderPane rootPane;
		Insets insets = new Insets(10);
		Scene scene;
		final Object[] selection = { defaultSelectionValue };
		
		// set up the message node
		messageNode = messageNode(message);
		
		// set up the buttons node
		buttonBox = new HBox();
		buttonBox.setAlignment(Pos.BASELINE_CENTER);
		for (Object selVal : selectionValues) {
			Button button;
			final Object finalSelVal = selVal;
			
			if (finalSelVal instanceof Button) {
				button = (Button) finalSelVal;
			} else if (finalSelVal instanceof String) {
				button = new Button((String) finalSelVal);
			} else {
				throw new IllegalArgumentException(
						"selectionValues[i] must be a String or a Button");
			}
			
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					selection[0] = finalSelVal;
					
					stage.close();
				}
			});
			
			buttonBox.getChildren().add(button);

			HBox.setMargin(button, insets);
		}
		
		// create the dialog root pane
		rootPane = new BorderPane();
		rootPane.setCenter(messageNode);
		BorderPane.setMargin(messageNode, insets);
		rootPane.setBottom(buttonBox);
		BorderPane.setMargin(buttonBox, insets);
		
		// create the dialog scene
		scene = new Scene(rootPane);
		
		// attach the dialog scene to the dialog
		stage.setScene(scene);
		
		// show the dialog and get the response
		stage.showAndWait();
		
		return selection[0];
	}

	/**
	 * @param message
	 * @throws IllegalArgumentException
	 */
	protected Node messageNode(Object message) throws IllegalArgumentException {
		Node messageNode;
		
		if (message instanceof Node) {
			messageNode = (Node) message;
		} else if (message instanceof String) {
			messageNode = createMessageNode((String) message);
		} else {
			throw new IllegalArgumentException(
					"message must be a String or a Node");
		}
		
		return messageNode;
	}
	
	protected Parent createDialogPane(Stage stage, String message) {
		BorderPane dialog = new BorderPane();
		Node buttonNode = createButtonNode(stage);
//		Node iconNode = createIconNode();
		Node messageNode = createMessageNode(message);
		double hInset = WIDTH / 10;
		double vInset = HEIGHT / 8;
		
		BorderPane.setMargin(buttonNode, new Insets(vInset / 2, hInset, vInset, hInset));
		BorderPane.setMargin(messageNode, new Insets(vInset, hInset, vInset / 2, hInset));

		dialog.setBottom(buttonNode);
		dialog.setCenter(messageNode);
//		dialog.setLeft(iconNode);
		
		return dialog;
	}
	
	protected Node createIconNode() {
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

	protected Node createButtonNode(final Stage stage) {
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

	protected Stage createStage(Window owner, String title) {
		final Stage stage = new Stage();

		if (owner != null) {
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(owner);
		}
		
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setFullScreen(false);
		stage.setIconified(false);
		stage.setResizable(false);
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

	protected Stage createStage(
			Window owner, String title, String message, Color background) {
		final Stage stage = new Stage();

		if (owner != null) {
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(owner);
		}
		
//		stage.initStyle(StageStyle.UTILITY);
		stage.initStyle(StageStyle.UNDECORATED);
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
