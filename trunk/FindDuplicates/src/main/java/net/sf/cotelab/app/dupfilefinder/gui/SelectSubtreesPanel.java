/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.sf.cotelab.app.dupfilefinder.tree.ActivePathTreeNode;

/**
 * @author US80653H
 *
 */
public class SelectSubtreesPanel extends JPanel implements ResettableObject {
	public static final Cursor WAIT_CURSOR =
	    	Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	
	private static final long serialVersionUID = 1L;
	
	protected CheckBoxAndButtonPanel checkBoxAndButtonPanel =
			new CheckBoxAndButtonPanel();
    protected Stack<Cursor> cursorStack = new Stack<Cursor>();
	protected TreeExpansionListener tel = new TreeExpansionListener() {
		@Override
		public void treeCollapsed(TreeExpansionEvent event) {
			// NOTHING
		}
		
		@Override
		public void treeExpanded(TreeExpansionEvent event) {
			popCursor();
		}
	};
	protected DefaultTreeModel treeModel = null;
	protected JTreeScrollPanel treeScrollPanel = new JTreeScrollPanel();
	protected TreeWillExpandListener twel = new TreeWillExpandListener() {
		@Override
		public void treeWillCollapse(TreeExpansionEvent event)
				throws ExpandVetoException {
			// NOTHING
		}

		@Override
		public void treeWillExpand(TreeExpansionEvent event)
				throws ExpandVetoException {
			pushCursor(WAIT_CURSOR);
		}
	};

	/**
	 * 
	 */
	public SelectSubtreesPanel() {
		super();
		
		initComponents();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public SelectSubtreesPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
		initComponents();
	}

	/**
	 * @param layout
	 */
	public SelectSubtreesPanel(LayoutManager layout) {
		super(layout);
		
		initComponents();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public SelectSubtreesPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
		initComponents();
	}

    public void addStartButtonActionListener(ActionListener listener) {
		checkBoxAndButtonPanel.addStartButtonActionListener(listener);
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#getSelectedPaths()
	 */
	public TreePath[] getSelectedPaths() {
		return treeScrollPanel.getSelectedPaths();
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#getModel()
	 */
	public TreeModel getTreeModel() {
		return treeScrollPanel.getModel();
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.CheckBoxAndButtonPanel#isSpecialHandling()
	 */
	public boolean isSpecialHandling() {
		return checkBoxAndButtonPanel.isSpecialHandling();
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#listSelectedFiles()
	 */
	public List<File> listSelectedFiles() {
		return treeScrollPanel.listSelectedFiles();
	}

	public void popCursor() {
        setCursor(cursorStack.pop());
    }
	
	public void pushCursor(Cursor newCursor) {
        cursorStack.push(getCursor());
        setCursor(newCursor);
    }

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	@Override
	public void reset() {
		checkBoxAndButtonPanel.reset();
//		treeScrollPanel.reset();
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.CheckBoxAndButtonPanel#setButtonEnabled(boolean)
	 */
	public void setButtonEnabled(boolean b) {
		checkBoxAndButtonPanel.setButtonEnabled(b);
	}

	/**
	 * @param x
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setCellRenderer(javax.swing.tree.TreeCellRenderer)
	 */
	public void setCellRenderer(TreeCellRenderer x) {
		treeScrollPanel.setCellRenderer(x);
	}

	/**
	 * @param defaultRootNodeText
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setDefaultRootNodeText(java.lang.String)
	 */
	public void setDefaultRootNodeText(String defaultRootNodeText) {
		treeScrollPanel.setDefaultRootNodeText(defaultRootNodeText);
	}

	/**
	 * @param rootVisible
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setRootVisible(boolean)
	 */
	public void setRootVisible(boolean rootVisible) {
		treeScrollPanel.setRootVisible(rootVisible);
	}
	
	/**
	 * @param paths
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setSelectionPaths(javax.swing.tree.TreePath[])
	 */
	public void setSelectionPaths(TreePath[] paths) {
		treeScrollPanel.setSelectionPaths(paths);
	}

	protected File[] getFilesystemsRoots() {
        File[] filesystemsRoots = File.listRoots();
        String osName = System.getProperty("os.name");

        if (osName.startsWith("Windows")) {
            filesystemsRoots = new File[1];
            filesystemsRoots[0] =
                    FileSystemView.getFileSystemView().getHomeDirectory();
        }

        return filesystemsRoots;
    }

	protected void initComponents() {
		setLayout(new BorderLayout());
		
		initTree();
		
		add(treeScrollPanel, BorderLayout.CENTER);
		add(checkBoxAndButtonPanel, BorderLayout.SOUTH);
	}

	protected void initTree() {
        DefaultMutableTreeNode rootNode =
                new DefaultMutableTreeNode("Filesystems", true);
        TreeNode[] visibleRootNode = new TreeNode[2];
        Path[] filesystemsRoots = ActivePathTreeNode.listFileSystemRootDirs();
        
        Arrays.sort(filesystemsRoots);

        treeModel = new DefaultTreeModel(rootNode);
        treeScrollPanel.setTreeModel(treeModel);

        visibleRootNode[0] = rootNode;
        visibleRootNode[1] = null;

        for (Path aRoot : filesystemsRoots) {
        	ActivePathTreeNode aRootNode =
        			new ActivePathTreeNode(treeModel, aRoot);

            rootNode.add(aRootNode);

            if (visibleRootNode[1] == null) {
                visibleRootNode[1] = aRootNode;
            }
        }

        treeScrollPanel.makeVisible(new TreePath(visibleRootNode));

        treeScrollPanel.addTreeWillExpandListener(twel);
        treeScrollPanel.addTreeExpansionListener(tel);
	}
}
