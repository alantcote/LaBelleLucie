package io.github.alantcote.labellelucie.view.impl;

import java.net.URL;
import java.util.List;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.facade.Move;
import io.github.alantcote.labellelucie.controller.facade.MoveType;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.menu.EditMenu;
import io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport;
import io.github.alantcote.playingcards.javafx.CardViewFactory;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The root of the scene graph.
 */
public class RootView extends BorderPane implements View {
	/**
	 * The magnification factor for cards images.
	 */
	public static final double CARD_ZOOM = 1.25;
	
	/**
	 * The fraction of a non-top card in a fan defaults to <code>1/FAN_OFFSET_FACTOR</code>.
	 */
	public static final double FAN_OFFSET_FACTOR = 5;

	/**
	 * The application window.
	 */
	protected Stage appStage;
	
	/**
	 * The size of a card image.
	 */
	protected Dimension2D cardSize;
	
	/**
	 * A factory for card views.
	 */
	protected CardViewFactory cardViewFactory;
	
	/**
	 * Label used to display the number of draws remaining in a game.
	 */
	protected Label drawsLabel;
	
	/**
	 * The Edit menu.
	 */
	protected Menu editMenu;
	
	/**
	 * The Redo menu item.
	 */
	protected MenuItem editRedoItem;
	
	/**
	 * The Undo menu item.
	 */
	protected MenuItem editUndoItem;
	
	/**
	 * The distance between left-hand edges of images of adjacent cards in a fan
	 *          view.
	 */
	protected double fanOffset;
	
	/**
	 * The Exit menu item.
	 */
	protected MenuItem fileExitItem;
	
	/**
	 * The File menu.
	 */
	protected Menu fileMenu;
	
	/**
	 * The Game menu.
	 */
	protected Menu gameMenu;
	
	/**
	 * The Reshuffle menu item.
	 */
	protected MenuItem gameReshuffleItem;
	
	/**
	 * The About menu item.
	 */
	protected MenuItem helpAboutItem;
	
	/**
	 * The Hint menu item.
	 */
	protected MenuItem helpHintItem;
	
	/**
	 * The Help menu.
	 */
	protected Menu helpMenu;
	
	/**
	 * The Rules menu item.
	 */
	protected MenuItem helpRulesItem;
	
	/**
	 * The Usage menu item.
	 */
	protected MenuItem helpUsageItem;
	
	/**
	 * The {@link HostServices}.
	 */
	protected HostServices hostServices;
	
	/**
	 * Support for the input handler.
	 */
	protected InputHandlerSupport inputHandlerSupport;
	
	/**
	 * The menu bar.
	 */
	protected MenuBar menuBar;
	
	/**
	 * The message bar.
	 */
	protected BorderPane messageBar;
	
	/**
	 * The model.
	 */
	protected GameState model;
	
	/**
	 * The New Game menu item.
	 */
	protected MenuItem newGameItem;
	
	/**
	 * Label used to display the number of reshuffles remaining in a game.
	 */
	protected Label reshufflesLabel;
	
	/**
	 * The table view.
	 */
	protected TableView tableView;

	/**
	 * Constructor.
	 * @param parentStage the application window.
	 * @param model the model.
	 * @param theHostServices the host services.
	 */
	public RootView(Stage parentStage, GameState model, HostServices theHostServices) {
		super();

		this.appStage = parentStage;
		this.model = model;
		this.hostServices = theHostServices;

		this.inputHandlerSupport = new InputHandlerSupport(this);

		inizVariables();

		inizChildren();
	}

	/**
	 * Get the input handler.
	 * @return the input handler.
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return inputHandlerSupport.getInputHandler();
	}

	/**
	 * Set the input handler.
	 * @param inputHandler the input handler.
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		inputHandlerSupport.setInputHandler(inputHandler);
		tableView.setInputHandler(inputHandler);
	}

	/**
	 * Show the About dialog.
	 */
	public void showHelpAboutDialog() {
		showHelpDialog("About LaBelleLucie", getResource("helpAbout.html"));
	}

