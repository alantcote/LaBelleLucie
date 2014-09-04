/**
 * 
 */
package net.sf.cotelab.app.cksummgr;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;

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
public class PersistentDirectoryTest extends jMockTestHelper {
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		directoryEntryDAO = context.mock(DirectoryEntryDAO.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		directoryEntryDAO = null;
	}

	@Test
	public void testAddEntry() {
		/* The addEntry(String name) method is expected to create a new
		 * DirectoryEntry object named name, with the PersistentDirectory's ID
		 * as its parent ID. It is then expected to insert the new entry into
		 * both the DAO and entries, and return the new entry.
		 */
		final DirectoryEntry parentDE =
				context.mock(DirectoryEntry.class, "parentEntry");
		PersistentDirectory fixture = new PersistentDirectory(parentDE);
		final String childName = "childEntry";
		final DirectoryEntry childDE =
				context.mock(DirectoryEntry.class, childName);
		DirectoryEntry realChildDE = null;
		
		assertEquals(parentDE, fixture.directoryEntry);
		fixture.entries = new HashMap<String, DirectoryEntry>();
		
		context.checking(new Expectations() {{
			oneOf(parentDE).insertChild(childName);
			will(returnValue(childDE));
		}});
		
		realChildDE = fixture.addEntry(childName);
		
		assertEquals(childDE, realChildDE);
		assertTrue(fixture.entries.containsKey(childName));
	}

	@Test
	public void testGetEntryByName() {
		final DirectoryEntry parentDE = context.mock(DirectoryEntry.class);
		final String childDEName = "childDE";
		final DirectoryEntry childDE =
				context.mock(DirectoryEntry.class, childDEName);
		final LinkedList<DirectoryEntry> emptyChildList =
				new LinkedList<DirectoryEntry>();
		final LinkedList<DirectoryEntry> fullChildList =
				new LinkedList<DirectoryEntry>();
		PersistentDirectory fixture = new PersistentDirectory(parentDE);
		DirectoryEntry foundDE= null;
		
		fullChildList.add(childDE);
		
		context.checking(new Expectations() {{
			oneOf(parentDE).fetchChildEntries();
			will(returnValue(emptyChildList));

			oneOf(parentDE).fetchChildEntries();
			will(returnValue(fullChildList));
			
			oneOf(childDE).getName();
			will(returnValue("childDE"));
		}});
		
		fixture.loadEntries();
		
		assertNotNull(fixture.entries);
		assertTrue(fixture.entries.isEmpty());
		
		fixture.loadEntries();
		
		assertNotNull(fixture.entries);
		assertEquals(1, fixture.entries.size());
		
		foundDE = fixture.getEntryByName(childDEName);
		
		assertEquals(childDE, foundDE);
	}

	@Test
	public void testLoadEntries() {
		final DirectoryEntry parentDE = context.mock(DirectoryEntry.class);
		final DirectoryEntry childDE =
				context.mock(DirectoryEntry.class, "childDE");
		final LinkedList<DirectoryEntry> emptyChildList =
				new LinkedList<DirectoryEntry>();
		final LinkedList<DirectoryEntry> fullChildList =
				new LinkedList<DirectoryEntry>();
		PersistentDirectory fixture = new PersistentDirectory(parentDE);
		
		fullChildList.add(childDE);
		
		context.checking(new Expectations() {{
			oneOf(parentDE).fetchChildEntries();
			will(returnValue(emptyChildList));

			oneOf(parentDE).fetchChildEntries();
			will(returnValue(fullChildList));
			
			oneOf(childDE).getName();
			will(returnValue("childDE"));
		}});
		
		fixture.loadEntries();
		
		assertNotNull(fixture.entries);
		assertTrue(fixture.entries.isEmpty());
		
		fixture.loadEntries();
		
		assertNotNull(fixture.entries);
		assertEquals(1, fixture.entries.size());
	}

	@Test
	public void testPersistentDirectory() {
		DirectoryEntry de = context.mock(DirectoryEntry.class);
		PersistentDirectory fixture = new PersistentDirectory(de);
		
		assertEquals(de, fixture.directoryEntry);
		assertNull(fixture.entries);
	}
	
	@Test
	public void testRemoveEntry() {
		final DirectoryEntry de = context.mock(DirectoryEntry.class, "de");
		final PersistentDirectory fixture = new PersistentDirectory(de);
		final LinkedList<DirectoryEntry> emptyChildList =
				new LinkedList<DirectoryEntry>();
		String childDE0Name = "childDE0";
		final String childDE1Name = "childDE1";
		String childDE2Name = "childDE2";
		DirectoryEntry childDE0 =
				context.mock(DirectoryEntry.class, childDE0Name);
		final DirectoryEntry childDE1 =
				context.mock(DirectoryEntry.class, childDE1Name);
		DirectoryEntry childDE2 =
				context.mock(DirectoryEntry.class, childDE2Name);
		
		context.checking(new Expectations() {{
			oneOf(de).fetchChildEntries();
			will(returnValue(emptyChildList));
			
			oneOf(childDE1).getName();
			will(returnValue(childDE1Name));
			
			oneOf(childDE1).remove();
		}});
		
		fixture.loadEntries();
		fixture.entries.put(childDE0Name, childDE0);
		fixture.entries.put(childDE1Name, childDE1);
		fixture.entries.put(childDE2Name, childDE2);
		
		fixture.removeEntry(childDE1);
		
		assertNotNull(fixture.getEntryByName(childDE0Name));
		assertNull(fixture.getEntryByName(childDE1Name));
		assertNotNull(fixture.getEntryByName(childDE2Name));
	}
	
	@Test
	public void testRemoveLonesomeEntries() {
		final DirectoryEntry de = context.mock(DirectoryEntry.class, "de");
		final PersistentDirectory fixture = new PersistentDirectory(de);
		final LinkedList<DirectoryEntry> emptyChildList =
				new LinkedList<DirectoryEntry>();
		String childDE0Name = "childDE0";
		final String childDE1Name = "childDE1";
		String childDE2Name = "childDE2";
		DirectoryEntry childDE0 =
				context.mock(DirectoryEntry.class, childDE0Name);
		final DirectoryEntry childDE1 =
				context.mock(DirectoryEntry.class, childDE1Name);
		DirectoryEntry childDE2 =
				context.mock(DirectoryEntry.class, childDE2Name);
		
		context.checking(new Expectations() {{
			oneOf(de).fetchChildEntries();
			will(returnValue(emptyChildList));
			
			oneOf(childDE1).getName();
			will(returnValue(childDE1Name));
			
			oneOf(childDE1).remove();
		}});
		
		fixture.loadEntries();
		fixture.entries.put(childDE0Name, childDE0);
		fixture.entries.put(childDE1Name, childDE1);
		fixture.entries.put(childDE2Name, childDE2);
		
		fixture.visitedEntries.add(childDE0);
		fixture.visitedEntries.add(childDE2);
		
		fixture.removeLonesomeEntries();
		
		assertNotNull(fixture.getEntryByName(childDE0Name));
		assertNull(fixture.getEntryByName(childDE1Name));
		assertNotNull(fixture.getEntryByName(childDE2Name));
	}
	
	@Test
	public void testSetVisited() {
		final DirectoryEntry parentDE =
				context.mock(DirectoryEntry.class, "parent");
		final DirectoryEntry childDE =
				context.mock(DirectoryEntry.class, "child");
		PersistentDirectory fixture = new PersistentDirectory(parentDE);
		
		fixture.setVisited(childDE);
		
		assertTrue(fixture.visitedEntries.contains(childDE));
	}
}
