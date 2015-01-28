package net.sf.cotelab.lbl.view.impl;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.Deck;
import net.sf.cotelab.playingcards.Rank;
import net.sf.cotelab.playingcards.Suit;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

public class StockView extends AnchorPane {
	public static final double MARGIN = 5;

	protected CardViewFactory cardViewFactory;
	protected InputHandlerSupport inputHandlerSupport;
	protected GameState model;
	protected CardView stockView = null;

	public StockView(CardViewFactory cardViewFactory, GameState model) {
		super();

		Dimension2D cardSize = cardViewFactory.getDimensions();
		double minHeight = cardSize.getHeight() + (MARGIN * 2);
		double minWidth = cardSize.getWidth() + (MARGIN * 2);
		
		setId("stock-view");
		setPadding(new Insets(MARGIN));
		
		setMinHeight(minHeight);
		setMinWidth(minWidth);
		setPrefHeight(minHeight);
		setPrefWidth(minWidth);

		this.cardViewFactory = cardViewFactory;
		this.inputHandlerSupport = new InputHandlerSupport(this);
		this.model = model;
		this.stockView = cardViewFactory.getBackView(
				new Card(Rank.JOKER_HIGH, Suit.JOKER));
		
		model.getStock().addListener(new ChangeListener<Deck>() {
			@Override
			public void changed(ObservableValue<? extends Deck> arg0,
					Deck arg1, Deck arg2) {
				updateChildren();
			}
		});
		
		updateChildren();
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (event.getClickCount() == 1) {
						getInputHandler().onReshuffleRequest();
					}
				}
			}
		});
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
	}
	
	protected void anchorLeft(ImageView view, double indent) {
		setLeftAnchor(view, indent);
	}

	protected void anchorTop(ImageView view, double indent) {
		setTopAnchor(view, indent);
	}

	protected void updateChildren() {
		ObjectProperty<Deck> stock = model.getStock();
		Deck stockDeck = stock.get();
		
		getChildren().remove(stockView);
		
		if ((stockDeck != null) && (!stockDeck.isEmpty())) {
			getChildren().add(stockView);
			
			anchorLeft(stockView, 0);
			anchorTop(stockView, 0);
		}
	}
}
