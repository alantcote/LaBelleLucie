/**
 * 
 */
package net.sf.cotelab.util.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * A base class for decorating <code>java.io.File</code>s.
 * @author us80653h
 */
public class DecoratedFile extends File {
	/**
	 * Serialization support.
	 */
	private static final long serialVersionUID = 1L;
	
	protected File delegate = null;

	public DecoratedFile(File delegate) {
		super(""); // use empty string because no zero-arg constructor in super
		
		this.delegate = delegate;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// local methods
	////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the delegate
	 */
	public File getDelegate() {
		return delegate;
	}

	/**
	 * @param delegate the delegate to set
	 */
	public void setDelegate(File delegate) {
		this.delegate = delegate;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// delegation methods
	////////////////////////////////////////////////////////////////////////////

	/**
	 * @return
	 * @see java.io.File#canExecute()
	 */
	public boolean canExecute() {
		return delegate.canExecute();
	}

	/**
	 * @return
	 * @see java.io.File#canRead()
	 */
	public boolean canRead() {
		return delegate.canRead();
	}

	/**
	 * @return
	 * @see java.io.File#canWrite()
	 */
	public boolean canWrite() {
		return delegate.canWrite();
	}

	/**
	 * @param pathname
	 * @return
	 * @see java.io.File#compareTo(java.io.File)
	 */
	public int compareTo(File pathname) {
		return delegate.compareTo(pathname);
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.File#createNewFile()
	 */
	public boolean createNewFile() throws IOException {
		return delegate.createNewFile();
	}

	/**
	 * @return
	 * @see java.io.File#delete()
	 */
	public boolean delete() {
		return delegate.delete();
	}

	/**
	 * 
	 * @see java.io.File#deleteOnExit()
	 */
	public void deleteOnExit() {
		delegate.deleteOnExit();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.io.File#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	/**
	 * @return
	 * @see java.io.File#exists()
	 */
	public boolean exists() {
		return delegate.exists();
	}

	/**
	 * @return
	 * @see java.io.File#getAbsoluteFile()
	 */
	public File getAbsoluteFile() {
		return delegate.getAbsoluteFile();
	}

	/**
	 * @return
	 * @see java.io.File#getAbsolutePath()
	 */
	public String getAbsolutePath() {
		return delegate.getAbsolutePath();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.File#getCanonicalFile()
	 */
	public File getCanonicalFile() throws IOException {
		return delegate.getCanonicalFile();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.File#getCanonicalPath()
	 */
	public String getCanonicalPath() throws IOException {
		return delegate.getCanonicalPath();
	}

	/**
	 * @return
	 * @see java.io.File#getFreeSpace()
	 */
	public long getFreeSpace() {
		return delegate.getFreeSpace();
	}

	/**
	 * @return
	 * @see java.io.File#getName()
	 */
	public String getName() {
		return delegate.getName();
	}

	/**
	 * @return
	 * @see java.io.File#getParent()
	 */
	public String getParent() {
		return delegate.getParent();
	}

	/**
	 * @return
	 * @see java.io.File#getParentFile()
	 */
	public File getParentFile() {
		return delegate.getParentFile();
	}

	/**
	 * @return
	 * @see java.io.File#getPath()
	 */
	public String getPath() {
		return delegate.getPath();
	}

	/**
	 * @return
	 * @see java.io.File#getTotalSpace()
	 */
	public long getTotalSpace() {
		return delegate.getTotalSpace();
	}

	/**
	 * @return
	 * @see java.io.File#getUsableSpace()
	 */
	public long getUsableSpace() {
		return delegate.getUsableSpace();
	}

	/**
	 * @return
	 * @see java.io.File#hashCode()
	 */
	public int hashCode() {
		return delegate.hashCode();
	}

	/**
	 * @return
	 * @see java.io.File#isAbsolute()
	 */
	public boolean isAbsolute() {
		return delegate.isAbsolute();
	}

	/**
	 * @return
	 * @see java.io.File#isDirectory()
	 */
	public boolean isDirectory() {
		return delegate.isDirectory();
	}

	/**
	 * @return
	 * @see java.io.File#isFile()
	 */
	public boolean isFile() {
		return delegate.isFile();
	}

	/**
	 * @return
	 * @see java.io.File#isHidden()
	 */
	public boolean isHidden() {
		return delegate.isHidden();
	}

	/**
	 * @return
	 * @see java.io.File#lastModified()
	 */
	public long lastModified() {
		return delegate.lastModified();
	}

	/**
	 * @return
	 * @see java.io.File#length()
	 */
	public long length() {
		return delegate.length();
	}

	/**
	 * @return
	 * @see java.io.File#list()
	 */
	public String[] list() {
		return delegate.list();
	}

	/**
	 * @param filter
	 * @return
	 * @see java.io.File#list(java.io.FilenameFilter)
	 */
	public String[] list(FilenameFilter filter) {
		return delegate.list(filter);
	}

	/**
	 * @return
	 * @see java.io.File#listFiles()
	 */
	public File[] listFiles() {
		return delegate.listFiles();
	}

	/**
	 * @param filter
	 * @return
	 * @see java.io.File#listFiles(java.io.FileFilter)
	 */
	public File[] listFiles(FileFilter filter) {
		return delegate.listFiles(filter);
	}

	/**
	 * @param filter
	 * @return
	 * @see java.io.File#listFiles(java.io.FilenameFilter)
	 */
	public File[] listFiles(FilenameFilter filter) {
		return delegate.listFiles(filter);
	}

	/**
	 * @return
	 * @see java.io.File#mkdir()
	 */
	public boolean mkdir() {
		return delegate.mkdir();
	}

	/**
	 * @return
	 * @see java.io.File#mkdirs()
	 */
	public boolean mkdirs() {
		return delegate.mkdirs();
	}

	/**
	 * @param dest
	 * @return
	 * @see java.io.File#renameTo(java.io.File)
	 */
	public boolean renameTo(File dest) {
		return delegate.renameTo(dest);
	}

	/**
	 * @param executable
	 * @param ownerOnly
	 * @return
	 * @see java.io.File#setExecutable(boolean, boolean)
	 */
	public boolean setExecutable(boolean executable, boolean ownerOnly) {
		return delegate.setExecutable(executable, ownerOnly);
	}

	/**
	 * @param executable
	 * @return
	 * @see java.io.File#setExecutable(boolean)
	 */
	public boolean setExecutable(boolean executable) {
		return delegate.setExecutable(executable);
	}

	/**
	 * @param time
	 * @return
	 * @see java.io.File#setLastModified(long)
	 */
	public boolean setLastModified(long time) {
		return delegate.setLastModified(time);
	}

	/**
	 * @param readable
	 * @param ownerOnly
	 * @return
	 * @see java.io.File#setReadable(boolean, boolean)
	 */
	public boolean setReadable(boolean readable, boolean ownerOnly) {
		return delegate.setReadable(readable, ownerOnly);
	}

	/**
	 * @param readable
	 * @return
	 * @see java.io.File#setReadable(boolean)
	 */
	public boolean setReadable(boolean readable) {
		return delegate.setReadable(readable);
	}

	/**
	 * @return
	 * @see java.io.File#setReadOnly()
	 */
	public boolean setReadOnly() {
		return delegate.setReadOnly();
	}

	/**
	 * @param writable
	 * @param ownerOnly
	 * @return
	 * @see java.io.File#setWritable(boolean, boolean)
	 */
	public boolean setWritable(boolean writable, boolean ownerOnly) {
		return delegate.setWritable(writable, ownerOnly);
	}

	/**
	 * @param writable
	 * @return
	 * @see java.io.File#setWritable(boolean)
	 */
	public boolean setWritable(boolean writable) {
		return delegate.setWritable(writable);
	}

	/**
	 * @return
	 * @see java.io.File#toString()
	 */
	public String toString() {
		return delegate.toString();
	}

	/**
	 * @return
	 * @see java.io.File#toURI()
	 */
	public URI toURI() {
		return delegate.toURI();
	}

	/**
	 * @return
	 * @throws MalformedURLException
	 * @deprecated
	 * @see java.io.File#toURL()
	 */
	public URL toURL() throws MalformedURLException {
		return delegate.toURL();
	}
}
