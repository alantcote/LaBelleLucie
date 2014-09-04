package net.sf.cotelab.app.dupfilefinder.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 * 
 * @author cote
 */
public class CollapseAllAction extends AbstractAction {
	/**
	 * Serialization support.
	 */
	private static final long serialVersionUID = 1L;
	protected JTree jTree;

	public CollapseAllAction(String name, JTree jTree) {
		super(name);

		this.jTree = jTree;
	}

	public void actionPerformed(ActionEvent e) {
		TreePath[] treePaths = jTree.getSelectionPaths();

		if (treePaths != null) {
			for (TreePath treePath : treePaths) {
				collapseAll(treePath);
			}
		}
	}

	protected void collapseAll(TreePath parent) {
		jTree.collapsePath(parent);
	}
}
