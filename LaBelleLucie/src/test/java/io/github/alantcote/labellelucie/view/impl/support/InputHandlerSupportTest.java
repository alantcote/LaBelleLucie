package io.github.alantcote.labellelucie.view.impl.support;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.alantcote.labellelucie.controller.facade.DefaultInputHandler;
import io.github.alantcote.labellelucie.controller.facade.InputHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Test case for {@link io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport}.
 */
public class InputHandlerSupportTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#InputHandlerSupport(javafx.scene.Node)}.
	 */
	@Test
	public void testInputHandlerSupport() {
		Node testNode = new Label("label");
		InputHandlerSupport fixture = new InputHandlerSupport(testNode);
		
		assertNotNull(fixture);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#getInputHandler()}.
	 */
	@Test
	public void testGetInputHandler() {
		Node testNode = new Label("label");
		InputHandlerSupport fixture = new InputHandlerSupport(testNode);
		
		assertEquals(fixture.inputHandler, fixture.getInputHandler());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#setInputHandler(io.github.alantcote.labellelucie.controller.facade.InputHandler)}.
	 */
	@Test
	public void testSetInputHandler() {
		Node testNode = new Label("label");
		InputHandlerSupport fixture = new InputHandlerSupport(testNode);
		InputHandler testInputHandler = new DefaultInputHandler();
		
		assertNotEquals(fixture.inputHandler, testInputHandler);
		
		fixture.setInputHandler(testInputHandler);
		
		assertEquals(fixture.inputHandler, testInputHandler);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#monitorEvents(javafx.scene.Node)}.
	 */
	@Test
	public void testMonitorEvents() {
		SimpleIntegerProperty stateProperty = new SimpleIntegerProperty(0);
		Node testNode = new Label("label");
		InputHandlerSupport fixture = new InputHandlerSupport(testNode) {

			@Override
			protected void monitorMouseEvents(Node source) {
				assertEquals(testNode, source);
				
				assertEquals(stateProperty.get(), 0);
				
				stateProperty.set(1);
			}
			
		};
		
		stateProperty.set(0);
		
		fixture.monitorEvents(testNode);
		
		assertEquals(stateProperty.get(), 1);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.view.impl.support.InputHandlerSupport#monitorMouseEvents(javafx.scene.Node)}.
	 */
	@Test
	public void testMonitorMouseEvents() {
		Node testNode = new Label("label");
		InputHandlerSupport fixture = new InputHandlerSupport(testNode);
		
		fixture.monitorMouseEvents(testNode);
		
		assertNotNull(testNode.getOnMouseClicked());
		assertNotNull(testNode.getOnMouseEntered());
		assertNotNull(testNode.getOnMouseExited());
	}

}
