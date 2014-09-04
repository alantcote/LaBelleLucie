/**
 * 
 */
package net.sf.cotelab.app.cksummgr;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;

import net.sf.cotelab.app.cksummgr.db.DirectoryEntry;
import net.sf.cotelab.app.cksummgr.db.DirectoryEntryDAO;

/**
 * A <tt>FileVisitor</tt> that updates a database with current checksums for
 * visited files. The <tt>Path</tt> that is used as the first parameter to
 * <tt>Files.walkFileTree()</tt> when using an object of this class must denote
 * a root path (i.e., if <tt>p</tt> is the <tt>Path</tt>, then
 * <tt>Files.isSameFile(p, p.getRoot())</tt> must be <tt>true</tt>.
 * @author acote
 */
public class CksumUpdateVisitor extends SimpleFileVisitor<Path> {
	public static final int BLOCKSIZE = 2048;
	
	protected Stack<PersistentDirectory> ancestors =
			new Stack<PersistentDirectory>();
	protected DirectoryEntryDAO dao;
	protected Logger log = Logger.getLogger(CksumUpdateVisitor.class.getName());
	protected PersistentDirectory parentDirectory = null;
	
	public CksumUpdateVisitor(DirectoryEntryDAO dao) {
		super();
		
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#postVisitDirectory(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc)
			throws IOException {
		parentDirectory.removeLonesomeEntries();
		
		parentDirectory = ancestors.pop();
		
		return FileVisitResult.CONTINUE;
	}
	
	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#preVisitDirectory(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			throws IOException {
		if (parentDirectory == null) {
			establishRoot(dir, attrs);
		} else {
			String name = dir.getFileName().toString();
			DirectoryEntry newDE = parentDirectory.getEntryByName(name);
			
			if (newDE == null) {
				// no corresponding entry exists in DB; need to make one.
				newDE = parentDirectory.addEntry(name);
			} else if (!newDE.isDirectory()) {
				newDE.remove();

				newDE = parentDirectory.addEntry(name);
			}
			
			newDE.setDirectory(true);
			newDE.setLastModifiedTime(attrs.lastModifiedTime().toMillis());
			newDE.setSize(attrs.size());
			
			newDE.update();
			
			parentDirectory.setVisited(newDE);
			
			ancestors.push(parentDirectory);
			parentDirectory = new PersistentDirectory(newDE);
		}
		
		return FileVisitResult.CONTINUE;
	}
	
	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		// TODO Auto-generated method stub
		return super.visitFile(file, attrs);
		/* DB Entry | attrs | action(s)
		 * ---------+-------+---------------------------------------------------
		 * null     | reg   | new db entry w/date & type & cksum
		 * dir      | reg   | rm db entry; new db entry w/date & type & cksum
		 * reg      | reg   | if db entry date stale, update w/date & cksum
		 * sym      | reg   | update db entry type & date & cksum
		 * other    | reg   | update db entry type & date & cksum
		 * ---------+-------+---------------------------------------------------
		 * null     | sym   | new db entry w/date & type
		 * dir      | sym   | rm db entry; new db entry w/date & type
		 * reg      | sym   | update db entry type & date
		 * sym      | sym   | if db entry date stale, update db entry date
		 * other    | sym   | update db entry type & date
		 * ---------+-------+---------------------------------------------------
		 * null     | other | new db entry w/date & type
		 * dir      | other | rm db entry; new db entry w/date & type
		 * reg      | other | update db entry type & date
		 * sym      | other | update db entry type & date
		 * other    | other | if db entry date stale, update it
		 * ---------+-------+---------------------------------------------------
		 */
		/* DirectoryEntry newDE = parentDirectory.getEntryByName(file)
		 * 
		 * if (newDE == null) {
		 *   newDE = parentDirectory.addEntry(file.getFileName().toString());
		 *   applyAttrs(newDE, attrs);
		 *   newDE.update();
		 * }
		 * 
		 * if (attrs.isRegularFile()) {
		 *   visitRegularFile(newDE, file, attrs);
		 * } else if (attrs.isSymbolicLink()) {
		 *   visitSymbolicLink(newDE, file, attrs);
		 * } else if (attrs.isOther()) {
		 *   visitOther(newDE, file, attrs);
		 * } else {
		 *   log.log(Level.WARNING, "bad file type: " + file);
		 * }
		 * 
		 * parentDirectory.setVisited(newDE);
		 * 
		 * return FileVisitResult.CONTINUE;
		 */
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc)
			throws IOException {
		log.warning(exc.getMessage());
		
		// any matching persistent item will be removed in postVisitDirectory()
		// because it will not have been marked as visited.
		
		return FileVisitResult.CONTINUE;
	}

	protected void applyAttrs(DirectoryEntry de, BasicFileAttributes attrs) {
		de.setDirectory(attrs.isDirectory());
		de.setOther(attrs.isOther());
		de.setRegularFile(attrs.isRegularFile());
		de.setSymbolicLink(attrs.isSymbolicLink());
		de.setLastModifiedTime(attrs.lastModifiedTime().toMillis());
		de.setSize(attrs.size());
	}
	
	protected long calculateChecksum(Path path) throws IOException {
		CRC32 crc32 = new CRC32();
        BufferedInputStream bis = openPath(path);
        byte[] block = new byte[BLOCKSIZE];
        int biteSize = -2;
        
        while ((biteSize = bis.read(block)) > 0) {
            crc32.update(block, 0, biteSize);
        }
        
        bis.close();
        
        return crc32.getValue();
	}
	
	protected void establishRoot(Path dir, BasicFileAttributes attrs) throws IOException {
		/* Path absDir = dir.toAbsolutePath()
		 * if !Files.isSameFile(absDir, absDir.getRoot())
		 * then
		 *   throw new CksumUpdateVisitorException("not root path: " + dir);
		 * endif
		 * 
		 * Look in the db for a DirectoryEntry corresponding to absDir.
		 * if such a thing exists, wrap it in a PersistentDirectory and set it
		 * as parentDirectory. Else create a suitable DirectoryEntry, insert it
		 * into the db, wrap it as a PersistentDirectory, and set it as
		 * parentDirectory.
		 */
		Path absPath = dir.toAbsolutePath();
		Path rootPath = absPath.getRoot();
		String rootPathName = null;
		DirectoryEntry newDE = null;
		
		requireRoot(dir);
		
		rootPathName = rootPath.toString();
		newDE = dao.fetchEntryByParentIDAndName(0, rootPathName);
		
		if (newDE == null) {
			newDE = dao.newEntry();
			
			newDE.setDirectory(true);
			newDE.setLastModifiedTime(attrs.lastModifiedTime().toMillis());
			newDE.setName(rootPathName);
			newDE.setParentID(0);
			newDE.setSize(attrs.size());
			
			newDE.insert();
		}
		
		parentDirectory = new PersistentDirectory(newDE);
	}

	protected BufferedInputStream openPath(Path aPath) throws IOException {
		InputStream is = Files.newInputStream(aPath, new OpenOption[0]);
		BufferedInputStream bis = new BufferedInputStream(is);
		
		return bis;
	}

	protected void requireRoot(Path root) throws IOException {
		Path absPath = root.toAbsolutePath();
		
		if (!Files.isSameFile(absPath, absPath.getRoot())) {
			throw new CksumUpdateVisitorException("not root path: " + root);
		}
	}

	protected FileVisitResult visitRegularFile(
			DirectoryEntry de, Path file, BasicFileAttributes attrs) {
		/* There are 5 sub-cases here.
		 * 1. de is a directory: replace de with
		 *    a new one with date and type from attrs, and checksum from file.
		 * 2. de is a regular file and its date is before the attrs date: update
		 *    de with date from attrs and checksum from file.
		 * 3. de is a regular file and its date is at or beyond the attrs date:
		 *    no action required.
		 * 4. de is a symbolic link: update de with the date and type from
		 *    attrs and checksum from file.
		 * 5. de is an other: update de with the date and type from attrs and
		 *    checksum from file.
		 */
		
		if (de.isDirectory()) {
			// replace de with a new one with date and type from attrs and
			// checksum from file
			parentDirectory.removeEntry(de);
			de = parentDirectory.addEntry(de.getName());
			applyAttrs(de, attrs);
			
			try {
				de.setChecksum(calculateChecksum(file));
				de.update();
				
				parentDirectory.setVisited(de);
			} catch (IOException e) {
				// file is not readable
				// report the event; file will be removed from DB later
				log.log(Level.WARNING, "Failed to checksum " + file, e);
			}
		} else if (de.isRegularFile()) {
			long lmt = attrs.lastModifiedTime().toMillis();
			
			if (de.getLastModifiedTime() < lmt) {
				// update de with lmt and checksum from file
				de.setLastModifiedTime(lmt);
				de.setSize(attrs.size());
				
				try {
					de.setChecksum(calculateChecksum(file));
					de.update();
					
					parentDirectory.setVisited(de);
				} catch (IOException e) {
					// file is not readable
					// report the event; file will be removed from DB later
					log.log(Level.WARNING, "Failed to checksum " + file, e);
				}
			} else {
				parentDirectory.setVisited(de);
			}
		} else {
			// update de with date and type from attrs and checksum from file
			applyAttrs(de, attrs);
			
			try {
				de.setChecksum(calculateChecksum(file));
				de.update();
				
				parentDirectory.setVisited(de);
			} catch (IOException e) {
				// file is not readable
				// report the event; file will be removed from DB later
				log.log(Level.WARNING, "Failed to checksum " + file, e);
			}
		}
		
		return FileVisitResult.CONTINUE;
	}
}
