/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.Component;
import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import net.sf.cotelab.app.dupfilefinder.action.CollapseAllAction;
import net.sf.cotelab.app.dupfilefinder.action.DeleteFilesAction;
import net.sf.cotelab.app.dupfilefinder.action.ExpandAllAction;

/**
 * @author US80653H
 */
public class JTreeScrollPanel extends JScrollPane
		implements ResettableObject {
	private static final long serialVersionUID = 1L;

	protected TreePath[] selectedPaths;
	protected ResettableJTree tree = new ResettableJTree();
	protected JPopupMenu treeMenu = new JPopupMenu();

	/**
	 * 
	 */
	public JTreeScrollPanel() {
		super();
		
		initComponents();
	}

	/**
	 * @param view
	 */
	public JTreeScrollPanel(Component view) {
		super(view);
		
		initComponents();
	}

	/**
	 * @param view
	 * @param vsbPolicy
	 * @param hsbPolicy
	 */
	public JTreeScrollPanel(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		
		initComponents();
	}

	/**
	 * @param vsbPolicy
	 * @param hsbPolicy
	 */
	public JTreeScrollPanel(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		
		initComponents();
	}

	/**
	 * @param tel
	 * @see javax.swing.JTree#addTreeExpansionListener(javax.swing.event.TreeExpansionListener)
	 */
	public void addTreeExpansionListener(TreeExpansionListener tel) {
		tree.addTreeExpansionListener(tel);
	}

	/**
	 * @param tel
	 * @see javax.swing.JTree#addTreeWillExpandListener(javax.swing.event.TreeWillExpandListener)
	 */
	public void addTreeWillExpandListener(TreeWillExpandListener tel) {
		tree.addTreeWillExpandListener(tel);
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ResettableJTree#getDefaultRootNodeText()
	 */
	public String getDefaultRootNodeText() {
		return tree.getDefaultRootNodeText();
	}

	/**
	 * @return
	 * @see javax.swing.JTree#getModel()
	 */
	public TreeModel getModel() {
		return tree.getModel();
	}

	/**
	 * @return the selectedPaths
	 */
	public TreePath[] getSelectedPaths() {
		return selectedPaths;
	}

	public List<File> listSelectedFiles() {
        List<File> selectedFiles = new LinkedList<File>();
        selectedPaths = tree.getSelectionPaths();

        if (selectedPaths != null) {
            for (TreePath path : selectedPaths) {
                DefaultMutableTreeNode lftn =
                        (DefaultMutableTreeNode) path.getLastPathComponent();
                Object uo = lftn.getUserObject();
                File aFile = null;
                
                if ((uo != null) && (uo instanceof Path)) {
                	aFile = ((Path) uo).toFile();

                    selectedFiles.add(aFile);
                }
            }
        }

        return selectedFiles;
    }
	/**
	 * @param path
	 * @see javax.swing.JTree#makeVisible(javax.swing.tree.TreePath)
	 */
	public void makeVisible(TreePath path) {
		tree.makeVisible(path);
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	@Override
	public void reset() {
		tree.reset();
	}

	/**
	 * @param x
	 * @see javax.swing.JTree#setCellRenderer(javax.swing.tree.TreeCellRenderer)
	 */
	public void setCellRenderer(TreeCellRenderer x) {
		tree.setCellRenderer(x);
	}

	/**
	 * @param defaultRootNodeText
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ResettableJTree#setDefaultRootNodeText(java.lang.String)
	 */
	public void setDefaultRootNodeText(String defaultRootNodeText) {
		tree.setDefaultRootNodeText(defaultRootNodeText);
	}
	
	/**
	 * @param rootVisible
	 * @see javax.swing.JTree#setRootVisible(boolean)
	 */
	public void setRootVisible(boolean rootVisible) {
		tree.setRootVisible(rootVisible);
	}

	/**
	 * @param paths
	 * @see javax.swing.JTree#setSelectionPaths(javax.swing.tree.TreePath[])
	 */
	public void setSelectionPaths(TreePath[] paths) {
		tree.setSelectionPaths(paths);
	}

	/**
	 * @param newModel
	 * @see javax.swing.JTree#setModel(javax.swing.tree.TreeModel)
	 */
	public void setTreeModel(TreeModel newModel) {
		tree.setModel(newModel);
	}

	protected void initComponents() {
		setViewportView(tree);

		treeMenu.add(new ExpandAllAction("Expand All", tree));
		treeMenu.add(new CollapseAllAction("Collapse All", tree));
		treeMenu.addSeparator();
		treeMenu.add(new DeleteFilesAction("Delete", tree));
		
		tree.setComponentPopupMenu(treeMenu);
		tree.setRootVisible(true);
        
        ToolTipManager.sharedInstance().registerComponent(tree);
	}
}
