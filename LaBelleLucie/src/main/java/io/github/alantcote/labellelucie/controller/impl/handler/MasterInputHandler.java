package io.github.alantcote.labellelucie.controller.impl.handler;

import java.util.List;
import java.util.logging.Logger;

import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import io.github.alantcote.labellelucie.controller.facade.Move;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.playingcards.Card;

/**
 * The input handler provided by {@link ControllerImpl}.
 */
public class MasterInputHandler extends DefaultInputHandler {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(MasterInputHandler.class.getName());

	/**
	 * The controller associated with this input handler.
	 */
	protected ControllerImpl controller;

	/**
	 * Construct a new object.
	 * 
	 * @param controller the controller to be associated with this input handler.
	 */
	public MasterInputHandler(ControllerImpl controller) {
		super();

		this.controller = controller;

		controller.updateGameSummary();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Move> listMoves() {
		return controller.listMoves();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCardMoveRequested(Card card) {
//		log.info("calling controller.onCardMoveRequested");

		controller.onCardMoveRequested(card);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDrawRequested(Card card) {
		controller.onDrawRequested(card);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onExitRequest() {
		controller.onExitRequest();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewGameRequested() {
		controller.onNewGameRequested();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReshuffleRequest() {
		controller.onReshuffleRequest();
	}
}
