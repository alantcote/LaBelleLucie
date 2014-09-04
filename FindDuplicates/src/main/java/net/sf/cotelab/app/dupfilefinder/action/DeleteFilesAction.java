package net.sf.cotelab.app.dupfilefinder.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import net.sf.cotelab.app.dupfilefinder.tree.ActivePathTreeNode;
import net.sf.cotelab.app.dupfilefinder.tree.CachedFileTreeCellRenderer;

/**
 *
 * @author cote
 */
public class DeleteFilesAction extends AbstractAction {
    /**
	 * Serialization support.
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTree jTree;
    
    public DeleteFilesAction(String name, JTree jTree) {
        super(name);
        
        this.jTree = jTree;
    }
    
    public void actionPerformed(ActionEvent e) {
        Component comp = (Component) e.getSource();
        TreePath[] treePaths = jTree.getSelectionPaths();
        Collection<File> targets = gatherTargets(treePaths);
        
        if (!targets.isEmpty()) {
            if(isDeletionConfirmed(comp, targets)) {
                remove(treePaths);
            }
        }
    }
    
    protected Collection<File> gatherTargets(TreePath[] treePaths) {
        LinkedList<File> retVal = new LinkedList<File>();
        
        if (treePaths != null) {
            for (TreePath tp : treePaths) {
                Object lpc = tp.getLastPathComponent();

//                if (lpc instanceof LazyFileTreeNode) {
//                    LazyFileTreeNode lftn = (LazyFileTreeNode) lpc;
//                    File file = (File) lftn.getUserObject();
//                    
//                    retVal.add(file);
//                }
                if (lpc instanceof ActivePathTreeNode) {
                	ActivePathTreeNode lftn = (ActivePathTreeNode) lpc;
                    File file = ((Path) lftn.getUserObject()).toFile();
                    
                    retVal.add(file);
                }
            }
        }
        
        return retVal;
    }

    protected boolean isDeletionConfirmed(
            Component comp, Collection<File> targets) {
        boolean retVal = false;
        String message;
        String title;
        int size = targets.size();
        
        if (size != 0) {
            int dialogResult;
            
            if (size == 1) {
                File file = targets.iterator().next();
                String filename = file.getName();
                
                if (filename.length() == 0) {
                    filename = file.getPath();
                }
                
                message = "Delete \"" + filename + "\"?";
                title = message;
            } else {
                message = "Delete these " + size + " files?";
                title = "Delete " + size + " files?";
            }
            
            dialogResult = JOptionPane.showConfirmDialog(
                    comp, message, title, JOptionPane.YES_NO_OPTION);
            
            retVal = (dialogResult == JOptionPane.YES_OPTION);
        }
        
        return retVal;
    }

    protected boolean remove(File filesysObj) {
    	boolean result = true;
    	
    	if (filesysObj.isFile()) {
    		result = filesysObj.delete();
    		
    		if (result) {
    			removeTraces(filesysObj);
    		} else {
            	JOptionPane.showInternalMessageDialog(
            			jTree, "Deletion failed: " + filesysObj,
            			"Deletion failure", JOptionPane.ERROR_MESSAGE);
    		}
    	} else if (filesysObj.isDirectory()) {
    		File[] kids = filesysObj.listFiles();
    		
    		for (File kid : kids) {
    			result = remove(kid);
    			
    			if (!result) {
    				break;
    			}
    		}
    		
    		if (result) {
        		result = filesysObj.delete();

        		if (result) {
        			removeTraces(filesysObj);
        		} else {
                	JOptionPane.showInternalMessageDialog(
                			jTree, "Deletion failed: " + filesysObj,
                			"Deletion failure", JOptionPane.ERROR_MESSAGE);
        		}
    		}
    	}
    	
    	return result;
    }
    
    protected void remove(TreePath[] treePaths) {
        DefaultTreeModel treeModel = (DefaultTreeModel) jTree.getModel();
        
        for (TreePath tp : treePaths) {
            Object lpc = tp.getLastPathComponent();

            if (lpc instanceof ActivePathTreeNode) {
            	ActivePathTreeNode lftn = (ActivePathTreeNode) lpc;
                File file = ((Path) lftn.getUserObject()).toFile();

                if (remove(file)) {
                    treeModel.removeNodeFromParent(lftn);
                }
            }
        }
    }

	protected void removeTraces(File file) {
        TreeCellRenderer tcr = jTree.getCellRenderer();
        Map<File, Collection<File>> f2esm = null;
    	HashMap<File, Integer> aod = null;
        
        if (tcr instanceof CachedFileTreeCellRenderer) {
        	CachedFileTreeCellRenderer cftcr = (CachedFileTreeCellRenderer) tcr;
        	
        	f2esm = cftcr.getFile2EquivSetMap();
        	aod = cftcr.getAncestorsOfDups();
        }
        
        if (f2esm != null) {
        	Collection<File> dupColl = f2esm.get(file);
        	
        	f2esm.remove(file);
        	if (dupColl != null) {
            	dupColl.remove(file);
            	
            	if (dupColl.size() == 1) {
            		File dup = dupColl.iterator().next();
            		
            		f2esm.remove(dup);
                    
                    if (aod != null) {
                    	File ancestor = dup.getParentFile();
                    	
                    	while (ancestor != null) {
                    		Integer count = aod.get(ancestor);
                    		
                    		aod.put(ancestor, count - 1);
                    		
                    		ancestor = ancestor.getParentFile();
                    	}
                    }
            	}
        	}
        }
        
        if (aod != null) {
        	File ancestor = file.getParentFile();
        	
        	while (ancestor != null) {
        		Integer count = aod.get(ancestor);
        		
        		if (count != null) {
            		aod.put(ancestor, count - 1);
        		}
        		
        		ancestor = ancestor.getParentFile();
        	}
        }
	}
}
