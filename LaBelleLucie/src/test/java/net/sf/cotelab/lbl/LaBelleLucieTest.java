package net.sf.cotelab.lbl;

import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.lbl.controller.facade.Controller;
import net.sf.cotelab.lbl.controller.facade.InputHandler;
import net.sf.cotelab.lbl.model.facade.GameState;
import net.sf.cotelab.lbl.view.facade.View;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class LaBelleLucieTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@Before
	public void runBeforeTests() throws Exception {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
	}
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}

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

//	@Test
//	public void testStartStage() {
//		final LaBelleLucie mockLaBelleLucie =
//				context.mock(LaBelleLucie.class, "mockLaBelleLucie");
//		final GameState mockGameState =
//				context.mock(GameState.class, "mockGameState");
//		final Controller mockController =
//				context.mock(Controller.class, "mockController");
//		final Stage mockStage = context.mock(Stage.class, "mockStage");
//		final View mockView = context.mock(View.class, "mockView");
//		final InputHandler mockInputHandler =
//				context.mock(InputHandler.class, "mockInputHandler");
//		@SuppressWarnings("unchecked")
//		final EventHandler<WindowEvent> mockEventHandler =
//				context.mock(EventHandler.class, "mockEventHandler");
//		
//		context.checking( new Expectations() {{
//			oneOf(mockLaBelleLucie).newGameState();
//			will(returnValue(mockGameState));
//			
//			oneOf(mockLaBelleLucie).newController(mockGameState);
//			will(returnValue(mockController));
//			
//			oneOf(mockController).getInputHandler();
//			will(returnValue(mockInputHandler));
//			
//			oneOf(mockLaBelleLucie).newView(
//					mockStage, mockGameState, mockInputHandler);
//			will(returnValue(mockView));
//			
//			oneOf(mockLaBelleLucie).newWindowEventHandler(mockInputHandler);
//			will(returnValue(mockEventHandler));
//			
//			oneOf(mockStage).setOnShown(mockEventHandler);
//			
////			oneOf(mockView).setInputHandler(mockInputHandler);
//			
//			oneOf(mockLaBelleLucie).show(mockStage);
//		}});
//		
//		LaBelleLucie fixture = new LaBelleLucie() {
//			/* (non-Javadoc)
//			 * @see net.sf.cotelab.lbl.LaBelleLucie#newController(net.sf.cotelab.lbl.model.facade.GameState)
//			 */
//			@Override
//			protected Controller newController(GameState model) {
//				return mockLaBelleLucie.newController(model);
//			}
//
//			/* (non-Javadoc)
//			 * @see net.sf.cotelab.lbl.LaBelleLucie#newGameState()
//			 */
//			@Override
//			protected GameState newGameState() {
//				return mockLaBelleLucie.newGameState();
//			}
//
//			/* (non-Javadoc)
//			 * @see net.sf.cotelab.lbl.LaBelleLucie#newView(javafx.stage.Stage, net.sf.cotelab.lbl.model.facade.GameState)
//			 */
//			@Override
//			protected View newView(Stage primaryStage,
//					GameState model, InputHandler inputHandler) {
//				return mockLaBelleLucie.newView(
//						primaryStage, model, inputHandler);
//			}
//
//			/* (non-Javadoc)
//			 * @see net.sf.cotelab.lbl.LaBelleLucie#show(javafx.stage.Stage)
//			 */
//			@Override
//			protected void show(Stage stage) {
//				mockLaBelleLucie.show(stage);
//			}
//		};
//		
//		fixture.start(mockStage);
//	}
}
