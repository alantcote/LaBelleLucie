/**
 * 
 */
package net.sf.cotelab.app.cksummgr.db;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;

import net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry;
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
public class DefaultDirectoryEntryTest extends jMockTestHelper {
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

	protected DirectoryEntryDAO directoryEntryDAO = null;
	protected DefaultDirectoryEntry fixture = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		directoryEntryDAO = context.mock(DirectoryEntryDAO.class);
		fixture = new DefaultDirectoryEntry(directoryEntryDAO);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		fixture = null;
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#DefaultDirectoryEntry()}.
	 */
	@Test
	public void testDefaultDirectoryEntry() {
		assertEquals(directoryEntryDAO, fixture.directoryEntryDAO);
		assertFalse(fixture.checksumDirty);
		assertFalse(fixture.idDirty);
		assertFalse(fixture.lastModifiedTimeDirty);
		assertFalse(fixture.nameDirty);
		assertFalse(fixture.parentIDDirty);
		assertFalse(fixture.sizeDirty);
		assertFalse(fixture.directoryDirty);
		assertFalse(fixture.otherDirty);
		assertFalse(fixture.regularFileDirty);
		assertFalse(fixture.symbolicLinkDirty);
	}

	@Test
	public void testFetchChildEntries() {
		final int parentID = 3;
		final DefaultDirectoryEntry childDE =
				new DefaultDirectoryEntry(directoryEntryDAO);
		final LinkedList<DirectoryEntry> deList =
				new LinkedList<DirectoryEntry>();
		Collection<DirectoryEntry> result = null;
		
		context.checking(new Expectations() {{
			oneOf(directoryEntryDAO).fetchEntriesByParentID(parentID);
			will(returnValue(deList));
		}});
		
		fixture.setId(parentID);
		deList.add(childDE);
		
		result = fixture.fetchChildEntries();
		
		assertNotNull(result);
		assertEquals(1, result.size());
	}
	
	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#getChecksum()}.
	 */
	@Test
	public void testGetChecksum() {
		long expected = 12345;
		long actual;
		
		fixture.checksum = expected;
		
		actual = fixture.getChecksum();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#getId()}.
	 */
	@Test
	public void testGetId() {
		int expected = 12345;
		int actual;
		
		fixture.id = expected;
		
		actual = fixture.getId();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#getLastModifiedTime()}.
	 */
	@Test
	public void testGetLastModifiedTime() {
		long expected = 12345;
		long actual;
		
		fixture.lastModifiedTime = expected;
		
		actual = fixture.getLastModifiedTime();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#getName()}.
	 */
	@Test
	public void testGetName() {
		String expected = "SampleName";
		String actual;
		
		fixture.name = expected;
		
		actual = fixture.getName();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#getParentID()}.
	 */
	@Test
	public void testGetParentID() {
		int expected = 12345;
		int actual;
		
		fixture.parentID = expected;
		
		actual = fixture.getParentID();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#getSize()}.
	 */
	@Test
	public void testGetSize() {
		long expected = 12345;
		long actual;
		
		fixture.size = expected;
		
		actual = fixture.getSize();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testInsert() {
		context.checking(new Expectations() {{
			oneOf(directoryEntryDAO).insertEntry(fixture);
		}});
		
		fixture.setName("name");
		fixture.insert();
	}

	@Test
	public void testInsertChild() {
		final int parentID = 3;
		final String childName = "child";
		final DefaultDirectoryEntry childDE =
				context.mock(DefaultDirectoryEntry.class);
		DirectoryEntry result = null;
		
		context.checking(new Expectations() {{
			oneOf(directoryEntryDAO).newEntry();
			will(returnValue(childDE));
			
			oneOf(childDE).setName(with(childName));
			
			oneOf(childDE).setParentID(with(parentID));
			
			oneOf(childDE).insert();
		}});
		
		fixture.setId(parentID);
		
		result = fixture.insertChild(childName);
		
		assertEquals(childDE, result);
	}
	
	@Test
	public void testIsChecksumDirty() {
		testSetChecksum();
		assertTrue(fixture.isChecksumDirty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#isDirectory()}.
	 */
	@Test
	public void testIsDirectory() {
		boolean expected = true;
		boolean actual;
		
		fixture.directory = expected;
		
		actual = fixture.isDirectory();
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.directory = expected;
		
		actual = fixture.isDirectory();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testIsDirectoryDirty() {
		testSetDirectory();
		assertTrue(fixture.isDirectoryDirty());
	}

	@Test
	public void testIsDirty() {
		fixture.checksumDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.idDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.lastModifiedTimeDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.nameDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.parentIDDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.sizeDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.directoryDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.otherDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.regularFileDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
		
		fixture.symbolicLinkDirty = true;
		assertTrue(fixture.isDirty());
		fixture.resetDirtyFlags();
	}

	@Test
	public void testIsIdDirty() {
		testSetId();
		assertTrue(fixture.isIdDirty());
	}

	@Test
	public void testIsLastModifiedTimeDirty() {
		testSetLastModifiedTime();
		assertTrue(fixture.isLastModifiedTimeDirty());
	}

	@Test
	public void testIsNameDirty() {
		testSetName();
		assertTrue(fixture.isNameDirty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#isOther()}.
	 */
	@Test
	public void testIsOther() {
		boolean expected = true;
		boolean actual;
		
		fixture.other = expected;
		
		actual = fixture.isOther();
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.other = expected;
		
		actual = fixture.isOther();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testIsOtherDirty() {
		testSetOther();
		assertTrue(fixture.isOtherDirty());
	}

	@Test
	public void testIsParentIDDirty() {
		testSetParentID();
		assertTrue(fixture.isParentIDDirty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#isRegularFile()}.
	 */
	@Test
	public void testIsRegularFile() {
		boolean expected = true;
		boolean actual;
		
		fixture.regularFile = expected;
		
		actual = fixture.isRegularFile();
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.regularFile = expected;
		
		actual = fixture.isRegularFile();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testIsRegularFileDirty() {
		testSetRegularFile();
		assertTrue(fixture.isRegularFileDirty());
	}

	@Test
	public void testIsSizeDirty() {
		testSetSize();
		assertTrue(fixture.isSizeDirty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#isSymbolicLink()}.
	 */
	@Test
	public void testIsSymbolicLink() {
		boolean expected = true;
		boolean actual;
		
		fixture.symbolicLink = expected;
		
		actual = fixture.isSymbolicLink();
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.symbolicLink = expected;
		
		actual = fixture.isSymbolicLink();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testIsSymbolicLinkDirty() {
		testSetSymbolicLink();
		assertTrue(fixture.isSymbolicLinkDirty());
	}

	@Test
	public void testRemove() {
		context.checking(new Expectations() {{
			oneOf(directoryEntryDAO).insertEntry(fixture);
			oneOf(directoryEntryDAO).removeEntry(fixture);
		}});
		
		fixture.setName("name");
		fixture.insert();
		fixture.remove();
	}

	@Test
	public void testResetDirtyFlags() {
		testSetChecksum();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isChecksumDirty());
		
		testSetDirectory();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isDirectoryDirty());
		
		testSetId();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isIdDirty());
		
		testSetLastModifiedTime();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isLastModifiedTimeDirty());
		
		testSetName();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isNameDirty());
		
		testSetOther();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isOtherDirty());
		
		testSetParentID();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isParentIDDirty());
		
		testSetRegularFile();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isRegularFileDirty());
		
		testSetSize();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isSizeDirty());
		
		testSetSymbolicLink();
		fixture.resetDirtyFlags();
		assertTrue(!fixture.isSymbolicLinkDirty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setChecksum(long)}.
	 */
	@Test
	public void testSetChecksum() {
		long expected = 12345;
		long actual;
		
		fixture.setChecksum(expected);
		
		actual = fixture.checksum;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setDirectory(boolean)}.
	 */
	@Test
	public void testSetDirectory() {
		boolean expected = true;
		boolean actual;
		
		fixture.setDirectory(expected);
		
		actual = fixture.directory;
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.setDirectory(expected);
		
		actual = fixture.directory;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setId(int)}.
	 */
	@Test
	public void testSetId() {
		int expected = 12345;
		int actual;
		
		fixture.setId(expected);
		
		actual = fixture.id;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setLastModifiedTime(long)}.
	 */
	@Test
	public void testSetLastModifiedTime() {
		long expected = 12345;
		long actual;
		
		fixture.setLastModifiedTime(expected);
		
		actual = fixture.lastModifiedTime;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		String expected = "SampleName";
		String actual;
		
		fixture.setName(expected);
		
		actual = fixture.name;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setOther(boolean)}.
	 */
	@Test
	public void testSetOther() {
		boolean expected = true;
		boolean actual;
		
		fixture.setOther(expected);
		
		actual = fixture.other;
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.setOther(expected);
		
		actual = fixture.other;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setParentID(int)}.
	 */
	@Test
	public void testSetParentID() {
		int expected = 12345;
		int actual;
		
		fixture.setParentID(expected);
		
		actual = fixture.parentID;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setRegularFile(boolean)}.
	 */
	@Test
	public void testSetRegularFile() {
		boolean expected = true;
		boolean actual;
		
		fixture.setRegularFile(expected);
		
		actual = fixture.regularFile;
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.setRegularFile(expected);
		
		actual = fixture.regularFile;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setSize(long)}.
	 */
	@Test
	public void testSetSize() {
		long expected = 12345;
		long actual;
		
		fixture.setSize(expected);
		
		actual = fixture.size;
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry#setSymbolicLink(boolean)}.
	 */
	@Test
	public void testSetSymbolicLink() {
		boolean expected = true;
		boolean actual;
		
		fixture.setSymbolicLink(expected);
		
		actual = fixture.symbolicLink;
		
		assertEquals(expected, actual);
		
		expected = false;
		fixture.setSymbolicLink(expected);
		
		actual = fixture.symbolicLink;
		
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdate() {
		context.checking(new Expectations() {{
			oneOf(directoryEntryDAO).insertEntry(fixture);
			oneOf(directoryEntryDAO).updateEntry(fixture);
			oneOf(directoryEntryDAO).updateEntry(fixture);
		}});
		
		fixture.setName("name");
		fixture.insert();
		fixture.setName("name2");
		fixture.update();
		fixture.update(); // dao.updateEntry not called as fixture not dirty
		fixture.setName("name2");
		fixture.update(); // dao.updateEntry not called as fixture not dirty
		fixture.setName("name1");
		fixture.update(); // dao.updateEntry called because fixture is dirty
	}
}
