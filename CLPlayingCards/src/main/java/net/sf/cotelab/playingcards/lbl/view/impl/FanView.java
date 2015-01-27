package net.sf.cotelab.playingcards.lbl.view.impl;

import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;
import net.sf.cotelab.playingcards.lbl.controller.facade.InputHandler;
import net.sf.cotelab.playingcards.lbl.model.facade.Fan;

public class FanView extends AnchorPane {
	public static final double MARGIN = 5;
	public static final int MAX_FAN_SIZE = 8;
	
	protected CardViewFactory cardViewFactory;
	protected FanBinding fanBinding;
	protected double fanOffset;
	protected InputHandlerSupport inputHandlerSupport;
	protected Fan model;
	
	public FanView(
			CardViewFactory cardViewFactory, double fanOffset, Fan model) {
		super();

		Dimension2D cardSize = cardViewFactory.getDimensions();
		double minHeight = cardSize.getHeight() + (MARGIN * 2);
		double minWidth = cardSize.getWidth() + (MARGIN * 2) +
				(fanOffset * (MAX_FAN_SIZE - 1));
		
		setId("fan-view");
		setPadding(new Insets(MARGIN));
		
		setMinHeight(minHeight);
		setMinWidth(minWidth);
		setPrefHeight(minHeight);
		setPrefWidth(minWidth);
		
		this.cardViewFactory = cardViewFactory;
		this.fanBinding = new FanBinding(this);
		this.fanOffset = fanOffset;
		this.inputHandlerSupport = new InputHandlerSupport(this);
		
		setModel(model);
	}

	/**
	 * @return the cardViewFactory
	 */
	public CardViewFactory getCardViewFactory() {
		return cardViewFactory;
	}

	/**
	 * @return the fanOffset
	 */
	public double getFanOffset() {
		return fanOffset;
	}

	/**
	 * @return
	 * @see net.sf.cotelab.playingcards.lbl.view.impl.InputHandlerSupport#getInputHandler()
	 */
	public InputHandler getInputHandler() {
		return inputHandlerSupport.getInputHandler();
	}

	/**
	 * @return the model
	 */
	public Fan getModel() {
		return model;
	}

	public void reloadChildren() {
		ObservableList<Node> kids = getChildren();
		InputHandler ih = getInputHandler();
		
		kids.clear();
		
		for (Card card : model) {
			CardView cardView = cardViewFactory.getFrontView(card);
			InputHandlerSupport ihs = new InputHandlerSupport(cardView);
			
			kids.add(cardView);
			ihs.setInputHandler(ih);
		}
	}

	/**
	 * @param cardViewFactory the cardViewFactory to set
	 */
	public void setCardViewFactory(CardViewFactory cardViewFactory) {
		this.cardViewFactory = cardViewFactory;
	}

	/**
	 * @param fanOffset the fanOffset to set
	 */
	public void setFanOffset(double fanOffset) {
		this.fanOffset = fanOffset;
	}
	
	/**
	 * @param inputHandler
	 * @see net.sf.cotelab.playingcards.lbl.view.impl.InputHandlerSupport#setInputHandler(net.sf.cotelab.playingcards.lbl.controller.facade.InputHandler)
	 */
	public void setInputHandler(InputHandler inputHandler) {
		inputHandlerSupport.setInputHandler(inputHandler);
		
		reloadChildren();
	}
	
	/**
	 * @param model the model to set
	 */
	public void setModel(Fan model) {
		if (this.model != null) {
			this.model.removeListener(fanBinding);
		}
		
		this.model = model;
		
		model.addListener(fanBinding);
		
		reloadChildren();
	}

	protected void anchorLeft(ImageView view, double indent) {
		setLeftAnchor(view, indent);
	}

	protected void anchorTop(ImageView view, double indent) {
		setTopAnchor(view, indent);
	}

	/* (non-Javadoc)
	 * @see javafx.scene.layout.AnchorPane#layoutChildren()
	 */
	@Override
	protected void layoutChildren() {
		/**
		 * If getChildren().size() <= MAX_FAN_SIZE, use fanOffset. Else use
		 * (fanOffset * (MAX_FAN_SIZE - 1)) / (getChildren().size() - 1).
		 */
		int layer = 0;
		ObservableList<Node> kids = getChildren();
		int kidSize = kids.size();
		double realOffset = fanOffset;
		
		if (kidSize > MAX_FAN_SIZE) {
			realOffset = (fanOffset * (MAX_FAN_SIZE - 1)) / (kidSize - 1);
		}
		
		for (Node cardNode : kids) {
			if (cardNode instanceof ImageView) {
				ImageView cardView = (ImageView) cardNode;
				
				anchorLeft(cardView, realOffset * layer);
				anchorTop(cardView, 0);
				
				++layer;
			}
		}
		
		super.layoutChildren();
	}
}
