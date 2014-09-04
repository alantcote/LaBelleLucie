/**
 * 
 */
package net.sf.cotelab.app.cksummgr.db;

import java.util.Collection;


/**
 * An entry in a directory.
 * @author acote
 */
public class DefaultDirectoryEntry implements DirectoryEntry {
	/**
	 * This entry's checksum.
	 */
	protected long checksum = 0;

	protected boolean checksumDirty;
	
	/**
	 * Tells whether this entry is a directory.
	 */
	protected boolean directory = false;
	
	protected boolean directoryDirty;
	
	protected DirectoryEntryDAO directoryEntryDAO;
	
	/**
	 * The id is this entry's identifier.
	 */
	protected int id = 0;
	
	protected boolean idDirty;
	
	/**
	 * The time of last modification of this entry.
	 */
	protected long lastModifiedTime = 0;
	
	protected boolean lastModifiedTimeDirty;
	
	/**
	 * The farthest element from the root in this entry's directory hierarchy.
	 */
	protected String name = null;

	protected boolean nameDirty;

	/**
	 * Tells whether this entry is something other than a regular file,
	 * directory, or symbolic link.
	 */
	protected boolean other = false;

	protected boolean otherDirty;

	/**
	 * This entry's parent directory id. The value <tt>0</tt> denotes that this
	 * entry is the root of a filesystem.
	 */
	protected int parentID = 0;

	protected boolean parentIDDirty;

	/**
	 * Tells whether this entry is a regular file with opaque content.
	 */
	protected boolean regularFile = false;

	protected boolean regularFileDirty;

	/**
	 * The size of this entry's file, in bytes.
	 */
	protected long size = 0;

	protected boolean sizeDirty;

	/**
	 * Tells whether this entry is a symbolic link.
	 */
	protected boolean symbolicLink = false;

	protected boolean symbolicLinkDirty;
	
	public DefaultDirectoryEntry(DirectoryEntryDAO directoryEntryDAO) {
		super();
		
		this.directoryEntryDAO = directoryEntryDAO;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if ((obj != null) && (obj instanceof DefaultDirectoryEntry)) {
			DefaultDirectoryEntry o = (DefaultDirectoryEntry) obj;
			
			result = checksum == o.checksum;
			result = result && directory == o.directory;
			result = result && id == o.id;
			result = result && lastModifiedTime == o.lastModifiedTime;
			result = result && ((name == o.name) || name.equals(o.name));
			result = result && other == o.other;
			result = result && parentID == o.parentID;
			result = result && regularFile == o.regularFile;
			result = result && size == o.size;
			result = result && symbolicLink == o.symbolicLink;
		}
		
		return result;
	}

	public Collection<DirectoryEntry> fetchChildEntries() {
		return directoryEntryDAO.fetchEntriesByParentID(id);
	}

	public long getChecksum() {
		return checksum;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#getId()
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#getLastModifiedTime()
	 */
	public long getLastModifiedTime() {
		return lastModifiedTime;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#getParentID()
	 */
	public int getParentID() {
		return parentID;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#getSize()
	 */
	public long getSize() {
		return size;
	}

	public void insert() {
		directoryEntryDAO.insertEntry(this);
	}

	public boolean isChecksumDirty() {
		return checksumDirty;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#isDirectory()
	 */
	public boolean isDirectory() {
		return directory;
	}

	public boolean isDirectoryDirty() {
		return directoryDirty;
	}

	public boolean isDirty() {
		boolean result =
				checksumDirty || idDirty || lastModifiedTimeDirty ||
				nameDirty || parentIDDirty || sizeDirty || directoryDirty ||
				otherDirty || regularFileDirty || symbolicLinkDirty;
		
		return result;
	}

	public boolean isIdDirty() {
		return idDirty;
	}

	public boolean isLastModifiedTimeDirty() {
		return lastModifiedTimeDirty;
	}

	public boolean isNameDirty() {
		return nameDirty;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#isOther()
	 */
	public boolean isOther() {
		return other;
	}

	public boolean isOtherDirty() {
		return otherDirty;
	}

	public boolean isParentIDDirty() {
		return parentIDDirty;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#isRegularFile()
	 */
	public boolean isRegularFile() {
		return regularFile;
	}

	public boolean isRegularFileDirty() {
		return regularFileDirty;
	}

	public boolean isSizeDirty() {
		return sizeDirty;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#isSymbolicLink()
	 */
	public boolean isSymbolicLink() {
		return symbolicLink;
	}

	public boolean isSymbolicLinkDirty() {
		return symbolicLinkDirty;
	}

	public void remove() {
		directoryEntryDAO.removeEntry(this);
	}

	public void resetDirtyFlags() {
		checksumDirty = false;
		idDirty = false;
		lastModifiedTimeDirty = false;
		nameDirty = false;
		parentIDDirty = false;
		sizeDirty = false;
		directoryDirty = false;
		otherDirty = false;
		regularFileDirty = false;
		symbolicLinkDirty = false;
	}

	public void setChecksum(long checksum) {
		if (this.checksum != checksum) {
			this.checksum = checksum;
			checksumDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setDirectory(boolean)
	 */
	public void setDirectory(boolean directory) {
		if (this.directory != directory) {
			this.directory = directory;
			directoryDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setId(int)
	 */
	public void setId(int id) {
		if (this.id != id) {
			this.id = id;
			idDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setLastModifiedTime(long)
	 */
	public void setLastModifiedTime(long lastModifiedTime) {
		if (this.lastModifiedTime != lastModifiedTime) {
			this.lastModifiedTime = lastModifiedTime;
			lastModifiedTimeDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setName(java.lang.String)
	 */
	public void setName(String name) {
		if ((this.name == null) || !this.name.equals(name)) {
			this.name = name;
			nameDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setOther(boolean)
	 */
	public void setOther(boolean other) {
		if (this.other != other) {
			this.other = other;
			otherDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setParentID(int)
	 */
	public void setParentID(int parentID) {
		if (this.parentID != parentID) {
			this.parentID = parentID;
			parentIDDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setRegularFile(boolean)
	 */
	public void setRegularFile(boolean regularFile) {
		if (this.regularFile != regularFile) {
			this.regularFile = regularFile;
			regularFileDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setSize(long)
	 */
	public void setSize(long size) {
		if (this.size != size) {
			this.size = size;
			sizeDirty = true;
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.app.cksummgr.db.DirectoryEntry#setSymbolicLink(boolean)
	 */
	public void setSymbolicLink(boolean symbolicLink) {
		if (this.symbolicLink != symbolicLink) {
			this.symbolicLink = symbolicLink;
			symbolicLinkDirty = true;
		}
	}

	public void update() {
		if (isDirty()) {
			directoryEntryDAO.updateEntry(this);
			this.resetDirtyFlags();
		}
	}

	public DirectoryEntry insertChild(String childName) {
		DirectoryEntry child = directoryEntryDAO.newEntry();
		
		child.setName(childName);
		child.setParentID(id);
		
		child.insert();
		
		return child;
	}
}
