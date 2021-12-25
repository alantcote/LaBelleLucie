package net.sf.cotelab.lbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import net.sf.cotelab.lbl.view.impl.FanView;

public class NodeAdapter {
	public Object fxObject;

	public NodeAdapter(Object fxObject) {
		this.fxObject = fxObject;

		System.out.println("NodeAdapter.NodeAdapter(): fxObject.getClass() = " + fxObject.getClass());
	}

	public List<NodeAdapter> getChildrenUnmodifiable() {
		List<NodeAdapter> result = new ArrayList<NodeAdapter>();
		
		if (fxObject instanceof Menu) {
			((Menu) fxObject).getItems().forEach(item -> result.add(new NodeAdapter(item)));
		} else if (fxObject instanceof Tab) {
			Node content = ((Tab) fxObject).getContent();
			if (content != null)
				result.add(new NodeAdapter(content));
		} else // extended parent types
		if (fxObject instanceof MenuBar) {
			((MenuBar) fxObject).getMenus().forEach(menu -> result.add(new NodeAdapter(menu)));
		} else if (fxObject instanceof TabPane) {
			((TabPane) fxObject).getTabs().forEach(tab -> result.add(new NodeAdapter(tab)));
		} else if (fxObject instanceof TitledPane) {
			Node content = ((TitledPane) fxObject).getContent();
			if (content != null)
				result.add(new NodeAdapter(content));
		} else if (fxObject instanceof ScrollPane) {
			Node content = ((ScrollPane) fxObject).getContent();
			if (content != null)
				result.add(new NodeAdapter(content));
		} else if (fxObject instanceof Accordion) {
			((Accordion) fxObject).getPanes().forEach(pane -> result.add(new NodeAdapter(pane)));
		} else if (fxObject instanceof SplitPane) {
			((SplitPane) fxObject).getItems().forEach(item -> result.add(new NodeAdapter(item)));
		} else if (fxObject instanceof ToolBar) {
			((ToolBar) fxObject).getItems().forEach(item -> result.add(new NodeAdapter(item)));
		} else if (fxObject instanceof ButtonBar) {
			((ButtonBar) fxObject).getButtons().forEach(button -> result.add(new NodeAdapter(button)));
		} else if (fxObject instanceof BorderPane) {
			BorderPane bp = (BorderPane) fxObject;
			Object kid;
			
			kid = bp.getBottom();
			if (kid != null) {
				result.add(new NodeAdapter(kid));
			}
			
			kid = bp.getCenter();
			if (kid != null) {
				result.add(new NodeAdapter(kid));
			}
			
			kid = bp.getLeft();
			if (kid != null) {
				result.add(new NodeAdapter(kid));
			}
			
			kid = bp.getRight();
			if (kid != null) {
				result.add(new NodeAdapter(kid));
			}
			
			kid = bp.getTop();
			if (kid != null) {
				result.add(new NodeAdapter(kid));
			}
		} else if (fxObject instanceof Control) {
			ContextMenu contextMenu = ((Control) fxObject).getContextMenu();
			if (contextMenu != null) {
				contextMenu.getItems().forEach(item -> result.add(new NodeAdapter(item)));
			}
		} else if (fxObject instanceof GridPane) {
			((GridPane) fxObject).getChildren().forEach(child -> result.add(new NodeAdapter(child)));
		} else if (fxObject instanceof FanView) {
			((FanView) fxObject).getChildren().forEach(child -> result.add(new NodeAdapter(child)));
		} else if (fxObject instanceof AnchorPane) {
			((AnchorPane) fxObject).getChildrenUnmodifiable().forEach(child -> result.add(new NodeAdapter(child)));
		} else if (fxObject instanceof Parent) {
			// primary parent type derived from the root type
			((Parent) fxObject).getChildrenUnmodifiable().forEach(node -> result.add(new NodeAdapter(node)));
		} else {
			System.err.println("NodeAdapter.getChildrenUnmodifiable(): fxObject.getClass() = " + fxObject.getClass());
		}

		return Collections.unmodifiableList(result);
	}
}
