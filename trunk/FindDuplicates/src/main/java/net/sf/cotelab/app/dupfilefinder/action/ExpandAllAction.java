package net.sf.cotelab.app.dupfilefinder.action;

import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author cote
 */
public class ExpandAllAction extends AbstractAction {
    /**
	 * Serialization support.
	 */
	private static final long serialVersionUID = 1L;
	protected JTree jTree;
    
    public ExpandAllAction(String name, JTree jTree) {
        super(name);
        
        this.jTree = jTree;
    }
    
    public void actionPerformed(ActionEvent e) {
        TreePath[] treePaths = jTree.getSelectionPaths();
        
        if (treePaths != null) {
            for (TreePath treePath : treePaths) {
            	expandAll(treePath);
            }
        }
    }
    
    @SuppressWarnings("rawtypes")
	protected void expandAll(TreePath parent) {
    	TreeNode node = (TreeNode) parent.getLastPathComponent();
    	
    	if (node.getChildCount() > 0) {
    		for (Enumeration e = node.children(); e.hasMoreElements();) {
    			TreeNode n = (TreeNode) e.nextElement();
    			TreePath path = parent.pathByAddingChild(n);
    			
    			expandAll(path);
    		}
        	
        	jTree.expandPath(parent);
    	}
    }
}
