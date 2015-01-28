package net.sf.cotelab.lbl.view.impl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

public class RootView extends BorderPane {
	public static final double CARD_ZOOM = 1.2;
	public static final double FAN_OFFSET_FACTOR = 5;
	
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
	protected Menu helpMenu;
	protected InputHandlerSupport inputHandlerSupport;
	protected MenuBar menuBar;
	protected BorderPane messageBar;
	protected GameState model;
	protected MenuItem newGameItem;
	protected Label reshufflesLabel;
	protected TableView tableView;

	public RootView(GameState model) {
		super();
		
		this.model = model;
		this.inputHandlerSupport = new InputHandlerSupport(this);
		
		inizVariables();
		
		inizChildren();
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
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				drawsLabel.setText(createDrawsText());
			}
		});
		
		messageBar.setLeft(drawsLabel);
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

	protected void establishEditMenu() {
		editMenu = new EditMenu(model, getInputHandler());
		
		menuBar.getMenus().add(editMenu);
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
	
	protected void establishHelpMenu() {
		helpMenu = new Menu("Help");

		menuBar.getMenus().add(helpMenu);
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
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
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
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
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
	
	protected void inizChildren() {
		establishMenuBar();
		establishTableView();
		establishMessageBar();
	}

	protected void inizVariables() {
		cardViewFactory = new CardViewFactory(
				CardViewFactory.DEFAULT_MAX_DIM * CARD_ZOOM);
		cardSize = cardViewFactory.getDimensions();
		fanOffset = calcFanOffset();
	}
}
