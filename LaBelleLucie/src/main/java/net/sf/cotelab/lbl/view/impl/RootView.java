package net.sf.cotelab.lbl.view.impl;

import java.net.URL;
import java.util.List;

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
import net.sf.cotelab.lbl.NodeAdapter;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.controller.facade.Move;
import net.sf.cotelab.lbl.controller.facade.MoveType;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.lbl.view.impl.menu.EditMenu;
import net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

public class RootView extends BorderPane implements View {
	public static final double CARD_ZOOM = 1.0;
	public static final double FAN_OFFSET_FACTOR = 5;

	protected Stage appStage;
	protected Dimension2D cardSize;
	protected CardViewFactory cardViewFactory;
	protected Label drawsLabel;
	protected Menu editMenu;
	protected MenuItem editRedoItem;
	protected MenuItem editUndoItem;
	protected double fanOffset;
	protected MenuItem fileExitItem;
	protected Menu fileMenu;
	protected Menu gameMenu;
	protected MenuItem gameReshuffleItem;
	protected MenuItem helpAboutItem;
	protected MenuItem helpHintItem;
	protected Menu helpMenu;
	protected MenuItem helpRulesItem;
	protected MenuItem helpUsageItem;
	protected InputHandlerSupport inputHandlerSupport;
	protected MenuBar menuBar;
	protected BorderPane messageBar;
	protected GameState model;
	protected MenuItem newGameItem;
	protected Label reshufflesLabel;
	protected TableView tableView;

	public RootView(Stage parentStage, GameState model) {
		super();

		this.appStage = parentStage;
		this.model = model;
		this.inputHandlerSupport = new InputHandlerSupport(this);

		inizVariables();

		inizChildren();
		
//		traverseSceneGraph(this);
	}
	
	protected void traverseSceneGraph(Object root) {
		NodeAdapter nodeAdapter = new NodeAdapter(root);
		
		for (NodeAdapter na : nodeAdapter.getChildrenUnmodifiable()) {
			Object fxObject = na.fxObject;
			Class<? extends Object> fxClass = fxObject.getClass();
			
			System.out.println("RootView.traverseSceneGraph: " + fxClass);
		}
	}

	/**
	 * @return
	 * @see net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return inputHandlerSupport.getInputHandler();
	}

	/**
	 * @param inputHandler
	 * @see net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport#setInputHandler(net.sf.cotelab.lbl.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		inputHandlerSupport.setInputHandler(inputHandler);
		tableView.setInputHandler(inputHandler);
	}

	public void showHelpAboutDialog() {
		showHelpDialog("About LaBelleLucie", getResource("helpAbout.html"));
	}

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

		mywebEngine.load(contentURL);

		myDialogPane.setContent(myview);
		
		myDialog.showAndWait();
	}
	
	public void showHelpRulesDialog() {
		showHelpDialog("Rules", "https://en.wikipedia.org/wiki/La_Belle_Lucie");
	}

	public void showHelpUsageDialog() {
		showHelpDialog("Usage", "https://github.com/alantcote/LaBelleLucie/wiki/UsingLaBelleLucie");
	}

	protected double calcFanOffset() {
		return cardSize.getWidth() / FAN_OFFSET_FACTOR;
	}

	protected String createDrawsText() {
		return "Draws remaining: " + model.getDrawsRemaining().get();
	}

	protected String createReshufflesText() {
		return "Reshuffles remaining: " + model.getRedealsRemaining().get();
	}

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

	protected void establishEditMenu() {
		editMenu = new EditMenu(model, getInputHandler());

		menuBar.getMenus().add(editMenu);
	}

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

	protected void establishFileMenu() {
		fileMenu = new Menu("File");

		establishFileExitItem();

		menuBar.getMenus().add(fileMenu);
	}

	protected void establishGameMenu() {
		gameMenu = new Menu("Game");

		establishReshuffleItem();
		establishNewGameItem();

		menuBar.getMenus().add(gameMenu);
	}

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

	protected void establishHelpHintItem() {
		helpHintItem = new MenuItem("Hint");

		helpHintItem.setAccelerator(new KeyCharacterCombination("h"));

		helpHintItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				List<Move> moves = inputHandlerSupport.getInputHandler().listMoves();

//				System.out.println("hint selected");
//				System.out.println("moves = " + moves);

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

	protected void establishHelpMenu() {
		helpMenu = new Menu("Help");

		establishHelpHintItem();
		establishHelpRulesItem();
		establishHelpUsageItem();
		establishHelpAboutItem();

		menuBar.getMenus().add(helpMenu);
	}

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

	protected void establishMenuBar() {
		menuBar = new MenuBar();

		establishFileMenu();
		establishEditMenu();
		establishGameMenu();
		establishHelpMenu();

		setTop(menuBar);
	}

	protected void establishMessageBar() {
		messageBar = new BorderPane();

		messageBar.setId("message-bar");

		establishDrawsLabel();
		establishReshufflesLabel();

		setBottom(messageBar);
	}

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

	protected void inizChildren() {
		establishMenuBar();
		establishTableView();
		establishMessageBar();
	}

	protected void inizVariables() {
		cardViewFactory = new CardViewFactory(CardViewFactory.DEFAULT_MAX_DIM * CARD_ZOOM);
		cardSize = cardViewFactory.getDimensions();
		fanOffset = calcFanOffset();
	}
}
