/**
 * 
 */
package io.github.alantcote.labellelucie.view.impl.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.Controller;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.handler.CloseRequestHandler}.
 */
public class CloseRequestHandlerTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.handler.CloseRequestHandler#CloseRequestHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testCloseRequestHandler() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = new MasterInputHandler(testControllerImpl);
		CloseRequestHandler fixture = new CloseRequestHandler(testInputHandler);
		
		assertTrue(testInputHandler == fixture.inputHandler);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.handler.CloseRequestHandler#handle(javafx.stage.WindowEvent)}.
	 */
	@Test
	public void testHandle() {
		// This method would ask the input handler to handle a program exit
		// request. Not a great idea for a test. We stipulate the method.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.handler.CloseRequestHandler#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testSetInputHandler() {
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = new MasterInputHandler(testControllerImpl);
		CloseRequestHandler fixture = new CloseRequestHandler(testInputHandler);
		InputHandler testInputHandler2 = new MasterInputHandler(testControllerImpl);
		
		assertFalse(testInputHandler2 == fixture.inputHandler);
		
		fixture.setInputHandler(testInputHandler2);
		
		assertTrue(testInputHandler2 == fixture.inputHandler);
	}

}
