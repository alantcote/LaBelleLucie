/**
 * 
 */
package net.sf.cotelab.app.cksummgr;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.cotelab.app.cksummgr.db.DirectoryEntry;

/**
 * @author acote
 */
public class PersistentDirectory {
	protected DirectoryEntry directoryEntry = null;
	protected Map<String, DirectoryEntry> entries = null;
	protected Set<DirectoryEntry> visitedEntries =
			new HashSet<DirectoryEntry>();

	public PersistentDirectory(DirectoryEntry directoryEntry) {
		super();
		
		this.directoryEntry = directoryEntry;
	}

	/**
	 * Create a new entry in the database and <tt>entries</tt> with the given
	 * name and <tt>directoryEntry.getId()</tt> as its parent ID.
	 * @param name the given name.
	 * @return the new entry.
	 */
	public DirectoryEntry addEntry(String name) {
		DirectoryEntry newDE = directoryEntry.insertChild(name);
		
		if (entries == null) {
			loadEntries();
		}
		
		entries.put(name, newDE);
		
		return newDE;
	}

	public DirectoryEntry getEntryByName(String name) {
		DirectoryEntry result = null;
		
		if (entries == null) {
			loadEntries();
		}
		
		result = entries.get(name);
		
		return result;
	}
	
	/**
	 * @param entry
	 */
	public void removeEntry(DirectoryEntry entry) {
		entries.remove(entry.getName());
		entry.remove();
	}

	/**
	 * Remove all child entries that have not been marked as visited from this
	 * object's <tt>entries</tt> cache and the persistent store.
	 */
	public void removeLonesomeEntries() {
		Set<DirectoryEntry> lonesomeEntries = new HashSet<DirectoryEntry>();
		
		if (entries == null) {
			loadEntries();
		}
		
		for (DirectoryEntry entry : entries.values()) {
			if (!visitedEntries.contains(entry)) {
				lonesomeEntries.add(entry);
			}
		}
		
		for (DirectoryEntry entry : lonesomeEntries) {
			removeEntry(entry);
		}
	}

	public void setVisited(DirectoryEntry de) {
		visitedEntries.add(de);
	}

	protected void loadEntries() {
		Collection<DirectoryEntry> entryColl =
				directoryEntry.fetchChildEntries();
		
		entries = new HashMap<String, DirectoryEntry>();
		
		for (DirectoryEntry entry : entryColl) {
			entries.put(entry.getName(), entry);
		}
	}
}