	/**
	 * Show a Help dialog.
	 * @param title a title for the dialog.
	 * @param contentURL the URL from which to load the dialog content.
	 */
	public void showHelpDialog(String title, String contentURL) {
		Dialog<ButtonType> myDialog = new Dialog<ButtonType>();
		DialogPane myDialogPane = myDialog.getDialogPane();
		ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		WebView myview = new WebView();
		final WebEngine mywebEngine = myview.getEngine();

		myDialog.initOwner(appStage);
		myDialog.setTitle(title);
		myDialogPane.getButtonTypes().add(okButtonType);
		myDialogPane.lookupButton(okButtonType).setDisable(false);
		myDialogPane.setPrefHeight(250);
		myDialogPane.setPrefWidth(500);

		mywebEngine.load(contentURL);

		myDialogPane.setContent(myview);

		myDialog.showAndWait();
	}

	/**
	 * Show the Rules dialog.
	 */
	public void showHelpRulesDialog() {
		hostServices.showDocument("https://en.wikipedia.org/wiki/La_Belle_Lucie");
	}

	/**
	 * Show the Usage dialog.
	 */
	public void showHelpUsageDialog() {
		hostServices.showDocument("https://github.com/alantcote/LaBelleLucie/wiki/UsingLaBelleLucie");
	}

	/**
	 * Calculate the fan offset.
	 * @return the fan offset.
	 */
	protected double calcFanOffset() {
		return cardSize.getWidth() / FAN_OFFSET_FACTOR;
	}

	/**
	 * Create the draws remaining message.
	 * @return the draws remaining message.
	 */
	protected String createDrawsText() {
		return "Draws remaining: " + model.getDrawsRemaining().get();
	}

	/**
	 * Create the reshuffles remaining message.
	 * @return the reshuffles remaining message.
	 */
	protected String createReshufflesText() {
		return "Reshuffles remaining: " + model.getRedealsRemaining().get();
	}

