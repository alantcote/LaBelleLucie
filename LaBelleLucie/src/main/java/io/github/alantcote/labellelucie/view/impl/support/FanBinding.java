package io.github.alantcote.labellelucie.view.impl.support;

import io.github.alantcote.labellelucie.view.impl.FanView;
import io.github.alantcote.playingcards.Card;
import javafx.collections.ListChangeListener;

/**
 * The binding that updates a fan view when its peer fan model changes.
 */
public class FanBinding implements ListChangeListener<Card> {
	/**
	 * The fan view to be updated.
	 */
	protected FanView view;

	/**
	 * Constructor.
	 * @param view the fan view to be updated.
	 */
	public FanBinding(FanView view) {
		super();

		this.view = view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * This method instructs the fan view to rebuild itself from the model.
	 */
	@Override
	public void onChanged(Change<? extends Card> change) {
		view.reloadChildren();
	}
}
