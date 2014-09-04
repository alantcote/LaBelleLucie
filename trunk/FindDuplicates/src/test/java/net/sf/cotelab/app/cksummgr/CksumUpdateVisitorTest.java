/**
 * 
 */
package net.sf.cotelab.app.cksummgr;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.logging.Logger;

import net.sf.cotelab.app.cksummgr.CksumUpdateVisitor;
import net.sf.cotelab.app.cksummgr.CksumUpdateVisitorException;
import net.sf.cotelab.app.cksummgr.PersistentDirectory;
import net.sf.cotelab.app.cksummgr.db.DirectoryEntry;
import net.sf.cotelab.app.cksummgr.db.DirectoryEntryDAO;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author acote
 */
public class CksumUpdateVisitorTest extends jMockTestHelper {
	public static final long DUMMY_FILE_CKSUM = 3695260310L;
	public static final int DUMMY_FILE_LEN = 7 + (1024 * 1024);
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	protected DirectoryEntryDAO dao = null;

	protected CksumUpdateVisitor fixture = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = context.mock(DirectoryEntryDAO.class);
		fixture = new CksumUpdateVisitor(dao);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testApplyAttrs() {
		/* We test applyAttrs(DirectoryEntry de, BasicFileAttributes attrs) to
		 * verify that the following are propagated into de from attrs:
		 * isDirectory()
		 * isOther()
		 * isRegularFile()
		 * isSymbolicLink()
		 * lastModifiedTime()
		 * size()
		 */
		
		final DirectoryEntry de = context.mock(DirectoryEntry.class);
		final BasicFileAttributes attrs =
				context.mock(BasicFileAttributes.class);
		final long timeValue = 12345;
		final FileTime fileTime = FileTime.fromMillis(timeValue);
		final long size = 54321;
		
		context.checking(new Expectations() {{
			// check a directory
			oneOf(attrs).isDirectory();
			will(returnValue(true));
			oneOf(de).setDirectory(true);

			oneOf(attrs).isOther();
			will(returnValue(false));
			oneOf(de).setOther(false);

			oneOf(attrs).isRegularFile();
			will(returnValue(false));
			oneOf(de).setRegularFile(false);

			oneOf(attrs).isSymbolicLink();
			will(returnValue(false));
			oneOf(de).setSymbolicLink(false);

			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTime));
			oneOf(de).setLastModifiedTime(timeValue);

			oneOf(attrs).size();
			will(returnValue(size));
			oneOf(de).setSize(size);

			// check an other
			oneOf(attrs).isDirectory();
			will(returnValue(false));
			oneOf(de).setDirectory(false);

			oneOf(attrs).isOther();
			will(returnValue(true));
			oneOf(de).setOther(true);

			oneOf(attrs).isRegularFile();
			will(returnValue(false));
			oneOf(de).setRegularFile(false);

			oneOf(attrs).isSymbolicLink();
			will(returnValue(false));
			oneOf(de).setSymbolicLink(false);

			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTime));
			oneOf(de).setLastModifiedTime(timeValue);

			oneOf(attrs).size();
			will(returnValue(size));
			oneOf(de).setSize(size);

			// check a regular file
			oneOf(attrs).isDirectory();
			will(returnValue(false));
			oneOf(de).setDirectory(false);

			oneOf(attrs).isOther();
			will(returnValue(false));
			oneOf(de).setOther(false);

			oneOf(attrs).isRegularFile();
			will(returnValue(true));
			oneOf(de).setRegularFile(true);

			oneOf(attrs).isSymbolicLink();
			will(returnValue(false));
			oneOf(de).setSymbolicLink(false);

			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTime));
			oneOf(de).setLastModifiedTime(timeValue);

			oneOf(attrs).size();
			will(returnValue(size));
			oneOf(de).setSize(size);

			// check a symbolic link
			oneOf(attrs).isDirectory();
			will(returnValue(false));
			oneOf(de).setDirectory(false);

			oneOf(attrs).isOther();
			will(returnValue(false));
			oneOf(de).setOther(false);

			oneOf(attrs).isRegularFile();
			will(returnValue(false));
			oneOf(de).setRegularFile(false);

			oneOf(attrs).isSymbolicLink();
			will(returnValue(true));
			oneOf(de).setSymbolicLink(true);

			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTime));
			oneOf(de).setLastModifiedTime(timeValue);

			oneOf(attrs).size();
			will(returnValue(size));
			oneOf(de).setSize(size);
		}});
		
		fixture.applyAttrs(de, attrs); // check a directory
		fixture.applyAttrs(de, attrs); // check an other
		fixture.applyAttrs(de, attrs); // check a regular file
		fixture.applyAttrs(de, attrs); // check a symbolic link
	}
	
	@Test
	public void testCalculateChecksum() throws IOException {
		final Path path = context.mock(Path.class);
		final ByteArrayInputStream bais =
				new ByteArrayInputStream(createDummyFileInBuffer());
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			protected BufferedInputStream openPath(Path aPath) {
				BufferedInputStream bis = new BufferedInputStream(bais);
				
				assertEquals(path, aPath);
				
				return bis;
			}
		};
		long cksum;
		
