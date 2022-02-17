package io.github.alantcote.labellelucie.view.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import de.saxsys.mvvmfx.testingutils.jfxrunner.TestInJfxThread;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import javafx.application.HostServices;
import javafx.stage.Stage;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.ViewImpl}.
 */
@RunWith(JfxRunner.class)
public class ViewImplTest {

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.ViewImpl#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	@TestInJfxThread
	public void testSetInputHandler() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = new MasterInputHandler(testControllerImpl);
		HostServices testHostServices = null;
		ViewImpl fixture = new ViewImpl(testStage, testGameState, testInputHandler, testHostServices);

		fixture.setInputHandler(testInputHandler);
	}

	/**
	 * Test method for
	 * {@link io.github.alantcote.labellelucie.view.impl.ViewImpl#ViewImpl(javafx.stage.Stage, io.github.alantcote.labellelucie.model.facade.GameState, io.github.alantcote.labellelucie.controller.facade.InputHandler, javafx.application.HostServices)}.
	 */
	@Test
	@TestInJfxThread
	public void testViewImpl() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = new MasterInputHandler(testControllerImpl);
		HostServices testHostServices = null;
		ViewImpl fixture = new ViewImpl(testStage, testGameState, testInputHandler, testHostServices);

		assertNotNull(fixture);

		assertNotNull(fixture.sceneView);
		assertTrue(testStage == fixture.window);
		assertNotNull(fixture.gameResultListener);
		assertNotNull(fixture.closeRequestHandler);
		assertTrue(testInputHandler == fixture.inputHandler);
	}

}
