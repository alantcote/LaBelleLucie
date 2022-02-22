package io.github.alantcote.labellelucie.view.impl;

import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.view.facade.View;
import io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Deck;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;
import io.github.alantcote.playingcards.javafx.CardView;
import io.github.alantcote.playingcards.javafx.CardViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * View of the stock.
 */
public class StockView extends AnchorPane implements View {
	/**
	 * Margin around the stock.
	 */
	public static final double MARGIN = 5;

	/**
	 * The card view factory.
	 */
	protected CardViewFactory cardViewFactory;

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
	protected CardView stockView = null;

	/**
	 * Constructor.
	 * 
	 * @param cardViewFactory the card view factory.
	 * @param model           the model.
	 */
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
		this.stockView = cardViewFactory.getBackView(new Card(Rank.JOKER_HIGH, Suit.JOKER));

		model.getStock().addListener(new ChangeListener<Deck>() {
			@Override
			public void changed(ObservableValue<? extends Deck> arg0, Deck arg1, Deck arg2) {
				updateChildren();
			}
		});
		model.getRedealsRemaining().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
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
	 * Get the input handler.
	 * 
	 * @return the input handler.
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return inputHandlerSupport.getInputHandler();
	}

	/**
	 * Set the input handler.
	 * 
	 * @param inputHandler the input handler.
	 * @see io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		inputHandlerSupport.setInputHandler(inputHandler);
	}

	/**
	 * Position a card image relative to the left edge of this view.
	 * 
	 * @param view   the card image.
	 * @param indent the distance between the left edge of this view and the card
	 *               image.
	 */
	protected void anchorLeft(ImageView view, double indent) {
		setLeftAnchor(view, indent);
	}

	/**
	 * Position a card image relative to the top edge of this view.
	 * 
	 * @param view   the card image.
	 * @param indent the distance between the top edge of this view and the card
	 *               image.
	 */
	protected void anchorTop(ImageView view, double indent) {
		setTopAnchor(view, indent);
	}

	/**
	 * Update the children of this node in the scene graph.
	 */
	protected void updateChildren() {
		if (model.getRedealsRemaining().get() > 0) {
			getChildren().remove(stockView);
			getChildren().add(stockView);

			anchorLeft(stockView, 0);
			anchorTop(stockView, 0);
		} else {
			getChildren().remove(stockView);
		}
	}
}
