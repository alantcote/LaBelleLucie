package io.github.alantcote.labellelucie.view.impl;

import java.util.List;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.javafx.CardView;
import io.github.alantcote.playingcards.javafx.CardViewFactory;

public class TableView extends GridPane implements View {
	public static final int FOUNDATION_COLUMN = 5;
	public static final int TABLEAU_COLUMN_COUNT = FOUNDATION_COLUMN;
	
	protected CardViewFactory cardViewFactory;
	protected double fanOffset;
	protected FanView[] foundationFanView;
	protected InputHandlerSupport inputHandlerSupport;
	protected GameState model;
	protected StockView stockView;
	protected FanView[] tableauFanView;
	
	public TableView(CardViewFactory cardViewFactory, double fanOffset,
			GameState model) {
		super();
		
		this.cardViewFactory = cardViewFactory;
		this.fanOffset = fanOffset;
		this.inputHandlerSupport = new InputHandlerSupport(this);
		this.model = model;
		
		inizChildren();
	}
	
	/**
	 * @return
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return inputHandlerSupport.getInputHandler();
	}

	public void highlightTopTableauCard(int fanIndex) {
		tableauFanView[fanIndex].highlightTopCard();
	}

	/**
	 * @param inputHandler
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		inputHandlerSupport.setInputHandler(inputHandler);
		
		for (FanView fanView : foundationFanView) {
			fanView.setInputHandler(inputHandler);
		}
		
		for (FanView fanView : tableauFanView) {
			fanView.setInputHandler(inputHandler);
		}
		
		stockView.setInputHandler(inputHandler);
	}
	
	protected ContextMenu createContextMenu(List<Card> options) {
		ContextMenu contextMenu = new ContextMenu();
		Menu drawMenu = new Menu("Draw");
		
		for (final Card option : options) {
			CardView optionView = cardViewFactory.getFrontView(option);
			MenuItem optionItem = new MenuItem("", optionView);
			
			optionItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					InputHandler handler =
							inputHandlerSupport.getInputHandler();
					
					handler.onDrawRequested(option);
				}
			});
			
			drawMenu.getItems().add(optionItem);
		}
		
		contextMenu.getItems().add(drawMenu);
		
		return contextMenu;
	}
	
	protected void inizChildren() {
		inizFoundation();
		inizTableau();
		inizStock();
	}
	
	protected void inizFoundation() {
		Fan[] fan = model.getFoundation();
		
		foundationFanView = new FanView[fan.length];
		
		for (int fanIndex = 0; fanIndex < fan.length; ++fanIndex) {
			foundationFanView[fanIndex] = new FanView(
					cardViewFactory, 0, fan[fanIndex]);
			
			add(foundationFanView[fanIndex], TABLEAU_COLUMN_COUNT, fanIndex);
			foundationFanView[fanIndex].setInputHandler(getInputHandler());
		}
	}
	
	protected void inizStock() {
		stockView = new StockView(cardViewFactory, model);
		
		stockView.setInputHandler(getInputHandler());

		add(stockView, TableView.TABLEAU_COLUMN_COUNT - 1, 3);
	}

	protected void inizTableau() {
		Fan[] fan = model.getTableau();
		
		tableauFanView = new FanView[fan.length];
		
		for (int fanIndex = 0; fanIndex < fan.length; ++fanIndex) {
			final int thisFanIndex = fanIndex;
			int col = fanIndex % TABLEAU_COLUMN_COUNT;
			int row = fanIndex / TABLEAU_COLUMN_COUNT;
			tableauFanView[fanIndex] = new FanView(
					cardViewFactory, fanOffset, fan[fanIndex]);
			
			add(tableauFanView[fanIndex], col, row);
			tableauFanView[fanIndex].setInputHandler(getInputHandler());
			
			tableauFanView[fanIndex].setOnContextMenuRequested(
					new EventHandler<ContextMenuEvent>() {
				@Override
				public void handle(ContextMenuEvent event) {
					if (model.getDrawsRemaining().get() > 0) {
						Fan stack = model.getTableau()[thisFanIndex];
						int stackSize = stack.size();
						
						if (stackSize > 1) {
							List<Card> options =
									stack.subList(0, stackSize - 1);
							ContextMenu menu = createContextMenu(options);
							
							menu.show(tableauFanView[thisFanIndex],
									event.getScreenX(), event.getScreenY());
						}
					}
				}
			});
		}
	}
}
