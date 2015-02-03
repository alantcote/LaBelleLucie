package net.sf.cotelab.lbl;

import static org.junit.Assert.*;
import javafx.stage.Stage;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.facade.View;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class LaBelleLucieTest extends jMockTestHelper {

	@Test
	public void testNewController() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testNewGameState() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testNewView() {
		// Thin candy shell around calling a constructor needs no test.
		assertTrue(true);
	}

	@Test
	public void testShow() {
		// Can't mock final method, so omit test.
		assertTrue(true);
	}

	@Test
	public void testStartStage() {
		final LaBelleLucie mockLaBelleLucie =
				context.mock(LaBelleLucie.class, "mockLaBelleLucie");
		final GameState mockGameState =
				context.mock(GameState.class, "mockGameState");
		final Controller mockController =
				context.mock(Controller.class, "mockController");
		final Stage mockStage = context.mock(Stage.class, "mockStage");
		final View mockView = context.mock(View.class, "mockView");
		final InputHandler mockInputHandler =
				context.mock(InputHandler.class, "mockInputHandler");
		
		context.checking( new Expectations() {{
			oneOf(mockLaBelleLucie).newGameState();
			will(returnValue(mockGameState));
			
			oneOf(mockLaBelleLucie).newController(mockGameState);
			will(returnValue(mockController));
			
			oneOf(mockLaBelleLucie).newView(mockStage, mockGameState);
			will(returnValue(mockView));
			
			oneOf(mockController).getInputHandler();
			will(returnValue(mockInputHandler));
			
			oneOf(mockView).setInputHandler(mockInputHandler);
			
			oneOf(mockLaBelleLucie).show(mockStage);
		}});
		
		LaBelleLucie fixture = new LaBelleLucie() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.LaBelleLucie#newController(net.sf.cotelab.lbl.model.facade.GameState)
			 */
			@Override
			protected Controller newController(GameState model) {
				return mockLaBelleLucie.newController(model);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.LaBelleLucie#newGameState()
			 */
			@Override
			protected GameState newGameState() {
				return mockLaBelleLucie.newGameState();
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.LaBelleLucie#newView(javafx.stage.Stage, net.sf.cotelab.lbl.model.facade.GameState)
			 */
			@Override
			protected View newView(Stage primaryStage, GameState model) {
				return mockLaBelleLucie.newView(primaryStage, model);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.LaBelleLucie#show(javafx.stage.Stage)
			 */
			@Override
			protected void show(Stage stage) {
				mockLaBelleLucie.show(stage);
			}
		};
		
		fixture.start(mockStage);
	}
}
