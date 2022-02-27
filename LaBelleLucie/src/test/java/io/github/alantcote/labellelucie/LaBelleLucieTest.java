package io.github.alantcote.labellelucie;

import static org.junit.Assert.*;

import java.util.prefs.BackingStoreException;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import de.saxsys.mvvmfx.testingutils.jfxrunner.TestInJfxThread;
import io.github.alantcote.fxutilities.javafx.windowprefs.WindowPrefs;
import io.github.alantcote.labellelucie.controller.facade.Controller;
import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.labellelucie.model.impl.GameStateImpl;
import io.github.alantcote.labellelucie.view.facade.View;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;

/**
 * Test case for {@link io.github.alantcote.labellelucie.LaBelleLucie}.
 */
@RunWith(JfxRunner.class)
public class LaBelleLucieTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		// This is the standard main method for JavaFX programs. It works.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#start(javafx.stage.Stage)}.
	 */
	@Test
	@TestInJfxThread
	public void testStartStage() {
		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		SimpleObjectProperty<GameState> gameStateProperty = new SimpleObjectProperty<GameState>();
		LaBelleLucie fixture = new LaBelleLucie() {

			@Override
			protected void createView(Stage primaryStage, GameState model, InputHandler inputHandler) {
				assertTrue(2 == stateProperty.get());
				stateProperty.set(3);
				
				assertTrue(stage == primaryStage);
				assertTrue(gameStateProperty.get() == model);
				assertNotNull(inputHandler);
				
				super.createView(primaryStage, model, inputHandler);
			}

			@Override
			protected Controller newController(GameState model) {
				assertTrue(1 == stateProperty.get());
				stateProperty.set(2);
				
				assertTrue(gameStateProperty.get() == model);
				
				return super.newController(model);
			}

			@Override
			protected GameState newGameState() {
				assertTrue(0 == stateProperty.get());
				stateProperty.set(1);
				
				gameStateProperty.set(super.newGameState());
				
				return gameStateProperty.get();
			}

			@Override
			protected void showStage(Stage stage) {
				assertTrue(3 == stateProperty.get());
				stateProperty.set(4);
				
				// the following would open a window. rather not.
//				super.showStage(stage);
			}
			
		};
		
		Stage testStage = new Stage();
		
		fixture.start(testStage);

		assertTrue(4 == stateProperty.get());
		assertTrue(testStage == fixture.stage);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#createView(javafx.stage.Stage, io.github.alantcote.labellelucie.model.facade.GameState, io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	@TestInJfxThread
	public void testCreateView() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		InputHandler testInputHandler = new DefaultInputHandler();
		LaBelleLucie fixture = new LaBelleLucie() {

			@Override
			protected View newView(Stage primaryStage, GameState model, InputHandler inputHandler) {
				// TODO Auto-generated method stub
				return super.newView(primaryStage, model, inputHandler);
			}
			
		};
		
		fixture.createView(testStage, testGameState, testInputHandler);
		
		assertNotNull(fixture.view);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#establishWindowPrefs()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishWindowPrefs() {
		Stage testStage = new Stage();
		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		SimpleObjectProperty<WindowPrefs> windowPrefsProperty = new SimpleObjectProperty<WindowPrefs>();
		LaBelleLucie fixture = new LaBelleLucie() {

			@Override
			protected WindowPrefs newWindowPrefs() throws BackingStoreException {
				assertTrue(0 == stateProperty.get());
				stateProperty.set(1);
				
				windowPrefsProperty.set(super.newWindowPrefs());
				
				return windowPrefsProperty.get();
			}
			
		};
		
		fixture.stage = testStage;
		
		fixture.establishWindowPrefs();

		assertTrue(1 == stateProperty.get());
		assertTrue(windowPrefsProperty.get() == fixture.windowPrefs);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#inizGUI(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testInizGUI() {
		InputHandler testInputHandler = new DefaultInputHandler();
		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		LaBelleLucie fixture = new LaBelleLucie() {

			@Override
			protected void establishWindowPrefs() {
				assertTrue(0 == stateProperty.get());
				stateProperty.set(1);
			}
			
		};
		
		fixture.inizGUI(testInputHandler);
		
		assertTrue(1 == stateProperty.get());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#newController(io.github.alantcote.labellelucie.model.facade.GameState)}.
	 */
	@Test
	public void testNewController() {
		LaBelleLucie fixture = new LaBelleLucie();
		GameState testGameState = fixture.newGameState();
		
		assertNotNull(fixture.newController(testGameState));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#newGameState()}.
	 */
	@Test
	public void testNewGameState() {
		LaBelleLucie fixture = new LaBelleLucie();
		
		assertNotNull(fixture.newGameState());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#newWindowEventHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testNewWindowEventHandler() {
		LaBelleLucie fixture = new LaBelleLucie();
		InputHandler testInputHandler = new DefaultInputHandler();
		
		assertNotNull(fixture.newWindowEventHandler(testInputHandler));
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#newWindowPrefs()}.
	 * @throws BackingStoreException if thrown by the code under test.
	 */
	@Test
	@TestInJfxThread
	public void testNewWindowPrefs() throws BackingStoreException {
		LaBelleLucie fixture = new LaBelleLucie();
		
		fixture.stage = new Stage();

		assertNotNull(fixture.newWindowPrefs());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.LaBelleLucie#showStage(javafx.stage.Stage)}.
	 */
	@Test
	public void testShowStage() {
		// There is no practical way to test this method without displaying a
		// window. This test has to be runnable when headless. Will stipulate
		// that showStage() works, without test code.
	}

}
