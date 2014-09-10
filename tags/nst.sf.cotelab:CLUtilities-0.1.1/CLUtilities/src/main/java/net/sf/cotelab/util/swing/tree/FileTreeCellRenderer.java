/*
 * FileTreeCellRenderer.java
 *
 * Created on May 28, 2003, 7:44 AM
 */

package net.sf.cotelab.util.swing.tree;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * A <tt>DefaultTreeCellRenderer</tt> that renders <tt>File</tt> objects with
 * platform-authentic icons and text.
 * @author  acote
 */
public class FileTreeCellRenderer extends DefaultTreeCellRenderer {
    /**
     * A supplier of platform-authentic icons and such.
     */
    public static final FileSystemView fileSystemView =
            FileSystemView.getFileSystemView();
    
    /**
     * Serialization support.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * If this value is <tt>true</tt>, then the text shown for each node is the
     * absolute path of the <tt>File</tt>.  Otherwise, the value calculated by
     * the superclass is used.
     */
    protected boolean showAbsolutePaths = false;
    
    /**
     * Construct a new object.
     */
    public FileTreeCellRenderer() {
        super();
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus) {
        Component retValue =
                super.getTreeCellRendererComponent(
                    tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) value;
            Object uo = dmtn.getUserObject();
            
            if (uo instanceof File) {
                File fileValue = (File) uo;
                boolean needSystemIcon = true;

                setText(fileSystemView.getSystemDisplayName(fileValue));
                
                if (showAbsolutePaths) {
                    setText(fileValue.getAbsolutePath());
                }
                
                if (fileValue.isDirectory()) {
                    if ((fileValue.getParentFile() != null) &&
                            fileValue.getPath().startsWith(
                            fileValue.getParent())) {
                        try {
                            fileValue.getCanonicalPath();
                            needSystemIcon = false;
                            
                            if (leaf) {
                                if (isEnabled()) {
                                    setIcon(UIManager.getIcon(
                                            "Tree.closedIcon"));
                                } else {
                                    setDisabledIcon(UIManager.getIcon(
                                            "Tree.closedIcon"));
                                }
                            }
                        } catch (IOException ex) {
                        }
                    }
                }

                if (needSystemIcon) {
                    if (isEnabled()) {
                        setIcon(fileSystemView.getSystemIcon(fileValue));
                    } else {
                        setDisabledIcon(
                                fileSystemView.getSystemIcon(fileValue));
                    }
                }
            }
        }
        
        return retValue;
    }

    public boolean isShowAbsolutePaths() {
        return showAbsolutePaths;
    }

    public void setShowAbsolutePaths(boolean showAbsolutePaths) {
        this.showAbsolutePaths = showAbsolutePaths;
    }
}