//		context.checking(new Expectations() {{
//		}});
		
		cksum = mockedFixture.calculateChecksum(path);
		
		assertEquals(DUMMY_FILE_CKSUM, cksum);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#CksumUpdateVisitor(net.sf.cotelab.app.cksummgr.db.DirectoryEntryDAO)}.
	 */
	@Test
	public void testCksumUpdateVisitor() {
		assertEquals(dao, fixture.dao);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#establishRoot(java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes)}.
	 * @throws IOException 
	 */
	@Test
	public void testEstablishRoot() throws IOException {
		final Path rawPath = context.mock(Path.class, "rawPath");
		final Path absolutePath = context.mock(Path.class, "absolutePath");
		final String rootPathString = "root directory";
		// jMock ignores calls on Object-defined methods --> use real Path
		final Path rootPath = (new File(rootPathString)).toPath();
		final DirectoryEntry rootDirectoryEntry =
				context.mock(DirectoryEntry.class);
		BasicFileAttributes attrs = context.mock(BasicFileAttributes.class);
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#requireRoot(java.nio.file.Path)
			 */
			@Override
			protected void requireRoot(Path root) throws IOException {
				// NOTHING
			}
		};
		
		context.checking(new Expectations() {{
			oneOf(rawPath).toAbsolutePath();
			will(returnValue(absolutePath));

			oneOf(absolutePath).getRoot();
			will(returnValue(rootPath));
			
			// jMock ignores calls on Object-defined methods
//			oneOf(rootPath).toString();
//			will(returnValue(rootPathString));
			
			oneOf(dao).fetchEntryByParentIDAndName(0, rootPathString);
			will(returnValue(rootDirectoryEntry));
		}});
		
		assertNull(mockedFixture.parentDirectory);
		
		mockedFixture.establishRoot(rawPath, attrs);
		
		assertNotNull(mockedFixture.parentDirectory);
		assertEquals(
				rootDirectoryEntry,
				mockedFixture.parentDirectory.directoryEntry);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#postVisitDirectory(java.nio.file.Path, java.io.IOException)}.
	 * @throws IOException 
	 */
	@Test
	public void testPostVisitDirectoryPathIOException() throws IOException {
		/* postVisitDirectory() is expected to . . .
		 * 1) remove all entries from parentDirectory that weren't visited.
		 * 2) parentDirectory = ancestors.pop().
		 */
		final PersistentDirectory pd =
				context.mock(PersistentDirectory.class, "pd");
		final PersistentDirectory tos =
				context.mock(PersistentDirectory.class, "tos");
		
		fixture.parentDirectory = pd;
		fixture.ancestors.push(tos);
		
		context.checking(new Expectations() {{
			oneOf(pd).removeLonesomeEntries();
		}});
		
		assertEquals(FileVisitResult.CONTINUE,
				fixture.postVisitDirectory(null, null));
		
		assertEquals(tos, fixture.parentDirectory);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#preVisitDirectory(java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes)}.
	 * @throws IOException 
	 */
	@Test
	public void testPreVisitDirectoryPathBasicFileAttributes()
			throws IOException {
		final Path rawPath = context.mock(Path.class, "rawPath");
		final Path absolutePath = context.mock(Path.class, "absolutePath");
		final String rootPathString = "root directory";
		// jMock ignores calls on Object-defined methods --> use real Path
		final Path rootPath = (new File(rootPathString)).toPath();
		final DirectoryEntry rootDirectoryEntry =
				context.mock(DirectoryEntry.class);
		BasicFileAttributes attrs = context.mock(BasicFileAttributes.class);
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#requireRoot(java.nio.file.Path)
			 */
			@Override
			protected void requireRoot(Path root) throws IOException {
				// NOTHING
			}
		};
		
		context.checking(new Expectations() {{
			// begin calls from establishRoot()
			oneOf(rawPath).toAbsolutePath();
			will(returnValue(absolutePath));

			oneOf(absolutePath).getRoot();
			will(returnValue(rootPath));
			
			// jMock ignores calls on Object-defined methods
//			oneOf(rootPath).toString();
//			will(returnValue(rootPathString));
			
			oneOf(dao).fetchEntryByParentIDAndName(0, rootPathString);
			will(returnValue(rootDirectoryEntry));
			// end calls from establishRoot()
		}});
		
		assertNull(mockedFixture.parentDirectory);
		
		assertEquals(FileVisitResult.CONTINUE,
				mockedFixture.preVisitDirectory(rawPath, attrs));
		
		assertNotNull(mockedFixture.parentDirectory);
		assertEquals(
				rootDirectoryEntry,
				mockedFixture.parentDirectory.directoryEntry);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#requireRoot(java.nio.file.Path)}.
	 * @throws IOException 
	 */
	@Test
	public void testRequireRoot() throws IOException {
		/* Some would argue that this is more appropriate to integration testing
		 * than it is to unit testing. There were 2 choices in deciding how to
		 * approach the problem of finding out whether a path is its own root:
		 * 1) Create (and use) a non-static version of
		 *    <tt>java.nio.file.Files</tt>, which could have a mocked
		 *    <tt>isSameFile()</tt>.
		 * 2) Create a <tt>requireRoot()</tt> method in
		 *    <tt>CksumUpdateVisitor</tt>, test it, and use an anonymous inner
		 *    class to provide mocked results in tests that might otherwise use
		 *    the real method.
		 * Option 2 seems simpler, so, by Occam's Razor, is the option chosen.
		 * Unfortunately, that means that <tt>requireRoot()</tt> must be tested
		 * with real (*not* mocked) data, to assure that it will work in real
		 * life.
		 */
		/* Path absPath = path.toAbsolutePath()
		 * if !Files.isSameFile(absPath, absPath.getRoot())
		 * then
		 *   throw new CksumUpdateVisitorException("not root path: " + path);
		 * endif
		 */
		Path root =
				FileSystems.getDefault().getRootDirectories().iterator().next();
		
		fixture.requireRoot(root);
		
		root = (new File("/tmp")).toPath();
		
		try {
			fixture.requireRoot(root);
			
			fail("requireRoot() failed to throw an exception");
		} catch (CksumUpdateVisitorException e) {
			// NOTHING; this is desired behavior
		}
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#visitFile(java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes)}.
	 */
	@Test
	public void testVisitFile() {
	/* There are 4 possible starting conditions here, suggesting 4 test methods:
	 * 1) parentDirectory has no entry by that name
	 * 2) parentDirectory has an entry by that name but attribs are wrong
	 * 3) parentDirectory has an entry by that name but date is stale
	 * 4) parentDirectory has an entry by that name and date is current
	 */
//		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.CksumUpdateVisitor#visitFileFailed(java.nio.file.Path, java.io.IOException)}.
	 * @throws IOException 
	 */
	@Test
	public void testVisitFileFailedPathIOException() throws IOException {
		/* log.warn(exc)
		 * 
		 * // any matching kidRow will be removed by postVisitDirectory()
		 * // because it has not been marked as visited.
		 * 
		 * return FileVisitResult.CONTINUE;
		 */
		final Logger log = context.mock(Logger.class);
		final String logMssg = "visit failed";
		final IOException ex = context.mock(IOException.class);
		
		fixture.log = log;
		
		context.checking(new Expectations() {{
			oneOf(ex).getMessage();
			will(returnValue(logMssg));
			
			oneOf(log).warning(logMssg);
		}});
		
		assertEquals(FileVisitResult.CONTINUE,
				fixture.visitFileFailed(null, ex));
	}
	
	@Test
	public void testVisitRegularFileCurrentDate() {
		/* This tests case 3 of visitRegularFile(de, file, attrs), where de is a
		 * regular file, but de's date is not older than attrs' date.
		 * 
		 * The test expects the fixture to update de with date, type, and size
		 * from attrs, and checksum from file.
		 */
		final DirectoryEntry de = context.mock(DirectoryEntry.class);
		final PersistentDirectory pd = context.mock(PersistentDirectory.class);
		final String fileName = "fileName";
		final BasicFileAttributes attrs =
				context.mock(BasicFileAttributes.class);
		final long lastModTimeOld = 123456;
		final FileTime fileTimeOld = FileTime.fromMillis(lastModTimeOld);
		final long ckSum = 13579;
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#calculateChecksum(java.nio.file.Path)
			 */
			@Override
			protected long calculateChecksum(Path path) throws IOException {
				return ckSum;
			}
		};
		Path file = new File(fileName).toPath();
		
		mockedFixture.parentDirectory = pd;
		
		context.checking(new Expectations() {{
			oneOf(de).isDirectory();
			will(returnValue(false));

			oneOf(de).isRegularFile();
			will(returnValue(true));
			
			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTimeOld));
			
			oneOf(de).getLastModifiedTime();
			will(returnValue(lastModTimeOld));
			
			oneOf(pd).setVisited(de);
		}});

		mockedFixture.visitRegularFile(de, file, attrs);
	}
	
	@Test
	public void testVisitRegularFileNeeDirectory() {
		/* This tests case 1 of visitRegularFile(de, file, attrs), where de is a
		 * directory.
		 * 
		 * The test expects the fixture to replace de with a new one with date,
		 * type, and size from attrs, and checksum from file.
		 */
		final DirectoryEntry de = context.mock(DirectoryEntry.class);
		final PersistentDirectory pd = context.mock(PersistentDirectory.class);
		final String fileName = "fileName";
		final BasicFileAttributes attrs =
				context.mock(BasicFileAttributes.class);
		final long lastModTimeOld = 123456;
		final FileTime fileTimeOld = FileTime.fromMillis(lastModTimeOld);
		final long theSize = 98765;
		final long ckSum = 13579;
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#calculateChecksum(java.nio.file.Path)
			 */
			@Override
			protected long calculateChecksum(Path path) throws IOException {
				return ckSum;
			}
		};
		Path file = new File(fileName).toPath();
		
		mockedFixture.parentDirectory = pd;
		
		context.checking(new Expectations() {{
			oneOf(de).isDirectory();
			will(returnValue(true));
			
			oneOf(pd).removeEntry(de);
			
			oneOf(de).getName();
			will(returnValue(fileName));
			
			oneOf(pd).addEntry(fileName);
			will(returnValue(de));
			
			oneOf(attrs).isDirectory();
			will(returnValue(false));
			
			oneOf(de).setDirectory(false);
			
			oneOf(attrs).isOther();
			will(returnValue(false));
			
			oneOf(de).setOther(false);
			
			oneOf(attrs).isRegularFile();
			will(returnValue(true));
			
			oneOf(de).setRegularFile(true);
			
			oneOf(attrs).isSymbolicLink();
			will(returnValue(false));
			
			oneOf(de).setSymbolicLink(false);
			
			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTimeOld));
			
			oneOf(de).setLastModifiedTime(lastModTimeOld);
			
			oneOf(attrs).size();
			will(returnValue(theSize));
			
			oneOf(de).setSize(theSize);
			
			oneOf(de).setChecksum(ckSum);
			
			oneOf(de).update();
			
			oneOf(pd).setVisited(de);
		}});

		mockedFixture.visitRegularFile(de, file, attrs);
	}
	
	@Test
	public void testVisitRegularFileNeeOther() {
		/* This tests case 5 of visitRegularFile(de, file, attrs), where de is
		 * not a directory, regular file, or symbolic link.
		 * 
		 * The test expects the fixture to replace de with a new one with date,
		 * type, and size from attrs, and checksum from file.
		 */
		final DirectoryEntry de = context.mock(DirectoryEntry.class);
		final PersistentDirectory pd = context.mock(PersistentDirectory.class);
		final String fileName = "fileName";
		final BasicFileAttributes attrs =
				context.mock(BasicFileAttributes.class);
		final long lastModTimeOld = 123456;
		final FileTime fileTimeOld = FileTime.fromMillis(lastModTimeOld);
		final long theSize = 98765;
		final long ckSum = 13579;
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#calculateChecksum(java.nio.file.Path)
			 */
			@Override
			protected long calculateChecksum(Path path) throws IOException {
				return ckSum;
			}
		};
		Path file = new File(fileName).toPath();
		
		mockedFixture.parentDirectory = pd;
		
		context.checking(new Expectations() {{
			oneOf(de).isDirectory();
			will(returnValue(false));

			oneOf(de).isRegularFile();
			will(returnValue(false));
			
			oneOf(attrs).isDirectory();
			will(returnValue(false));
			
			oneOf(de).setDirectory(false);
			
			oneOf(attrs).isOther();
			will(returnValue(true));
			
			oneOf(de).setOther(true);
			
			oneOf(attrs).isRegularFile();
			will(returnValue(false));
			
			oneOf(de).setRegularFile(false);
			
			oneOf(attrs).isSymbolicLink();
			will(returnValue(false));
			
			oneOf(de).setSymbolicLink(false);
			
			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTimeOld));
			
			oneOf(de).setLastModifiedTime(lastModTimeOld);
			
			oneOf(attrs).size();
			will(returnValue(theSize));
			
			oneOf(de).setSize(theSize);
			
			oneOf(de).setChecksum(ckSum);
			
			oneOf(de).update();
			
			oneOf(pd).setVisited(de);
		}});

		mockedFixture.visitRegularFile(de, file, attrs);
	}
	
	@Test
	public void testVisitRegularFileNeeSymLink() {
		/* This tests case 4 of visitRegularFile(de, file, attrs), where de is a
		 * symbolic link.
		 * 
		 * The test expects the fixture to replace de with a new one with date,
		 * type, and size from attrs, and checksum from file.
		 */
		final DirectoryEntry de = context.mock(DirectoryEntry.class);
		final PersistentDirectory pd = context.mock(PersistentDirectory.class);
		final String fileName = "fileName";
		final BasicFileAttributes attrs =
				context.mock(BasicFileAttributes.class);
		final long lastModTimeOld = 123456;
		final FileTime fileTimeOld = FileTime.fromMillis(lastModTimeOld);
		final long theSize = 98765;
		final long ckSum = 13579;
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#calculateChecksum(java.nio.file.Path)
			 */
			@Override
			protected long calculateChecksum(Path path) throws IOException {
				return ckSum;
			}
		};
		Path file = new File(fileName).toPath();
		
		mockedFixture.parentDirectory = pd;
		
		context.checking(new Expectations() {{
			oneOf(de).isDirectory();
			will(returnValue(false));

			oneOf(de).isRegularFile();
			will(returnValue(false));
			
			oneOf(attrs).isDirectory();
			will(returnValue(false));
			
			oneOf(de).setDirectory(false);
			
			oneOf(attrs).isOther();
			will(returnValue(false));
			
			oneOf(de).setOther(false);
			
			oneOf(attrs).isRegularFile();
			will(returnValue(false));
			
			oneOf(de).setRegularFile(false);
			
			oneOf(attrs).isSymbolicLink();
			will(returnValue(true));
			
			oneOf(de).setSymbolicLink(true);
			
			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTimeOld));
			
			oneOf(de).setLastModifiedTime(lastModTimeOld);
			
			oneOf(attrs).size();
			will(returnValue(theSize));
			
			oneOf(de).setSize(theSize);
			
			oneOf(de).setChecksum(ckSum);
			
			oneOf(de).update();
			
			oneOf(pd).setVisited(de);
		}});

		mockedFixture.visitRegularFile(de, file, attrs);
	}
	
	@Test
	public void testVisitRegularFileStaleDate() {
		/* This tests case 2 of visitRegularFile(de, file, attrs), where de is a
		 * regular file, but de's date is older than attrs' date.
		 * 
		 * The test expects the fixture to update de with date, type, and size
		 * from attrs, and checksum from file.
		 */
		final DirectoryEntry de = context.mock(DirectoryEntry.class);
		final PersistentDirectory pd = context.mock(PersistentDirectory.class);
		final String fileName = "fileName";
		final BasicFileAttributes attrs =
				context.mock(BasicFileAttributes.class);
		final long lastModTimeOld = 123456;
		final long theSize = 98765;
		final long ckSum = 13579;
		CksumUpdateVisitor mockedFixture = new CksumUpdateVisitor(dao) {
			/* (non-Javadoc)
			 * @see net.cote.app.cksummgr.CksumUpdateVisitor#calculateChecksum(java.nio.file.Path)
			 */
			@Override
			protected long calculateChecksum(Path path) throws IOException {
				return ckSum;
			}
		};
		Path file = new File(fileName).toPath();
		final long lastModTimeNew = lastModTimeOld + 5000;
		final FileTime fileTimeNew = FileTime.fromMillis(lastModTimeNew);
		
		mockedFixture.parentDirectory = pd;
		
		context.checking(new Expectations() {{
			oneOf(de).isDirectory();
			will(returnValue(false));

			oneOf(de).isRegularFile();
			will(returnValue(true));
			
			oneOf(attrs).lastModifiedTime();
			will(returnValue(fileTimeNew));
			
			oneOf(de).getLastModifiedTime();
			will(returnValue(lastModTimeOld));
			
			oneOf(de).setLastModifiedTime(lastModTimeNew);
			
			oneOf(attrs).size();
			will(returnValue(theSize));
			
			oneOf(de).setSize(theSize);
			
			oneOf(de).setChecksum(ckSum);
			
			oneOf(de).update();
			
			oneOf(pd).setVisited(de);
		}});

		mockedFixture.visitRegularFile(de, file, attrs);
	}
	
	protected byte[] createDummyFileInBuffer() {
		byte[] result = new byte[DUMMY_FILE_LEN];
		byte val = 0;
		
		for (int i = 0; i < DUMMY_FILE_LEN; ++i) {
			result[i] = val++;
		}
		
		return result;
	}
}
