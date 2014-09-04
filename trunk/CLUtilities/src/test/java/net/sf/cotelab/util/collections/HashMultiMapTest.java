/* $Id: HashMultiMapTest.java,v 1.1 2007/10/09 19:49:36 acote Exp $
 *
 * $Log: HashMultiMapTest.java,v $
 * Revision 1.1  2007/10/09 19:49:36  acote
 * Part of the GUT (Grand Unified Theory) of IDE support, supporting both Eclipse and NetBeans.
 *
 * Revision 1.2  2007/10/06 02:39:32  acote
 * Organized imports.
 *
 * Revision 1.1  2007/10/06 01:44:42  acote
 * A unit test for HashMultiMap objects.
 *
 */
package net.sf.cotelab.util.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import junit.framework.TestCase;

/**
 * A test case for (@link net.cote.collections.HashMultiMap).
 * @author alancote@rochester.rr.com
 */
public class HashMultiMapTest extends TestCase {
	/**
	 * The number of values in (@link objectPool).
	 */
	protected static final int OBJECT_POOL_SIZE = 20;

	/**
	 * The number of entries in the fixture.
	 */
	protected static final int RAND_MAP_SIZE = 40;
	
	/**
	 * The test fixture.
	 */
	protected HashMultiMap<Integer, Integer> fixture = null;
	
	/**
	 * Keys and/or values in the fixture.
	 */
	protected Integer[] objectPool;
	
	/**
	 * The random number generator.
	 */
	protected Random rand;
    
	/**
	 * Construct a new object.
	 * @param name the name of the test.
	 */
	public HashMultiMapTest(String name) {
		super(name);
	}

	/**
	 * Choose a random object from (@link objectPool).
	 * @return a random object from (@link objectPool).
	 */
	protected Integer randomObject() {
		return objectPool[rand.nextInt(objectPool.length)];
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
        rand = new Random(271828); // for reproducibility!
        objectPool = new Integer[OBJECT_POOL_SIZE];
        
        for(int n = 0; n < objectPool.length; n++) {
            objectPool[n] = new Integer(n);
        }
        
        fixture = new HashMultiMap<Integer, Integer>();
        
        for(int n = 0; n < RAND_MAP_SIZE; n++) {
        	fixture.put(randomObject(), randomObject());
        }
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
        rand = null;
        objectPool = null;
        fixture = null;
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#clear()}.
	 */
	public void testClear() {
		assertFalse(fixture.isEmpty());
		fixture.clear();
		assertTrue(fixture.isEmpty());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#containsKey(java.lang.Object)}.
	 */
	public void testContainsKey() {
		assertFalse(fixture.containsKey(new Integer(OBJECT_POOL_SIZE)));
		assertTrue(
				fixture.containsKey((Integer) fixture.keySet().toArray()[0]));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#containsValue(java.lang.Object)}.
	 */
	public void testContainsValue() {
		assertFalse(fixture.containsValue(new Integer(OBJECT_POOL_SIZE)));
		assertTrue(
				fixture.containsValue((Integer) fixture.values().toArray()[0]));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#entrySet()}.
	 */
	public void testEntrySet() {
		assertEquals(fixture.size(), fixture.entrySet().size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#equals(java.lang.Object)}.
	 * @throws CloneNotSupportedException in annoying circumstances.
	 */
	public void testEqualsObject() throws CloneNotSupportedException {
		Object fixtureCopy =
			new HashMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixture.equals(fixtureCopy));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#get(java.lang.Object)}.
	 */
	public void testGet() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		Collection<Integer> valueColl = null;
		Object sampleValue = null;
		
		fixture.put(newKey, newValue);
		valueColl = fixture.get(newKey);
		assertNotNull(valueColl);
		assertEquals(1, valueColl.size());
		sampleValue = valueColl.toArray()[0];
		assertTrue(newValue.equals(sampleValue));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#hashCode()}.
	 */
	public void testHashCode() {
		assertFalse(0 == fixture.hashCode());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#HashMultiMap()}.
	 */
	public void testHashMultiMap() {
		assertNotNull(new HashMultiMap<Integer, Integer>());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#HashMultiMap(java.util.Map)}.
	 */
	public void testHashMultiMapMapOfQextendsKQextendsV() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMultiMap<Integer, Integer> hmm = null;
		
		map.put(OBJECT_POOL_SIZE, RAND_MAP_SIZE);
		hmm = new HashMultiMap<Integer, Integer>(map);
		
		assertNotNull(hmm);
		assertEquals(1, hmm.size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#HashMultiMap(net.cote.collections.MultiMap)}.
	 */
	public void testHashMultiMapMultiMapOfQextendsKQextendsV() {
		Object fixtureCopy =
			new HashMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixture.equals(fixtureCopy));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#isEmpty()}.
	 */
	public void testIsEmpty() {
		assertFalse(fixture.isEmpty());
		fixture.clear();
		assertTrue(fixture.isEmpty());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#keySet()}.
	 */
	public void testKeySet() {
		assertNotNull(fixture.keySet());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#put(java.lang.Object, java.lang.Object)}.
	 */
	public void testPut() {
		int targetSize = fixture.size();
		
        for(int n = 0; n < RAND_MAP_SIZE; n++) {
        	if (fixture.put(randomObject(), randomObject())) {
        		++targetSize;
        	}
        }
        
        assertEquals(targetSize, fixture.size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#putAll(java.lang.Object, java.util.Collection)}.
	 */
	public void testPutAllKCollectionOfQextendsV() {
		Vector<Integer> coll = new Vector<Integer>();
		int sizeBefore = fixture.size();
		
		for (int i = 0; i < OBJECT_POOL_SIZE; ++i) {
			coll.add(objectPool[i]);
		}
		
		fixture.putAll(new Integer(RAND_MAP_SIZE), coll);
		
		assertEquals(sizeBefore + OBJECT_POOL_SIZE, fixture.size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#putAll(java.util.Map)}.
	 */
	public void testPutAllMapOfQextendsKQextendsV() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMultiMap<Integer, Integer> hmm = null;
		
		map.put(OBJECT_POOL_SIZE, RAND_MAP_SIZE);
		hmm = new HashMultiMap<Integer, Integer>();
		
		assertNotNull(hmm);
		hmm.putAll(map);
		assertEquals(1, hmm.size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#putAll(net.cote.collections.MultiMap)}.
	 */
	public void testPutAllMultiMapOfQextendsKQextendsV() {
		Object fixtureCopy =
			new HashMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixture.equals(fixtureCopy));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#remove(java.lang.Object, java.lang.Object)}.
	 */
	public void testRemove() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		
		assertTrue(fixture.put(newKey, newValue));
		assertTrue(fixture.remove(newKey, newValue));
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#removeKey(java.lang.Object)}.
	 */
	public void testRemoveKey() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		
		assertTrue(fixture.put(newKey, newValue));
		assertEquals(1, fixture.removeKey(newKey).size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#size()}.
	 */
	public void testSize() {
		assertEquals(fixture.size(), fixture.entrySet().size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashMultiMap#values()}.
	 */
	public void testValues() {
		assertNotNull(fixture.values());
	}
}
