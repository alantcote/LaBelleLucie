/* $Id: MultiMap.java,v 1.7 2007/12/02 23:46:59 acote Exp $
 *
 * $Log: MultiMap.java,v $
 * Revision 1.7  2007/12/02 23:46:59  acote
 * Snap!
 *
 * Revision 1.6  2007/10/08 14:55:08  acote
 * Updates to enhance (hopefully) the quality of the API documentation produced for this project.
 *
 * Revision 1.5  2007/10/08 11:39:54  acote
 * Cleaning up javadoc comments.
 *
 * Revision 1.4  2007/10/06 16:58:12  acote
 * Nothing evolves quite like software.
 *
 * Revision 1.3  2007/10/06 15:00:25  acote
 * Migrated Entry nested type into MapEntry independent type.
 *
 * Revision 1.2  2007/10/06 14:23:19  acote
 * Improved javadoc comments a bit.
 *
 * Revision 1.1  2007/10/05 17:16:56  acote
 * A new approach to getting clean collections.
 *
 */

package net.sf.cotelab.util.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A map which allows multiple values per key.  This interface is similar to
 * {@link Map}, but while the entries in an ordinary map have unique
 * keys, the entries in a {@link MultiMap} are unique by key-value pair.
 * <p>
 * The behavior of a {@link MultiMap} is undefined if its keys or values are
 * mutable.
 * <p>
 * All general-purpose {@link MultiMap} implementation classes should provide
 * three "standard" constructors:
 * <dl>
 *   <dt>A void (no arguments) constructor</dt>
 *     <dd>Create an empty {@link MultiMap}</dd>
 *   <dt>A constructor with a single argument of type {@link java.util.Map}</dt>
 *     <dd>Create a new {@link MultiMap} with the same key-value mappings
 *         as its argument</dd>
 *   <dt>A constructor with a single argument of type {@link MultiMap}</dt>
 *     <dd>Create a new ({@link MultiMap} with the same key-value mappings
 *         as its argument</dd>
 * </dl>
 * <p>
 * As with the standard Java collections classes, instances of {@link MultiMap}
 * are unsynchronized.
 * @param K the type of key to be supported.
 * @param V the type of value to be supported.
 * @author alancote@rochester.rr.com
 */
public interface MultiMap<K, V> {

    /**
     * Remove all of the mappings from this object.
     */
    public void clear();

    /**
     * Returns <tt>true</tt> if this object contains at least one mapping for
     * the given key.
     * @param key the given key.
     * @return <tt>true</tt> if this object contains at least one mapping for
     *         the given key.
     */
    public boolean containsKey(K key);

    /**
     * Returns <tt>true</tt> if this object maps at least one key to the
     * specified value.  This operation probably will require time directly
     * proportional to the value obtained from {@link MultiMap#size()}.
     * @param value the specified value.
     * @return <tt>true</tt> if this object maps at least one key to the
     *         specified value.
     */
    public boolean containsValue(V value);

    /**
     * Get a {@link java.util.Set} view of the mappings contained in this
     * object.  This view is a snapshot, so it is not affected by subsequent
     * changes to this object.
     * @return a {@link java.util.Set} view of the mappings contained in this
     *         object.
     */
    public Set<MapEntry<K, V>> entrySet();

    /**
     * Compare the specified object with this object for equality.
     * Returns <tt>true</tt> if the specified object is also a {@link MultiMap}
     * and the two objects contain the same mappings.
     * @param o the specified object.
     * @return <tt>true</tt> if the specified object is equal to this object.
     */
    public boolean equals(Object o);

    /**
     * Returns the collection of values to which this object maps a given key,
     * or <tt>null</tt> if this object contains no mapping for the key.
     * @param key the given key.
     * @return the collection of values to which this object maps <tt>key</tt>,
     *         or <tt>null</tt> if this object contains no mapping for
     *         <tt>key</tt>.
     */
    public Collection<V> get(K key);

    /**
     * Get the hash code value for this object.  The hash code of a {@link
     * MultiMap} is defined to be the sum of the hash codes of the entries in
     * the {@link MultiMap#entrySet} view.
     * @return the hash code value for this object.
     */
    public int hashCode();

    /**
     * Returns <tt>true</tt> if this object contains no key-value mappings.
     * @return <tt>true</tt> if this object contains no key-value mappings.
     */
    public boolean isEmpty();

    /**
     * Get a {@link java.util.Set} view of the keys contained in this object.
     * This view is a snapshot, so it is not affected by subsequent changes to
     * this object.
     * @return a {@link java.util.Set} view of the keys contained in this
     *         object.
     */
    public Set<K> keySet();

    /**
     * Add a mapping from a key to a value.  This does not replace other
     * existing mappings from the same key.  However, if the key/value pair is
     * already in this object, it will not be added again.
     * @param key the key portion of the mapping.
     * @param value the value portion of the mapping.
     * @return <tt>true</tt> if the new key/value pair was not already in this
     *         object.
     */
    public boolean put(K key, V value);

    /**
     * Add mappings from a key to several different values.  These values are in
     * addition to any existing mappings for the given key.
     * @param key the key portion of the mappings.
     * @param values the value portions of the mappings.
     * @return <tt>true</tt> if any of the new key-value pairs were not already
     *         in this object.
     */
    public boolean putAll(K key, Collection<? extends V> values);

    /**
     * Copy all of the mappings from the specified {@link java.util.Map} to this
     * object.
     * @param map the specified {@link java.util.Map}.
     */
    public void putAll(Map<? extends K, ? extends V> map);

    /**
     * Copy all of the mappings from the specified {@link MultiMap} to this
     * object.
     * @param multimap the specified {@link MultiMap}.
     */
    public void putAll(MultiMap<? extends K, ? extends V> multimap);

    /**
     * Remove a key-value mapping from this object.
     * @param key the key portion of the mapping.
     * @param value the value portion of the mapping.
     * @return true if the key-value mapping was in this object.
     */
    public boolean remove(K key, V value);

    /**
     * Remove all key/value mappings for a given key.
     * @param key the given key.
     * @return The set of values which were associated with <tt>key</tt>.
     */
    public Collection<V> removeKey(K key);

    /**
     * Get the number of key/value mappings in this object.
     * @return the number of key/value mappings in this object.
     */
    public int size();

    /**
     * Get a {@link java.util.Collection} view of the values contained in this
     * object.  This view is a snapshot, so it is not affected by subsequent
     * changes to this object.
     * @return a {@link java.util.Collection} view of the values contained in
     *         this object.
     */
    public Collection<V> values();
}