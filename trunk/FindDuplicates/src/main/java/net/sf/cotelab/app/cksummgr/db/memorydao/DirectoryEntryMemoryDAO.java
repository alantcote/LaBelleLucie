/**
 * 
 */
package net.sf.cotelab.app.cksummgr.db.memorydao;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry;
import net.sf.cotelab.app.cksummgr.db.DirectoryEntry;
import net.sf.cotelab.app.cksummgr.db.DirectoryEntryDAO;

/**
 * A <tt>DirectoryEntryDAO</tt> backed by an in-memory table.
 * @author acote
 */
public class DirectoryEntryMemoryDAO implements DirectoryEntryDAO {
	/**
	 * The next candidate auto-generated ID.
	 */
	protected int nextId = 1;
	
	/**
	 * The backing store for the entries.
	 */
	protected HashMap<Integer, DirectoryEntry> table =
			new HashMap<Integer, DirectoryEntry>();
	
	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntryDAO#fetchEntriesByParentID(int)
	 */
	public Collection<DirectoryEntry> fetchEntriesByParentID(int parentID) {
		HashSet<DirectoryEntry> result = new HashSet<DirectoryEntry>();
		
		for (DirectoryEntry entry : table.values()) {
			if (parentID == entry.getParentID()) {
				DirectoryEntry dup = duplicate(entry);
				
				dup.resetDirtyFlags();
				result.add(dup);
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntryDAO#fetchEntryById(int)
	 */
	public DirectoryEntry fetchEntryById(int id) {
		DirectoryEntry result = table.get(id);
		
		if (result != null) {
			result = duplicate(result);
			result.resetDirtyFlags();
		}
		
		return result;
	}

	public DirectoryEntry fetchEntryByParentIDAndName(
			int parentID, String name) {
		DirectoryEntry result = null;
		
		for (DirectoryEntry entry : table.values()) {
			if ((parentID == entry.getParentID()) &&
					name.equals(entry.getName())) {
				result = duplicate(entry);
				
				result.resetDirtyFlags();
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntryDAO#insertEntry(net.cote.app.cksummgr.DirectoryEntry)
	 */
	public void insertEntry(DirectoryEntry entry) {
		int id = generateId();
		
		entry.setId(id);
		
		table.put(id, duplicate(entry));
		entry.resetDirtyFlags();
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntryDAO#newEntry()
	 */
	public DirectoryEntry newEntry() {
		DirectoryEntry result = new DefaultDirectoryEntry(this);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntryDAO#removeEntry(net.cote.app.cksummgr.DirectoryEntry)
	 */
	public void removeEntry(DirectoryEntry entry) {
		if (entry.isDirectory()) {
			Collection<DirectoryEntry> kids =
					fetchEntriesByParentID(entry.getId());
			
			for (DirectoryEntry kid : kids) {
				removeEntry(kid);
			}
		}
		
		table.remove(entry.getId());
		
		entry.setId(0);
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntryDAO#updateEntry(net.cote.app.cksummgr.DirectoryEntry)
	 */
	public void updateEntry(DirectoryEntry entry) {
		DirectoryEntry row = table.get(entry.getId());
		
		row.setChecksum(entry.getChecksum());
		row.setDirectory(entry.isDirectory());
		row.setLastModifiedTime(entry.getLastModifiedTime());
		row.setName(entry.getName());
		row.setOther(entry.isOther());
		row.setParentID(entry.getParentID());
		row.setRegularFile(entry.isRegularFile());
		row.setSize(entry.getSize());
		row.setSymbolicLink(entry.isSymbolicLink());

		entry.resetDirtyFlags();
	}

	protected DirectoryEntry duplicate(DirectoryEntry entry) {
		DirectoryEntry result = newEntry();
		
		result.setChecksum(entry.getChecksum());
		result.setDirectory(entry.isDirectory());
		result.setId(entry.getId());
		result.setLastModifiedTime(entry.getLastModifiedTime());
		result.setName(entry.getName());
		result.setOther(entry.isOther());
		result.setParentID(entry.getParentID());
		result.setRegularFile(entry.isRegularFile());
		result.setSize(entry.getSize());
		result.setSymbolicLink(entry.isSymbolicLink());
		
		return result;
	}

	/**
	 * Generate an unused ID.
	 * @return the ID.
	 */
	protected int generateId() {
		int id = nextId;
		
		while (table.containsKey(id)) {
			id = incrementId(id);
		}
		
		nextId = incrementId(id);
		
		return id;
	}

	/**
	 * Increment the given id, cycling through the positive integers.
	 * @param id the provided id.
	 * @return the incremented id.
	 */
	protected int incrementId(int id) {
		if (id < 1) {
			id = 1;
		}
		
		if (id == Integer.MAX_VALUE) {
			id = 1;
		} else {
			++id;
		}
		
		return id;
	}
}
