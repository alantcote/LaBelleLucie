/* $Id: HashBidiMultiMapTest.java,v 1.1 2007/10/09 19:49:36 acote Exp $
 *
 * $Log: HashBidiMultiMapTest.java,v $
 * Revision 1.1  2007/10/09 19:49:36  acote
 * Part of the GUT (Grand Unified Theory) of IDE support, supporting both Eclipse and NetBeans.
 *
 * Revision 1.2  2007/10/06 19:24:52  acote
 * Suppressed seemingly-unavoidable type-safety issues.
 *
 * Revision 1.1  2007/10/06 19:09:27  acote
 * Added support for bidirectional multi-maps.
 *
 */

package net.sf.cotelab.util.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

/**
 * A test case for (@link net.cote.collections.HashBidiMultiMap).
 * @author alancote@rochester.rr.com
 */
public class HashBidiMultiMapTest extends TestCase {
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
	protected HashBidiMultiMap<Integer, Integer> fixture = null;
	
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
	public HashBidiMultiMapTest(String name) {
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
        
        fixture = new HashBidiMultiMap<Integer, Integer>();
        
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
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#containsValue(java.lang.Object)}.
	 */
	public void testContainsValue() {
		assertFalse(fixture.containsValue(new Integer(OBJECT_POOL_SIZE)));
		assertTrue(
				fixture.containsValue((Integer) fixture.values().toArray()[0]));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#equals(java.lang.Object)}.
	 */
	public void testEqualsObject() {
		Object fixtureCopy =
			new HashBidiMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixture.equals(fixtureCopy));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#put(java.lang.Object, java.lang.Object)}.
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
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#remove(java.lang.Object, java.lang.Object)}.
	 */
	public void testRemove() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		
		assertTrue(fixture.put(newKey, newValue));
		assertTrue(fixture.remove(newKey, newValue));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#removeKey(java.lang.Object)}.
	 */
	public void testRemoveKey() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		
		assertTrue(fixture.put(newKey, newValue));
		assertEquals(1, fixture.removeKey(newKey).size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#values()}.
	 */
	public void testValues() {
		assertNotNull(fixture.values());
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#HashBidiMultiMap()}.
	 */
	public void testHashBidiMultiMap() {
		assertNotNull(new HashBidiMultiMap<Integer, Integer>());
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#HashBidiMultiMap(net.cote.collections.BidiMultiMap)}.
	 */
	public void testHashBidiMultiMapBidiMultiMapOfQextendsKQextendsV() {
		Object fixtureCopy =
			new HashBidiMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixture.equals(fixtureCopy));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#HashBidiMultiMap(java.util.Map)}.
	 */
	public void testHashBidiMultiMapMapOfQextendsKQextendsV() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMultiMap<Integer, Integer> hmm = null;
		
		map.put(OBJECT_POOL_SIZE, RAND_MAP_SIZE);
		hmm = new HashBidiMultiMap<Integer, Integer>(map);
		
		assertNotNull(hmm);
		assertEquals(1, hmm.size());
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#HashBidiMultiMap(net.cote.collections.MultiMap)}.
	 */
	public void testHashBidiMultiMapMultiMapOfQextendsKQextendsV() {
		Object fixtureCopy =
			new HashMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixtureCopy.equals(fixture));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#getKeys(java.lang.Object)}.
	 */
	public void testGetKeys() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		Collection<Integer> keyColl = null;
		Object sampleKey = null;
		
		fixture.put(newKey, newValue);
		keyColl = fixture.getKeys(newValue);
		assertNotNull(keyColl);
		assertEquals(1, keyColl.size());
		sampleKey = keyColl.toArray()[0];
		assertTrue(newKey.equals(sampleKey));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#inverseBidiMultiMap()}.
	 */
	public void testInverseBidiMultiMap() {
		BidiMultiMap<Integer, Integer> backward = null;
		BidiMultiMap<Integer, Integer> forward = null;
		
		assertNotNull(backward = fixture.inverseBidiMultiMap());
		assertNotNull(forward = backward.inverseBidiMultiMap());
		
		assertEquals(fixture, forward);
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#putAll(net.cote.collections.BidiMultiMap)}.
	 */
	public void testPutAllBidiMultiMapOfQextendsKQextendsV() {
		Object fixtureCopy =
			new HashBidiMultiMap<Integer, Integer>(fixture);
		
		assertFalse(fixture.equals(randomObject()));
		assertTrue(fixture.equals(fixtureCopy));
	}

	/**
	 * Test method for {@link net.cote.collections.HashBidiMultiMap#removeValue(java.lang.Object)}.
	 */
	public void testRemoveValue() {
		Integer newKey = new Integer(OBJECT_POOL_SIZE);
		Integer newValue = new Integer(RAND_MAP_SIZE);
		
		assertTrue(fixture.put(newKey, newValue));
		assertEquals(1, fixture.removeValue(newValue).size());
	}

}
