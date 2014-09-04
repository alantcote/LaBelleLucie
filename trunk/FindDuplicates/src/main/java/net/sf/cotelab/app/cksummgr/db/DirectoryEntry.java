package net.sf.cotelab.app.cksummgr.db;

import java.util.Collection;

/**
 * An entry in a directory.
 * @author acote
 */
public interface DirectoryEntry {
	/**
	 * Retrieve the children of this entry.
	 * @return the children of this entry.
	 */
	public abstract Collection<DirectoryEntry> fetchChildEntries();
	
	/**
	 * This entry's checksum.
	 * @return the checksum
	 */
	public abstract long getChecksum();

	/**
	 * The id is this entry's identifier.
	 * @return the id
	 */
	public abstract int getId();
	
	/**
	 * The time of last modification of this entry.
	 * @return the lastModifiedTime
	 */
	public abstract long getLastModifiedTime();

	/**
	 * The farthest element from the root in this entry's directory hierarchy.
	 * @return the name
	 */
	public abstract String getName();
	
	/**
	 * This entry's parent directory id. The value <tt>0</tt> denotes that this
	 * entry is the root of a filesystem.
	 * @return the parentID
	 */
	public abstract int getParentID();
	
	/**
	 * The size of this entry's file, in bytes.
	 * @return the size
	 */
	public abstract long getSize();
	
	/**
	 * Insert this entry into the backing store. Clears the dirty flags.
	 */
	public abstract void insert();

	/**
	 * Insert a new entry with a given name and this entry as parent into the
	 * backing store. Clears the new entry's dirty flags.
	 * @param childName the name given for the new entry.
	 * @return the new entry.
	 */
	public abstract DirectoryEntry insertChild(String childName);
	
	/**
	 * @return <tt>true</tt> iff checksum has changed.
	 */
	public abstract boolean isChecksumDirty();
	
	/**
	 * Tells whether this entry is a directory.
	 * @return the directory
	 */
	public abstract boolean isDirectory();

	/**
	 * @return <tt>true</tt> iff directory changed.
	 */
	public abstract boolean isDirectoryDirty();
	
	/**
	 * @return <tt>true</tt> iff any persistent field changed.
	 */
	public abstract boolean isDirty();

	/**
	 * @return <tt>true</tt> iff id has changed.
	 */
	public abstract boolean isIdDirty();
	
	/**
	 * @return <tt>true</tt> iff lastModifiedTime has changed.
	 */
	public abstract boolean isLastModifiedTimeDirty();

	/**
	 * @return <tt>true</tt> iff name has changed.
	 */
	public abstract boolean isNameDirty();
	
	/**
	 * Tells whether this entry is something other than a regular file,
	 * directory, or symbolic link.
	 * @return the other
	 */
	public abstract boolean isOther();

	/**
	 * @return <tt>true</tt> iff other changed.
	 */
	public abstract boolean isOtherDirty();
	
	/**
	 * @return <tt>true</tt> iff parentID has changed.
	 */
	public abstract boolean isParentIDDirty();

	/**
	 * Tells whether this entry is a regular file with opaque content.
	 * @return the regularFile
	 */
	public abstract boolean isRegularFile();
	
	/**
	 * @return <tt>true</tt> iff regularFile changed.
	 */
	public abstract boolean isRegularFileDirty();

	/**
	 * @return <tt>true</tt> iff size has changed.
	 */
	public abstract boolean isSizeDirty();
	
	/**
	 * Tells whether this entry is a symbolic link.
	 * @return the symbolicLink
	 */
	public abstract boolean isSymbolicLink();

	/**
	 * @return <tt>true</tt> iff symbolicLink changed.
	 */
	public abstract boolean isSymbolicLinkDirty();
	
	/**
	 * Delete the backing store version of this object. If this entry is a
	 * directory, first delete this entry's children.
	 */
	public abstract void remove();
	
	/**
	 * Reset (set <tt>false</tt>) all of the dirty flags.
	 */
	public abstract void resetDirtyFlags();

	/**
	 * This entry's checksum.
	 */
	public abstract void setChecksum(long checksum);

	/**
	 * Tells whether this entry is a directory.
	 * @param directory the directory to set
	 */
	public abstract void setDirectory(boolean directory);

	/**
	 * The id is this entry's identifier.
	 * @param id the id to set
	 */
	public abstract void setId(int id);

	/**
	 * The time of last modification of this entry.
	 * @param lastModifiedTime the lastModifiedTime to set
	 */
	public abstract void setLastModifiedTime(long lastModifiedTime);

	/**
	 * The farthest element from the root in this entry's directory hierarchy.
	 * @param name the name to set
	 */
	public abstract void setName(String name);

	/**
	 * Tells whether this entry is something other than a regular file,
	 * directory, or symbolic link.
	 * @param other the other to set
	 */
	public abstract void setOther(boolean other);

	/**
	 * This entry's parent directory id. The value <tt>0</tt> denotes that this
	 * entry is the root of a filesystem.
	 * @param parentID the parentID to set
	 */
	public abstract void setParentID(int parentID);

	/**
	 * Tells whether this entry is a regular file with opaque content.
	 * @param regularFile the regularFile to set
	 */
	public abstract void setRegularFile(boolean regularFile);

	/**
	 * The size of this entry's file, in bytes.
	 * @param size the size to set
	 */
	public abstract void setSize(long size);

	/**
	 * Tells whether this entry is a symbolic link.
	 * @param symbolicLink the symbolicLink to set
	 */
	public abstract void setSymbolicLink(boolean symbolicLink);

	/**
	 * Update the backing store version of any fields flagged as dirty. Clears
	 * the dirty flags. If this entry ceases to be a directory because of this
	 * update, first delete this entry's children.
	 */
	public abstract void update();
}