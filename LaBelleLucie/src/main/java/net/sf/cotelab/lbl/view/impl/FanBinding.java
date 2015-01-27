package net.sf.cotelab.lbl.view.impl;

import javafx.collections.ListChangeListener;
import net.sf.cotelab.playingcards.Card;

public class FanBinding implements ListChangeListener<Card> {
	protected FanView view;

	public FanBinding(FanView view) {
		super();
		
		this.view = view;
	}

	@Override
	public void onChanged(Change<? extends Card> change) {
		view.reloadChildren();
	}
}
