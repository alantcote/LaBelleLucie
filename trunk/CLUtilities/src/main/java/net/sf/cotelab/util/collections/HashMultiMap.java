/* $Id: HashMultiMap.java,v 1.10 2007/12/02 23:46:59 acote Exp $
 *
 * $Log: HashMultiMap.java,v $
 * Revision 1.10  2007/12/02 23:46:59  acote
 * Snap!
 *
 * Revision 1.9  2007/10/08 14:55:08  acote
 * Updates to enhance (hopefully) the quality of the API documentation produced for this project.
 *
 * Revision 1.8  2007/10/08 10:57:33  acote
 * Removed the '@override' annotations.
 *
 * Revision 1.7  2007/10/06 19:22:22  acote
 * Suppressed warnings about unavoidable type-safety issues.
 *
 * Revision 1.6  2007/10/06 16:58:12  acote
 * Nothing evolves quite like software.
 *
 * Revision 1.5  2007/10/06 15:00:25  acote
 * Migrated Entry nested type into MapEntry independent type.
 *
 * Revision 1.4  2007/10/06 02:41:34  acote
 * Eliminated compiler warnings.
 *
 * Revision 1.3  2007/10/06 01:43:39  acote
 * Evolved.
 *
 * Revision 1.2  2007/10/05 23:26:05  acote
 * A HashMap/HashSet-based implementation of MultiMap.
 *
 * Revision 1.1  2007/10/05 17:16:56  acote
 * A new approach to getting clean collections.
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
 * An implementation of the {@link MultiMap} interface, using hashed indexes.
 * @param <K> the type of key to be supported.
 * @param <V> the type of value to be supported.
 * @author alancote@rochester.rr.com
 */
public class HashMultiMap<K, V> implements MultiMap<K, V>, Serializable {

    /**
     * Serialization support.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The backing {@link java.util.Set}.
     */
    protected Set<MapEntry<K, V>> entrySet = new HashSet<MapEntry<K, V>>();
    /**
     * The backing {@link java.util.Map}.
     */
    protected Map<K, Set<MapEntry<K, V>>> mapKey2EntrySet = new HashMap<K, Set<MapEntry<K, V>>>();

    /**
     * Construct a new object that contains no mappings.
     */
    public HashMultiMap() {
        super();
    }

    /**
     * Construct a new object that contains the same mappings as the given
     * object.
     * @param map the given object.
     */
    public HashMultiMap(Map<? extends K, ? extends V> map) {
        super();

        putAll(map);
    }

    /**
     * Construct a new object that contains the same mappings as the given
     * object.
     * @param multimap the given object.
     */
    public HashMultiMap(MultiMap<? extends K, ? extends V> multimap) {
        super();

        putAll(multimap);
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#clear()
     */
    @Override
    public void clear() {
        mapKey2EntrySet.clear();
        entrySet.clear();
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#containsKey(java.lang.Object)
     */
    @Override
    public boolean containsKey(Object key) {
        return mapKey2EntrySet.containsKey(key);
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#containsValue(java.lang.Object)
     */
    @Override
    public boolean containsValue(Object value) {
        for (MapEntry<K, V> entry : entrySet) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#entrySet()
     */
    @Override
    public Set<MapEntry<K, V>> entrySet() {
        return new HashSet<MapEntry<K, V>>(entrySet);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings(value = "unchecked")
    @Override
    public boolean equals(Object obj) {
        MultiMap<K, V> other;
        Set<MapEntry<K, V>> otherEntrySet;

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof MultiMap<?, ?>)) {
            return false;
        }

        other = (MultiMap<K, V>) obj;

        if (size() != other.size()) {
            return false;
        }

        otherEntrySet = other.entrySet();

        return entrySet.containsAll(otherEntrySet)
        		&& otherEntrySet.containsAll(entrySet);
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#get(java.lang.Object)
     */
    @Override
    public Collection<V> get(Object key) {
        Set<MapEntry<K, V>> matchedEntries = mapKey2EntrySet.get(key);
        Set<V> retVal = null;

        if (matchedEntries != null) {
            retVal = new HashSet<V>();

            for (MapEntry<K, V> entry : matchedEntries) {
                retVal.add(entry.getValue());
            }
        }

        return retVal;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int retVal = 0;

        for (MapEntry<K, V> entry : entrySet) {
            retVal += entry.hashCode();
        }

        return retVal;
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return entrySet.isEmpty();
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#keySet()
     */
    @Override
    public Set<K> keySet() {
        return mapKey2EntrySet.keySet();
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean put(K key, V value) {
        MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
        Set<MapEntry<K, V>> matchSet = mapKey2EntrySet.get(key);

        if (entrySet.contains(entry)) {
            return false;
        }

        entrySet.add(entry);
        if (matchSet == null) {
            matchSet = new HashSet<MapEntry<K, V>>();
            matchSet.add(entry);
            mapKey2EntrySet.put(key, matchSet);
        } else {
            matchSet.add(entry);
        }

        return true;
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#putAll(java.lang.Object, java.util.Collection)
     */
    @Override
    public boolean putAll(K key, Collection<? extends V> values) {
        boolean retVal = false;

        for (V value : values) {
            if (put(key, value)) {
                retVal = true;
            }
        }

        return retVal;
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#putAll(java.util.Map)
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#putAll(net.cote.collections.MultiMap)
     */
    @Override
    public void putAll(MultiMap<? extends K, ? extends V> multimap) {
        for (MapEntry<? extends K, ? extends V> entry : multimap.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#remove(java.lang.Object, java.lang.Object)
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
        return true;
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#removeKey(java.lang.Object)
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
            entrySet.remove(entry);
        }

        mapKey2EntrySet.remove(key);

        return retVal;
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#size()
     */
    @Override
    public int size() {
        return entrySet().size();
    }

    /* (non-Javadoc)
     * @see net.cote.collections.MultiMap#values()
     */
    @Override
    public Collection<V> values() {
        Set<V> retVal = new HashSet<V>();

        for (MapEntry<K, V> entry : entrySet) {
            retVal.add(entry.getValue());
        }

        return retVal;
    }
}