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
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.RootView}.
 */
@RunWith(JfxRunner.class)
public class RootViewTest {
	public int state = 0;

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#RootView(javafx.stage.Stage, io.github.alantcote.labellelucie.model.facade.GameState, javafx.application.HostServices)}.
	 */
	@Test
	@TestInJfxThread
	public void testRootView() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		
		state = 0;
		
		RootView fixture = new RootView(testStage, testGameState, testHostServices) {

			@Override
			protected void inizChildren() {
				assertTrue(1 == state);
				++state;
			}

			@Override
			protected void inizVariables() {
				assertTrue(0 == state);
				++state;
			}
			
		};

		assertTrue(2 == state);
		
		assertTrue(testStage == fixture.appStage);
		assertTrue(testGameState == fixture.model);
		assertTrue(testHostServices == fixture.hostServices);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#getInputHandler()}.
	 */
	@Test
	@TestInJfxThread
	public void testGetInputHandler() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		assertTrue(fixture.inputHandlerSupport.getInputHandler() == fixture.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	@TestInJfxThread
	public void testSetInputHandler() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		InputHandler testInputHandler = new MasterInputHandler(new ControllerImpl(testGameState));
		
		fixture.setInputHandler(testInputHandler);
		
		assertTrue(testInputHandler == fixture.inputHandlerSupport.getInputHandler());
		assertTrue(testInputHandler == fixture.tableView.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#showHelpAboutDialog()}.
	 */
	@Test
	@TestInJfxThread
	public void testShowHelpAboutDialog() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		
		state = 0;
		
		RootView fixture = new RootView(testStage, testGameState, testHostServices) {

			@Override
			public void showHelpDialog(String title, String contentURL) {
				assertTrue(0 == state);
				++state;
				
				assertEquals("About LaBelleLucie", title);
				assertTrue(contentURL.endsWith("helpAbout.html"));
			}
			
		};
		
		fixture.showHelpAboutDialog();

		assertTrue(1 == state);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#showHelpDialog(java.lang.String, java.lang.String)}.
	 */
	@Test
	@TestInJfxThread
	public void testShowHelpDialog() {
		// This method ultimately opens a window. Not a good idea.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#showHelpRulesDialog()}.
	 */
	@Test
	@TestInJfxThread
	public void testShowHelpRulesDialog() {
		// This method ultimately opens a window. Not a good idea.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#showHelpUsageDialog()}.
	 */
	@Test
	@TestInJfxThread
	public void testShowHelpUsageDialog() {
		// This method ultimately opens a window. Not a good idea.
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#calcFanOffset()}.
	 */
	@Test
	@TestInJfxThread
	public void testCalcFanOffset() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		double expected = fixture.cardSize.getWidth() / RootView.FAN_OFFSET_FACTOR;
		double actual = fixture.calcFanOffset();
		
		assertTrue(expected == actual);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#createDrawsText()}.
	 */
	@Test
	@TestInJfxThread
	public void testCreateDrawsText() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		String expected = "Draws remaining: " + fixture.model.getDrawsRemaining().get();
		String actual = fixture.createDrawsText();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#createReshufflesText()}.
	 */
	@Test
	@TestInJfxThread
	public void testCreateReshufflesText() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		String expected = "Reshuffles remaining: " + fixture.model.getRedealsRemaining().get();
		String actual = fixture.createReshufflesText();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishDrawsLabel()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishDrawsLabel() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishDrawsLabel();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishEditMenu()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishEditMenu() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishEditMenu();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishFileExitItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishFileExitItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishFileExitItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishFileMenu()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishFileMenu() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishFileMenu();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishGameMenu()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishGameMenu() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishGameMenu();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishHelpAboutItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishHelpAboutItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishHelpAboutItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishHelpHintItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishHelpHintItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishHelpHintItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishHelpMenu()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishHelpMenu() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishHelpMenu();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishHelpRulesItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishHelpRulesItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishHelpRulesItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishHelpUsageItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishHelpUsageItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishHelpUsageItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishMenuBar()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishMenuBar() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishMenuBar();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishMessageBar()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishMessageBar() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishMessageBar();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishNewGameItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishNewGameItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishNewGameItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishReshuffleItem()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishReshuffleItem() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishReshuffleItem();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishReshufflesLabel()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishReshufflesLabel() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishReshufflesLabel();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#establishTableView()}.
	 */
	@Test
	@TestInJfxThread
	public void testEstablishTableView() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.establishTableView();
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#getResource(java.lang.String)}.
	 */
	@Test
	@TestInJfxThread
	public void testGetResource() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.model.reset();
		
		fixture.getResource("helpAbout.html");
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#inizChildren()}.
	 */
	@Test
	@TestInJfxThread
	public void testInizChildren() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		
		state = 0;
		
		RootView fixture = new RootView(testStage, testGameState, testHostServices) {

			@Override
			protected void establishMenuBar() {
				assertTrue(0 == state);
				++state;
			}

			@Override
			protected void establishMessageBar() {
				assertTrue(2 == state);
				++state;
			}

			@Override
			protected void establishTableView() {
				assertTrue(1 == state);
				++state;
			}
			
		};
		
		state = 0;
		
		fixture.inizChildren();

		assertTrue(3 == state);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.RootView#inizVariables()}.
	 */
	@Test
	@TestInJfxThread
	public void testInizVariables() {
		Stage testStage = new Stage();
		GameState testGameState = new GameStateImpl();
		HostServices testHostServices = null;
		RootView fixture = new RootView(testStage, testGameState, testHostServices);
		
		fixture.inizVariables();
		
		assertNotNull(fixture.cardViewFactory);
		assertNotNull(fixture.cardSize);
		assertTrue(0 < fixture.fanOffset);
	}

}
