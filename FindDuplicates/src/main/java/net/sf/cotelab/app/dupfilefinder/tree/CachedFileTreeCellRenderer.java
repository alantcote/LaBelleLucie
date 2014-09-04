/**
 * $Id: CachedFileTreeCellRenderer.java,v 1.4 2011/11/01 21:11:45 acote Exp $
 * $Log: CachedFileTreeCellRenderer.java,v $
 * Revision 1.4  2011/11/01 21:11:45  acote
 * progress toward improved reporting.
 *
 * Revision 1.3  2011/11/01 18:23:29  acote
 * added some stuff to make duplicates identifiable, with their peers, in the root object selection jtree.
 *
 * Revision 1.2  2008/04/19 11:47:38  acote
 * Wrapping up the 1.2 release.
 *
 * Revision 1.1  2008/04/16 13:06:46  acote
 * Reorganized source code.
 *
 * Revision 1.3  2008/04/15 18:56:44  acote
 * Cleared up warnings.
 *
 * Revision 1.2  2008/04/15 16:17:51  acote
 * Still working on improving behavior when expanding nodes in the search root
 * selection JTree.
 *
 * Revision 1.1  2008/04/15 14:42:38  acote
 * Trying to speed up the search root selection JTree display.
 *
 */
package net.sf.cotelab.app.dupfilefinder.tree;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import net.sf.cotelab.util.swing.tree.FileTreeCellRenderer;

/**
 * A <tt>FileTreeCellRenderer</tt> that caches <tt>File</tt>-to-<tt>Icon</tt>
 * relationships in an effort to improve performance.
 * @author cote
 */
public class CachedFileTreeCellRenderer extends FileTreeCellRenderer {
    protected static final Icon closedLeafIcon =
		UIManager.getIcon("Tree.closedIcon");
    
	/**
     * Serialization support
     */
    private static final long serialVersionUID = 1L;

    protected HashMap<File, Integer> ancestorsOfDups;
	protected HashMap<File, String> file2DisplayName;
	protected Map<File, Collection<File>> file2EquivSetMap;
	protected HashMap<File, Icon> file2Icon;
    protected boolean useFullPathnames = false;

	/**
	 * 
	 */
	public CachedFileTreeCellRenderer(
			HashMap<File, String> file2DisplayNameCache,
			HashMap<File, Icon> file2IconCache,
			Map<File, Collection<File>> file2EquivSetMap,
			HashMap<File,Integer> ancestorsOfDups) {
        super();
        
        this.ancestorsOfDups = ancestorsOfDups;
        file2DisplayName = file2DisplayNameCache;
        this.file2EquivSetMap = file2EquivSetMap;
        file2Icon = file2IconCache;
	}
    /**
	 * @return the ancestorsOfDups
	 */
	public HashMap<File, Integer> getAncestorsOfDups() {
		return ancestorsOfDups;
	}

	/**
	 * @return the file2EquivSetMap
	 */
	public Map<File, Collection<File>> getFile2EquivSetMap() {
		return file2EquivSetMap;
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

        try {
            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) value;
                Object uo = dmtn.getUserObject();
                File fileValue = null;
                Path pathValue = null;
                
                if (uo instanceof File) {
                    fileValue = (File) uo;
                } else if (uo instanceof Path) {
                	pathValue = (Path) uo;
                	fileValue = pathValue.toFile();
                }
                
                if (fileValue != null) {
                    applyFileInfo(leaf, dmtn, fileValue);
                }
                
                if (pathValue != null) {
                	if (Files.isSymbolicLink(pathValue)) {
                		String baseText = getText();
                		Path realPath = pathValue.toRealPath();
                		String annotatedText = baseText + " --> " + realPath;
                		
                		setText(annotatedText);
                	}
                }
            }
        } catch (Throwable t) {
        	System.err.println("Caught: " + t.getMessage());
        	t.printStackTrace(System.err);
        }
        
        return retValue;
    }

	/**
	 * @return the useFullPathnames
	 */
	public boolean isUseFullPathnames() {
		return useFullPathnames;
	}

	/**
	 * @param useFullPathnames the useFullPathnames to set
	 */
	public void setUseFullPathnames(boolean useFullPathnames) {
		this.useFullPathnames = useFullPathnames;
	}

	protected void applyFileInfo(boolean leaf, DefaultMutableTreeNode dmtn,
			File fileValue) {
		setText(findDisplayName(fileValue));
		
		if (showAbsolutePaths) {
		    setText(fileValue.getAbsolutePath());
		}

		setIcon(fileSystemView.getSystemIcon(fileValue));
			
		markDuplicates(fileValue);
	}

	protected String findDisplayName(File aFile) {
    	String retVal = file2DisplayName.get(aFile);
    	
    	if (retVal == null) {
    		retVal = fileSystemView.getSystemDisplayName(aFile);
    		
    		if (isUseFullPathnames()) {
    			try {
    				String parentPath = aFile.getParentFile().getCanonicalPath();
    				
					retVal = retVal + " (" + parentPath + ")";
				} catch (IOException e) {
//					e.printStackTrace();
				}
    		}
    		
    		file2DisplayName.put(aFile, retVal);
    	}
    	
    	return retVal;
    }
    
    protected Icon findIcon(File aFile, boolean leaf) {
    	Icon retVal = file2Icon.get(aFile);
    	
    	if (retVal == null) {
    		retVal = getSystemIcon(aFile, leaf);
    		
    		if (retVal != closedLeafIcon) {
        		file2Icon.put(aFile, retVal);
    		}
    	}
    	
    	return retVal;
    }

	protected Icon getSystemIcon(File file, boolean leaf) {
        if (file.isDirectory()) {
            if ((file.getParentFile() != null) &&
            		file.getPath().startsWith(
            				file.getParent())) {
                try {
                	file.getCanonicalPath();
                    
                    if (leaf) {
                        return closedLeafIcon;
                    }
                } catch (IOException ex) {
                }
            }
        }
        
		return fileSystemView.getSystemIcon(file);
	}

	protected void markDuplicates(File fileValue) {
		// NOTHING
	}
}
