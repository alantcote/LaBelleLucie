package net.sf.cotelab.lbl.view.impl;

import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.lbl.view.impl.support.FanBinding;
import net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.playingcards.javafx.CardView;
import net.sf.cotelab.playingcards.javafx.CardViewFactory;

/**
 * A view of a fan.
 * @author cote
 */
public class FanView extends AnchorPane implements View {
	public static final String CSS_ID = "fan-view";
	
	/**
	 * The insets.
	 */
	public static final double MARGIN = 5;
	/**
	 * The number of cards in a fan that can be displayed with minimum overlap,
	 * this is used to calculate the size of the view. When the actual number of
	 * cards in the fan is larger than this number, the overlap is increased, to
	 * permit all of the cards to be displayed, though those not on top have
	 * smaller visible portions.
	 */
	public static final int MAX_FAN_SIZE = 8;
	
	protected CardViewFactory cardViewFactory;
	protected FanBinding fanBinding;
	protected double fanOffset;
	protected InputHandlerSupport inputHandlerSupport;
	protected Fan model;
	
	/**
	 * Construct a new object.
	 * @param cardViewFactory the factory for card views.
	 * @param fanOffset the maximum offset between the edge of one card's view
	 * 		and the edge of the view of the card on top of it.
	 * @param model the fan to be viewed.
	 */
	public FanView(
			CardViewFactory cardViewFactory, double fanOffset, Fan model) {
		super();

		Dimension2D cardSize = cardViewFactory.getDimensions();
		double minHeight = getHeight(cardSize) + (MARGIN * 2);
		double minWidth = getWidth(cardSize) + (MARGIN * 2) +
				(fanOffset * (MAX_FAN_SIZE - 1));
		
		setCSSId(CSS_ID);
		applyPadding(newInsets(MARGIN));
		
		applyMinHeight(minHeight);
		applyMinWidth(minWidth);
		applyPrefHeight(minHeight);
		applyPrefWidth(minWidth);
		
		this.cardViewFactory = cardViewFactory;
		this.fanBinding = newFanBinding(this);
		this.fanOffset = fanOffset;
		this.inputHandlerSupport = newInputHandlerSupport(this);
		
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
	 * @return the input handler.
	 * @see net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport#getInputHandler()
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
	
	/**
	 * Rebuild the hierarchy beneath this node, by removing the existing one and
	 * building up a replacement.
	 */
	public void reloadChildren() {
		ObservableList<Node> kids = getChildren();
		InputHandler ih = getInputHandler();
		
		kids.clear();
		
		for (Card card : model) {
			CardView cardView = cardViewFactory.getFrontView(card);
			InputHandlerSupport ihs = newInputHandlerSupport(cardView);
			Tooltip tooltip = newTooltip();
			ImageView ttView = newImageView(cardView.getImage());
			
			tooltip.setGraphic(ttView);
			installTooltip(cardView, tooltip);
			
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
	 * @param inputHandler the inputHandler to set.
	 * @see net.sf.cotelab.lbl.view.impl.support.InputHandlerSupport#setInputHandler(net.sf.cotelab.lbl.controller.facade.InputHandler)
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
	
	/**
	 * Anchor a given view at a given distance from the left-hand edge of this
	 * view.
	 * The method that <tt>AnchorPane</tt> provides for this purpose is
	 * <tt>static</tt>, and therefore unmockable. This method provides a means
	 * of mocking out the behavior, for unit testing.
	 * @param view the view to be anchored.
	 * @param indent the distance at which the given view is to be placed from
	 * 		the left-hand edge of this view.
	 */
	protected void anchorLeft(ImageView view, double indent) {
		setLeftAnchor(view, indent);
	}
	
	/**
	 * Anchor a given view at a given distance from the top edge of this view.
	 * The method that <tt>AnchorPane</tt> provides for this purpose is
	 * <tt>static</tt>, and therefore unmockable. This method provides a means
	 * of mocking out the behavior, for unit testing.
	 * @param view the view to be anchored.
	 * @param indent the distance at which the given view is to be placed from
	 * 		the top edge of this view.
	 */
	protected void anchorTop(ImageView view, double indent) {
		setTopAnchor(view, indent);
	}

	protected void applyMinHeight(double value) {
		setMinHeight(value);
	}

	protected void applyMinWidth(double value) {
		setMinWidth(value);
	}

	protected void applyPadding(Insets insets) {
		setPadding(insets);
	}

	protected void applyPrefHeight(double value) {
		setPrefHeight(value);
	}

	protected void applyPrefWidth(double value) {
		setPrefWidth(value);
	}

	protected double getHeight(Dimension2D d2d) {
		return d2d.getHeight();
	}

	protected double getWidth(Dimension2D d2d) {
		return d2d.getWidth();
	}

	/**
	 * Install a given tooltip on a node.
	 * This method exists to provide a means of mocking calls to
	 * <tt>Tooltip</tt>'s <tt>install() static</tt> method.
	 * @param node the node.
	 * @param tooltip the tooltip.
	 */
	protected void installTooltip(Node node, Tooltip tooltip) {
		Tooltip.install(node, tooltip);
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

	/**
	 * Create a new <tt>FanBinding</tt> object.
	 * This method is included here to enable mocking new object creation.
	 * @param supported the supported object.
	 * @return the new object.
	 */
	protected FanBinding newFanBinding(FanView supported) {
		return new FanBinding(supported);
	}
	
	/**
	 * Create a new object.
	 * This method is provided to enable mocking the behavior.
	 * @param image the image to be wrapped.
	 * @return the new object.
	 */
	protected ImageView newImageView(Image image) {
		return new ImageView(image);
	}
	
	/**
	 * Create a new <tt>InputHandlerSupport</tt> object.
	 * This method is included here to enable mocking new object creation.
	 * @param supported the supported object.
	 * @return the new object.
	 */
	protected InputHandlerSupport newInputHandlerSupport(Node supported) {
		return new InputHandlerSupport(supported);
	}

	/**
	 * Manufacture a new <tt>Insets</tt> object.
	 * @param topRightBottomLeft the inset to apply to all 4 edges.
	 * @return the new object.
	 */
	protected Insets newInsets(double topRightBottomLeft) {
		return new Insets(topRightBottomLeft);
	}

	/**
	 * Create a new object.
	 * This method is provided to enable mocking the behavior.
	 * @return the new object.
	 */
	protected Tooltip newTooltip() {
		return new Tooltip();
	}

	/**
	 * Set the id of this node, to enable CSS to support it.
	 * The method that <tt>Node</tt> provides for this purpose is final, so it
	 * cannot be mocked. This method is used to wrap that method, to facilitate
	 * unit testing of this class.
	 * @param id the id.
	 */
	protected void setCSSId(String id) {
		setId(id);
	}
}
