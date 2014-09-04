/**
 * 
 */
package net.sf.cotelab.app.cksummgr.db;

import java.util.Collection;


/**
 * Data Access Object for DirectoryEntry tables.
 * @author acote
 */
public interface DirectoryEntryDAO {
	/**
	 * Retrieve directory entries that have a given <tt>parentID</tt>.
	 * @param parentID the <tt>parentID</tt>.
	 * @return the matching entries.
	 */
	public abstract Collection<DirectoryEntry> fetchEntriesByParentID(
			int parentID);
	
	/**
	 * Retrieve the directory entry that has a given <tt>id</tt>.
	 * @param id the <tt>id</tt>.
	 * @return the matching entry.
	 */
	public abstract DirectoryEntry fetchEntryById(int id);
	
	/**
	 * Retrieve the directory entry that has a given <tt>parentID</tt> and
	 * <tt>name</tt>.
	 * @param parentID
	 * @param name
	 * @return the directory entry, if it exists, else <tt>null</tt>.
	 */
	public abstract DirectoryEntry fetchEntryByParentIDAndName(
			int parentID, String name);
	
	/**
	 * Insert a directory entry into the table. Auto-generates the entry's id,
	 * storing it back into the entry.
	 * @param entry
	 */
	public abstract void insertEntry(DirectoryEntry entry);
	
	/**
	 * Create a new directory entry with default field values. This method does
	 * not change table contents.
	 * @return the new object.
	 */
	public abstract DirectoryEntry newEntry();
	
	/**
	 * Remove (delete) a given directory entry from the table. As a side-effect,
	 * the entry's <tt>id</tt> is zeroed. If the table contains entries with the
	 * entry's <tt>id</tt> as <tt>parentID</tt>, those entries are removed, as
	 * well (recursively, as needed).
	 * @param entry the entry.
	 */
	public abstract void removeEntry(DirectoryEntry entry);
	
	/**
	 * Update a given directory entry's table columns to match the entry.
	 * @param entry the entry.
	 */
	public abstract void updateEntry(DirectoryEntry entry);
}
