package io.github.alantcote.labellelucie.view.impl.support;

import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.playingcards.javafx.CardView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * An adapter for converting input events into calls on {@link InputHandler}
 * methods.
 */
public class InputHandlerSupport {
	/**
	 * The input handler.
	 */
	protected InputHandler inputHandler = new DefaultInputHandler();

	/**
	 * Constructor.
	 * 
	 * @param source the source of input events.
	 */
	public InputHandlerSupport(Node source) {
		super();

		monitorEvents(source);
	}

	/**
	 * Get the input handler.
	 * 
	 * @return the inputHandler.
	 */
	public InputHandler getInputHandler() {
		return inputHandler;
	}

	/**
	 * Set the input handler.
	 * 
	 * @param inputHandler the inputHandler to set.
	 */
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	/**
	 * Set event handlers on a node. Each handler forwards its received events to
	 * the relevant method of <code>inputHandler</code>.
	 * 
	 * @param source the node.
	 */
	protected void monitorEvents(Node source) {
		monitorMouseEvents(source);
	}

	/**
	 * Set MouseEvent handlers on a node. Each handler forwards its received events
	 * to the relevant method of {@link InputHandler}.
	 * 
	 * @param source the node.
	 */
	protected void monitorMouseEvents(Node source) {
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
