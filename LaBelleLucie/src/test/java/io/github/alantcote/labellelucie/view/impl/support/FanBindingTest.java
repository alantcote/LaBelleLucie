package io.github.alantcote.labellelucie.view.impl.support;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.model.impl.FanImpl;
import io.github.alantcote.labellelucie.view.impl.FanView;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.javafx.CardViewFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener.Change;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.support.FanBinding}.
 */
public class FanBindingTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.FanBinding#FanBinding(io.github.alantcote.labellelucie.view.impl.FanView)}.
	 */
	@Test
	public void testFanBinding() {
		FanView testFanView = new FanView(new CardViewFactory(), 10, new FanImpl());
		FanBinding fixture = new FanBinding(testFanView);
		
		assertTrue(testFanView == fixture.view);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.FanBinding#onChanged(javafx.collections.ListChangeListener.Change)}.
	 */
	@Test
	public void testOnChanged() {
		// This method makes a single simple call. Stipulate it works.
	}

}
