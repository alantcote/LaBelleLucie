package io.github.alantcote.labellelucie.view.impl.listeners;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import de.saxsys.mvvmfx.testingutils.jfxrunner.TestInJfxThread;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.controller.impl.ControllerImpl;
import io.github.alantcote.labellelucie.controller.impl.handler.MasterInputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.facade.GameSummary;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener}.
 */
@RunWith(JfxRunner.class)
public class GameResultListenerTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#GameResultListener(javafx.stage.Window)}.
	 */
	@Test
	@TestInJfxThread
	public void testGameResultListener() {
		Stage testStage = new Stage();
		GameResultListener fixture = new GameResultListener(testStage);
		
		assertTrue(testStage == fixture.window);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#changed(javafx.beans.value.ObservableValue, io.github.alantcote.labellelucie.model.facade.GameSummary, io.github.alantcote.labellelucie.model.facade.GameSummary)}.
	 */
	@Test
	@TestInJfxThread
	public void testChanged() {
		SimpleIntegerProperty testState = new SimpleIntegerProperty(0);
		Stage testStage = new Stage();
		GameResultListener fixture = new GameResultListener(testStage) {

			@Override
			protected void processEvent(GameSummary newValue) {
				assertTrue(0 == testState.get());
				
				testState.set(1);
				
				assertTrue(GameSummary.LOST == newValue);
			}
			
		};
		
		fixture.changed(null, GameSummary.IN_PROGRESS, GameSummary.LOST);
		
		assertTrue(1 == testState.get());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	@TestInJfxThread
	public void testSetInputHandler() {
		Stage testStage = new Stage();
		GameResultListener fixture = new GameResultListener(testStage);
		GameState testGameState = new GameStateImpl();
		ControllerImpl testControllerImpl = new ControllerImpl(testGameState);
		InputHandler testInputHandler = new MasterInputHandler(testControllerImpl);
		
		assertTrue(null == fixture.inputHandler);
		
		fixture.setInputHandler(testInputHandler);
		
		assertTrue(testInputHandler == fixture.inputHandler);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#newGameLostAlert()}.
	 */
	@Test
	@TestInJfxThread
	public void testNewGameLostAlert() {
		Stage testStage = new Stage();
		GameResultListener fixture = new GameResultListener(testStage);

		assertNotNull(fixture.window);
		assertNotNull(fixture.newGameLostAlert());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#newGameWonAlert()}.
	 */
	@Test
	@TestInJfxThread
	public void testNewGameWonAlert() {
		Stage testStage = new Stage();
		GameResultListener fixture = new GameResultListener(testStage);
		
		assertNotNull(fixture.newGameWonAlert());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#processEvent(io.github.alantcote.labellelucie.model.facade.GameSummary)}.
	 */
	@Test
	@TestInJfxThread
	public void testProcessEvent() {
		SimpleIntegerProperty testState = new SimpleIntegerProperty(0);
		SimpleObjectProperty<Alert> alertProperty = new SimpleObjectProperty<Alert>();
		Stage testStage = new Stage();
		GameResultListener fixture = new GameResultListener(testStage) {

			@Override
			protected Alert newGameLostAlert() {
				assertTrue(0 == testState.get());
				
				testState.set(1);
				
				alertProperty.set(super.newGameLostAlert());
				
				return alertProperty.get();
			}

			@Override
			protected Alert newGameWonAlert() {
				fail("this method should not be called");
				
				return null;
			}

			@Override
			protected void requestNewGame() {
				assertTrue(3 == testState.get());
				
				testState.set(4);
			}

			@Override
			protected void requestProgramExit() {
				fail("this method should not be called");
			}

			@Override
			protected Optional<ButtonType> showAndWait(Alert alert) {
				assertTrue(2 == testState.get());
				
				testState.set(3);
				
				assertTrue(alertProperty.get() == alert);
				
				Optional<ButtonType> result = Optional.<ButtonType>ofNullable(GameResultListener.NEW_GAME_BT);
				
				return result;
			}

			@Override
			protected void initAlertOwner(Alert alert) {
				assertTrue(1 == testState.get());
				
				testState.set(2);
				
				assertTrue(alertProperty.get() == alert);
			}
			
		};
		GameSummary testGameSummary = GameSummary.LOST;
		
		fixture.processEvent(testGameSummary);
		
		assertTrue(4 == testState.get());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#requestNewGame()}.
	 */
	@Test
	public void testRequestNewGame() {
		// A one-liner. Stipulate that it works.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#requestProgramExit()}.
	 */
	@Test
	public void testRequestProgramExit() {
		// A one-liner. Stipulate that it works.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.listeners.GameResultListener#showAndWait(javafx.scene.control.Alert)}.
	 */
	@Test
	public void testShowAndWait() {
		// Would open an Alert on the GUI. Not good in a unit test.
	}

}
