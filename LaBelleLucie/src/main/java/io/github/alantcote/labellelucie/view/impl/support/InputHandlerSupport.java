package io.github.alantcote.labellelucie.view.impl.support;

import java.util.logging.Logger;

import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.playingcards.javafx.CardView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * An adapter for converting input events into calls on
 * <code>InputHandler</code> methods.
 */
public class InputHandlerSupport {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(InputHandlerSupport.class.getName());

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
	 * to the relevant method of <code>inputHandler</code>.
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
//							log.info("calling onCardMoveRequested");

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
