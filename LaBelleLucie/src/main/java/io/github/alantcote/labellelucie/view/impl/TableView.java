package io.github.alantcote.labellelucie.view.impl;

import java.util.List;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.javafx.CardView;
import io.github.alantcote.playingcards.javafx.CardViewFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;

/**
 * The view of the card table in the scene graph.
 */
public class TableView extends GridPane implements View {
	/**
	 * The grid column to use for the foundation.
	 */
	public static final int FOUNDATION_COLUMN = 5;

	/**
	 * The number of grid columns to use for the tableau.
	 */
	public static final int TABLEAU_COLUMN_COUNT = FOUNDATION_COLUMN;

	/**
	 * The card view factory.
	 */
	protected CardViewFactory cardViewFactory;

	/**
	 * The tableau fan offset.
	 */
	protected double fanOffset;

	/**
	 * The foundation fan views.
	 */
	protected FanView[] foundationFanView;

	/**
	 * The input handler support.
	 */
	protected InputHandlerSupport inputHandlerSupport;

	/**
	 * The model.
	 */
	protected GameState model;

	/**
	 * The stock view.
	 */
	protected StockView stockView;

	/**
	 * The tableau fan views.
	 */
	protected FanView[] tableauFanView;

	/**
	 * Constructor.
	 * 
	 * @param cardViewFactory the card view factory.
	 * @param fanOffset       the fan offset.
	 * @param model           the model.
	 */
	public TableView(CardViewFactory cardViewFactory, double fanOffset, GameState model) {
		super();

		this.cardViewFactory = cardViewFactory;
		this.fanOffset = fanOffset;
		this.inputHandlerSupport = new InputHandlerSupport(this);
		this.model = model;

		inizChildren();
	}

	/**
	 * Get the input handler.
	 * 
	 * @return the input handler.
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return inputHandlerSupport.getInputHandler();
	}

	/**
	 * Highlight the top card in a given tableau fan.
	 * 
	 * @param fanIndex the index of the tableau fan.
	 */
	public void highlightTopTableauCard(int fanIndex) {
		tableauFanView[fanIndex].highlightTopCard();
	}

	/**
	 * Set the input handler.
	 * 
	 * @param inputHandler the input handler.
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

	/**
	 * Create the context menu to be used to select a draw.
	 * 
	 * @param options the cards to be included in the menu.
	 * @return the context menu.
	 */
	protected ContextMenu createContextMenu(List<Card> options) {
		ContextMenu contextMenu = new ContextMenu();
		Menu drawMenu = new Menu("Draw");

		for (final Card option : options) {
			CardView optionView = cardViewFactory.getFrontView(option);
			MenuItem optionItem = new MenuItem("", optionView);

			optionItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					InputHandler handler = inputHandlerSupport.getInputHandler();

					handler.onDrawRequested(option);
				}
			});

			drawMenu.getItems().add(optionItem);
		}

		contextMenu.getItems().add(drawMenu);

		return contextMenu;
	}

	/**
	 * Initialize the children of this node in the scene graph.
	 */
	protected void inizChildren() {
		inizFoundation();
		inizTableau();
		inizStock();
	}

	/**
	 * Initialize the foundation view.
	 */
	protected void inizFoundation() {
		Fan[] fan = model.getFoundation();

		foundationFanView = new FanView[fan.length];

		for (int fanIndex = 0; fanIndex < fan.length; ++fanIndex) {
			foundationFanView[fanIndex] = new FanView(cardViewFactory, 0, fan[fanIndex]);

			add(foundationFanView[fanIndex], TABLEAU_COLUMN_COUNT, fanIndex);
			foundationFanView[fanIndex].setInputHandler(getInputHandler());
		}
	}

	/**
	 * Initialize the stock view.
	 */
	protected void inizStock() {
		stockView = new StockView(cardViewFactory, model);

		stockView.setInputHandler(getInputHandler());

		add(stockView, TableView.TABLEAU_COLUMN_COUNT - 1, 3);
	}

	/**
	 * Initialize the tableau view.
	 */
	protected void inizTableau() {
		Fan[] fan = model.getTableau();

		tableauFanView = new FanView[fan.length];

		for (int fanIndex = 0; fanIndex < fan.length; ++fanIndex) {
			final int thisFanIndex = fanIndex;
			int col = fanIndex % TABLEAU_COLUMN_COUNT;
			int row = fanIndex / TABLEAU_COLUMN_COUNT;
			tableauFanView[fanIndex] = new FanView(cardViewFactory, fanOffset, fan[fanIndex]);

			add(tableauFanView[fanIndex], col, row);
			tableauFanView[fanIndex].setInputHandler(getInputHandler());

			tableauFanView[fanIndex].setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
				@Override
				public void handle(ContextMenuEvent event) {
					if (model.getDrawsRemaining().get() > 0) {
						Fan stack = model.getTableau()[thisFanIndex];
						int stackSize = stack.size();

						if (stackSize > 1) {
							List<Card> options = stack.subList(0, stackSize - 1);
							ContextMenu menu = createContextMenu(options);

							menu.show(tableauFanView[thisFanIndex], event.getScreenX(), event.getScreenY());
						}
					}
				}
			});
		}
	}
}
