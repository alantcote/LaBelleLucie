package io.github.alantcote.labellelucie.view.impl;

import static org.junit.Assert.*;

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
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.SceneView}.
 */
@RunWith(JfxRunner.class)
public class SceneViewTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.SceneView#SceneView(javafx.stage.Stage, io.github.alantcote.labellelucie.model.facade.GameState, javafx.application.HostServices)}.
	 */
	@Test
	@TestInJfxThread
	public void testSceneView() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		SceneView fixture = new SceneView(testStage, testGameState, testHostServices);
		
		assertNotNull(fixture);
		
		assertNotNull(fixture.rootView);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.SceneView#getInputHandler()}.
	 */
	@Test
	@TestInJfxThread
	public void testGetInputHandler() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		SceneView fixture = new SceneView(testStage, testGameState, testHostServices);
		
		assertTrue(fixture.rootView.getInputHandler() == fixture.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.SceneView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	@TestInJfxThread
	public void testSetInputHandler() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		SceneView fixture = new SceneView(testStage, testGameState, testHostServices);
		InputHandler testInputHandler = new MasterInputHandler(new ControllerImpl(testGameState));

		assertTrue(testInputHandler != fixture.getInputHandler());
		
		fixture.setInputHandler(testInputHandler);

		assertTrue(testInputHandler == fixture.getInputHandler());
	}

}
