/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

/**
 * @author US80653H
 */
public class ResettableJTree extends JTree implements ResettableObject {
	public static final String DEFAULT_ROOT_NODE_TEXT = "";

	private static final long serialVersionUID = 1L;

	protected String defaultRootNodeText = DEFAULT_ROOT_NODE_TEXT;

	/**
	 * 
	 */
	public ResettableJTree() {
		super();
		
		initComponents();
	}

	/**
	 * @param value
	 */
	public ResettableJTree(Hashtable<?, ?> value) {
		super(value);
		
		initComponents();
	}

	/**
	 * @param value
	 */
	public ResettableJTree(Object[] value) {
		super(value);
		
		initComponents();
	}

	/**
	 * @param newModel
	 */
	public ResettableJTree(TreeModel newModel) {
		super(newModel);
		
		initComponents();
	}

	/**
	 * @param root
	 */
	public ResettableJTree(TreeNode root) {
		super(root);
		
		initComponents();
	}

	/**
	 * @param root
	 * @param asksAllowsChildren
	 */
	public ResettableJTree(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
		
		initComponents();
	}
	
	/**
	 * @param value
	 */
	public ResettableJTree(Vector<?> value) {
		super(value);
		
		initComponents();
	}
	
	/**
	 * @return the defaultRootNodeText
	 */
	public String getDefaultRootNodeText() {
		return defaultRootNodeText;
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	@Override
	public void reset() {
        setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode(defaultRootNodeText)));
	}

	/**
	 * @param defaultRootNodeText the defaultRootNodeText to set
	 */
	public void setDefaultRootNodeText(String defaultRootNodeText) {
		this.defaultRootNodeText = defaultRootNodeText;
	}

	protected void initComponents() {
		setDragEnabled(true);
		setShowsRootHandles(true);
        setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode(defaultRootNodeText)));
	}
}
