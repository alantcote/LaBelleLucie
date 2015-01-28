package net.sf.cotelab.lbl.controller.impl.handler;

import net.sf.cotelab.lbl.controller.facade.DefaultInputHandler;
import net.sf.cotelab.lbl.controller.impl.ControllerImpl;
import net.sf.cotelab.playingcards.Card;

public class MasterInputHandler extends DefaultInputHandler {
//	private static final Logger log =
//			Logger.getLogger(MasterInputHandler.class.getName());
	
	protected ControllerImpl controller;

	public MasterInputHandler(ControllerImpl controller) {
		super();
		
		this.controller = controller;
		
		controller.updateGameSummary();
	}
	
	/* (non-Javadoc)
	 * @see net.sf.cotelab.playingcards.lbl.controller.facade.DefaultInputHandler#onMouseClicked(net.sf.cotelab.playingcards.Card)
	 */
	@Override
	public void onCardMoveRequested(Card card) {
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
