/* $Id: HashBidiMultiMap.java,v 1.4 2007/10/08 14:55:08 acote Exp $
 *
 * $Log: HashBidiMultiMap.java,v $
 * Revision 1.4  2007/10/08 14:55:08  acote
 * Updates to enhance (hopefully) the quality of the API documentation produced for this project.
 *
 * Revision 1.3  2007/10/08 11:05:50  acote
 * Removed the '@override' annotations.
 *
 * Revision 1.2  2007/10/06 19:22:22  acote
 * Suppressed warnings about unavoidable type-safety issues.
 *
 * Revision 1.1  2007/10/06 19:09:27  acote
 * Added support for bidirectional multi-maps.
 *
 */

package net.sf.cotelab.util.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of the {@link BidiMultiMap} interface, using hashed
 * indexes.
 * @param <K> the type of key to be supported.
 * @param <V> the type of value to be supported.
 * @author alancote@rochester.rr.com
 */
public class HashBidiMultiMap<K, V>
		extends HashMultiMap<K, V>
		implements BidiMultiMap<K, V>, Serializable {
	/**
	 * Serialization support.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The backing {@link java.util.Map} from values to entries.
	 */
	protected Map<V, Set<MapEntry<K, V>>> mapValue2EntrySet =
		new HashMap<V, Set<MapEntry<K, V>>>();

	/**
	 * Construct a new object that contains no mappings.
	 */
	public HashBidiMultiMap() {
		super();
	}

	/**
	 * Construct a new object that contains the same mappings as the given
	 * object.
	 * @param bidimultimap the given object.
	 */
	public HashBidiMultiMap(
			BidiMultiMap<? extends K, ? extends V> bidimultimap) {
		super();
		
		putAll(bidimultimap);
	}

	/**
	 * Construct a new object that contains the same mappings as the given
	 * object.
	 * @param map the given object.
	 */
	public HashBidiMultiMap(Map<? extends K, ? extends V> map) {
		super();
		
		putAll(map);
	}

	/**
	 * Construct a new object that contains the same mappings as the given
	 * object.
	 * @param multimap the given object.
	 */
	public HashBidiMultiMap(MultiMap<? extends K, ? extends V> multimap) {
		super();
		
		putAll(multimap);
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.HashMultiMap#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return mapValue2EntrySet.containsKey(value);
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.HashMultiMap#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return ((obj == null) || (obj instanceof BidiMultiMap)) &&
		       super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.BidiMultiMap#getKeys(java.lang.Object)
	 */
	public Collection<K> getKeys(Object value) {
		Set<MapEntry<K, V>> matchedEntries = mapValue2EntrySet.get(value);
		Set<K> retVal = null;
		
		if (matchedEntries != null) {
			retVal = new HashSet<K>();

			for (MapEntry<K, V> entry : matchedEntries) {
				retVal.add(entry.getKey());
			}
		}
		
		return retVal;
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.BidiMultiMap#inverseBidiMultiMap()
	 */
	public BidiMultiMap<V, K> inverseBidiMultiMap() {
		BidiMultiMap<V, K> retVal = new HashBidiMultiMap<V, K>();
		
		for (MapEntry<K, V> entry : entrySet) {
			retVal.put(entry.getValue(), entry.getKey());
		}
		
		return retVal;
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.HashMultiMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean put(K key, V value) {
		MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
		Set<MapEntry<K, V>> keyMatchSet = mapKey2EntrySet.get(key);
		Set<MapEntry<K, V>> valueMatchSet = mapValue2EntrySet.get(value);
		
		if (entrySet.contains(entry)) {
			return false;
		}
		
		entrySet.add(entry);
		
		if (keyMatchSet == null) {
			keyMatchSet = new HashSet<MapEntry<K, V>>();
			keyMatchSet.add(entry);
			mapKey2EntrySet.put(key, keyMatchSet);
		} else {
			keyMatchSet.add(entry);
		}
		
		if (valueMatchSet == null) {
			valueMatchSet = new HashSet<MapEntry<K, V>>();
			valueMatchSet.add(entry);
			mapValue2EntrySet.put(value, keyMatchSet);
		} else {
			valueMatchSet.add(entry);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.BidiMultiMap#putAll(net.cote.collections.BidiMultiMap)
	 */
	public void putAll(BidiMultiMap<? extends K, ? extends V> bidimultimap) {
		for (MapEntry<? extends K, ? extends V> entry
				: bidimultimap.entrySet()) {
			put((K) entry.getKey(), (V) entry.getValue());
		}
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.HashMultiMap#remove(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean remove(K key, V value) {
		MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
		Set<MapEntry<K, V>> matchSet = null;
		
		if (!entrySet.contains(entry)) {
			return false;
		}
		
		entrySet.remove(entry);
		
		matchSet = mapKey2EntrySet.get(key);
		matchSet.remove(entry);
		if (matchSet.isEmpty()) {
			mapKey2EntrySet.remove(key);
		}
		
		matchSet = mapValue2EntrySet.get(value);
		matchSet.remove(entry);
		if (matchSet.isEmpty()) {
			mapValue2EntrySet.remove(value);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.HashMultiMap#removeKey(java.lang.Object)
	 */
	@Override
	public Collection<V> removeKey(Object key) {
		Set<MapEntry<K, V>> matchSet = mapKey2EntrySet.get(key);
		Set<V> retVal = new HashSet<V>();
		
		if ((matchSet == null) || matchSet.isEmpty()) {
			return null;
		}
		
		for (MapEntry<K, V> entry : matchSet) {
			retVal.add(entry.getValue());
			remove(entry.getKey(), entry.getValue());
		}
		
		return retVal;
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.BidiMultiMap#removeValue(java.lang.Object)
	 */
	public Collection<K> removeValue(V value) {
		Set<MapEntry<K, V>> matchSet = mapValue2EntrySet.get(value);
		Set<K> retVal = new HashSet<K>();
		
		if ((matchSet == null) || matchSet.isEmpty()) {
			return null;
		}
		
		for (MapEntry<K, V> entry : matchSet) {
			retVal.add(entry.getKey());
			remove(entry.getKey(), entry.getValue());
		}
		
		return retVal;
	}

	/* (non-Javadoc)
	 * @see net.cote.collections.HashMultiMap#values()
	 */
	@Override
	public Collection<V> values() {
		return mapValue2EntrySet.keySet();
	}
}
