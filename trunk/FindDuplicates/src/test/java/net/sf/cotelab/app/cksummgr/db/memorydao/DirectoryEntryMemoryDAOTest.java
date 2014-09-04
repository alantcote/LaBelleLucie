/**
 * 
 */
package net.sf.cotelab.app.cksummgr.db.memorydao;

import static org.junit.Assert.*;

import java.util.Collection;

import net.sf.cotelab.app.cksummgr.db.DefaultDirectoryEntry;
import net.sf.cotelab.app.cksummgr.db.DirectoryEntry;
import net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author acote
 */
public class DirectoryEntryMemoryDAOTest {
	protected DirectoryEntryMemoryDAO fixture = null;
	
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fixture = new DirectoryEntryMemoryDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		fixture = null;
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#fetchEntriesByParentID(int)}.
	 */
	@Test
	public void testFetchEntriesByParentID() {
		DefaultDirectoryEntry expected = new DefaultDirectoryEntry(fixture);
		int expectedId = 1;
		int expectedParentID = 0;
		Collection<DirectoryEntry> actuals = null;
		DirectoryEntry actual = null;
		
		expected.setId(expectedId);
		expected.setParentID(expectedParentID);
		fixture.table.put(expected.getId(), expected);
		
		actuals = fixture.fetchEntriesByParentID(expectedParentID);
		
		assertNotNull(actuals);
		assertEquals(1, actuals.size());
		
		actual = actuals.iterator().next();
		
		assertFalse(actual.isDirty());
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#fetchEntryById(int)}.
	 */
	@Test
	public void testFetchEntryById() {
		DefaultDirectoryEntry expected = new DefaultDirectoryEntry(fixture);
		int expectedId = 1;
		int expectedParentID = 0;
		DirectoryEntry actual = null;
		
		expected.setId(expectedId);
		expected.setParentID(expectedParentID);
		fixture.table.put(expected.getId(), expected);
		
		actual = fixture.fetchEntryById(expectedId);

		assertFalse(actual.isDirty());
		assertEquals(expected, actual);
	}
	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#fetchEntryByParentIDAndName(int, String)}.
	 */
	@Test
	public void testFetchEntryByParentIDAndName() {
		DefaultDirectoryEntry expected = new DefaultDirectoryEntry(fixture);
		int expectedId = 1;
		String expectedName = "name";
		int expectedParentID = 0;
		DirectoryEntry actual = null;
		
		expected.setId(expectedId);
		expected.setName(expectedName);
		expected.setParentID(expectedParentID);
		fixture.table.put(expected.getId(), expected);
		
		actual = fixture.fetchEntryByParentIDAndName(
				expectedParentID, expectedName);
		
		assertFalse(actual.isDirty());
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#insertEntry(net.sf.cotelab.app.cksummgr.db.DirectoryEntry)}.
	 */
	@Test
	public void testInsertEntry() {
		DefaultDirectoryEntry expected = new DefaultDirectoryEntry(fixture);
		int expectedId = 1;
		DirectoryEntry actual = null;
		
		fixture.insertEntry(expected);

		assertFalse(expected.isDirty());
		assertEquals(expectedId, expected.getId());
		
		actual = fixture.table.get(expected.getId());

		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#newEntry()}.
	 */
	@Test
	public void testNewEntry() {
		DirectoryEntry actual = fixture.newEntry();
		
		assertNotNull(actual);
		assertFalse(actual.isDirty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#removeEntry(net.sf.cotelab.app.cksummgr.db.DirectoryEntry)}.
	 */
	@Test
	public void testRemoveEntry() {
		DefaultDirectoryEntry expected = new DefaultDirectoryEntry(fixture);
		int expectedId = 1;
		int expectedParentID = 0;
		DefaultDirectoryEntry child = new DefaultDirectoryEntry(fixture);
		int childId = 2;
		int childParentID = expectedId;
		DefaultDirectoryEntry grandChild = new DefaultDirectoryEntry(fixture);
		int grandChildId = 3;
		int grandChildParentID = childId;
		
		expected.setDirectory(true);
		expected.setId(expectedId);
		expected.setParentID(expectedParentID);
		fixture.table.put(expectedId, expected);
		
		child.setDirectory(true);
		child.setId(childId);
		child.setParentID(childParentID);
		fixture.table.put(childId, child);
		
		grandChild.setRegularFile(true);
		grandChild.setId(grandChildId);
		grandChild.setParentID(grandChildParentID);
		fixture.table.put(grandChildId, grandChild);
		
		fixture.removeEntry(expected);
		
		assertEquals(0, expected.getId());
		
		assertEquals(null, fixture.table.get(expectedId));
		assertEquals(null, fixture.table.get(childId));
		assertEquals(null, fixture.table.get(grandChildId));
		
		assertTrue(fixture.table.isEmpty());
	}

	/**
	 * Test method for {@link net.sf.cotelab.app.cksummgr.db.memorydao.DirectoryEntryMemoryDAO#updateEntry(net.sf.cotelab.app.cksummgr.db.DirectoryEntry)}.
	 */
	@Test
	public void testUpdateEntry() {
		DirectoryEntry expected = fixture.newEntry();
		long expectedCksum = 12345;
		DirectoryEntry actual = null;
		
		assertNotNull(expected);
		
		expected.setChecksum(0);
		fixture.insertEntry(expected);

		expected = fixture.fetchEntryById(expected.getId());
		assertFalse(expected.isDirty());
		
		expected.setChecksum(expectedCksum);
		
		fixture.updateEntry(expected);
		
		actual = fixture.fetchEntryById(expected.getId());

		assertFalse(actual.isDirty());
		assertTrue(expected.equals(actual));
	}
}
