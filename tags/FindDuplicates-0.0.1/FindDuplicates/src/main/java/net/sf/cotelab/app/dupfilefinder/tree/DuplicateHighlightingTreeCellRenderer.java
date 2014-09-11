package net.sf.cotelab.app.dupfilefinder.tree;

import java.awt.Color;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.Icon;

public class DuplicateHighlightingTreeCellRenderer extends
		CachedFileTreeCellRenderer {
	public static final Color ACCESS_DENIED_COLOR = Color.red;
	public static final Color ANCESTOR_COLOR = Color.cyan.darker();
	public static final Color DUPLICATE_COLOR = Color.blue;
	
	/**
     * Serialization support
     */
    private static final long serialVersionUID = 1L;
    
	protected HashSet<File> accessDeniedFiles;

	public DuplicateHighlightingTreeCellRenderer(
			HashMap<File, String> file2DisplayNameCache,
			HashMap<File, Icon> file2IconCache,
			Map<File, Collection<File>> file2EquivSetMap,
			HashMap<File, Integer> ancestorsOfDups, HashSet<File> accessDeniedFiles) {
		super(file2DisplayNameCache, file2IconCache, file2EquivSetMap,
				ancestorsOfDups);
		
		this.accessDeniedFiles = accessDeniedFiles;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.tree.CachedFileTreeCellRenderer#markDuplicates(java.io.File)
	 */
	@Override
	protected void markDuplicates(File fileValue) {
		setToolTipText(null);
		
		if (ancestorsOfDups != null) {
			File absFileValue = fileValue.getAbsoluteFile();
			Integer dupChildCount = ancestorsOfDups.get(absFileValue);
			
			if ((dupChildCount != null) && (0 < dupChildCount)) {
				markEntryAsAncestorOfDuplicate();
			}
		}
		
		if (accessDeniedFiles != null) {
			File absFileValue = fileValue.getAbsoluteFile();
			
			if (accessDeniedFiles.contains(absFileValue)) {
				markEntryAsAccessDenied();
			}
		}
		
		if (file2EquivSetMap != null) {
		    if (file2EquivSetMap.containsKey(fileValue)) {
		    	markEntryAsDuplicate(fileValue);
		    }
		}
	}

	protected void markEntryAsAccessDenied() {
		setForeground(ACCESS_DENIED_COLOR);
		setToolTipText("Access to this part of the filesystem was denied.");
	}

	protected void markEntryAsAncestorOfDuplicate() {
		setForeground(ANCESTOR_COLOR);
		setToolTipText(
				"At least one of this directory's descendants is a duplicate.");
	}

	protected void markEntryAsDuplicate(File fileValue) {
    	Collection<File> eqColl = file2EquivSetMap.get(fileValue);
    	String ttt = "<html><body><h3>Duplicates:</h3><ul>";
    	String sep = "<li>";
    	
    	for (File f : eqColl) {
    		if (!fileValue.equals(f)) {
    			ttt = ttt + sep + f.getAbsolutePath();
    			
    			sep = "</li><li>";
    		}
    	}
    	
    	ttt = ttt + "</li></ul></body></html>";
    	setToolTipText(ttt);

		setForeground(DUPLICATE_COLOR);
	}
}
