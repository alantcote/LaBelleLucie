/**
 * The La Belle Lucie solitaire card game.
 */
module labellelucie {
	exports io.github.alantcote.labellelucie;
	exports io.github.alantcote.labellelucie.controller.facade;
	exports io.github.alantcote.labellelucie.controller.impl;
	exports io.github.alantcote.labellelucie.controller.impl.handler;
	exports io.github.alantcote.labellelucie.controller.impl.undoableop;
	exports io.github.alantcote.labellelucie.model.facade;
	exports io.github.alantcote.labellelucie.model.impl;
	exports io.github.alantcote.labellelucie.undo;
	exports io.github.alantcote.labellelucie.view.facade;
	exports io.github.alantcote.labellelucie.view.impl;
	exports io.github.alantcote.labellelucie.view.impl.dialog;
	exports io.github.alantcote.labellelucie.view.impl.handler;
	exports io.github.alantcote.labellelucie.view.impl.listeners;
	exports io.github.alantcote.labellelucie.view.impl.menu;
	exports io.github.alantcote.labellelucie.view.impl.support;
	
	requires transitive clutilities;
	requires java.logging;
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires javafx.web;
	requires transitive playingcards;
	requires javafx.base;
}