	/**
	 * Establish the draws remaining label.
	 */
	protected void establishDrawsLabel() {
		drawsLabel = new Label(createDrawsText());

		model.getDrawsRemaining().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				drawsLabel.setText(createDrawsText());
			}
		});

		messageBar.setLeft(drawsLabel);
	}

	/**
	 * Establish the Edit menu.
	 */
	protected void establishEditMenu() {
		editMenu = new EditMenu(model, getInputHandler());

		menuBar.getMenus().add(editMenu);
	}

	/**
	 * Establish the Exit menu item.
	 */
	protected void establishFileExitItem() {
		fileExitItem = new MenuItem("Exit");

		fileExitItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				inputHandlerSupport.getInputHandler().onExitRequest();
			}
		});

		fileMenu.getItems().add(fileExitItem);
	}

	/**
	 * Establish the File menu.
	 */
	protected void establishFileMenu() {
		fileMenu = new Menu("File");

		establishFileExitItem();

		menuBar.getMenus().add(fileMenu);
	}

	/**
	 * Establish the Game menu.
	 */
	protected void establishGameMenu() {
		gameMenu = new Menu("Game");

		establishReshuffleItem();
		establishNewGameItem();

		menuBar.getMenus().add(gameMenu);
	}

	/**
	 * Establish the About menu item.
	 */
	protected void establishHelpAboutItem() {
		helpAboutItem = new MenuItem("About LaBelleLucie");

		helpAboutItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showHelpAboutDialog();
			}
		});

		helpMenu.getItems().add(helpAboutItem);
	}

	/**
	 * Establish the Hint menu item.
	 */
	protected void establishHelpHintItem() {
		helpHintItem = new MenuItem("Hint");

		helpHintItem.setAccelerator(new KeyCharacterCombination("h"));

		helpHintItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				List<Move> moves = inputHandlerSupport.getInputHandler().listMoves();

				if (0 < moves.size()) {
					Move move = moves.get(0);
					MoveType moveType = move.getType();
					int srcFanIndex = move.getSrcFanIndex();

					if ((moveType == MoveType.TABLEAU_TO_FOUNDATION) || (moveType == MoveType.TABLEAU_TO_TABLEAU)) {
						tableView.highlightTopTableauCard(srcFanIndex);
					}
				}
			}
		});

		helpMenu.getItems().add(helpHintItem);
	}

	/**
	 * Establish the Help menu.
	 */
	protected void establishHelpMenu() {
		helpMenu = new Menu("Help");

		establishHelpHintItem();
		establishHelpRulesItem();
		establishHelpUsageItem();
		establishHelpAboutItem();

		menuBar.getMenus().add(helpMenu);
	}

	/**
	 * Establish the Rules menu item.
	 */
	protected void establishHelpRulesItem() {
		helpRulesItem = new MenuItem("Rules");

		helpRulesItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showHelpRulesDialog();
			}
		});

		helpMenu.getItems().add(helpRulesItem);
	}

	/**
	 * Establish the Usage menu item.
	 */
	protected void establishHelpUsageItem() {
		helpUsageItem = new MenuItem("Usage");

		helpUsageItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showHelpUsageDialog();
			}
		});

		helpMenu.getItems().add(helpUsageItem);
	}

	/**
	 * Establish the menu bar.
	 */
	protected void establishMenuBar() {
		menuBar = new MenuBar();

		establishFileMenu();
		establishEditMenu();
		establishGameMenu();
		establishHelpMenu();

		setTop(menuBar);
	}

	/**
	 * Establish the message bar.
	 */
	protected void establishMessageBar() {
		messageBar = new BorderPane();

		messageBar.setId("message-bar");

		establishDrawsLabel();
		establishReshufflesLabel();

		setBottom(messageBar);
	}

	/**
	 * Establish the New Game menu item.
	 */
	protected void establishNewGameItem() {
		newGameItem = new MenuItem("New Game");

		// newGameItem.setAccelerator(new KeyCodeCombination(KeyCode.F2));

		newGameItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				inputHandlerSupport.getInputHandler().onNewGameRequested();
			}
		});

		gameMenu.getItems().add(newGameItem);
	}

	/**
	 * Establish the Reshuffle menu item.
	 */
	protected void establishReshuffleItem() {
		int val = model.getRedealsRemaining().get();

		gameReshuffleItem = new MenuItem("Reshuffle");
		gameReshuffleItem.setDisable(val < 1);

		gameReshuffleItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				inputHandlerSupport.getInputHandler().onReshuffleRequest();
			}
		});

		model.getRedealsRemaining().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int val = model.getRedealsRemaining().get();

				gameReshuffleItem.setDisable(val < 1);
			}
		});

		gameMenu.getItems().add(gameReshuffleItem);
	}

	/**
	 * Establish the reshuffles remaining label.
	 */
	protected void establishReshufflesLabel() {
		reshufflesLabel = new Label(createReshufflesText());

		model.getRedealsRemaining().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				reshufflesLabel.setText(createReshufflesText());
			}
		});

		messageBar.setRight(reshufflesLabel);
	}

	/**
	 * Establish the table view.
	 */
	protected void establishTableView() {
		tableView = new TableView(cardViewFactory, fanOffset, model);

		tableView.setInputHandler(getInputHandler());

		setCenter(tableView);
	}

	/**
	 * Get the URL of a given resource.
	 * 
	 * @param resourceName the resource path, relative to this class' package.
	 * @return the URL.
	 */
	protected String getResource(String resourceName) {
		System.out.println("resourceName: " + resourceName);

		URL url = getClass().getResource(resourceName);

		return url.toExternalForm();
	}

	/**
	 * Establish the children of this node in the scene graph.
	 */
	protected void inizChildren() {
		establishMenuBar();
		establishTableView();
		establishMessageBar();
	}

	/**
	 * Initialize this object's instance variables.
	 */
	protected void inizVariables() {
		cardViewFactory = new CardViewFactory(CardViewFactory.DEFAULT_MAX_DIM * CARD_ZOOM);
		cardSize = cardViewFactory.getDimensions();
		fanOffset = calcFanOffset();
	}
}
