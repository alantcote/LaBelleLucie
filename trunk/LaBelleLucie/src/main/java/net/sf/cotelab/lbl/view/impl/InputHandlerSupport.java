package net.sf.cotelab.lbl.view.impl;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import net.sf.cotelab.lbl.controller.facade.DefaultInputHandler;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.playingcards.javafx.CardView;

/**
 * An adapter for converting input events into calls on <tt>InputHandler</tt>
 * methods.
 * @author US80653H
 *
 */
public class InputHandlerSupport {
	protected InputHandler inputHandler = new DefaultInputHandler();

	public InputHandlerSupport(Node source) {
		super();
		
		monitorEvents(source);
	}

	/**
	 * @return the inputHandler
	 */
	public InputHandler getInputHandler() {
		return inputHandler;
	}

	/**
	 * @param inputHandler the inputHandler to set
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
	
	/**
	 * Set DragEvent handlers on a node.
	 * Each handler forwards its received events to the relevant method of
	 * <tt>inputHandler</tt>.
	 * @param source the node.
	 */
	protected void monitorDragEvents(Node source) {
		source.setOnDragDone(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onDragDone(cardView.getCard());
				}
			}
		});

		source.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onDragDropped(cardView.getCard());
				}
			}
		});

		source.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onDragEntered(cardView.getCard());
				}
			}
		});

		source.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onDragExited(cardView.getCard());
				}
			}
		});

		source.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onDragOver(cardView.getCard());
				}
			}
		});
	}
	
	/**
	 * Set event handlers on a node.
	 * Each handler forwards its received events to the relevant method of
	 * <tt>inputHandler</tt>.
	 * @param source the node.
	 */
	protected void monitorEvents(Node source) {
		monitorDragEvents(source);
		monitorMouseEvents(source);
	}
	
	/**
	 * Set MouseEvent handlers on a node.
	 * Each handler forwards its received events to the relevant method of
	 * <tt>inputHandler</tt>.
	 * @param source the node.
	 */
	protected void monitorMouseEvents(Node source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onDragDetected(cardView.getCard());
				}
			}
		});

		source.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					if (MouseButton.PRIMARY == event.getButton()) {
						if (1 == event.getClickCount()) {
							inputHandler.onCardMoveRequested(cardView.getCard());
						}
					}
				}
			}
		});

		source.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onMouseEntered(cardView.getCard());
				}
			}
		});

		source.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Object src = event.getSource();
				
				if (src instanceof CardView) {
					CardView cardView = (CardView) src;
					
					inputHandler.onMouseExited(cardView.getCard());
				}
			}
		});
	}
}
