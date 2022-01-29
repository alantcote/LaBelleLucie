package io.github.alantcote.labellelucie.controller.impl.handler;

import java.util.List;
import java.util.logging.Logger;

import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import io.github.alantcote.labellelucie.controller.facade.Move;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import net.sf.cotelab.playingcards.Card;

public class MasterInputHandler extends DefaultInputHandler {
	@SuppressWarnings("unused")
	private static Logger log =
			Logger.getLogger(MasterInputHandler.class.getName());
	
	protected ControllerImpl controller;

	public MasterInputHandler(ControllerImpl controller) {
		super();
		
		this.controller = controller;
		
		controller.updateGameSummary();
	}
	
	@Override
	public List<Move> listMoves() {
		return controller.listMoves();
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onMouseClicked(net.sf.cotelab.playingcards.Card)
	 */
	@Override
	public void onCardMoveRequested(Card card) {
//		log.info("calling controller.onCardMoveRequested");
		
		controller.onCardMoveRequested(card);
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onDrawRequested(net.sf.cotelab.playingcards.Card)
	 */
	@Override
	public void onDrawRequested(Card card) {
		controller.onDrawRequested(card);
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onExitRequest()
	 */
	@Override
	public void onExitRequest() {
		controller.onExitRequest();
	}
	
	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onNewGameRequested()
	 */
	@Override
	public void onNewGameRequested() {
		controller.onNewGameRequested();
	}

	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onReshuffleRequest()
	 */
	@Override
	public void onReshuffleRequest() {
		controller.onReshuffleRequest();
	}
}
